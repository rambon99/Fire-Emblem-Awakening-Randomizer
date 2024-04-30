# Odd Rhythm - A Fire Emblem Awakening Randomizer

Alright, let's cut to the chase. You know what a randomizer is, I know what a randomizer is. With the click of a button, classes and
recruitment order get all swirled up for a new, interesting experience. A bunch of Fire Emblem games have had a tool like this, so
I think it's about time Awakening has a good one. This tool's been around for a while, but it was made with sticks and stones and barely
had any features, so I was thinking it would be good to improve it. So here it is, the latest version of the Fire Emblem Awakening Randomizer.

## Features

- Recruitment order randomization: Randomize the order in which you get characters! Get Nowi as your lord, and have Chrom become a spotpass character! The possibilities are limitless!
- Reverse recruitment option: Instead of just randomizing the order of recruitment, reverse it and start with Priam as your lord, little sister Aversa and Jagen Yen'fay!
- Class set randomization: Randomize the class set of each character, giving them new starting classes and reclass options!
- Skill implementation: Previously, each character just used the skills of the character they replaced (So whoever replaced Frederick would get Discipline and Outdoors Fighter). Now however, a character's skills are determined by their current class and level!
- Cutscene and Support swapping: Bored of seeing Chrom and crew in every scene? Well, you can now choose to replace the characters in the cutscenes with their appropriate replacements!
- UGA Compatibility and general compatibility improvements: I've changed the way the app goes through game data, meaning it should (theoretically) work with every other mod. IMPORTANT NOTE: I do not believe this works with Project Thabes, but I have not tested it.
If the mod includes a lot of changes such as new classes or characters, it is very probable that the randomizer will only partially work or not work at all
- File randomization: Create an xml file to record how the game was randomized, so that you can randomize it the same way in the future (either to share with friends, or for a new fixed version of the app) or you can change some of the settings and re-randomize the game to your liking!
- Improved output: Added more info to the output, option to not generate any output at all, as well as new debug output in case you got an error and want to send it to me
- Other misc bug fixes
- Nah: Nah :3

## NEW Features!

- Stat modification: Adjust the stats of each character depending on their growths, base stats, or randomize them at your leisure!

## Requirements
[Java 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)

[FEAT](https://github.com/VelouriasMoon/FEAT/releases/tag/v2.6) (Comes packed with the zip)

## Known Issues

- Characters can sometimes replace themselves during randomization, meaning they are recruited at their exact same spot
- Characters can obtain more than one version of the same class in their class set (essentially reducing their class set)
- Sometimes even if supports are swapped, some base conversations will play. This will happen if two characters can support, but the characters they replace cannot.
(Eg. Anna and Tiki can support in the base game. If Anna replaces Maribelle and Tiki replaces Sumia, Sumia and Maribelle don't have a support conversation. But Anna and Tiki still have a support. Thus, the default conversation will play).

## Future implementations

- Built-in FEAT implementation + non-destructive output: FEAT is a pain in the ass and I want to make the process be more smooth. Also want to be able to test things without having to delete and copy my files over and over
- Randomize Premonition: Premonition chars are temp and don't affect the game as a whole but I'm tired of people complaining the randomizer didn't work cus they see Robin and Chrom in premonitions lol
- Change Falchion and other character exclusive weapons to match their replacement/new class' weapon type
- Randomize/change the skill learnset for classes

## Waaaayyyyy in the future implementations

- Strength/Magic based class randomization: Strength people inherit strengths classes, magic people inherit magic classes
- Balanced class randomization: I had a run where like 5 people randomized into dark mages, not doing that again lol
- Force Dancer & Max dancer options
- Randomize Robin's class
- Exclude tactician from class randomization
- Cross generation swaps
- Growth randomization: Randomize a character's growth (swap stats around, redistribute, etc). Also Randomize class growths
- Stat cap randomization
- Support/Pair up stat randomization
- NPC/Enemy randomization: Randomize generics and NPCs, but also have characters that have randomized appear as real enemies (Gangrel replacement instead of Gangrel, Walhart replacement instead of Walhart, etc.)
- Replace a characters's support options with those they are replacing (not just conversations)
- Improve compatibility with more mods
- GUI tooltips
- Option to exclude spotpass chars from randomization
- fully random weapon and weapon ranks

## Options explanation
I believe that most of the options in the randomizer are self explanatory, but in case you're not sure what each one does exactly here's a summary of what each button/option does

### Recruitment Order
* Randomize fully: Randomizes recruitment order for all characters, meaning you will encounter some much earlier and some much later. This includes spotpass characters. Currently, only gen 1 and gen 2 are randomized separately, 
* Reverse recruitment: Reverses the recruitment order of all characters, starting with Chrom and ending with Priam. This means you will Priam instead of Chrom, Aversa instead of Priam, etc. Like before, gen 1 and gen 2 are reversed separately.
* Swap Story & Cutscenes: This swaps all cutscenes and important dialogue in the game so that it matches whichever characters have been replaced. So whoever you recieved as your first character will appear in cutscenes and talk instead of Chrom. This works for all characters
Voice lines are also swapped, but since everyone's voice lines are different and not ordered in any particular way, they will basically say random things. This is not planned on being fixed because it's funny and part of the charm. Another thing of note is that animations
for female and male characters are different. So if a female character replaces a male character or vice versa, they will have funny animations in cutscenes. This again will not be fixed because it's funny.
* Swap Supports Convos: This swaps all support conversations and other flavor text such as standing in glowing spots so characters match whoever they replaced. This only changes the support CONVERSATIONS, not the support options. This means that sometimes conversations won't
appear swapped, like mentioned in the known issues section. In the future, it is planned to also swap the support options so that it doesn't feel as wonky.

### Class Sets
* Randomize: Randomizes the class sets of every character, including what class they start in. Some unique and enemy only classes are available, such as soldier, lodestar and merchant. Single tier classes are treated differently depending on how good they are. Both promoted and
unpromoted units can randomize into Taguels and Manaketes. Only promoted units can randomize into Conqueror. Only unpromoted units can randomize into Soldier, Lodestar and Merchant. Promoted characters can be any promoted class in the game, but every class always has the same
base class (Great Knights will always have Cavaliers as base, Heroes will always have Mercenaries as base, etc)

### Stat Modification
* Default: Most basic stat modification. Characters will keep the stats of the characters they replace (or their own stats if they only randomized classes). Simple but can be unbalanced, especially when magic and strength characters swap
* Growth Redistribution: Takes the base stat total of the character being replaced and redistributes them using the new character's personal and class growths. Makes all characters a little bit better and focused around their strengths/current class	
* Base Redistribution: Takes the original character's base stat total, and redistributes them using the new character's original base stats. Makes all characters function closer to how they appear originally, especially in cases where their bases do not match their growths. (A lot of chars have unnaturally high luck, for example)
Do note that this works kinda bad for characters that have very low bases. Chrom has 2 base str and 1 base mag, meaning his magic will scale almost as much as his str. It can get very wacky, this is probably the most unbalanced stat distribution option. I would have removed it but I coded it already so.
* Level Scaled: "Scales" characters up and down by calculating a character's average base stats after leveling up and down a certain amount of levels. This takes both growths and base stats into account, making it as close to the original character as possible
NOTE: This is probably least objective type of stat distribution. There's actual internal level for any charcter, so I'm just guessing how much/little they get scaled. The spotpass chars in specific have crazy stats (priam especially had to get nerfed for this) so while I think it's an okay type of balancing it can be wonky.
* Shuffle Base: Swaps the base stats of the character they are replacing around. So Lissa's 8 Luck base might be put in her replacement's Str, or her -1 Res base might be put in Def. A way of randomizing stats without going too crazy
* Randomize from Base: Takes the base stat total of the character being replaced and distributes it randomly through all the stats. So you might get a nice even distribution or someone with 14 Lck and no Str or Mag.

### Randomize from file
This option will allow you to edit the gamefiles in order to match exactly what is in the given xml. For when you want to remake the files for a run you already had either because of errors or to share with a friend.
You can also edit the parameters of randomization in order to make a more custom built experience. In order to see exactly what options to write for each field, check the GitHub and the data folder, where all the xmls containing data are held.

### Other Options
* Generate text file: Generates a text file that details all of the changes made to the rom, be it class recruitment to stats and skills.
* Generate xml file: Generates an xml file with randomization information, that way it can be randomized in the same or similar manner again.
* Generate debug file. Generates file that you can send me so that I can diagnose any potential problems with your run.

# How to use/Install:

## Step 1 - The romfs folder

- Obtain Awakening's romf folder. In order to do that, you have to extract it from a copy of Fire Emblem Awakening. If you wanna do it 
through your 3DS, you can follow one of the guides from this site: https://gbatemp.net/threads/title-dumping-and-file-extraction-using-godmode9-1-0.465427/
Alternatively, if you happen to have Awakening installed in a certain orange, citrus-y application, you can just dump the romfs by right clicking the game
and selecting that option.
- Once it's done, I recommend you make a backup of these files, so that in case you wanna re-randomize or something goes wrong, you won't have to dump the romfs again

## Step 2 - Setting up the new romfs folder

- Create a folder and call it romfs in the same folder as the application (I've heard that putting the new romfs folder can sometimes not work, but I haven't researched into it so just leave it close to be safe)
This is the folder that we're going to use to randomize the game
- Go into the old romfs -> 00040000000A0500 and copy the following folders into your new romfs folder: data, m, scripts
(Note, if the name of the folder inside your old romfs isn't 00040000000A0500, then you probably have a non american version of awakening.
for ease of use, I reccommend just getting the american version. Tho if you don't care about texts, you can probably just rename your text folders
to m and it should work?)
- Your new romfs folder should look like this:

romfs
|-----data
|-----m
|-----scripts

No more! No less!

- Open up FEAT (should be included in the download, if not here's a link). Drag both the data and m folders into FEAT
- Like in the previous step, I recommend you keep a backup of this folder in case you want to randomize the game again or something goes wrong

## Step 2.5 - Installing other mods (Optional)

- Just before, drag the data and m folders into FEAT so they will be decompressed. You don't need to decompress any of the other folders
- Copy/drag the mod into the same folder as the base romfs, so that you overwrite the vanilla files with those of the mod

## Step 3 - Randomizing the game

- Open the app and select the romfs folder where the files are all contained.
- If all the files are in order, a new window will pop up and you'll be able to choose your modifications!
- If you get an error that some of the files are missing, then you might have one of the following issues:
* Some files ARE missing, get a copy of the original unmodified backup of romfs you
made and try again. 
* You've accidentally selected something other than the romfs folder.
*  You forgot to copy some files/folders. Make sure you copied all the necessary files from the
instructions above
* You didn't use FEAT to decompress the files from .bin.lz to .bin . The randomizer doesn't recognize
.bin.lz files so make sure they're .bin files.
- Choose whatever options you wish to use for modification! They're all plenty self explanatory, but feel free to go to the features
section and read them over
- Hit the randomize button and watch Nah go!

## Step 4 - Finalizing setup
- Make sure to fully close the randomizer! Otherwise files will not compress
- With Auto Extract option on (it's on by default), drag and drop the m folder into FEAT. Wait till the files are done compressing.
- Select Options -> Batch Compress and drag the data folder into FEAT
(Note: Do NOT drag the script folder into FEAT! Script files do not need to be compressed)
- That's it! Now just drag your files into whatever device you want to play them on!

## Step 5A - Luma patching

1. Put your 3ds sd card in your computer and go into the luma folder.
2. If there isn't one already, create a folder and name it "titles" (Not 'Titles' or 'title')
3. Go inside the titles folder and create a folder with the name of your 3ds game code. It's different
for each region, but the code for USA Awakening is "00040000000a0500", so if that's the version you're
using that's what you should name it.
4. Place your new modified romfs folder here. NOTE: While it doesn't matter the name you gave to your
new romfs folder while modidying/storing it, in this part the folder MUST be named "romfs".
5. Take the SD card out of your computer and place it in your 3ds. 
6. As you power up your 3ds, hold the Select button. This will take you to the Luma configuration screen
7. Go to the option that is called "Enable game patching". Hit A to enable it, and an x will appear between 
the parentheses to indicate your selection.
8. Press start to save and start up Awakening. You should be playing your patched game now!

## Step 5B - Citra patching
1. Right click on Awakening on Citra
2. Select "Open Mod Location"
3. Place the romfs files in there

#FAQ
"It says I need java installed even though Java is installed!"
For some reason, the NEWEST version of java doesn't seem to work. Don't ask me why, I barely understand why Java works at all. If you wanna
use this randomizer, I recommend downgrading to 18, which is guaranteed to work. Although I've had it work with some older versions *shrug*

"I randomized the game and Premonition (the tutorial chapter where Chrom and Robin fight Validar) is unchanged!"
Yes, you are correct! That chapter uses fake versions of every character, that will not be part of your army in any meaningful manner.
As such, I decided not to mess with them. I'll probably implement randomization of that chapter too at some point in the future. To see if
randomization worked, you must reach the prologue (after you beat validar and save)

"I try to open the program, but it says I have missing files"
There's a number of reasons why this could happen. Here are some options:
- You didn't decompress the files using FEAT. The m folder should have txt files and the data folder should have bin files.
- You actually are missing files! Make sure you grabbed everything you need directly from the romfs. Mods like UGA will only have a few
chapters and not data for the whole game.

"I placed the files in the characters on the map are the same!"
Make sure you recompress the files using FEAT. For the gameplay files to work, you have to pass the data folder through FEAT with the batch
compression options turned on.

"The text is completely unchanged!"
Another compression issue. Remember to pass the m folder through FEAT WITHOUT the batch compression option on. Otherwise, it will turn the txt
files into txt.lz instead of bin.lz which is what the game actually reads
# Contact
If you have any sort of errors or whatever, please contact me on discord at rambon99. Will I respond? Maybe. Probably not. But I don't
really check a lot of other sites, so realistically speaking that's probably the best place to reach out to me. Thanks.

# Afterword

I'm surprised I'm still working on this lol. In all honesty, this update could have been uploaded a while ago. There were just a couple of final
bugs that weren't the most difficult to fix, just kinda annoying haha. This isn't a big update by any measure. Just added the stat stuff and bug fixing.
That being said, I definitely have been working on this less and my interest is diminishing. I don't know how much lonver I'll bring myself to
working on this, but I definitely wanna make it so that next update I remove the necessity to use FEAT alltogether. FEAT is the last thing holding
back this app from being simple and intuitive. And frankly, I'm tired of it lol. If i can't accomplish anything else, at least I wanna do that.
