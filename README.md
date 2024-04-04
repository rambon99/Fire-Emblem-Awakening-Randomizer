# Odd Rhythm - A Fire Emblem Awakening Randomizer

Alright, let's cut to the chase. You know what a randomizer is, I know what a randomizer is. With the click of a button, classes and
recruitment order get all swirled up for a new, interesting experience. A bunch of Fire Emblem games have had a tool like this, so
I think it's about time Awakening has a good one. This tool's been around for a while, but it was made with sticks and stones and barely
had any features, so I was thinking it would be good to improve it. So here it is, the latest version of the Fire Emblem Awakening Randomizer.

## Features

- Recruitment order randomization: Randomize the order in which you get characters! Get Nowi as your lord, and have Chrom become a spotpass character! The possibilities are limitless!
- Class set randomization: Randomize the class set of each character, giving them new starting classes and reclass options!

## NEW Features!

- Skill implementation: Previously, each character just used the skills of the character they replaced (So whoever replaced Frederick would get Discipline and Outdoors Fighter). Now however, a character's skills are determined by their current class and level!
- Cutscene and Support swapping: Bored of seeing Chrom and crew in every scene? Well, you can now choose to replace the characters in the cutscenes with their appropriate replacements!
(NOTE: Currently, only the support conversations themselves are swapped. The actual supports will depend on mods or base awakening)
- Reverse recruitment option: Instead of just randomizing the order of recruitment, reverse it and start with Priam as your lord, little sister Aversa and Jagen Yen'fay!
- UGA Compatibility and general compatibility improvements: I've changed the way the app goes through game data, meaning it should (theoretically) work with every other mod. Probably won't work with new characters or if the characters have new FIDs.
- File randomization: Create an xml file to record how the game was randomized, so that you can randomize it the same way in the future (either to share with friends, or for a new fixed version of the app) or you can change some of the settings and re-randomize the game to your liking!
- Improved output: Added more info to the output, option to not generate any output at all, as well as new debug output in case you got an error and want to send it to me
- Other misc bug fixes
- Nah: Nah :3

## Known Issues

- Some of the cutscenes are broken. Specifically ones featuring 'marth', as well as cutscenes that feature combat. The dialogues should play correctly, it's just the in game cutscene that might be weird
- Of note is script of chapter 2, which skips the first intro in the barracks entirely. I'm probably gonna make a hotfix in order to resolve this one at least, but rn I'm too lazy so I'm just publishing it lol
- Characters can sometimes replace themselves during randomization, meaning they are recruited at their exact same spot
- Characters can obtain more than one version of the same class in their class set (essentially reducing their class set)
- Sometimes even if supports are swapped, some base conversations will play. This will happen if two characters can support, but the characters they replace cannot.
(Eg. Anna and Tiki can support in the base game. If Anna replaces Maribelle and Tiki replaces Sumia, Sumia and Maribelle don't have a support conversation. But Anna and Tiki still have a support. Thus, the default conversation will play).

## Future implementations

- Change Falchion to be the weapon type of the base class of the Chrom replacement (unless it's a staff, probably(
- Base stat changes: Currently characters just inherit the base stats of whoever they replace. I want to implement a level scale as well as a growth based base stat redistribution in the future. Also just fully random
- Growth randomization: Randomize a character's growth (swap stats around, redistribute, etc). Also Randomize class growths
- Strength/Magic based class randomization: Strength people inherit strengths classes, magic people inherit magic classes
- Balanced class randomization: I had a run where like 5 people randomized into dark mages, not doing that again lol
- Force Dancer & Max dancer options

## Waaaayyyyy in the future implementations

- NPC/Enemy randomization: Randomize generics and NPCs, but also have characters that have randomized appear as real enemies (Gangrel replacement instead of Gangrel, Walhart replacement instead of Walhart, etc.)
- Randomize/change the skill learnset for classes
- Replace a characters's support options with those they are replacing (not just conversations)
- GUI tooltips
- Nondestructive randomization: Make new files instead of overwriting existing files
- fully random weapon and weapon ranks

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
-romfs
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

# Afterword

6 longs years ago, back when the skies were brighter and world was simpler, I released a shitty lil application into the world. 
Back then I only had rudimentary programming knowledge, and god does it show. Looking at my code today, it's a complete mess dear lord.
But for how simple and shitty my app was, it apparently worked. There were definitely a lot of more things I could have added. 
Avenues to be improved. Even after this release, there's still features I would like added. But for the longest time, I just didn't have
the drive or interest to keep at it. I guess I must have been satisfied with the absolutely rudimentary state in which the application found
itself in. Honestly, a part of me kinda hoped that it would just be forgotten so that people would never see how awkward and dinky the app was
So why is it, that after all these years, I somehow returned to finish the job? One simple reason. Pettiness.
The other day I was talking to my friend about my 3DS hacking past, mentioning some of the cool projects I'd seen or worked on. Just your 
average reminiscing in the past, the sort of casual conversation brought up with no deeper meaning, intended to occupy just a few moments
of interpersonal connection and nothing more. In my search for examples, I ended up stumbling onto a channel that showcased a lot of these
3DS FE mods. One in particular caught my mind, one of an Awakening Reverse Recruitment mod. I thought, "Hey! Reverse Recruitment is cool!
I've always wanted to try doing that." Much to my horror however, when I opened the video instead of seeing Priam or Aversa, I was met
with Basilio's worn face. In my opinion, Awakening Spotpass characters are some of the coolest things in the whole game. It's a shame that
you can only unlock them AFTER you beat the game. That's why I thought a reverse recruitment would be the best place to finally use them.
So then, why is it that Basilio's ugly mug is the first new thing you get in the game?!? Worst of all, Chrom was STILL Chrom. According to
the vid, it was because it's not possible to swap Chrom out. But it is though! I've done it! My shitty randomizer does that, and I wrote
that piece of trash in 2019. I strictly remember being able to go past chapter 11 or 13 with randomized units and even recruiting children
(though those might be rose tinted glasses). Anyways, I was furious. Fuming. I couldn't let this stand. This wasn't the reverse recruitment
I had always dreamed of. No, I had to rectify this. I had the tools to do so, so why didn't I? Like a man possessed, I suddenly started
to delve into Java and Awakening hacking. Literally all my 3DS hacking files are gone by this point. If not for the GitHub, the whole app
would have been lost forever. But I didn't let that stop me. I used whatever rudimentary Awakening hacking knowledge I still somehow retained
and clawed myself back from the grave until I managed to pull out this release. As you might have seen, reverse recruitment is already
Implemented, so at least some of that has been pacified in my mind. How long will this randomizer hyper obession last for? That I cannot
say. I want to do more, but it's completely possible nay probably that I'll just up and disappear one day. Until then, here is this thing. 
I hope someone finds it useful.

IMPORTANT NOTE!!! Absolutely no hate or negativity to the creator of the Reverse Recruitment mod btw!! I'm sure you're a great person and worked
hard to get that working! It's more of a reflection on me and how much of a shitty guy I am than anything else. If anything, the fact that
you actually got something done and shared it with the world while I just sat on my ass doing nothing for 6 years is incredibly impressive.
I just thought that after so many years of being dead and not caring about Awakening hacking, this was the thing that pulled me back in.
