package main.Structure;

import java.util.Arrays;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.ACharacter;

public class CharacterStarter{
	public CharacterStarter(){
		
	}
	
	public void setCharacter(ArrayList<ACharacter> c){
		try{
		File currentDir = new File(".");
		File inputFile = new File (currentDir, "/main/Data/Characters.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Character");
		for (int x =0; x < 49; x++){
			Node nNode = nList.item(x);
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			Element eElement = (Element) nNode;
			ACharacter cx = new ACharacter();
			cx.setName(eElement.getAttribute("name"));
			cx.setActual(eElement.getAttribute("name"));
			cx.setPid(eElement.getAttribute("cid"));
			cx.setFid(eElement.getAttribute("fid"));
			
			String[] tmp = new String[] {eElement.getAttribute("class"), "", ""};
			cx.setClasses(tmp);
			
			cx.setPpid(eElement.getAttribute("parent"));
			cx.setHpid(eElement.getAttribute("hpid"));
			//System.out.println(eElement.getAttribute("male"));
			if (eElement.getAttribute("gender").equals("male")){
				cx.setMale(true);
			}
			else {
				cx.setMale(false);
			}
			if (eElement.getAttribute("promoted").equals("1")){
				cx.setPromoted(true);
			}
			else {
				cx.setPromoted(false);
			}
			c.add(cx);
		}
		}
		catch (Exception e){
			System.out.println("oops");
		}
	}
}