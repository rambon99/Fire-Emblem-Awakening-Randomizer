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

import main.Structure.ACharacter;

public class CharacterStarter{
	public CharacterStarter(){
		
	}
	
	public void setCharacter(ArrayList<ACharacter> c, StringBuilder stat){
		try{
			InputStream inputStream = Main.class.getResourceAsStream("Data/Characters.xml");
			File inputFile = File.createTempFile("temp", ".xml");;
			FileOutputStream outputStream = new FileOutputStream(inputFile);
			IOUtils.copy(inputStream, outputStream);
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
			cx.setFid(GetRealFID(eElement.getAttribute("fid"), stat));
			cx.setPid(GetRealPID(cx.getFid(), stat));
			DebugBuilder.DebugOutput(cx.getName() + " loaded with an FID of: " + cx.getFid() + ", and a CID of: " + cx.getPid());
			cx.setjName(eElement.getAttribute("jName"));
			cx.setVoice(eElement.getAttribute("voice"));
			
			String[] tmp = new String[] {eElement.getAttribute("class"), "", ""};
			cx.setClasses(tmp);
			
			cx.setPpid(eElement.getAttribute("parent").equals("") ? "" : ReturnParentPID(eElement.getAttribute("parent"), c, stat));
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
			DebugBuilder.DebugOutput("oops char starter failed");
		}
	}
	
	//finds the real fid of each character by searching it up through static.bin and using pointer math to reverse engineer it
	public String GetRealFID(String fid, StringBuilder stat){
		int index = (stat.indexOf(fid.toLowerCase())/ 2) - 32;
		/*System.out.println("ind: " + index);
		System.out.println("fid: " + fid);
		System.out.println("stat length : " + stat.length());*/
		return String.format("%04x", index);
	}
	
	//finds the real pid of each character by searching their FID in static.bin and finding the pid which is right next to it
	public String GetRealPID(String fid, StringBuilder stat){
		//puts fid in correct pointer form
		String fidPointer = fid.substring(2, 4) + fid.substring(0,2);
		//finds location of fid, then shifts forward 8 slots (4 in hex) to get pid
		int index = stat.indexOf(fidPointer.toLowerCase()) - 8;
		//reverses the pid we find because that's the format in which it is read by this program
		return stat.substring(index + 2, index + 4) + stat.substring(index, index + 2);
	}
	
	//MRobin hexPID: 50 49 44 5F 83 76 83 8C 83 43 83 84 81 5B 92 6A
	//FRobin hexPID: 50 49 44 5F 83 76 83 8C 83 43 83 84 81 5B 8F 97
	public String ReturnParentPID(String parentName, ArrayList<ACharacter> charList, StringBuilder stat){
		//robins are not part of the xml, so they must be handled separately
		if (parentName.equals("RobinF") || parentName.equals("RobinM")){
			String pid = parentName == "RobinM" ? "5049445F8376838C83438384815B926A" : "5049445F8376838C83438384815B8F97";
			int index = (stat.indexOf(pid.toLowerCase())/2) - 32;
			return String.format("%04x", index);
		}
		else{
			for (ACharacter c : charList){
				//System.out.println("checking if " + c.getName() + " is " + parentName);
				if (parentName.equals(c.getName())){
					return c.getPid();
				}
			}
		}
		DebugBuilder.DebugOutput("parent PID failed for "  + parentName + "'s child");
		return "";
	}
}