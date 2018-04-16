package main.Structure;

import java.util.Arrays;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.AClasses;

public class ClassStarter{
	public ClassStarter(){
		
	}
	
	public void setClasses(ArrayList<AClasses> c){
		try{
		File currentDir = new File(".");
		File inputFile = new File (currentDir, "/main/Data/Classes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Class");
		for (int x =0; x < 76; x++){
			Node nNode = nList.item(x);
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			Element eElement = (Element) nNode;
			AClasses cx = new AClasses();
			cx.setName(eElement.getAttribute("name"));
			cx.setCid(eElement.getAttribute("cid"));
			cx.setBase(eElement.getAttribute("base"));
			cx.setOc(eElement.getAttribute("og"));
			cx.setWa(eElement.getAttribute("wa"));
			cx.setWb(eElement.getAttribute("wb"));
			cx.setWc(eElement.getAttribute("wc"));
			
			//System.out.println(eElement.getAttribute("promoted"));
			if (eElement.getAttribute("gender").equals("m")){
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