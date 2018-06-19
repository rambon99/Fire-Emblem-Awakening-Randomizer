package main;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
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
import main.Swapping.ClassSwapper;
import main.Swapping.ItemSwapper;
import main.Structure.AChapter;
import main.Structure.ChapterStarter;
import main.Randomizer.CharacterShuffler;
import main.Swapping.CharSwapper;
import main.Output.TextFile;
import main.Program.GUI;
import main.Program.FileChecker;

public class Main{
	
	public Main(){
		
	}
	
	public void randomizeShit(boolean clb, boolean chb) throws Exception{
		System.out.println(clb);
		System.out.println(chb);
		ArrayList<ACharacter> c = new ArrayList<ACharacter>();
		ArrayList<AClasses> cl = new ArrayList<AClasses>();
		ArrayList<AChapter> ch = new ArrayList<AChapter>();
		ClassStarter cls = new ClassStarter();
		ClassSwapper cs = new ClassSwapper();
		CharacterStarter chars = new CharacterStarter();
		ItemSwapper is = new ItemSwapper();
		BinFiles bin = new BinFiles();
		ChapterStarter chaps = new ChapterStarter();
		CharSwapper chs = new CharSwapper();
		TextFile tf = new TextFile();
		
		StringBuilder statc = new StringBuilder();
		bin.getStatic(statc);
		
		chars.setCharacter(c);
		cls.setClasses(cl);
		chaps.setChapters(ch);
		
		if (chb){
			chs.swapChars(c, statc, ch);
			if (!clb){
				cs.adjustClasses(c, cl, statc);
			}
		}
		System.out.println("Chars Swapped");
		if (clb){
			cs.swapClasses(c, cl, statc);
		}
		System.out.println("Classes Swapped");
		is.swapItems(c, cl, statc, ch);
		System.out.println("Items Swapped");
		
		tf.makeTextFile(c);
		System.out.println("End main");
	}
	
}