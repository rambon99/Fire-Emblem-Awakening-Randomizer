# Odd Rhythm - A Fire Emblem Awakening Randomizer

From the dawn of existence, man has wanted one thing. Food, water, and the ability to replay their favorite games but with a slight twist to the characters
and mechanics. I can't provide the first two, but I can certainly maybe attempt to do the last one. There are no more external programs, no more weird
stupidly complicated details. This is literally as simple as I can make it. Just take your game files, select them and the app will spit em back out at you
After so long, people will finally be able to randomize this game without any kind of complications.

## Features

- Simple to use: Doesn't require any external features other than Java
- Recruitment order randomization: Randomize the order in which you get characters! Get Nowi as your lord, and have Chrom become a spotpass character! The possibilities are limitless!
- Reverse recruitment option: Instead of just randomizing the order of recruitment, reverse it and start with Priam as your lord, little sister Aversa and Jagen Yen'fay!
- Class set randomization: Randomize the class set of each character, giving them new starting classes and reclass options!
- Skill implementation: Previously, each character just used the skills of the character they replaced (So whoever replaced Frederick would get Discipline and Outdoors Fighter). Now however, a character's skills are determined by their current class and level!
- Cutscene and Support swapping: Bored of seeing Chrom and crew in every scene? Well, you can now choose to replace the characters in the cutscenes with their appropriate replacements!
- Stat modification: Adjust the stats of each character depending on their growths, base stats, or randomize them at your leisure!
- UGA Compatibility and general compatibility improvements: I've changed the way the app goes through game data, meaning it should (theoretically) work with every other mod. IMPORTANT NOTE: I do not believe this works with Project Thabes, but I have not tested it.
If the mod includes a lot of changes such as new classes or characters, it is very probable that the randomizer will only partially work or not work at all
- File randomization: Create an xml file to record how the game was randomized, so that you can randomize it the same way in the future (either to share with friends, or for a new fixed version of the app) or you can change some of the settings and re-randomize the game to your liking!
- Improved output: Added more info to the output, option to not generate any output at all, as well as new debug output in case you got an error and want to send it to me
- Original files are no longer modified, so no need to keep an extra backup
- Other misc bug fixes
- Nah: Nah :3

## Bugfixes
- Marth (Lucina) appearing as a shadow in cutscenes
- Some map events not triggering correctly
- Owain and Cynthia chapters fixed
- Morgan replacements showing up with blank names
- Morgan replacements not displaying the correct names

## Requirements
[Java 18] (https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)

## Known Issues

- This is not really "problem" but the nah sprite stops moving while the program is loading. This doesn't mean the program has crashed! There's nothing wrong!
It's just visually shocking. I might add a progress/loading bar in the future
- Characters can sometimes replace themselves during randomization, meaning they are recruited at their exact same spot
- Characters can obtain more than one version of the same class in their class set (essentially reducing their class set)
- Only Chrom's weapon has been changed to belong to Chrom's replacement. Other unique weapons like Lucina's or Owain's have not beend adjusted
- Sometimes even if supports are swapped, some base conversations will play. This will happen if two characters can support, but the characters they replace cannot.
(Eg. Anna and Tiki can support in the base game. If Anna replaces Maribelle and Tiki replaces Sumia, Sumia and Maribelle don't have a support conversation. But Anna and Tiki still have a support. Thus, the default conversation will play).
- XML files produced when only selecting to shuffle recruitment order and not classes are broken. Will figure out a better way to implement it in the future.

## Future implementations

- Randomize Premonition: Premonition chars are temp and don't affect the game as a whole but I'm tired of people complaining the randomizer didn't work cus they see Robin and Chrom in premonitions lol
- Change Falchion and other character exclusive weapons to match their replacement/new class' weapon type
- Randomize/change the skill learnset for classes
- Shuffle classes option
- Randomize Robin's class
- Exclude tactician from class randomization
- Growth randomization: Randomize a character's growth (swap stats around, redistribute, etc). Also Randomize class growths
- NPC/Enemy randomization: Randomize generics and NPCs, but also have characters that have randomized appear as real enemies (Gangrel replacement instead of Gangrel, Walhart replacement instead of Walhart, etc.)

## Waaaayyyyy in the future implementations

- Strength/Magic based class randomization: Strength people inherit strengths classes, magic people inherit magic classes
- Balanced class randomization: I had a run where like 5 people randomized into dark mages, not doing that again lol
- Force Dancer & Max dancer options
- Cross generation swaps
- Stat cap randomization
- Support/Pair up stat randomization
- Replace a characters's support options with those they are replacing (not just conversations)
- Improve compatibility with more mods
- GUI tooltips
- Option to exclude spotpass chars from randomization
- fully random weapon and weapon ranks

## Options explanation
I believe that most of the options in the randomizer are self explanatory, but in case you're not sure what each one does exactly here's a summary of what each button/option does

- Recruitment Order
* Randomize fully: Randomizes recruitment order for all characters, meaning you will encounter some much earlier and some much later. This includes spotpass characters. Currently, only gen 1 and gen 2 are randomized separately, 
* Reverse recruitment: Reverses the recruitment order of all characters, starting with Chrom and ending with Priam. This means you will Priam instead of Chrom, Aversa instead of Priam, etc. Like before, gen 1 and gen 2 are reversed separately.
* Swap Story & Cutscenes: This swaps all cutscenes and important dialogue in the game so that it matches whichever characters have been replaced. So whoever you recieved as your first character will appear in cutscenes and talk instead of Chrom. This works for all characters
Voice lines are also swapped, but since everyone's voice lines are different and not ordered in any particular way, they will basically say random things. This is not planned on being fixed because it's funny and part of the charm. Another thing of note is that animations
for female and male characters are different. So if a female character replaces a male character or vice versa, they will have funny animations in cutscenes. This again will not be fixed because it's funny.
* Swap Supports Convos: This swaps all support conversations and other flavor text such as standing in glowing spots so characters match whoever they replaced. This only changes the support CONVERSATIONS, not the support options. This means that sometimes conversations won't
appear swapped, like mentioned in the known issues section. In the future, it is planned to also swap the support options so that it doesn't feel as wonky.

- Class Sets
* Randomize: Randomizes the class sets of every character, including what class they start in. Some unique and enemy only classes are available, such as soldier, lodestar and merchant. Single tier classes are treated differently depending on how good they are. Both promoted and
unpromoted units can randomize into Taguels and Manaketes. Only promoted units can randomize into Conqueror. Only unpromoted units can randomize into Soldier, Lodestar and Merchant. Promoted characters can be any promoted class in the game, but every class always has the same
base class (Great Knights will always have Cavaliers as base, Heroes will always have Mercenaries as base, etc)

- Stat Modification
* Default: Most basic stat modification. Characters will keep the stats of the characters they replace (or their own stats if they only randomized classes). Simple but can be unbalanced, especially when magic and strength characters swap
* Growth Redistribution: Takes the base stat total of the character being replaced and redistributes them using the new character's personal and class growths. Makes all characters a little bit better and focused around their strengths/current class	
* Base Redistribution: Takes the original character's base stat total, and redistributes them using the new character's original base stats. Makes all characters function closer to how they appear originally, especially in cases where their bases do not match their growths. (A lot of chars have unnaturally high luck, for example)
Do note that this works kinda bad for characters that have very low bases. Chrom has 2 base str and 1 base mag, meaning his magic will scale almost as much as his str. It can get very wacky, this is probably the most unbalanced stat distribution option. I would have removed it but I coded it already so.
* Level Scaled: "Scales" characters up and down by calculating a character's average base stats after leveling up and down a certain amount of levels. This takes both growths and base stats into account, making it as close to the original character as possible
NOTE: This is probably least objective type of stat distribution. There's actual internal level for any charcter, so I'm just guessing how much/little they get scaled. The spotpass chars in specific have crazy stats (priam especially had to get nerfed for this) so while I think it's an okay type of balancing it can be wonky.
* Shuffle Base: Swaps the base stats of the character they are replacing around. So Lissa's 8 Luck base might be put in her replacement's Str, or her -1 Res base might be put in Def. A way of randomizing stats without going too crazy
* Randomize from Base: Takes the base stat total of the character being replaced and distributes it randomly through all the stats. So you might get a nice even distribution or someone with 14 Lck and no Str or Mag.

- Randomize from file
This option will allow you to edit the gamefiles in order to match exactly what is in the given xml. For when you want to remake the files for a run you already had either because of errors or to share with a friend.
You can also edit the parameters of randomization in order to make a more custom built experience. In order to see exactly what options to write for each field, check the GitHub and the data folder, where all the xmls containing data are held.

- Other Options
* Generate text file: Generates a text file that details all of the changes made to the rom, be it class recruitment to stats and skills.
* Generate xml file: Generates an xml file with randomization information, that way it can be randomized in the same or similar manner again.
* Generate debug file. Generates file that you can send me so that I can diagnose any potential problems with your run.

# How to use/Install:

## Step 1 - The romfs folder

- Obtain Awakening's romf folder. In order to do that, you have to extract it from a copy of Fire Emblem Awakening. If you wanna do it 
through your 3DS, you can follow one of the guides from this site: https://gbatemp.net/threads/title-dumping-and-file-extraction-using-godmode9-1-0.465427/
Alternatively, if you happen to have Awakening installed in a certain yellow, citrus-y application, you can just dump the romfs by right clicking the game
and selecting that option.

## Step 2 - Setting up the new romfs folder

- Create a new folder where we're going to put the input for the randomizer. You can name this folder whatever you want. For the purposes of this tutorial,
we'll call it "input"
(IMPORTANT: You can name this folder anything EXCEPT romfs! If you name the input folder "romfs" the application will not start! This is for ease of use
and to clear confusion.)
- Go into the romfs folder that you just extracted: romfs -> 00040000000A0500, and copy the following folders into your new input folder: data, m, scripts
(Note, if the name of the folder inside your old romfs isn't 00040000000A0500, then you probably have a non american version of awakening.
for ease of use, I reccommend just getting the american version. Tho if you don't care about texts, you can probably just rename your text folders
to m and it should work?)
- Your new input folder should look like this:
-(input)
|-----data
|-----m
|-----scripts
No more! No less!

## Step 2.5 - Installing other mods (Optional)

- Copy/drag the mod files into the same folder as your input folder, so that you overwrite the vanilla files with those of the mod

## Step 3 - Randomizing the game

- Open the app and select the romfs folder where the files are all contained.
- If all the files are in order, a new window will pop up and you'll be able to choose your modifications!
- Choose whatever options you wish to use for modification! They're all plenty self explanatory, but they are explained in the
option explanation section.
- Hit the randomize button and watch Nah go!

## Step 4 - Finalizing setup
- In the same folder as the randomizer, you will now find a folder named "romfs". This is where all of the modified files are
stored, and what you need to use to randomize the game!
- Aditionally, if you selected any of the extra options (like text output) they will appear in the output folder.
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
Exactly where it says on the message! Somewhere in there, you have to be missing important files. Note that if you're using a mod like UGA, it will
not include all the necessary files from the base game. You need to merge your mod files into your romfs folder!!!

"I try to open the program, but it says that I can't name my input folder romfs!"
I mean honestly. Honestly. Honestly. There's just no way.

# Contact
If you have any sort of errors or whatever, please contact me on discord at rambon99. Will I respond? Maybe. Probably not. But I don't
really check a lot of other sites, so realistically speaking that's probably the best place to reach out to me. Thanks.

# Afterword

This is just a small bugfixing update. I'm planning to rehaul the system that actually replaces characters on the map, which should take
a good while. However, it should let me do some very cool things so, please look forward to that!
