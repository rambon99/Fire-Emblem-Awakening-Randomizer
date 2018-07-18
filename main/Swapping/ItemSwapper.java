package main.Swapping;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.BinFiles;
import main.Structure.ACharacter;
import main.Structure.AClasses;
import main.Structure.AChapter;
import main.Structure.ChapterStarter;

public class ItemSwapper{
	
	public ItemSwapper(){
		
	}
	
	public void swapItems(ArrayList <ACharacter> c, ArrayList <AClasses> cl, StringBuilder stat, ArrayList<AChapter> ch) throws Exception{
		BinFiles bin = new BinFiles();
		
		File currentDir = new File(".");
		File inputFile = new File (currentDir, "/main/Data/Item.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Item");
		
		ItemSwapper is = new ItemSwapper();
		
		for (int x = 0; x < 36; x++){ //36
			String[] c1i = ch.get(x).getC1i();  ///CRASHES ON X18
			String[] c2i = ch.get(x).getC2i();
			String c3i = ch.get(x).getC3i();
			
			StringBuilder chap = new StringBuilder();
			String cname = ch.get(x).getName();
			int size = 0;
			size = bin.getDispos(chap, cname, size);
			
			int ind3 = 0;
			int ind2 = 0;
			int ind1 = 0;
			
			int y = 0;
			int y2 = 0;
			
			
			
			if (!ch.get(x).getC1().equals("")){                         ///WEAPON RANKS C1
				while (!c.get(y).getActual().equals(ch.get(x).getC1())){
						y = y + 1;
				}
				String[] tmpc = c.get(y).getClasses();
				while (!tmpc[0].equals(cl.get(y2).getName())){
					y2 = y2 + 1;
				}
				String fid = c.get(y).getFid();
				///System.out.println(fid);
				String nfid = fid.substring(2,4)+fid.substring(0,2);
				ind1 = stat.lastIndexOf(("00" + nfid.toLowerCase() + "0000")) + 2;
				stat.replace(ind1 + 100, ind1 + 112, "000000000000");
				if (c.get(y).isPromoted()){
					if (cl.get(y2).getWa().equals("Sword") || cl.get(y2).getWb().equals("Sword")){
						stat.replace(ind1 + 100, ind1 + 102, "24");
					}
					if (cl.get(y2).getWa().equals("Lance") || cl.get(y2).getWb().equals("Lance")){
						stat.replace(ind1 + 102, ind1 + 104, "24");
					}
					if (cl.get(y2).getWa().equals("Axe") || cl.get(y2).getWb().equals("Axe") || cl.get(y2).getWc().equals("Axe")){
						stat.replace(ind1 + 104, ind1 + 106, "24");
					}
					if (cl.get(y2).getWa().equals("Bow") || cl.get(y2).getWb().equals("Bow")){
						stat.replace(ind1 + 106, ind1 + 108, "24");
					}
					if (cl.get(y2).getWa().equals("Tome") || cl.get(y2).getWb().equals("Tome")){
						stat.replace(ind1 + 108, ind1 + 110, "24");
					}
					if (cl.get(y2).getWa().equals("Staff") || cl.get(y2).getWb().equals("Staff")){
						stat.replace(ind1 + 110, ind1 + 112, "24");
					}
				}
			}
			
			int y3 = 0;
			int y4 = 0;
			if (!ch.get(x).getC2().equals("")){                         ///WEAPON RANKS C2
				while (!c.get(y3).getActual().equals(ch.get(x).getC2())){
						y3 = y3 + 1;
				}
				String[] tmpc = c.get(y3).getClasses();
				while (!tmpc[0].equals(cl.get(y4).getName())){
					y4 = y4 + 1;
				}
				String fid = c.get(y3).getFid();
				///System.out.println(fid);
				String nfid = fid.substring(2,4)+fid.substring(0,2);
				ind2 = stat.lastIndexOf(("00" + nfid.toLowerCase() + "0000")) + 2;
				stat.replace(ind2 + 100, ind2 + 112, "000000000000");
				if (c.get(y3).isPromoted()){
					if (cl.get(y4).getWa().equals("Sword") || cl.get(y4).getWb().equals("Sword")){
						stat.replace(ind2 + 100, ind2 + 102, "24");
					}
					if (cl.get(y4).getWa().equals("Lance") || cl.get(y4).getWb().equals("Lance")){
						stat.replace(ind2 + 102, ind2 + 104, "24");
					}
					if (cl.get(y4).getWa().equals("Axe") || cl.get(y4).getWb().equals("Axe") || cl.get(y4).getWc().equals("Axe")){
						stat.replace(ind2 + 104, ind2 + 106, "24");
					}
					if (cl.get(y4).getWa().equals("Bow") || cl.get(y4).getWb().equals("Bow")){
						stat.replace(ind2 + 106, ind2 + 108, "24");
					}
					if (cl.get(y4).getWa().equals("Tome") || cl.get(y4).getWb().equals("Tome")){
						stat.replace(ind2 + 108, ind2 + 110, "24");
					}
					if (cl.get(y4).getWa().equals("Staff") || cl.get(y4).getWb().equals("Staff")){
						stat.replace(ind2 + 110, ind2 + 112, "24");
					}
				}
			}
			
			int y5 = 0;
			int y6 = 0;
			if (!ch.get(x).getC3().equals("")){                         ///WEAPON RANKS C3
				while (!c.get(y5).getActual().equals(ch.get(x).getC3())){
						y5 = y5 + 1;
				}
				String[] tmpc = c.get(y5).getClasses();
				while (!tmpc[0].equals(cl.get(y6).getName())){
					y6 = y6 + 1;
				}
				String fid = c.get(y5).getFid();
				///System.out.println(fid);
				String nfid = fid.substring(2,4)+fid.substring(0,2);
				ind3 = stat.lastIndexOf(("00" + nfid.toLowerCase() + "0000")) + 2;
				stat.replace(ind3 + 100, ind3 + 112, "000000000000");
				if (c.get(y5).isPromoted()){
					if (cl.get(y6).getWa().equals("Sword") || cl.get(y6).getWb().equals("Sword")){
						stat.replace(ind3 + 100, ind3 + 102, "24");
					}
					if (cl.get(y6).getWa().equals("Lance") || cl.get(y6).getWb().equals("Lance")){
						stat.replace(ind3 + 102, ind3 + 104, "24");
					}
					if (cl.get(y6).getWa().equals("Axe") || cl.get(y6).getWb().equals("Axe") || cl.get(y6).getWc().equals("Axe")){
						stat.replace(ind3 + 104, ind3 + 106, "24");
					}
					if (cl.get(y6).getWa().equals("Bow") || cl.get(y6).getWb().equals("Bow")){
						stat.replace(ind3 + 106, ind3 + 108, "24");
					}
					if (cl.get(y6).getWa().equals("Tome") || cl.get(y6).getWb().equals("Tome")){
						stat.replace(ind3 + 108, ind3 + 110, "24");
					}
					if (cl.get(y6).getWa().equals("Staff") || cl.get(y6).getWb().equals("Staff")){
						stat.replace(ind3 + 110, ind3 + 112, "24");
					}
				}
			}
			
			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			
			System.out.println(cname);
			//System.out.println(size);
			
			if(!c1i[0].equals("") || !c1i[1].equals("")){  //////FIRST INVENTORY
				//System.out.println("ok"); 
				int block1 = ch.get(x).getCb1();
				String p = new String();
				String pa = new String();
				
				int swr1 = 0;
				int lr1 = 0;
				int ar1 = 0;
				int br1 = 0;
				int tr1 = 0;
				int str1 = 0;
				if (c.get(y).isPromoted()){
					if (cl.get(y2).getWa().equals("Sword") || cl.get(y6).getWb().equals("Sword")){
						swr1 = 16;
					}
					if (cl.get(y2).getWa().equals("Lance") || cl.get(y6).getWb().equals("Lance")){
						lr1 = 16;
					}
					if (cl.get(y2).getWa().equals("Axe") || cl.get(y6).getWb().equals("Axe") || cl.get(y2).getWc().equals("Axe")){
						ar1 = 16;
					}
					if (cl.get(y2).getWa().equals("Bow") || cl.get(y6).getWb().equals("Bow")){
						br1 = 16;
					}
					if (cl.get(y2).getWa().equals("Tome") || cl.get(y6).getWb().equals("Tome")){
						tr1 = 16;
					}
					if (cl.get(y2).getWa().equals("Staff") || cl.get(y6).getWb().equals("Staff")){
						str1 = 16;
					}
				}
				
				if (c.get(y).getActual().equals("Cordelia")){
					if (!cl.get(y2).getName().equals("Pegasus Knight") || !cl.get(y2).getName().equals("Wyvern Rider")){
						chap.replace(12832, 12840, "010D010D");
					}
				}
				//if (c.get(y).getActual().equals("Henry")){
				//	chap.replace(10056, 10064, "09130913"); //0913
				//}
				
				//System.out.println(c.get(y).getName());
				//System.out.println(c.get(y).isPromoted());
				int z = 0;
				if(!c1i[0].equals("")){               ///FIRST INVENTORY ITEM 1
					nNode = nList.item(z);
					eElement = (Element) nNode;
					String w1 = new String();
					if (cl.get(y2).getWa().equals("BStone")){
						if (c1i[0].equals("Silver") || c1i[0].equals("Legend")){
							nNode = nList.item(73);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(72);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
					}
					else if (cl.get(y2).getWa().equals("DStone")){
						if (c1i[0].equals("Silver") || c1i[0].equals("Legend")){
							nNode = nList.item(71);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(70);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
					}
					else{
						if (c1i[0].equals("BStone") || c1i[0].equals("DStone")){
							while (!eElement.getAttribute("itype").equals("Steel") || !eElement.getAttribute("wtype").equals(cl.get(y2).getWa())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
							}
						
						}
						else if (c1i[0].equals("DStone+")){
							while (!eElement.getAttribute("itype").equals("Silver") || !eElement.getAttribute("wtype").equals(cl.get(y2).getWa())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
							}
						
						}
						else{
						//System.out.println(c1i[0]);
						while (!eElement.getAttribute("itype").equals(c1i[0]) || !eElement.getAttribute("wtype").equals(cl.get(y2).getWa())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}}
						w1 = eElement.getAttribute("hiid");
					}
					if (cl.get(y2).getWa().equals("Sword")){
						swr1 = is.setWeaponRanks(eElement, swr1);
						//System.out.println("Sw");
					}
					if (cl.get(y2).getWa().equals("Lance")){
						lr1 = is.setWeaponRanks(eElement, lr1);
						//System.out.println("l");
					}
					if (cl.get(y2).getWa().equals("Axe")){
						ar1 = is.setWeaponRanks(eElement, ar1);
						//System.out.println("a");
					}
					if (cl.get(y2).getWa().equals("Bow")){
						br1 = is.setWeaponRanks(eElement, br1);
						//System.out.println("b");
					}
					if (cl.get(y2).getWa().equals("Tome") || cl.get(y2).getWa().equals("Dark")){
						tr1 = is.setWeaponRanks(eElement, tr1);
						//System.out.println("t");
					}
					if (cl.get(y2).getWa().equals("Staff")){
						str1 = is.setWeaponRanks(eElement, str1);
						//System.out.println("st");
					}
					//System.out.println(eElement.getAttribute("name"));
					chap.append(w1);
					p = "0" + Integer.toHexString(size - 32);
					//System.out.println(p);
					pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
					chap.replace(block1 + 56 , block1 + 60, pa);
					size = size + (w1.length()/2);
					
					if (c.get(y).getActual().equals("Tiki")){  ///ADD TIKI'S ITEM
						StringBuilder script = new StringBuilder();
						int ssize = 0;
						ssize = bin.getScript(script, "X017.cmb", ssize);
						
						String table = script.substring(66, 68) + script.substring(64, 66);
						int enc = Integer.parseInt(table, 16);
						
						String ad11t = "000" + Integer.toHexString(ssize - enc);
						String ad11 = ad11t.substring(ad11t.length()-4, ad11t.length());
						String ad12 = ad11.substring(ad11.length() - 2, ad11.length()) + ad11.substring(ad11.length() - 4, ad11.length() - 2);
						
						int tind = script.indexOf("4949445f905e97b390ce00")/2;
						
						String ad21t = "000" + Integer.toHexString(tind - enc);
						String ad21 = ad21t.substring(ad21t.length()-4, ad21t.length());
						String ad22 = ad21.substring(ad21.length() - 2, ad21.length()) + ad21.substring(ad21.length() - 4, ad21.length() - 2);
						int sind1 = script.indexOf("d1" + ad21.toLowerCase()) + 2;
						int sind2 = script.indexOf(ad22.toLowerCase()+"0000");
						
						while (sind1 != -1 || sind2 != -1){
							if (sind1 != -1){
								script.replace(sind1, sind1 + 4, ad11);
								sind1 = script.indexOf(ad21.toLowerCase());
				
							}
							if (sind2 != -1){
								script.replace(sind2, sind2 + 4, ad12);
								sind2 = script.indexOf(ad22.toLowerCase());
				
							}
						}
						script.append(w1);
						ssize = ssize + (w1.length()/2);
						bin.writeScript(script, "X017.cmb");

					}
					
					z = 0;
				}
				if(!c1i[1].equals("")){      ////FIRST IVENTORY ITEM 2
					String w2 = new String();
					if (!cl.get(y2).getWb().equals("")){ 
					nNode = nList.item(z);
					eElement = (Element) nNode;
					//System.out.println(eElement.getAttribute("name"));
						while (!eElement.getAttribute("itype").equals(c1i[1]) || !eElement.getAttribute("wtype").equals(cl.get(y2).getWb())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w2 = eElement.getAttribute("hiid");
						if (cl.get(y2).getWb().equals("Sword")){
							swr1 = is.setWeaponRanks(eElement, swr1);
							//System.out.println("Sw");
						}
						if (cl.get(y2).getWb().equals("Lance")){
							lr1 = is.setWeaponRanks(eElement, lr1);
							//System.out.println("l");
						}
						if (cl.get(y2).getWb().equals("Axe")){
							ar1 = is.setWeaponRanks(eElement, ar1);
							//System.out.println("a");
						}
						if (cl.get(y2).getWb().equals("Bow")){
							br1 = is.setWeaponRanks(eElement, br1);
							//System.out.println("b");
						}
						if (cl.get(y2).getWb().equals("Tome") || cl.get(y2).getWb().equals("Dark")){
							tr1 = is.setWeaponRanks(eElement, tr1);
							//System.out.println("t");
						}
						if (cl.get(y2).getWb().equals("Staff")){
							str1 = is.setWeaponRanks(eElement, str1);
							//System.out.println("st");
						}
					}
					
					else{
					nNode = nList.item(z);
					eElement = (Element) nNode;
					if (cl.get(y2).getWa().equals("BStone")){
						if (c1i[1].equals("Silver") || c1i[1].equals("Legend")){
							nNode = nList.item(73);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(72);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
					}
					else if (cl.get(y2).getWa().equals("DStone")){
						if (c1i[1].equals("Silver") || c1i[1].equals("Legend")){
							nNode = nList.item(71);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(70);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
					}
					else{
						while (!eElement.getAttribute("itype").equals(c1i[1]) || !eElement.getAttribute("wtype").equals(cl.get(y2).getWa())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w2 = eElement.getAttribute("hiid");
						if (cl.get(y2).getWa().equals("Sword")){
							swr1 = is.setWeaponRanks(eElement, swr1);
							//System.out.println("Sw");
						}
						if (cl.get(y2).getWa().equals("Lance")){
							lr1 = is.setWeaponRanks(eElement, lr1);
							//System.out.println("l");
						}
						if (cl.get(y2).getWa().equals("Axe")){
							ar1 = is.setWeaponRanks(eElement, ar1);
							//System.out.println("a");
						}
						if (cl.get(y2).getWa().equals("Bow")){
							br1 = is.setWeaponRanks(eElement, br1);
							//System.out.println("b");
						}
						if (cl.get(y2).getWa().equals("Tome") || cl.get(y2).getWa().equals("Dark")){
							tr1 = is.setWeaponRanks(eElement, tr1);
							//System.out.println("t");
						}
						if (cl.get(y2).getWa().equals("Staff")){
							str1 = is.setWeaponRanks(eElement, str1);
							//System.out.println("st");
						}
					}
				}
				z = 0;
				chap.append(w2);
				p = "0" + Integer.toHexString(size - 32);
				//System.out.println(p);
				pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
				chap.replace(block1 + 72 , block1 + 76, pa);
				size = size + (w2.length()/2);
				}
					if(!c1i[2].equals("")){           ////FIRST INVENTORY ITEM 3
					String w3 = new String();
					if (!cl.get(y2).getWc().equals("")){
					nNode = nList.item(z);
					eElement = (Element) nNode;
						while (!eElement.getAttribute("itype").equals(c1i[2]) || !eElement.getAttribute("wtype").equals(cl.get(y2).getWc())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w3 = eElement.getAttribute("hiid");
						if (cl.get(y2).getWc().equals("Sword")){
							is.setWeaponRanks(eElement, swr1);
						}
						if (cl.get(y2).getWc().equals("Lance")){
							is.setWeaponRanks(eElement, lr1);
						}
						if (cl.get(y2).getWc().equals("Axe")){
							is.setWeaponRanks(eElement, ar1);
						}
						if (cl.get(y2).getWc().equals("Bow")){
							is.setWeaponRanks(eElement, br1);
						}
						if (cl.get(y2).getWc().equals("Tome") || cl.get(y2).getWa().equals("Dark")){
							is.setWeaponRanks(eElement, tr1);
						}
						if (cl.get(y2).getWc().equals("Staff")){
							is.setWeaponRanks(eElement, str1);
						}
					}
					else{
					nNode = nList.item(z);
					eElement = (Element) nNode;
					if (cl.get(y2).getWa().equals("BStone")){
						if (c1i[2].equals("Silver") || c1i[2].equals("Legend")){
							nNode = nList.item(73);
							eElement = (Element) nNode;
							w3 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(72);
							eElement = (Element) nNode;
							w3 = eElement.getAttribute("hiid");
						}
					}
					else if (cl.get(y2).getWa().equals("DStone")){
						if (c1i[2].equals("Silver") || c1i[2].equals("Legend")){
							nNode = nList.item(71);
							eElement = (Element) nNode;
							w3 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(70);
							eElement = (Element) nNode;
							w3 = eElement.getAttribute("hiid");
						}
					}
					else{
						while (!eElement.getAttribute("itype").equals(c1i[2]) || !eElement.getAttribute("wtype").equals(cl.get(y2).getWa())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w3 = eElement.getAttribute("hiid");
						if (cl.get(y2).getWa().equals("Sword")){
							swr1 = is.setWeaponRanks(eElement, swr1);
						}
						if (cl.get(y2).getWa().equals("Lance")){
							lr1 = is.setWeaponRanks(eElement, lr1);
						}
						if (cl.get(y2).getWa().equals("Axe")){
							ar1 = is.setWeaponRanks(eElement, ar1);
						}
						if (cl.get(y2).getWa().equals("Bow")){
							br1 = is.setWeaponRanks(eElement, br1);
						}
						if (cl.get(y2).getWa().equals("Tome") || cl.get(y2).getWa().equals("Dark")){
							tr1 = setWeaponRanks(eElement, tr1);
						}
						if (cl.get(y2).getWa().equals("Staff")){
							str1 = setWeaponRanks(eElement, str1);
						}
					}
					}
				z = 0;
				//System.out.println(eElement.getAttribute("name"));
				chap.append(w3);
				p = "0" + Integer.toHexString(size - 32);
				
				//System.out.println(p);
				pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
				chap.replace(block1 + 88 , block1 + 92, pa);
				size = size + (w3.length()/2);
				}
				String sw = Integer.toHexString(swr1) + "0";
				String l = Integer.toHexString(lr1)+ "0";
				String a = Integer.toHexString(ar1)+ "0";
				String b = Integer.toHexString(br1)+ "0";
				String t = Integer.toHexString(tr1)+ "0";
				String st = Integer.toHexString(str1)+ "0";
				//System.out.println(cl.get(y2).getName());
				//System.out.println(t);
				//System.out.println(sw.substring(0,2) + l.substring(0,2) + a.substring(0,2) + b.substring(0,2) + t.substring(0,2) + st.substring(0,2));
				stat.replace(ind1 + 100, ind1 + 112, (sw.substring(0,2) + l.substring(0,2) + a.substring(0,2) + b.substring(0,2) + t.substring(0,2) + st.substring(0,2)));
					
				String end = "0" + Integer.toHexString(size);
				String enda = end.substring(end.length() - 2,end.length())+ end.substring(end.length() - 4, end.length() - 2) + "0";
				chap.replace(0, 5, enda);
				}
			if(!c2i[0].equals("") || !c2i[1].equals("")){       ///////SECOND INVENTORY
				//System.out.println("ok2");
				int block2 = ch.get(x).getCb2();
				String p = new String();
				String pa = new String();
				//System.out.println(c.get(y3).getName());
				//System.out.println(c2i[0]);
				int z = 0;
				
				int swr1 = 0;
				int lr1 = 0;
				int ar1 = 0;
				int br1 = 0;
				int tr1 = 0;
				int str1 = 0;
				if (c.get(y3).isPromoted()){
					if (cl.get(y4).getWa().equals("Sword") || cl.get(y6).getWb().equals("Sword")){
						swr1 = 16;
					}
					if (cl.get(y4).getWa().equals("Lance") || cl.get(y6).getWb().equals("Lance")){
						lr1 = 16;
					}
					if (cl.get(y4).getWa().equals("Axe") || cl.get(y6).getWb().equals("Axe") || cl.get(y4).getWc().equals("Axe")){
						ar1 = 16;
					}
					if (cl.get(y4).getWa().equals("Bow") || cl.get(y6).getWb().equals("Bow")){
						br1 = 16;
					}
					if (cl.get(y4).getWa().equals("Tome") || cl.get(y6).getWb().equals("Tome")){
						tr1 = 16;
					}
					if (cl.get(y4).getWa().equals("Staff") || cl.get(y6).getWb().equals("Staff")){
						str1 = 16;
					}
				}
				
				if(!c2i[0].equals("")){                        ////SECOND INVENTORY ITEM 1
					nNode = nList.item(z);
					eElement = (Element) nNode;
					String w1 = new String();
					if (cl.get(y4).getWa().equals("BStone")){
						if (c2i[0].equals("Silver") || c2i[0].equals("Legend")){
							nNode = nList.item(73);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(72);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
					}
					else if (cl.get(y4).getWa().equals("DStone")){
						if (c2i[0].equals("Silver") || c2i[0].equals("Legend")){
							nNode = nList.item(71);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(70);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
					}
					else{
						while (!eElement.getAttribute("itype").equals(c2i[0]) || !eElement.getAttribute("wtype").equals(cl.get(y4).getWa())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w1 = eElement.getAttribute("hiid");
						if (cl.get(y4).getWa().equals("Sword")){
							swr1 = is.setWeaponRanks(eElement, swr1);
							//System.out.println("Sw");
						}
						if (cl.get(y4).getWa().equals("Lance")){
							lr1 = is.setWeaponRanks(eElement, lr1);
							//System.out.println("l");
						}
						if (cl.get(y4).getWa().equals("Axe")){
							ar1 = is.setWeaponRanks(eElement, ar1);
							//System.out.println("a");
						}
						if (cl.get(y4).getWa().equals("Bow")){
							br1 = is.setWeaponRanks(eElement, br1);
							//System.out.println("b");
						}
						if (cl.get(y4).getWa().equals("Tome") || cl.get(y4).getWa().equals("Dark")){
							tr1 = is.setWeaponRanks(eElement, tr1);
							//System.out.println("t");
						}
						if (cl.get(y4).getWa().equals("Staff")){
							str1 = is.setWeaponRanks(eElement, str1);
							//System.out.println("st");
						}
					}
					//System.out.println(eElement.getAttribute("name"));
					chap.append(w1);
					p = "0" + Integer.toHexString(size - 32);
					//System.out.println(block2);
					pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
					chap.replace(block2 + 56 , block2 + 60, pa);
					size = size + (w1.length()/2);
					z = 0;
				}
				if(!c2i[1].equals("")){                    ///INVENTORY 2 ITEM 2
					String w2 = new String();
					if (!cl.get(y4).getWb().equals("")){
					nNode = nList.item(z);
					eElement = (Element) nNode;
						while (!eElement.getAttribute("itype").equals(c2i[1]) || !eElement.getAttribute("wtype").equals(cl.get(y4).getWb())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w2 = eElement.getAttribute("hiid");
						if (cl.get(y4).getWb().equals("Sword")){
							swr1 = is.setWeaponRanks(eElement, swr1);
							//System.out.println("Sw");
						}
						if (cl.get(y4).getWb().equals("Lance")){
							lr1 = is.setWeaponRanks(eElement, lr1);
							//System.out.println("l");
						}
						if (cl.get(y4).getWb().equals("Axe")){
							ar1 = is.setWeaponRanks(eElement, ar1);
							//System.out.println("a");
						}
						if (cl.get(y4).getWb().equals("Bow")){
							br1 = is.setWeaponRanks(eElement, br1);
							//System.out.println("b");
						}
						if (cl.get(y4).getWb().equals("Tome") || cl.get(y4).getWa().equals("Dark")){
							tr1 = is.setWeaponRanks(eElement, tr1);
							//System.out.println("t");
						}
						if (cl.get(y4).getWb().equals("Staff")){
							str1 = is.setWeaponRanks(eElement, str1);
							//System.out.println("st");
						}
					}
					
					else{
					nNode = nList.item(z);
					eElement = (Element) nNode;
					if (cl.get(y4).getWa().equals("BStone")){
						if (c2i[1].equals("Silver") || c2i[1].equals("Legend")){
							nNode = nList.item(73);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(72);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
					}
					else if (cl.get(y4).getWa().equals("DStone")){
						if (c2i[1].equals("Silver") || c2i[1].equals("Legend")){
							nNode = nList.item(71);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(70);
							eElement = (Element) nNode;
							w2 = eElement.getAttribute("hiid");
						}
					}
					else{
						while (!eElement.getAttribute("itype").equals(c2i[1]) || !eElement.getAttribute("wtype").equals(cl.get(y4).getWa())){
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w2 = eElement.getAttribute("hiid");
						if (cl.get(y4).getWa().equals("Sword")){
							swr1 = is.setWeaponRanks(eElement, swr1);
							//System.out.println("Sw");
						}
						if (cl.get(y4).getWa().equals("Lance")){
							lr1 = is.setWeaponRanks(eElement, lr1);
							//System.out.println("l");
						}
						if (cl.get(y4).getWa().equals("Axe")){
							ar1 = is.setWeaponRanks(eElement, ar1);
							//System.out.println("a");
						}
						if (cl.get(y4).getWa().equals("Bow")){
							br1 = is.setWeaponRanks(eElement, br1);
							//System.out.println("b");
						}
						if (cl.get(y4).getWa().equals("Tome") || cl.get(y4).getWa().equals("Dark")){
							tr1 = is.setWeaponRanks(eElement, tr1);
							//System.out.println("t");
						}
						if (cl.get(y4).getWa().equals("Staff")){
							str1 = is.setWeaponRanks(eElement, str1);
							//System.out.println("st");
						}
					}
					}
				z = 0;
					
				//System.out.println(eElement.getAttribute("name"));
				chap.append(w2);
				p = "0" + Integer.toHexString(size - 32);
				//System.out.println(p);
				pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
				chap.replace(block2 + 72 , block2 + 76, pa);
				size = size + (w2.length()/2);
				}
				String sw = Integer.toHexString(swr1) + "0";
				String l = Integer.toHexString(lr1)+ "0";
				String a = Integer.toHexString(ar1)+ "0";
				String b = Integer.toHexString(br1)+ "0";
				String t = Integer.toHexString(tr1)+ "0";
				String st = Integer.toHexString(str1)+ "0";
				//System.out.println(cl.get(y2).getName());
				//System.out.println(t);
				//System.out.println(sw.substring(0,2) + l.substring(0,2) + a.substring(0,2) + b.substring(0,2) + t.substring(0,2) + st.substring(0,2));
				stat.replace(ind2 + 100, ind2 + 112, (sw.substring(0,2) + l.substring(0,2) + a.substring(0,2) + b.substring(0,2) + t.substring(0,2) + st.substring(0,2)));
					
				String end = "0" + Integer.toHexString(size);
				String enda = end.substring(end.length() - 2,end.length())+ end.substring(end.length() - 4, end.length() - 2) + "0";
				chap.replace(0, 5, enda);
				}
				
				
				if(!c3i.equals("")){       ///////THIRD INVENTORY
				//System.out.println("ok3");
				int block3 = ch.get(x).getCb3();
				if (x == 2){
					block3 = 10152;
				}
				String p = new String();
				String pa = new String();
				//System.out.println(c.get(y).getName());
				//System.out.println(c3i);
				int z = 0;
				
				int swr1 = 0;
				int lr1 = 0;
				int ar1 = 0;
				int br1 = 0;
				int tr1 = 0;
				int str1 = 0;
				if (c.get(y5).isPromoted()){
					if (cl.get(y6).getWa().equals("Sword") || cl.get(y6).getWb().equals("Sword")){
						swr1 = 16;
					}
					if (cl.get(y6).getWa().equals("Lance") || cl.get(y6).getWb().equals("Lance")){
						lr1 = 16;
					}
					if (cl.get(y6).getWa().equals("Axe") || cl.get(y6).getWb().equals("Axe") || cl.get(y6).getWc().equals("Axe")){
						ar1 = 16;
					}
					if (cl.get(y6).getWa().equals("Bow") || cl.get(y6).getWb().equals("Bow")){
						br1 = 16;
					}
					if (cl.get(y6).getWa().equals("Tome") || cl.get(y6).getWb().equals("Tome")){
						tr1 = 16;
					}
					if (cl.get(y6).getWa().equals("Staff") || cl.get(y6).getWb().equals("Staff")){
						str1 = 16;
					}
				}
				
				if(!c3i.equals("")){                        ////THIRD INVENTORY ITEM 1
					nNode = nList.item(z);
					eElement = (Element) nNode;
					//System.out.println(eElement.getAttribute("name"));
					String w1 = new String();
					if (cl.get(y6).getWa().equals("BStone")){
						if (c3i.equals("Silver") || c3i.equals("Legend")){
							nNode = nList.item(73);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(72);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
					}
					else if (cl.get(y6).getWa().equals("DStone")){
						if (c3i.equals("Silver") || c3i.equals("Legend")){
							nNode = nList.item(71);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
						else {
							nNode = nList.item(70);
							eElement = (Element) nNode;
							w1 = eElement.getAttribute("hiid");
						}
					}
					else{
						//System.out.println(c3i);
						while (!eElement.getAttribute("itype").equals(c3i) || !eElement.getAttribute("wtype").equals(cl.get(y6).getWa())){
							//System.out.println("in loop");
							z = z + 1;
							nNode = nList.item(z);
							eElement = (Element) nNode;
						}
						w1 = eElement.getAttribute("hiid");
						if (cl.get(y6).getWa().equals("Sword")){
							swr1 = is.setWeaponRanks(eElement, swr1);
						}
						if (cl.get(y6).getWa().equals("Lance")){
							lr1 = is.setWeaponRanks(eElement, lr1);
						}
						if (cl.get(y6).getWa().equals("Axe")){
							ar1 = is.setWeaponRanks(eElement, ar1);
						}
						if (cl.get(y6).getWa().equals("Bow")){
							br1 = is.setWeaponRanks(eElement, br1);
						}
						if (cl.get(y6).getWa().equals("Tome") || cl.get(y6).getWa().equals("Dark")){
							tr1 = setWeaponRanks(eElement, tr1);
						}
						if (cl.get(y6).getWa().equals("Staff")){
							str1 = setWeaponRanks(eElement, str1);
						}
					}
					//System.out.println(eElement.getAttribute("name"));
					chap.append(w1);
					p = "0" + Integer.toHexString(size - 32);
					//System.out.println(block3);
					pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
					chap.replace(block3 + 56 , block3 + 60, pa);
					size = size + (w1.length()/2);
					z = 0;
				}
				String sw = Integer.toHexString(swr1) + "0";
				String l = Integer.toHexString(lr1)+ "0";
				String a = Integer.toHexString(ar1)+ "0";
				String b = Integer.toHexString(br1)+ "0";
				String t = Integer.toHexString(tr1)+ "0";
				String st = Integer.toHexString(str1)+ "0";
				//System.out.println(cl.get(y2).getName());
				//System.out.println(t);
				//System.out.println(sw.substring(0,2) + l.substring(0,2) + a.substring(0,2) + b.substring(0,2) + t.substring(0,2) + st.substring(0,2));
				stat.replace(ind3 + 100, ind3 + 112, (sw.substring(0,2) + l.substring(0,2) + a.substring(0,2) + b.substring(0,2) + t.substring(0,2) + st.substring(0,2)));
					
				
				String end = "0" + Integer.toHexString(size);
				String enda = end.substring(end.length() - 2,end.length())+ end.substring(end.length() - 4, end.length() - 2) + "0";
				chap.replace(0, 5, enda);
				}
			bin.writeStatic(stat);
			bin.writeDispos(chap, cname);
			}
		}
		
		public int setWeaponRanks(Element eElement, int wr){
			if (eElement.getAttribute("itype").equals("Legend")){
				wr = 91;
			}
			else if (eElement.getAttribute("itype").equals("Magic") || eElement.getAttribute("itype").equals("Silver") || eElement.getAttribute("itype").equals("Special")){
				if (wr < 61){
					wr = 61;
				}
			}
			else if (eElement.getAttribute("itype").equals("Killer") || eElement.getAttribute("itype").equals("Steel")){
				if (eElement.getAttribute("itype").equals("Steel") && eElement.getAttribute("wtype").equals("Tome")){
					if (wr < 16){
						wr = 16;
					}
				}
				else if (eElement.getAttribute("itype").equals("Steel") && eElement.getAttribute("wtype").equals("Dark")){
					if (wr < 16){
						wr = 16;
					}
				}
				else if (eElement.getAttribute("itype").equals("Steel") && eElement.getAttribute("wtype").equals("Staff")){
					if (wr < 16){
						wr = 16;
					}
				}
				else if (wr < 36){
					wr = 36;
				}
			}
			else if (eElement.getAttribute("itype").equals("Effective") || eElement.getAttribute("itype").equals("Iron") || eElement.getAttribute("itype").equals("Range")){
				//System.out.println("in eff");
				//System.out.println(eElement.getAttribute("itype"));
				//System.out.println(eElement.getAttribute("wtype"));
				if (eElement.getAttribute("itype").equals("Range") && eElement.getAttribute("wtype").equals("Dark")){
					if (wr < 36){
						wr = 36;
					}
				}
				else if (eElement.getAttribute("itype").equals("Range") && eElement.getAttribute("wtype").equals("Staff")){
					if (wr < 36){
						wr = 36;
					}
				}
				else if (eElement.getAttribute("itype").equals("Iron") && eElement.getAttribute("wtype").equals("Staff")){
					if (wr < 1){
						wr = 0;
					}
				}
				else if (eElement.getAttribute("itype").equals("Iron") && eElement.getAttribute("wtype").equals("Tome")){
					if (wr < 1){
						wr = 0;
					}
				}
				else if (eElement.getAttribute("itype").equals("Iron") && eElement.getAttribute("wtype").equals("Dark")){
					if (wr < 1){
						wr = 0;
					}
				}
				else if (wr < 16){
					wr = 16;
				}
				//System.out.println(wr);
			}
			//System.out.println(wr);
			return wr;
		}
	}
	