Fire Emblem Awakening Randomizer - BETA VERION
-----------------------------------------------
A Fire Emblem Awakening Randomizer that attempts to swap in game content
for a more varied experience.
Do note that this is a BETA version, which means it will be prone to
difficulties and crashes. ONLY USE AT YOUR OWN RISK.

Features
-----------------------------------------------
-Swap the classes of in-game playable characters
-Swap the Join order of in-game playable characters

Instructions
-----------------------------------------------
1. Obtain Awakening's romf folder. If you don't know how to do that, follow this thread on how to access
other files from CIAs or gamecarts. https://gbatemp.net/threads/title-dumping-and-file-extraction-using-godmode9-1-0.465427/
2. Create a copy of the romfs folder and keep it somwhere safe. You should keep this copy as
backup in case something goes wrong.
3. Create a new folder and name it romfs in the same folder where the application is. Keep this and
your original romfs folder separate, do not mix them up.
4. Open both the new and the original romfs folder. From the original romfs folder, drag the entire scripts
folder into the new one. 
5. Create a new folder in the new romfs called "data" (Type that exactly, not 'Data' or 'datas')
6. Drag the "person" and "dispos" folder along with the GameData.bin.lz from the original romfs 
into the data folder of the new romfs.  
7. Open FEAT (Included in the zip file) and drag the new romfs folder into it.
8. Open the Fire Emblem Awakening Randomizer jar file. 
9. Select the romfs folder. If you get an error saying that some files are missing then 
either: 
* Some files ARE missing, get a copy of the original unmodified backup of romfs you
made and try again. 
* You've accidentally selected something other than the romfs folder.
* Your file structure is incorrect. The file structure of a correct romfs folder is as follows:
-romfs
|-----data
|------------dispos
|------------person
|------------GameData
|-----scripts

*  You forgot to copy some files/folders. Make sure you copied all the necessary files from the
instructions above
* You didn't use FEAT to decompress the files from .bin.lz to .bin . The randomizer doesn't recognize
.bin.lz files so make sure they're .bin files.
10. Choose the options you wish to enable.
11. Hit Randomize. Once it is done, green text indicating that randomization was completed
will appear. If you get red text instead, it means that randomization failed at some point.
Replace the files you placed new romfs folder with the original ones from the original romfs folder
(Remember to use the FEAT to decompress them!).
12. Once your files have been randomized CLOSE THE RANDOMIZER APP and drag the new romfs folder into
FEAT one last time (This is to compress the bin files into bin.lz files because Awakening can only
read bin.lz files. IF YOU DO NOT CLOSE THE RANDOMIZER APP THEN FEAT WILL CRASH AND NOT COMPRESS THE FILES).
13. You're done! Drag your files into your patching app of choice (Luma, ntr, hans) 
and enjoy!

EXTRA
-------------------------------------------------------------------------------------------------------
This is an extra not necessary guide for if you don't know how to patch your modified game and you don't 
care what patching method to use

How to Patch Awakening with Luma
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