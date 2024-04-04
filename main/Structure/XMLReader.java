package main.Structure;

import main.Main;
import main.Output.DebugBuilder;
import main.Program.GUIBools;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class XMLReader {
    public XMLReader(){

    }

    public ArrayList<ACharacter> ReturnXMLCharList(ArrayList<ACharacter> charList, GUIBools randomizationOptions, File xmlFile){
        try{
            File parsedFile = File.createTempFile("temp", ".xml");;
            if (randomizationOptions.reverseRecruitment){
                InputStream inputStream = Main.class.getResourceAsStream("Data/ReverseRecruitment.xml");
                FileOutputStream outputStream = new FileOutputStream(parsedFile);
                IOUtils.copy(inputStream, outputStream);
            }
            else{
                parsedFile = xmlFile;
            }
            //reads xml
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(parsedFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Character");
            //new character list
            ArrayList<ACharacter> newCharList = new ArrayList<>();
            //there's 49 chars so loops through all the chars in the list
            for (int x =0; x < 49; x++){
                //xml stuff
                Node nNode = nList.item(x);
                Element eElement = (Element) nNode;
                //new character and sets all variables found in xml
                ACharacter newChar = new ACharacter();
                newChar.setName(eElement.getAttribute("Name"));
                newChar.setActual(eElement.getAttribute("Actual"));
                newChar.setMale(eElement.getAttribute("Male").equals("true"));
                newChar.setPromoted(eElement.getAttribute("Promoted").equals("true"));
                //skills and classes are more annoying to do cus they come in a weird list string
                newChar.setSkills(ReturnStringArray(eElement.getAttribute("Skills"), 5));
                newChar.setClasses(ReturnStringArray(eElement.getAttribute("Classes"), 3));
                //finds same character in the original char list and sets their values
                ACharacter sameChar = FindSameChar(newChar.getName(), charList);
                newChar.setPid(sameChar.getPid());
                //if the character swapped, for the ppid we actually need the ppid of the swapped character. Exception for Morgans that break the game otherwise
                ACharacter oldChar = FindSameChar(newChar.getActual(), charList);
                newChar.setPpid(oldChar.getPpid());
                newChar.setjName(sameChar.getjName());
                newChar.setFid(sameChar.getFid());
                newChar.setHpid(sameChar.getHpid());
                newChar.setVoice(sameChar.getVoice());
                //removes from list to make search quicker
                //charList.remove(sameChar);
                newCharList.add(newChar);
            }
            //reverse recruitment can have a great number of extra options, so we don't overwrite the bools
            if (!randomizationOptions.reverseRecruitment) {
                //read other bool settings there might be in the list
                NodeList nodeList2 = doc.getElementsByTagName("AppBools");
                Node nude = nodeList2.item(0);
                Element appBools = (Element) nude;
                randomizationOptions.cutscenes = appBools.getAttribute("Cutscenes").equals("true");
                randomizationOptions.supports = appBools.getAttribute("Supports").equals("true");
            }
            return newCharList;
        }
        catch (Exception e){
            DebugBuilder.DebugOutput("xml reading failed");
            return null;
        }
    }

    private ACharacter FindSameChar(String charName, ArrayList<ACharacter> charList){
        for (ACharacter aChar : charList){
            if (charName.equals(aChar.getName())) return aChar;
        }
        return null;
    }

    private String[] ReturnStringArray(String origString, int arrayNum){
        String[] newArray = new String[arrayNum];
        String[] splitString = origString.split(",", arrayNum);
        for (int i = 0; i < arrayNum; i++){
            String newString = splitString[i].substring(1, splitString[i].length());
            newString = newString.replaceAll("]", "");
            newArray[i] = newString;
        }
        return newArray;
    }
}
