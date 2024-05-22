package main.Swapping;

import main.Output.DebugBuilder;
import main.Program.GUIBools;
import main.Structure.ACharacter;
import main.Structure.AClasses;
import main.Structure.BinFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class StatSwapper {

    public StatSwapper(){

    }

    public void SwapStats(StringBuilder staticBin, ArrayList<ACharacter> charList, ArrayList<AClasses> classList, GUIBools bools){
        //Stat State: 1 = growthRedistribution, 2 = baseRedistribution, 3 = growthScale, 4 = shuffle, 5 = random
        //if the stat swapping will use any sort of growth related data, we create a hash list that will be easy to traverse
        HashMap<String, AClasses> classMap = new HashMap<>();
        if (bools.statState == 1 | bools.statState == 3) {
            for (AClasses aClasses : classList) {
                classMap.put(aClasses.getName(), aClasses);
            }
        }
        //i guess i have to make an int level hasmpa fuuuuu
        HashMap<String, Integer> intLvlMap = new HashMap<String, Integer>();
        //hashmap of all stats so that we they won't change once we reference them
        HashMap<String, int[]> statMap = new HashMap<>();
        for (ACharacter character : charList){
            statMap.put(character.getName(), character.getBaseStats());
            if (bools.statState == 3){
                intLvlMap.put(character.getName(), character.getInternalLevel());
            }
        }
        //loop through each of the characters
        for (ACharacter aCharacter : charList){
            //creates array for the stats
            int[] statArray = new int[8];
            //boolean aptitudeBoost = Arrays.stream(aCharacter.getSkills()).anyMatch("Aptitude"::equals);
            //if statements to determine how the new stats are calculated. This could be more optimal but man fuck it
            if (bools.statState == 3) {
                statArray = aCharacter.getBaseStats();
                //priam nerf cus he's too damn strong
                if (aCharacter.getName().equals("Priam")){
                    statArray[7] -= 12;
                    statArray[6] -= 16;
                }
                statArray = GrowthScale(statArray, aCharacter.getInternalLevel(), intLvlMap.get(aCharacter.getActual()), aCharacter.getGrowths(), classMap.get(aCharacter.getClasses()[0]).getClassGrowths());
            }
            else if (bools.statState == 1) statArray = GrowthRedistribution(statMap.get(aCharacter.getActual()), aCharacter.getGrowths(), classMap.get(aCharacter.getClasses()[0]).getClassGrowths());
            else if (bools.statState == 2) statArray = BaseRedistribution(aCharacter.getBaseStats(), statMap.get(aCharacter.getActual()));
            else if (bools.statState == 4) statArray = ShuffleStats(statMap.get(aCharacter.getActual()));
            else if (bools.statState == 5) statArray = RandomizeStats(statMap.get(aCharacter.getActual()));
            else if (bools.xml & !bools.reverseRecruitment) statArray = aCharacter.getBaseStats();
            else statArray = statMap.get(aCharacter.getActual());
            //loops through stat array to turn ints into a long hex string
            StringBuilder statString = new StringBuilder();
            for (int i = 0; i < 8; i++){
                String statByte = String.format("%02x", statArray[i]);
                statString.append(statByte);
            }
            //finds the character's index through the use of the FID
            //puts fid in correct pointer form
            String fidPointer = aCharacter.getFid().substring(2, 4) + aCharacter.getFid().substring(0,2);
            //finds location of fid, the shifts forward by 16 bytes (32 letters) that's where the skills are
            int index = staticBin.indexOf("0000" + fidPointer.toLowerCase() + "0000") + 32 + 4;
            //stringBuilder is done building so we add to static. It's 8 bytes long (16 letters)
            staticBin.replace(index, index + 16, statString.toString());
            //sets the new stats to character in order to print them out
            aCharacter.setStats(statArray);
            //Debug to make sure everrything is working okay
            DebugBuilder.DebugOutput(aCharacter.getName() + " has the following base stats");
            DebugBuilder.DebugOutput(Arrays.toString(aCharacter.getBaseStats()));
        }
        //function is over so set gamedata
        BinFiles.SetStatic(staticBin);
    }

    private int[] GrowthScale(int[] replaceStats, int replaceLvl, int origLvl, int[] charGrowths, int[] classGrowths){
        //subtract Priam's def by 16 and res by 12

        int[] newStatTotal = new int[8];
        int levelDifference = origLvl - replaceLvl;
        if (levelDifference != 0){
            for (int n = 0; n < 8; n++){
                float newStat = replaceStats[n] + (float) (levelDifference * (charGrowths[n] + classGrowths[n])) / 100;
                newStatTotal[n] = Math.max(Math.round(newStat), 0);
            }
        }
        else newStatTotal = replaceStats;
        return newStatTotal;
    }

    private int[] GrowthRedistribution(int[] otherStats, int[] charGrowths, int[] classGrowths){
        int[] newStatTotal = new int[8];
        int oldStatTotal = 0;
        int[] combinedGrowths = new int[8];
        int growthsTotal = 0;
        for (int n = 0; n < 8; n++){
            oldStatTotal += otherStats[n];
            combinedGrowths[n] = charGrowths[n] + classGrowths[n];
            growthsTotal += combinedGrowths[n];
        }
        for (int m = 0; m < 8; m++){
            float growthPercent = (float) combinedGrowths[m] / (float) growthsTotal;
            newStatTotal[m] = Math.round(oldStatTotal * growthPercent);
        }
        return newStatTotal;
    }
    private int[] BaseRedistribution(int[] oldStats, int[] otherStats){
        int[] newStatTotal = new int[8];
        int oldStatTotal = 0;
        int otherStatTotal = 0;
        //first finds the stat totals of the old and new stats
        for (int n = 0; n < 8; n++){
            oldStatTotal += oldStats[n];
            otherStatTotal += otherStats[n];
        }
        float totalDifference = (float) (otherStatTotal - oldStatTotal) / 10;
        int extraStats = Math.max(0, Math.round(totalDifference));
        oldStatTotal += 8 * extraStats;
        //now we do the calculations to determine the new stats
        for (int m = 0; m < 8; m++){
            float statPerc = (float) (oldStats[m] + extraStats) / (float) oldStatTotal;
            newStatTotal[m] = Math.round(otherStatTotal * statPerc);
        }
        return newStatTotal;
    }

    private int[] ShuffleStats(int[] oldStats){
        int[] newStats = new int[8];
        ArrayList<Integer> statArray = new ArrayList<Integer>();
        //loops once to fill array
        for (int i = 0; i < 8; i++){
            statArray.add(oldStats[i]);
        }
        //loops another time to dump all of the stats in the array
        Random rng = new Random();
        for (int j = 0; j < 8; j++){
            int index = statArray.size() == 1 ? 0 : rng.nextInt(0, statArray.size());
            newStats[j] = statArray.get(index);
            if (statArray.size() > 1 ) statArray.remove(index);
        }
        return newStats;
    }

    private int[] RandomizeStats(int[] oldStats){
        int[] newStats = new int[8];
        int oldStatTotal = 0;
        //sets baseline of 0 for all stats, and also finds base stat total
        for (int i = 0; i < 8; i++){
            newStats[i] = 0;
            oldStatTotal += oldStats[i];
        }
        //randomly assigns points to random stats until we run out of the old stat total
        Random rng = new Random();
        while (oldStatTotal > 0){
            newStats[rng.nextInt(0, 8)] += 1;
            oldStatTotal--;
        }
        return newStats;
    }
}
