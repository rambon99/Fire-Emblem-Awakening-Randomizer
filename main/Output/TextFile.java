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
		PrintWriter writer = new PrintWriter("Swaps.txt", "UTF-8");
		
		for(ACharacter classes : c) {
			writer.println("Character: " + classes.getName());
			writer.println("Old: " + classes.getActual());
			writer.println("Classes: " + Arrays.toString(classes.getClasses()));
			writer.println("");
       }
	   writer.close();
	}
	
}