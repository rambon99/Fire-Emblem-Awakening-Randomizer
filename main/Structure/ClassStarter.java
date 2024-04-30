package main.Structure;

import java.nio.file.Files;
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
			//reads the xml and puts it all into nodes
			InputStream inputStream = Main.class.getResourceAsStream("Data/Classes.xml");
			File inputFile =  File.createTempFile("temp", ".xml");
			FileOutputStream outputStream = new FileOutputStream(inputFile);
			IOUtils.copy(inputStream, outputStream);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Class");
			//gets the classId
			ArrayList<String[]> classIdList = ReturnClassIndexList();
			//gets the growth table from GameData. Thankfully, we don't have to modify anything
			StringBuilder gameData = new StringBuilder();
			BinFiles bin = new BinFiles();
			bin.getGamedata(gameData);
			int gameDataIndex = gameData.indexOf("5989d2d1");
			//gets the whole ass growth table. It's big. 510 is 255 * 2, which comes from it being a hex length of 0xFF with two bytes of legnth
			String growthTable = gameData.substring(gameDataIndex, gameDataIndex + 510);
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
				//finds the correct index for classes
				int ind = 0;
				for (String[] classIndex : classIdList){
					if (cx.getName().equals(classIndex[0]) & eElement.getAttribute("gender").equals(classIndex[1])){
						ind = Integer.parseInt(classIndex[2]);
						DebugBuilder.DebugOutput("Class index of " + ind);
					}
				}
				//gets growths of class
				cx.setClassGrowths(GetClassGrowths(eElement.getAttribute("jid"), ind - 1, gameData, growthTable));
				DebugBuilder.DebugOutput("Growths: " + Arrays.toString(cx.getClassGrowths()));

				c.add(cx);
			}
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("oops class starter");
		}
	}

	private ArrayList<String[]> ReturnClassIndexList() throws IOException {
		//reads text file
		ArrayList<String[]> classIndexList = new ArrayList<>();
		InputStream inputStream = Main.class.getResourceAsStream("Data/Class_list.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		while (reader.ready()){
			String line = reader.readLine();
			String[] namePart = line.split("_");
			String[] genderIndex = namePart[1].split("[*]");
			String[] classIndex = new String[3];
			classIndex[0] = namePart[0];
			classIndex[1] = genderIndex[0].replace(" ","").toLowerCase();
			classIndex[2] = genderIndex[1];
			classIndexList.add(classIndex);
		}
		return classIndexList;
	}
	
	private String GetRealJID(String JID, StringBuilder stat){
		int index = (stat.indexOf(JID.toLowerCase())/ 2) - 32;
		return String.format("%04x", index);
	}

	private int[] GetClassGrowths(String jid, int classId, StringBuilder gameData, String growthTable){
		int[] growths = new int[8];
		//first, we need to find the pointer to the class
		//we divide by 2 because each byte is two letters longs in the code. We also subtract by 32 (0x20) because that's just how it is
		int classPointer = (gameData.indexOf(jid.toLowerCase()) / 2) - 32;
		//we translate the pointer to hex in order to find it
		String hexPointer = "0" + Integer.toHexString(classPointer);
		//we inverse the pointer cus that's just what you do
		String hexPointerInverse = hexPointer.substring(4, 6) + hexPointer.substring(2, 4) + hexPointer.substring(0, 2) + "00";
		//now we find the index of the class so we can find that shit
		//we add 48 because the growths are 48 spaces ahead of the class index
		int growthIndex = gameData.indexOf(hexPointerInverse) + 48;
		//we have the growths so now we just do the calculations
		for (int n = 0; n < 8; n++){
			String hexGrowth = gameData.substring(growthIndex, growthIndex + 2);
			int encryptedGrowth = Integer.parseInt(hexGrowth, 16);
			//Formula for growth calculation in hex
			//INDEX = (ENCIPHERED[N]- (0x23 * ((CLASS_ID ^ 0x46) - 0xF1 * N) ^ 0x78)) & 0xFF;
			//GROWTH_RATE = LOOKUP_TABLE[INDEX];
			//Formula for growth calculation in real numbers
			//(encryptedGrowth - (35 * ((charID ^ 70) - 241 * i) ^ 120)) & 255
			int realGrowthIndex = ((encryptedGrowth - (35 * ((classId ^ 70) - 241 * n) ^ 120)) & 255) * 2;
			int realAssGrowth = Integer.parseInt(growthTable.substring(realGrowthIndex, realGrowthIndex + 2), 16);
			growths[n] = realAssGrowth;
			growthIndex += 2;
		}
		return growths;
	}
}