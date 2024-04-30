package main.Output;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.ACharacter;

public class TextFile{
	
	public TextFile(){
		
	}
	
	public void makeTextFile(ArrayList <ACharacter> c) throws Exception{
		String path = DebugBuilder.GenerateNewFilename("Swaps", ".txt");
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		
		for(ACharacter classes : c) {
			writer.println("Character: " + classes.getName());
			writer.println("Old: " + classes.getActual());
			writer.println("Classes: " + Arrays.toString(classes.getClasses()));
			writer.println("Skills: " + Arrays.toString(classes.getSkills()));
			writer.println("Base stats: " + Arrays.toString(classes.getBaseStats()));
			writer.println("");
       }
	   writer.close();
	}
	
}