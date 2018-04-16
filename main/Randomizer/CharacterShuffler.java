package main.Randomizer;

import java.util.Random;
import java.io.*;
import java.util.*;
import java.util.Collections;
import java.util.Arrays;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.ACharacter;

public class CharacterShuffler{
	
	public CharacterShuffler(){
		
	}
	
	public void randomizeCharacters(ArrayList <ACharacter> c){
		ArrayList <ACharacter> adults = new ArrayList <ACharacter>();
		ArrayList <ACharacter> children = new ArrayList <ACharacter>();
		ArrayList <ACharacter> updated = new ArrayList <ACharacter>();
		
		for (int x = 0; x < 49; x++){
			if (c.get(x).getPpid().equals("")){
				adults.add(c.get(x));
			}
			else {
				children.add(c.get(x));
			}
		}
		
		
		updated.addAll(c);
		c.clear();
		Collections.shuffle(adults);
		Collections.shuffle(children);
		c.addAll(adults);
		c.addAll(children);
		
		//updated.addAll(children);
		
		for (int y = 0; y < 49; y++){
			c.get(y).setActual(updated.get(y).getName());
			c.get(y).setPromoted(updated.get(y).isPromoted());
		}
		
		
		
	}
	
}