package main.Structure;

import java.util.Arrays;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

import main.Main;
import main.Output.DebugBuilder;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.AClasses;

public class ClassStarter{
	public ClassStarter(){
		
	}
	
	public void setClasses(ArrayList<AClasses> c, StringBuilder staticbin){
		try{
			InputStream inputStream = Main.class.getResourceAsStream("Data/Classes.xml");
			File inputFile =  File.createTempFile("temp", ".xml");;
			FileOutputStream outputStream = new FileOutputStream(inputFile);
			IOUtils.copy(inputStream, outputStream);
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
			
			cx.setCid(GetRealJID(eElement.getAttribute("jid"), staticbin));
			DebugBuilder.DebugOutput(cx.getName() + " class has a JID of " + cx.getCid());
			
			cx.setBase(eElement.getAttribute("base"));
			cx.setOc(eElement.getAttribute("og"));
			cx.setWa(eElement.getAttribute("wa"));
			cx.setWb(eElement.getAttribute("wb"));
			cx.setWc(eElement.getAttribute("wc"));
			cx.setFSkill(eElement.getAttribute("firstSkill"));
			cx.setSSkill(eElement.getAttribute("secondSkill"));
			
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
			DebugBuilder.DebugOutput("oops class starter");
		}
	}
	
	private String GetRealJID(String JID, StringBuilder stat){
		int index = (stat.indexOf(JID.toLowerCase())/ 2) - 32;
		return String.format("%04x", index);
	}
}