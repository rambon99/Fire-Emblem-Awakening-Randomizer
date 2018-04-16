package main.Structure;

import java.util.Arrays;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.AChapter;

public class ChapterStarter{
	public ChapterStarter(){
		
	}
	
	public void setChapters(ArrayList<AChapter> c){
		try{
			File currentDir = new File(".");
			File inputFile = new File (currentDir, "/main/Data/Chapters.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Chapter");
			for (int x = 0; x < 35; x ++){
				Node nNode = nList.item(x);
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				Element eElement = (Element) nNode;
				AChapter cx = new AChapter();
				cx.setName(eElement.getAttribute("name"));
				cx.setC1(eElement.getAttribute("c1"));
				cx.setC2(eElement.getAttribute("c2"));
				cx.setC3(eElement.getAttribute("c3"));
				cx.setC3i(eElement.getAttribute("c3i"));
				String[] tmp1;
				String[] tmp2;
				if (x == 8 || x == 11 || x == 14){
					tmp1 = new String[] {eElement.getAttribute("c1i"), eElement.getAttribute("c1i2"), eElement.getAttribute("c1i3")};
					tmp2 = new String[] {eElement.getAttribute("c2i"), eElement.getAttribute("c2i2")};
				}
				else{
					tmp1 = new String[] {eElement.getAttribute("c1i"), eElement.getAttribute("c1i2"), ""};
					tmp2 = new String[] {eElement.getAttribute("c2i"), ""};
				}
				cx.setC1i(tmp1);
				cx.setC2i(tmp2);
				cx.setCb1(Integer.parseInt(eElement.getAttribute("cb1")));
				cx.setCb2(Integer.parseInt(eElement.getAttribute("cb2")));
				cx.setCb3(Integer.parseInt(eElement.getAttribute("cb3")));
				c.add(cx);
			}
			}
		catch (Exception e){
			System.out.println("Chapter fucked up");
		}
	}
}