package main.Randomizer;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.Random;

import main.Structure.ACharacter;
import main.Structure.CharacterStarter;

public class ClassShuffler{
	
	public ClassShuffler(){
		
	}
	
	public void RandomizeClasses(ArrayList<ACharacter> c) throws IOException{
		try{
		File currentDir = new File(".");
		File inputFile = new File (currentDir, "/main/Data/Classes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc =  null;
		try {
        dBuilder = dbFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException ep) {
			System.out.println("dbuilder error in cshuffler");
		}
		try {
        doc = dBuilder.parse(inputFile);}
		catch (SAXException es) {
			System.out.println("");
		}
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Class");
		for (int x = 0; x < 49; x++)
		{
		 String [] tmp = new String[3];
		 boolean pr = c.get(x).isPromoted();
		 for (int y=0; y<3; y++){
			 Random rn = new Random();
			 int rng = rn.nextInt(75);
			 Node nNode = nList.item(rng);
			 Element eElement = (Element) nNode;
			//System.out.println("Student roll no : " + eElement.getAttribute("name"));
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			//System.out.println(eElement.getAttribute("promoted"));
			//System.out.println(pr);
			if (pr && y==0){
				while (eElement.getAttribute("promoted").equals("0")){
				//System.out.println(eElement.getAttribute("promoted"));
				//System.out.println(pr);
				rn = new Random();
				rng = rn.nextInt(75);
				nNode = nList.item(rng);
				eElement = (Element) nNode;
				}
			}
			else {
				while (eElement.getAttribute("promoted").equals("1")){
				//System.out.println(eElement.getAttribute("promoted"));
				//System.out.println(pr);
				rn = new Random();
				rng = rn.nextInt(75);
				nNode = nList.item(rng);
				eElement = (Element) nNode;
				}
			}
			 tmp[y] = eElement.getAttribute("name");
			 if (!c.get(x).getPpid().equals("")){
				 y = 3;
		 }
		 }
		 c.get(x).setClasses(tmp);
		}}
		catch (IOException e){
			System.out.println("In main IOException");
		}
		
//		for(ACharacter classes : c) {
//			System.out.println(classes.getName());
//			System.out.println(Arrays.toString(classes.getClasses()));
 //       }
	}
}