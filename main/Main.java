package main;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

import main.Output.DebugBuilder;
import main.Output.XMLBuilder;
import main.Program.GUIBools;
import main.Structure.XMLReader;
import main.Swapping.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import main.Structure.BinFiles;
import main.Randomizer.ClassShuffler;
import main.Structure.ACharacter;
import main.Structure.CharacterStarter;
import main.Structure.ClassStarter;
import main.Structure.AClasses;
import main.Structure.AChapter;
import main.Structure.ChapterStarter;
import main.Randomizer.CharacterShuffler;
import main.Output.TextFile;
import main.Program.GUI;
import main.Program.FileChecker;

public class Main{
	
	public Main(){
		
	}
	
	public void randomizeShit(GUIBools randomizedOptions, File folder, File xmlFile) throws Exception{
		DebugBuilder.DebugOutput("Class Swap: " + randomizedOptions.characters);
		DebugBuilder.DebugOutput("Character Swap: " + randomizedOptions.characters);
		ArrayList<ACharacter> c = new ArrayList<ACharacter>();
		ArrayList<AClasses> cl = new ArrayList<AClasses>();
		ArrayList<AChapter> ch = new ArrayList<AChapter>();
		ClassStarter cls = new ClassStarter();
		ClassSwapper cs = new ClassSwapper();
		CharacterStarter chars = new CharacterStarter();
		ItemSwapper is = new ItemSwapper();
		ChapterStarter chaps = new ChapterStarter();
		CharSwapper chs = new CharSwapper();
		TextFile tf = new TextFile();
		SkillSwapper skill = new SkillSwapper();
		TextSwapper ts = new TextSwapper();
		XMLBuilder xml = new XMLBuilder();
		StatSwapper statSwapper = new StatSwapper();

		//initializes binfiles static
		BinFiles.InitiateBin(folder);
		StringBuilder staticBin = new StringBuilder();
		staticBin = BinFiles.GetStatic();

		chars.setCharacter(c, staticBin);
		cls.setClasses(cl, staticBin);
		chaps.setChapters(ch);
		if (randomizedOptions.characters || randomizedOptions.xml){
			if (randomizedOptions.xml){
				XMLReader xmlReader = new XMLReader();
				c = xmlReader.ReturnXMLCharList(c, randomizedOptions, xmlFile);
			}
			else{
				CharacterShuffler chash = new CharacterShuffler();
				chash.randomizeCharacters(c);
			}
			//change story bool to fit whichever option is picked
			chs.swapChars(c, staticBin, ch, randomizedOptions.cutscenes);
			DebugBuilder.DebugOutput("Chars Swapped");
		}
		if (randomizedOptions.classes || randomizedOptions.xml){
			if (randomizedOptions.classes) {
				ClassShuffler ran = new ClassShuffler();
				ran.RandomizeClasses(c, cl);
			}
			cs.swapClasses(c, cl, staticBin);
			DebugBuilder.DebugOutput("Classes Swapped");
		}
		else {
			cs.adjustClasses(c, cl, staticBin);
			DebugBuilder.DebugOutput("Classes Adjusted");
		}
		is.swapItems(c, cl, staticBin, ch);
		DebugBuilder.DebugOutput("Items Swapped");
		skill.SwapSkills(c, cl, staticBin, randomizedOptions.xml && !randomizedOptions.classes);
		DebugBuilder.DebugOutput("Skills Swapped");
		ts.SwapTexts(c, randomizedOptions.cutscenes, randomizedOptions.supports);

		statSwapper.SwapStats(staticBin, c, cl, randomizedOptions);
		DebugBuilder.DebugOutput("Stats Swapped");
		if (randomizedOptions.newXml){
			xml.CreateXML(c, randomizedOptions);
			DebugBuilder.DebugOutput("xml created");
		}
		if (randomizedOptions.textFile) {
			tf.makeTextFile(c);
		}
		DebugBuilder.DebugOutput("Starting write");
		//writes all files now that randomization is finished
		BinFiles.WriteAll();
		//BinFiles.ListAllFiles();
		DebugBuilder.DebugOutput("End main");
	}
	
}