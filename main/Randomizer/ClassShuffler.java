package main.Randomizer;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;

import main.Main;
import main.Output.DebugBuilder;
import org.apache.commons.io.IOUtils;
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
			InputStream inputStream = Main.class.getResourceAsStream("Data/Classes.xml");
			File inputFile =  File.createTempFile("temp", ".xml");;
			FileOutputStream outputStream = new FileOutputStream(inputFile);
			IOUtils.copy(inputStream, outputStream);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc =  null;
		try {
        dBuilder = dbFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException ep) {
			DebugBuilder.DebugOutput("dbuilder error in class shuffler");
		}
		try {
        doc = dBuilder.parse(inputFile);}
		catch (SAXException es) {
			DebugBuilder.DebugOutput("dbuilder error in cshuffler2");
		}
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Class");
		for (int x = 0; x < 49; x++)
		{
		 String [] tmp = new String[3];
		 //exception for Tiki and  Walhart, who have different classes //might break walhart's level but who cares lol
		 boolean promoException = c.get(x).getActual().equals("Tiki") || c.get(x).getActual().equals("Walhart");
		 boolean pr = c.get(x).isPromoted() || promoException;
		 for (int y=0; y<3; y++){
			 Random rn = new Random();
			 int rng = rn.nextInt(75);
			 Node nNode = nList.item(rng);
			 Element eElement = (Element) nNode;
			//System.out.println("Student roll no : " + eElement.getAttribute("name"));
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			//System.out.println(eElement.getAttribute("promoted"));
			//System.out.println(pr);
			 //this function sets the base promoted class for promoted units
			if (pr && y==0){
				//exceptions that allows promoted units to be manaketes, conquerors and taguels
				boolean singleStageException = !eElement.getAttribute("name").equals("Conqueror") & !eElement.getAttribute("name").equals("Manakete") & !eElement.getAttribute("name").equals("Taguel");
				while (eElement.getAttribute("promoted").equals("0") & singleStageException){
				//System.out.println(eElement.getAttribute("promoted"));
				//System.out.println(pr);
				rn = new Random();
				rng = rn.nextInt(75);
				nNode = nList.item(rng);
				eElement = (Element) nNode;
				}
			}
			else {
				//exception for non promoted units to not become Conquerors
				//conqException will be true when: unit is NOT promoted, when the class is set to base and the class is conqueror. When conq esception is true, we keep looping to find a new class
				boolean conqException = !pr && y == 0 && eElement.getAttribute("name").equals("Conqueror");
				while (eElement.getAttribute("promoted").equals("1") || conqException){
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
			}
			//for(ACharacter classes : c) {
			//System.out.println(classes.getName());
			//System.out.println(classes.isPromoted());
			//System.out.println(Arrays.toString(classes.getClasses()));
        //}
		}
		catch (IOException e){
			DebugBuilder.DebugOutput("In main IOException");
		}
		
//		for(ACharacter classes : c) {
//			System.out.println(classes.getName());
//			System.out.println(Arrays.toString(classes.getClasses()));
 //       }
	}
}