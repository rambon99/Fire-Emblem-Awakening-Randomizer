package main.Randomizer;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

import main.Main;
import main.Output.DebugBuilder;
import main.Structure.AClasses;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.Random;

import main.Structure.ACharacter;
import main.Structure.CharacterStarter;

public class ClassShuffler{
	
	public ClassShuffler(){
		
	}
	
	public void RandomizeClasses(ArrayList<ACharacter> charList, ArrayList<AClasses> classList) throws IOException{
		ArrayList<AClasses> baseClasses = new ArrayList<>();
		ArrayList<AClasses> promotedClasses = new ArrayList<>();

		//takes the main list of classes and divides it into bases classes and promoted classes for easy randomization
		for (AClasses aClass : classList){
			if (aClass.isPromoted() | aClass.getName().matches("Conqueror|Manakete|Taguel")){
				promotedClasses.add(aClass);
			}
			if (!aClass.isPromoted() & !aClass.getName().equals("Conqueror")){
				baseClasses.add(aClass);
			}
		}

		//loops through all the characters and gives them all different classes
		for (ACharacter character : charList){
			//Tiki and Walhart have tier 1 classes, but their replacements should be promoted so we promote them
			boolean promotedBool = character.isPromoted() | character.getActual().equals("Tiki") | character.equals("Walhart");
			//array of classes so that a character won't get a promoted class and also a base class separately
			AClasses[] classSet = new AClasses[3];
			//each character has three classes, so we loop/randomize three times
			for (int cl = 0; cl < 3; cl++){
				AClasses newClass;
				//selects a promoted class for character if char is promoted and also if it's the first class, as only the first class can be promoted
				if (cl == 0 & promotedBool){
					newClass = RerollClass(promotedClasses, classSet, character.getClasses()[cl]);
				}//otherwise, just picks a random class from the base classes
				else{
					newClass = RerollClass(baseClasses, classSet, character.getClasses()[cl]);
				}
				classSet[cl] = newClass;
				//children chars only have their base class, so if detects that the char is a child it will only give them one class
				//NOTE: This will give problem with future implementation of cross gen char swapping
				if (!character.getPpid().isEmpty()){
					cl = 3;
				}
			}
			//turns our classes into strings because that's what character saves
			String[] classStrings = new String[3];
			for (int n = 0; n < 3; n++){
				if (classSet[n] == null){
					classStrings[n] = "null";
				}
				else{
					classStrings[n] = classSet[n].getName();
				}
			}
			character.setClasses(classStrings);
			DebugBuilder.DebugOutput("Character " + character.getName() + " given the following classes: ");
			DebugBuilder.DebugOutput(Arrays.toString(character.getClasses()));
		}
	}

	private AClasses RerollClass(ArrayList<AClasses> classList, AClasses[] classSet, String currentClass){
		Random rng = new Random();
		int randInd = rng.nextInt(classList.size());
		while (IsInArray(classList.get(randInd).getName(), classSet) | classList.get(randInd).getName().equals(currentClass)){
			randInd = rng.nextInt(classList.size());
		}
		return classList.get(randInd);
	}

	private boolean IsInArray(String newClass, AClasses[] classSet){
		for (int i = 0; i < 3; i++){
			if (classSet[i] != null) {
				if (newClass.equals(classSet[i].getName()) | newClass.equals(classSet[i].getBase())) return true;
			}
		}
		return false;
	}
}