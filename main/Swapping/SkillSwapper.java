package main.Swapping;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

import main.Main;
import main.Output.DebugBuilder;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.BinFiles;
import main.Structure.ACharacter;
import main.Structure.AClasses;

public class SkillSwapper{
	
	public SkillSwapper(){
		
	}
	
	public void SwapSkills(ArrayList <ACharacter> c, ArrayList <AClasses> cl, StringBuilder str, boolean setSkills){
		try{
		BinFiles bin = new BinFiles();
		//reads the skill xml file
			InputStream inputStream = Main.class.getResourceAsStream("Data/Skills.xml");
			File inputFile =  File.createTempFile("temp", ".xml");
			FileOutputStream outputStream = new FileOutputStream(inputFile);
			IOUtils.copy(inputStream, outputStream);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Skill");
		//compiles the xml of skills into a map
		Map<String, String> skillMap = new HashMap<String, String>();
		for (int x =0; x < 88; x++){
			Node nNode = nList.item(x);
			Element eElement = (Element) nNode;
			skillMap.put(eElement.getAttribute("name"), eElement.getAttribute("id"));
			DebugBuilder.DebugOutput(eElement.getAttribute("name") + " skill has been set with an ID of: " + skillMap.get(eElement.getAttribute("name")));
		}

		//temporary string builder so that the thing won't get confused
			StringBuilder tempStr = new StringBuilder(str);
		//loops through the character list to change the skills of every character
		for (int i = 0; i < c.size(); i++){
			//Finds character's fid
			String fid = c.get(i).getFid();
			//reverses bytes of fid to find it in static
			String fidPointer = "0000" + fid.substring(2,4)+fid.substring(0,2) + "0000";
			DebugBuilder.DebugOutput("FidPointer: " + fidPointer);
			//the distance between FID and the skill list is 120, so we find FID and add 120
			int skillListIndex = tempStr.indexOf(fidPointer.toLowerCase()) + 120 + 4;
			DebugBuilder.DebugOutput("skill index: " + skillListIndex);
			//skills is the string of bytes containing the skills
			StringBuilder skills = new StringBuilder();
			if (setSkills){
				skills = SetSkillSwap(c.get(i), skillMap);
			}
			else{
				skills = ClassBasedSwap(c.get(i), tempStr, cl, skillMap, skillListIndex);
			}
			//length of skill array is 20
			//strings must be of length 20 because that is the length of the array. We take the current string and append 00s until it reaches 20, that way it overwrites old skills
			int stringLength = 20 - skills.length();
			if (stringLength > 0){
				String format = "%0" + stringLength + "d";
				String stringFill = String.format(format, 0);
				skills.append(stringFill);
			}
			DebugBuilder.DebugOutput("full list string: " + skills);
			//overwrites the current string
			tempStr.replace(skillListIndex, skillListIndex + 20, String.format("%020d", 0));
			str.replace(skillListIndex, skillListIndex + 20, skills.toString());
		}
		//actually writes string into file
		bin.writeStatic(str);
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("skills exploded");
		}
	}
	

	
	public void RandomLevelSwap(){
		
	}

	private StringBuilder SetSkillSwap(ACharacter character, Map<String, String> skillMap){
		StringBuilder skills = new StringBuilder();
		for (String skill : character.getSkills()){
			if (!skill.equals("null") && !skill.isEmpty()){
				skills.append(skillMap.get(skill) + "00");
			}
		}
		return skills;
	}

	//adjusts the skills based on the class of the character
	private StringBuilder ClassBasedSwap(ACharacter c, StringBuilder str, ArrayList<AClasses> cl, Map<String, String> skillMap, int skillListIndex){
		StringBuilder skills = new StringBuilder();
		//the level index is 6 before the skillList index
		int levelIndex = skillListIndex - 6;
		DebugBuilder.DebugOutput("level index: " + levelIndex);
		DebugBuilder.DebugOutput("level in hex: " + str.substring(levelIndex, levelIndex + 2));
		int level = Integer.parseInt(str.substring(levelIndex, levelIndex + 2), 16);
		DebugBuilder.DebugOutput(c.getName() + " at level " + level + " in the " + c.getClasses()[0] +" class has been given the following skills: ");
		DebugBuilder.DebugOutput("level in hex: " + str.substring(levelIndex, levelIndex + 2));
		DebugBuilder.DebugOutput("Skillset: " + str.substring(skillListIndex, skillListIndex + 20));

		//finds the current class of the character through a for loops
		AClasses currentClass = new AClasses();
		for (AClasses aclass : cl){
			if (aclass.getName().equals(c.getClasses()[0])){
				currentClass = aclass;
				break;
			}
		}
		//skill functions different depending on whether char is promoted
		if (currentClass.isPromoted()){
			skills.append(PromotedSkillSwap(currentClass, level, cl, skillMap, c));
		}
		else{
			skills.append(RegularSkillSwap(currentClass, level, skillMap, c));
		}
		return skills;
	}
	
	//Function that changes skills for base classes
	public StringBuilder RegularSkillSwap(AClasses currentClass, int level, Map<String, String> skillMap, ACharacter character){
		StringBuilder skillList = new StringBuilder();
		//creates a skill list to add to the character
		String [] charSkills = new String[5];
		//skills have 00 bytes  at the end
		skillList.append(skillMap.get(currentClass.getFSkill()) + "00");
		charSkills[0] = currentClass.getFSkill();
		DebugBuilder.DebugOutput(skillMap.get(currentClass.getFSkill()) + " : " + currentClass.getFSkill() + ", ");
		if (level >= 10){
			skillList.append(skillMap.get(currentClass.getSSkill()) + "00");
			DebugBuilder.DebugOutput(skillMap.get(currentClass.getSSkill()) + " : " + currentClass.getSSkill() + ". ");
			charSkills[1] = currentClass.getSSkill();
		}
		character.setSkills(charSkills);
		return skillList;
	}
	
	//funcition that changes skills for promoted classes
	public StringBuilder PromotedSkillSwap(AClasses currentClass, int level, ArrayList <AClasses> cl, Map<String, String> skillMap, ACharacter character){
		AClasses baseClass = new AClasses();
		StringBuilder skillList = new StringBuilder();
		//I implemented this ins a STUPID way so I have to do this shit twice
		for (AClasses aclass : cl){
			if (aclass.getName().equals(currentClass.getBase())){
				baseClass = aclass;
				break;
			}
		}
		//creates a skill list to add to the character
		String [] charSkills = new String[5];
		//adds two base skills cus they will always be on there
		skillList.append(skillMap.get(baseClass.getFSkill()) + "00");
		skillList.append(skillMap.get(baseClass.getSSkill()) + "00");
		DebugBuilder.DebugOutput(skillMap.get(baseClass.getFSkill()) + " : " + baseClass.getFSkill() + ", ");
		DebugBuilder.DebugOutput(skillMap.get(baseClass.getSSkill()) + " : " + baseClass.getSSkill() + ", ");
		charSkills[0] = baseClass.getFSkill();
		charSkills[1] = baseClass.getSSkill();
		
		if (level >= 5){
			skillList.append(skillMap.get(currentClass.getFSkill()) + "00");
			DebugBuilder.DebugOutput(skillMap.get(currentClass.getFSkill()) + " : " + currentClass.getFSkill() + ", ");
			charSkills[2] = currentClass.getFSkill();
			if (level >= 15){
				skillList.append(skillMap.get(currentClass.getSSkill()) + "00");
				DebugBuilder.DebugOutput(skillMap.get(currentClass.getSSkill()) + " : " + currentClass.getSSkill() + ". ");
				charSkills[3] = currentClass.getSSkill();
			}
		}
		character.setSkills(charSkills);
		return skillList;
	}
	
	public String SkillSwapFunction(boolean isPromoted, int level){
		return "";
	}
}
	