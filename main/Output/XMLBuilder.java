package main.Output;

import main.Program.GUIBools;
import main.Structure.ACharacter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class XMLBuilder {

    public XMLBuilder(){

    }

    public void CreateXML(ArrayList<ACharacter> charList, GUIBools bools){
        Document dom;
        Element e = null;

        DebugBuilder.DebugOutput("initiate xml builder");
        //instance of document builder in order to creat doc
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            //instatantiates new doc
            dom = db.newDocument();

            //other important bools
            Element boolElement = dom.createElement("AppBools");
            boolElement.setAttribute("Cutscenes", String.valueOf(bools.cutscenes));
            boolElement.setAttribute("Supports", String.valueOf(bools.supports));

            //root element
            Element rootElement = dom.createElement("Characters");
            //all of the important elements per char
            for (ACharacter character : charList){
                Element newChar = dom.createElement("Character");
                newChar.setAttribute("Name", character.getName());
                newChar.setAttribute("Actual", character.getActual());
                newChar.setAttribute("Promoted", String.valueOf(character.isPromoted()));
                newChar.setAttribute("Male", String.valueOf(character.isMale()));
                newChar.setAttribute("Classes", Arrays.toString(character.getClasses()));
                newChar.setAttribute("Skills", Arrays.toString(character.getSkills()));
                rootElement.appendChild(newChar);
            }
            DebugBuilder.DebugOutput("CharacterList imprinted onto xml");
            rootElement.appendChild(boolElement);
            dom.appendChild(rootElement);
            //looped through all characters, so now we go to actually printing
            try {
                Transformer tf = TransformerFactory.newInstance().newTransformer();
                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                tf.setOutputProperty(OutputKeys.METHOD, "xml");
                tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tf.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "FileRandomization.dtd");
                String filename = DebugBuilder.GenerateNewFilename("RandomizationFile", ".xml");
                //seend the file to be created
                tf.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filename)));
            }
            catch(Exception exception){
                DebugBuilder.DebugOutput("XML Builder exploded");
            }
        }
        catch (ParserConfigurationException pce){
            DebugBuilder.DebugOutput("XML Builder exploded befored reading list");
        }
    }
}
