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
		BinFiles bin = new BinFiles();
		bin.SetFolder(folder);
		ChapterStarter chaps = new ChapterStarter();
		CharSwapper chs = new CharSwapper();
		TextFile tf = new TextFile();
		SkillSwapper skill = new SkillSwapper();
		TextSwapper ts = new TextSwapper();
		XMLBuilder xml = new XMLBuilder();
		
		StringBuilder statc = new StringBuilder();
		bin.getStatic(statc);
		
		chars.setCharacter(c, statc);
		cls.setClasses(cl, statc);
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
			chs.swapChars(c, statc, ch, randomizedOptions.cutscenes);
			DebugBuilder.DebugOutput("Chars Swapped");
		}
		if (randomizedOptions.classes || randomizedOptions.xml){
			if (randomizedOptions.classes) {
				ClassShuffler ran = new ClassShuffler();
				ran.RandomizeClasses(c);
			}
			cs.swapClasses(c, cl, statc);
			DebugBuilder.DebugOutput("Classes Swapped");
		}
		else {
			cs.adjustClasses(c, cl, statc);
			DebugBuilder.DebugOutput("Classes Adjusted");
		}
		is.swapItems(c, cl, statc, ch);
		DebugBuilder.DebugOutput("Items Swapped");
		skill.SwapSkills(c, cl, statc, randomizedOptions.xml && !randomizedOptions.classes);
		DebugBuilder.DebugOutput("Skills Swapped");
		ts.SwapTexts(c, randomizedOptions.cutscenes, randomizedOptions.supports);
		DebugBuilder.DebugOutput("texts swapped");

		if (randomizedOptions.newXml){
			xml.CreateXML(c, randomizedOptions);
			DebugBuilder.DebugOutput("xml created");
		}
		if (randomizedOptions.textFile) {
			tf.makeTextFile(c);
		}
		DebugBuilder.DebugOutput("End main");
	}
	
}