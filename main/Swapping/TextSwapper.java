package main.Swapping;

import main.Output.DebugBuilder;
import main.Structure.ACharacter;
import main.Structure.BinFiles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class TextSwapper {
    public TextSwapper(){

    }

    StringBuilder editedText;
    StringBuilder blankText;
    StringBuilder blankName;
    StringBuilder editedName;
    public void SwapTexts(ArrayList<ACharacter> charList, boolean story, boolean supports){
        if (!story && !supports) return;
        //Create hash map for names and voices
        Map<String, String> japaneseMap = new HashMap<>();
        Map<String, String> voiceMap = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        //creates new char list that will get smaller as we fill up the hash maps
        ArrayList<ACharacter> updatingList = new ArrayList<>(charList);
        for (ACharacter aCharacter : charList) {
            //loops through the updating list to check if there's the actual
            for (int j = 0; j < updatingList.size(); j++) {
                //once it finds the actual, it inputs all of the relevant values into the hashmaps and removes the actual from the updating list
                if (aCharacter.getActual().equals(updatingList.get(j).getName())) {
                    japaneseMap.put(aCharacter.getActual(), updatingList.get(j).getjName());
                    voiceMap.put(aCharacter.getActual(), updatingList.get(j).getVoice());
                    indexMap.put(aCharacter.getActual(), j);
                    break;
                }
            }
        }
        DebugBuilder.DebugOutput("Text Hash maps build");
        //contains all text files into two arrays: one for regular text and one for the support convos, which are handled slightly differently
        ArrayList<String> textFiles = new ArrayList<>();
        ArrayList<String> supportFiles = new ArrayList<>();
        if (story){
            textFiles.addAll(ReturnStory());
            //fixes script files for cutscenes
            //loops through all files in text files except the last 3, which do not correspond to chapters
            for (int i = 0; i < textFiles.size() - 3; i++){
                boolean edited = false;
                //gets the hex code for the script
                String scriptName = textFiles.get(i) + ".cmb";
                editedText = BinFiles.GetScript(scriptName);
                int size = editedText.length()/2;
                //like the other sections, we use a method of blank file and an edited file, that way the same chars are not replaced more than once
                blankText = new StringBuilder(editedText);
                //loops through all the characters every chapter to see if they can be replaced
                if (scriptName.equals("009.cmb")){
                    DebugBuilder.DebugOutput("This is where i check specific chapters");
                }
                for (ACharacter character : charList){
                    //checks that the HPID is in the script, meaning there is something to replace
                    String HPID = charList.get(indexMap.get(character.getActual())).getHpid().toLowerCase(); //puts HPID in its own thing so if statement doesnt suck
                    if (blankText.indexOf(HPID) != -1 | blankText.indexOf(HPID.substring(8, HPID.length() - 2) + "96a195fb00") != -1){
                        DebugBuilder.DebugOutput("Replacing " + character.getActual() + " with " + character.getName() + " in script for chapter " + scriptName);
                        size = fixScript(character, charList.get(indexMap.get(character.getActual())), size);
                        edited = true;
                    }
                }
                if (edited){
                    BinFiles.SetScript(editedText, scriptName);
                }
            }
        }
        if (supports){supportFiles.addAll(ReturnSupports(charList)); textFiles.addAll(ReturnSupportsExtra());}

        DebugBuilder.DebugOutput("Story swap: " + story);
        DebugBuilder.DebugOutput("Support Swap: " + supports);
        //loops through all of the files in the textfile list to replace their contents
        for (String tf : textFiles){
            //we set the current textfile to a string. This is the file we will be searching through for references
            //edited text file is the file we will actually be modifying. That way, there is no repeats or accidentally overwriting of characters
            editedText = BinFiles.GetText(tf);
            //create blank String builder that way characters who have been replaced won't be replaced more than once
            blankText = new StringBuilder(editedText);
            //loops through all characters
            for (ACharacter character : charList){
                //checks if character's japanese or real name is called at any point, and replaces all instances of them if so
                //exception for Lucina
                if (character.getName().equals("Lucina")){
                    ReplaceAllStrings("_" + japaneseMap.get(character.getActual()), "_マルス");
                    ReplaceAllStrings(japaneseMap.get(character.getActual()), character.getjName());

                }
                else if (character.getActual().equals("Lucina")){
                    ReplaceAllStrings(japaneseMap.get(character.getActual()), character.getjName());
                    ReplaceAllStrings("マルス", character.getjName());
                }
                else{
                    ReplaceAllStrings(japaneseMap.get(character.getActual()), character.getjName());
                }
                //morgan exception
                if (character.getName().equals("MorganM") | character.getName().equals("MorganF")){
                    ReplaceAllStrings(character.getActual(), "Morgan");
                }
                else{
                    ReplaceAllStrings(character.getActual(), character.getName());
                }
                ReplaceAllStrings("_" + voiceMap.get(character.getActual()) + "_", "_" + character.getVoice() + "_");
            }

            //writes the text file if it has been edited
            BinFiles.SetText(tf, editedText.toString());
        }
        //since we have to change the filenames for support convos, it get real annoying
        ArrayList<String> newFiles = new ArrayList<>();
        ArrayList<String> newContent = new ArrayList<>();
        for (String sf : supportFiles){
            //get the original text file
            //we use the same system as before with the blank/edited text files
            editedText = BinFiles.GetText(sf);
            blankText = new StringBuilder(editedText);
            //we use two new variables for the name of the file. Since this replacement will only happen
            StringBuilder blankName = new StringBuilder();
            blankName.append(sf);
            StringBuilder editedName = new StringBuilder();
            editedName.append(sf);

            //loops through all characters
            for (ACharacter character : charList){
                //checks if character's japanese or real name is called at any point, and replaces all instances of them if so
                //exception for Lucina
                if (character.getName().equals("Lucina")){
                    ReplaceAllStrings("_" + japaneseMap.get(character.getActual()), "_マルス");
                    ReplaceAllStrings(japaneseMap.get(character.getActual()), character.getjName());

                }
                else if (character.getActual().equals("Lucina")){
                    ReplaceAllStrings(japaneseMap.get(character.getActual()), character.getjName());
                    ReplaceAllStrings("マルス", character.getjName());
                }
                else{
                    ReplaceAllStrings(japaneseMap.get(character.getActual()), character.getjName());
                }
                //morgan exception
                if (character.getName().equals("MorganM") || character.getName().equals("MorganF")){
                    ReplaceAllStrings(character.getActual(), "Morgan");
                }
                else{
                    ReplaceAllStrings(character.getActual(), character.getName());
                }
                ReplaceAllStrings("_" + voiceMap.get(character.getActual()) + "_", "_" + character.getVoice() + "_");
                //file name replacement. Similar to the previous sections, but only occurs once
                int fileNameIndex = blankName.indexOf(japaneseMap.get(character.getActual()));
                if (fileNameIndex != -1){
                    editedName.replace(fileNameIndex, fileNameIndex + japaneseMap.get(character.getActual()).length(), character.getjName());
                    blankName.replace(fileNameIndex, fileNameIndex + japaneseMap.get(character.getActual()).length(), String.format("%0" + character.getjName().length() + "d", 1));
                }
            }
            newContent.add(editedText.toString());
            newFiles.add(editedName.toString());
        }

        for (int n = 0; n < newFiles.size(); n++){
            BinFiles.SetText(newFiles.get(n), newContent.get(n));
        }
        DebugBuilder.DebugOutput("texts swapped");
    }

    //EXCEPTIONS: Lucina uses マルス for the name of her support conversations, gameData
    //replace _マルス and ルキナ

    private void ReplaceAllStrings(String original, String replacement){
        int replaceIndex = blankText.indexOf(original);
        while (replaceIndex != -1){
            editedText.replace(replaceIndex, replaceIndex + original.length(), replacement);
            blankText.replace(replaceIndex, replaceIndex + original.length(), String.format("%0" + replacement.length() + "d", 1));
            replaceIndex = blankText.indexOf(original, replaceIndex + replacement.length());
        }
    }

    public ArrayList<String> ReturnSupports(ArrayList<ACharacter> charList){
        ArrayList<String> supportList = new ArrayList<>();
        //have to add male and female Robin to the conversations
        ArrayList<ACharacter> fullList = new ArrayList<>(charList);
        ACharacter mRobin = new ACharacter();
        mRobin.setjName("プレイヤー男");
        mRobin.setName("RobinM");
        fullList.add(mRobin);
        ACharacter fRobin = new ACharacter();
        fRobin.setjName("プレイヤー女");
        fRobin.setName("RobinF");
        fullList.add(fRobin);
        //nested for loop that finds all support conversations with other characters
        for (int char1 = 0; char1 < fullList.size() - 2; char1++){
            //adds exception for Lucina
            String jNameC1 = fullList.get(char1).getName().equals("Lucina") ? "マルス" : fullList.get(char1).getjName();
            DebugBuilder.DebugOutput("Checking support files for: " + jNameC1);
            for (int char2 = char1 + 1; char2 < fullList.size(); char2++){
                //creates new array of strings we will check to make sure they exist
                String jNameC2 = fullList.get(char2).getName().equals("Lucina") ? "マルス" : fullList.get(char2).getjName();
                //.out.println("Checking if " + jNameC2 + " can support " + jNameC1);
                ArrayList<String> possibleConvos =  new ArrayList<>();
                //regular conversation check
                possibleConvos.add(jNameC1 + "_" + jNameC2 );
                possibleConvos.add(jNameC2 + "_" + jNameC1);
                //parent child conversation check
                possibleConvos.add(jNameC1 + "_" + jNameC2 + "_親子");
                possibleConvos.add(jNameC2 + "_" + jNameC1 + "_親子");
                //sibling conversation check
                possibleConvos.add(jNameC1 + "_" + jNameC2 + "_兄弟");
                possibleConvos.add(jNameC2 + "_" + jNameC1 + "_兄弟");
                for (String convo : possibleConvos){
                    if (BinFiles.TextExists(convo)){
                        supportList.add(convo);
                    }
                }
            }
            //adds all four paired up special spot conversations
            for (int n = 0; n < 4; n++){
                supportList.add("様子_" + jNameC1 + (n + 1));
            }
        }

        return supportList;
    }

    public ArrayList<String> ReturnSupportsExtra(){
        ArrayList<String> extra = new ArrayList<>();
        //adds other random files for randomization
        extra.add("撤退"); //File for retreats
        extra.add("死亡"); //Files for deaths
        extra.add("独り言"); //base dialogue
        extra.add("様子_能力"); //special spot dialogue
        extra.add("様子_経験"); //specal spot dialogue 2
        extra.add("様子_武器経験"); //special spot dialogue 3
        extra.add("様子_拾得"); //special spot dialogue 4
        return  extra;
    }

    //returns an array with the name of all the story arrays
    public ArrayList<String> ReturnStory(){
        ArrayList<String> storyArray = new ArrayList<>();
        storyArray.add("P002");
        //for loop that adds all the main story chapter and paralogues
        //there's 26 chapters and 23 paralogues
        for (int i = 0; i < 26; i++){
            String chapterString = String.format("%03d", i + 1);
            storyArray.add(chapterString);
            //there's a total of 23 paralogues so qe add those. They all begin with x and have three digits
            if (i < 23){
                storyArray.add("X" + chapterString);
            }
        }
        //adds other random files for randomization
        storyArray.add("遭遇戦"); // file for skirmishes
        storyArray.add("紹介文"); // barracks character info
        storyArray.add("GameData"); // file with character info

        return storyArray;
    }

    //idk how this works, i stole it from charswapper. In theory, it should replace all instances of char2 with char1
    public int fixScript(ACharacter chr1, ACharacter chr2, int size){
        String table = blankText.substring(66, 68) + blankText.substring(64, 66);
        int enc = Integer.parseInt(table, 16);
        //the encoding table indicates where all the extra data for script files is stored
        //it's basically a table of pointers, where 00 is the first pointer and then we use pointer math to determine where the info is
        String ad11t = "000" + Integer.toHexString(size - enc);
        //size indicates the position in the file where we'll be appending the new character
        //the reason why we subtract from enc, is because it's a table so we're getting the pointer based on its position on the table
        String ad11 = ad11t.substring(ad11t.length()-4, ad11t.length());
        String ad12 = ad11.substring(ad11.length() - 2, ad11.length()) + ad11.substring(ad11.length() - 4, ad11.length() - 2);
        //Finds the location of the PID of the character we'll be replacing in the file
        int tind = blankText.indexOf(chr2.getHpid().toLowerCase())/2;
        //converts this into a pointer by subtracting the encoding table
        String ad21t = "000" + Integer.toHexString(tind - enc);
        String ad21 = ad21t.substring(ad21t.length()-4, ad21t.length());
        String ad22 = ad21.substring(ad21.length() - 2, ad21.length()) + ad21.substring(ad21.length() - 4, ad21.length() - 2);
        //searchs the pointers
        int ind1 = blankText.lastIndexOf("1d" + ad21.toLowerCase() + "47");
        int ind2 = blankText.lastIndexOf(ad22.toLowerCase()+"0000");
        editedText.append(chr1.getHpid());
        blankText.append(String.format("%0" + chr1.getHpid().length() + "d", 1));
        size = size + (chr1.getHpid().length()/2);
        //finds the point where the events start. Nothing before this point should be modified
        int eventStart = blankText.indexOf("006265763a") + 2;
        //function to loop and replace all corresponding instances
        ScriptIndexLoop(eventStart, enc,2, "1d" + ad21.toLowerCase() + "47", ad11.toLowerCase());
        ScriptIndexLoop(eventStart, enc,2, "1d" + ad21.toLowerCase() + "1d", ad11.toLowerCase());
        ScriptIndexLoop(eventStart, enc,0, ad22.toLowerCase()+"0000", ad12.toLowerCase());
        //1st loop corresponds to regular pid calls
        //2nd loop corresponds to pid calls for combat situations
        //3rd loop idk
        int tind2 = blankText.indexOf((chr2.getHpid().substring(8, chr2.getHpid().length()-2) + "96A195FB00").toLowerCase());
        if (tind2 != -1){
            //this corresponds to flag events that use 味方 instead of a regular PID call
            tind2 = tind2/2;
            String nname1 = chr1.getHpid().substring(8, chr1.getHpid().length()-2) + "96A195FB00";
            String ad31t = "000" + Integer.toHexString(size - enc);
            String ad31 = ad31t.substring(ad31t.length()-4, ad31t.length());
            String ad32 = ad31.substring(ad31.length() - 2, ad31.length()) + ad31.substring(ad31.length() - 4, ad31.length() - 2);

            String ad41t = "000" + Integer.toHexString(tind2 - enc);
            String ad41 = ad41t.substring(ad41t.length()-4, ad41t.length());
            String ad42 = ad41.substring(ad41.length() - 2, ad41.length()) + ad41.substring(ad41.length() - 4, ad41.length() - 2);
            ScriptIndexLoop(eventStart, enc,2, "1d" + ad41.toLowerCase() + "47", ad31.toLowerCase());
            ScriptIndexLoop(eventStart, enc,2, "1d" + ad41.toLowerCase() + "1d", ad31.toLowerCase());
            ScriptIndexLoop(eventStart, enc,0, ad42.toLowerCase()+"0000", ad32.toLowerCase());
            //similar to the last loop, but I'm honestly not super sure exactly what these do or entail
            editedText.append(nname1);
            blankText.append(String.format("%0" + nname1.length() + "d", 1));
            size = size + (nname1.length()/2);
        }
        editedText.replace(0, 6, "636d62");
        blankText.replace(0, 6, "636d62");
        return size;
    }

    private void ScriptIndexLoop(int scriptStart, int pointerTable, int extraLength, String original, String replacement){
        int index = 0;
        index = blankText.lastIndexOf(original);
        while (index != - 1){
            //script should not EVER edit things above the pointer table or below start of script. So we replace the blank (which we dont need to keep since we wont use it) and keep searching
            if (index/2 > pointerTable | index < scriptStart | index % 2 == 1){
                blankText.replace(index + extraLength, index + extraLength + 4, "ffff");
            }
            else{
                //replaces text with corresponding value and blank
                editedText.replace(index + extraLength, index + extraLength + 4, replacement);
                blankText.replace(index + extraLength, index + extraLength + 4, "0000");
            }
            index = blankText.lastIndexOf(original);
        }
    }
}
