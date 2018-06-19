package main.Swapping;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import main.Structure.BinFiles;
import main.Randomizer.ClassShuffler;
import main.Structure.ACharacter;
import main.Structure.CharacterStarter;
import main.Structure.ClassStarter;
import main.Structure.AClasses;

public class ClassSwapper{
	
	public ClassSwapper(){
		
	}
	
	public void swapClasses(ArrayList <ACharacter> c, ArrayList <AClasses> cl, StringBuilder str) throws Exception{
		ClassShuffler ran = new ClassShuffler();
		ran.RandomizeClasses(c);
		
		BinFiles bin = new BinFiles();
		//System.out.println(str);
		
		for (int x = 0; x < 49; x++){
			String fid = c.get(x).getFid();
			///System.out.println(fid);
			String nfid = fid.substring(2,4)+fid.substring(0,2);
			int ind = str.lastIndexOf(("00" + nfid.toLowerCase() + "0000")) + 2;
			if (x == 24 || x == 35 || x == 15){
				ind = str.indexOf(nfid.toLowerCase());
			}
			boolean gen = c.get(x).isMale();
			String[] cla = c.get(x).getClasses();
			//System.out.println(c.get(x).getName());
			//System.out.println(ind);
			for (int cn = 0; cn < 3; cn ++){
			//System.out.println(cla[cn]);
			if (cla[cn] == null){
				break;
			}
			int y = 0;
			String ccl = cl.get(y).getName();
			while (!ccl.equals(cla[cn])){
				y = y + 1;
				ccl = cl.get(y).getName();
			}
			String mcid = new String();
			String fcid = new String();
			//Set<Integer> lm = new HashSet<Integer>(Arrays.asList(24, 29, 30, 32, 34, 36, 37, 38, 42, 43, 45, 46));
			//Set<Integer> lmc = new HashSet<Integer>(Arrays.asList(36, 37, 38, 42, 43, 45, 46));
			//Set<Integer> lf = new HashSet<Integer>(Arrays.asList(4, 9, 15, 17, 19, 23, 25, 27, 28, 31, 33, 35, 39, 40, 41, 44, 47, 48));
			//Set<Integer> lfc = new HashSet<Integer>(Arrays.asList(35, 39, 40, 41, 44, 47, 48));
			if (gen){
				if (cl.get(y).getName().equals(cl.get(y).getOc()))
				{
					int y2 = y + 1;
					while (!cl.get(y2).getName().equals(cla[cn])){
						y2 = y2 + 1;
					}
					if (gen == cl.get(y).isMale()){
						mcid = cl.get(y).getCid();
						fcid = cl.get(y2).getCid();
					}
					else {
						mcid = cl.get(y2).getCid();
						fcid = cl.get(y).getCid();
					}
				}
				else {
					mcid = cl.get(y).getCid();
					if (cl.get(y).isMale() && !cl.get(y).getOc().equals("")){
						int z = 0; 
						while (!cl.get(z).getName().equals(cl.get(y).getOc())){
							z = z + 1;
						}
						fcid = cl.get(z).getCid();
					}
					else {
						fcid = cl.get(y).getCid();
					}
				}
				String nmcid = mcid.substring(2,4)+ mcid.substring(0,2)+"0";
				if (cn == 0){
				str.replace(ind + 8, ind + 13, nmcid);}
				if (cl.get(y).isPromoted()){
					String base = cl.get(y).getBase();
					//System.out.println(base);
					int y3 = 0;
					while (!cl.get(y3).getName().equals(base)){
						y3 = y3 + 1;
					}
					if (cl.get(y).getName().equals(cl.get(y).getOc()))
					{
						int y4 = y3 + 1;
						while (!cl.get(y4).getName().equals(base)){
							y4 = y4 + 1;
						}
						//System.out.println(cl.get(y3).isMale());
						//System.out.println(cl.get(y4).isMale());
						//System.out.println(cl.get(y3).getCid());
						//System.out.println(cl.get(y4).getCid());
						if (gen == cl.get(y3).isMale()){
							mcid = cl.get(y3).getCid();
							fcid = cl.get(y4).getCid();
						}
						else {
							mcid = cl.get(y4).getCid();
							fcid = cl.get(y3).getCid();
						}
					}
					else {
						mcid = cl.get(y3).getCid();
						if (cl.get(y3).isMale() && !cl.get(y3).getOc().equals("")){
							int z2 = 0; 
							while (!cl.get(z2).getName().equals(cl.get(y3).getOc())){
								z2 = z2 + 1;
							}
							fcid = cl.get(z2).getCid();
						}
						else {
							fcid = cl.get(y3).getCid();
						}
					}
				}
				if (c.get(x).getName().matches("Basilio|Gangrel|Walhart|Yen'fay|Priam|Owain|Inigo|Brady|Gerome|Morgan|Yarne|Laurent")){
					fcid = "0000";
					if (cn != 0 && !c.get(x).getPpid().equals("")){
						mcid = "0000";
					}				
					if (c.get(x).getName().equals("Morgan")){
						mcid = "0000";
					}
				}
				nmcid = mcid.substring(2,4)+ mcid.substring(0,2)+"0";
				str.replace(ind + 192 + (cn * 8), ind + 197 + (cn * 8), nmcid);
				String nfcid = fcid.substring(2,4)+ fcid.substring(0,2)+"0";
				str.replace(ind + 216 + (cn * 8), ind + 221 + (cn * 8), nfcid);
			}
			else {
				if (cl.get(y).getName().equals(cl.get(y).getOc()))
				{
					
					int y2 = y + 1;
					while (!cl.get(y2).getName().equals(cla[cn])){
						y2 = y2 + 1;
					}
					//System.out.println(cl.get(y).isMale());
					//System.out.println(cl.get(y2).isMale());
					//System.out.println(cl.get(y).getCid());
					//System.out.println(cl.get(y2).getCid());
					if (!gen == cl.get(y).isMale()){
						mcid = cl.get(y).getCid();
						fcid = cl.get(y2).getCid();
					}
					else {
						mcid = cl.get(y2).getCid();
						fcid = cl.get(y).getCid();
					}
				}
				else {
					fcid = cl.get(y).getCid();
					if (!cl.get(y).isMale() && !cl.get(y).getOc().equals("")){
						int z = 0; 
						while (!cl.get(z).getName().equals(cl.get(y).getOc())){
							z = z + 1;
						}
						mcid = cl.get(z).getCid();
					}
					else {
						mcid = cl.get(y).getCid();
					}
				}
				String nfcid = fcid.substring(2,4)+ fcid.substring(0,2)+"0";
				if (cn == 00){
				str.replace(ind + 8, ind + 13, nfcid);}
				if (cl.get(y).isPromoted()){
					String base = cl.get(y).getBase();
					int y3 = 0;
					while (!cl.get(y3).getName().equals(base)){
						y3 = y3 + 1;
					}
					if (cl.get(y).getName().equals(cl.get(y).getOc()))
					{
						int y4 = y3 + 1;
						while (!cl.get(y4).getName().equals(base)){
							y4 = y4 + 1;
						}
						if (!gen == cl.get(y3).isMale()){
							mcid = cl.get(y3).getCid();
							fcid = cl.get(y4).getCid();
						}
						else {
							mcid = cl.get(y4).getCid();
							fcid = cl.get(y3).getCid();
						}
					}
					else {
						fcid = cl.get(y3).getCid();
						if (!cl.get(y3).isMale() && !cl.get(y3).getOc().equals("")){
							int z2 = 0; 
							while (!cl.get(z2).getName().equals(cl.get(y3).getOc())){
								z2 = z2 + 1;
							}
							mcid = cl.get(z2).getCid();
						}
						else {
							mcid = cl.get(y3).getCid();
						}
					}
				}
				if (c.get(x).getName().matches("Flavia|Anna|Say'ri|Emmeryn|Aversa|Lucina|Kjelle|Cynthia|Severa|Morgan|Noire|Nah")){
					mcid = "0000";
					if (cn != 0 && !c.get(x).getPpid().equals("")){
						fcid = "0000";
					}				
					if (c.get(x).getName().equals("Morgan")){
						fcid = "0000";
					}
				}
				String nmcid = mcid.substring(2,4)+ mcid.substring(0,2)+"0";
				str.replace(ind + 192 + (cn * 8), ind + 197 + (cn * 8), nmcid);
				nfcid = fcid.substring(2,4)+ fcid.substring(0,2)+"0";
				str.replace(ind + 216 + (cn * 8), ind + 221 + (cn * 8), nfcid);
			}
			}
			//String cid = cl.get(y).getCid();
			//String ncid = cid.substring(2,4)+cid.substring(0,2)+"0";
			//sSystem.out.println(ncid);
			//str.replace(ind + 8, ind + 13, ncid);
			//str.replace(ind + 192, ind + 197, ncid);
		}
		
		bin.writeStatic(str);
	}
	
	public void adjustClasses(ArrayList <ACharacter> c, ArrayList <AClasses> cl, StringBuilder str) throws Exception{
		System.out.println("Start Char Adjus");
		BinFiles bin =  new BinFiles();
		
		for (int x = 0; x < 49; x++){
			System.out.println(c.get(x).getName());
			System.out.println(c.get(x).isPromoted());
			String fid = c.get(x).getFid();
			///System.out.println(fid);
			String nfid = fid.substring(2,4)+fid.substring(0,2);
			int ind = str.lastIndexOf(("00" + nfid.toLowerCase() + "0000")) + 2;
			
			String[] cla = c.get(x).getClasses();
			String[] tmp;
			int y = 0;
			while (!cl.get(y).getName().equals(cla[0])){
				y = y + 1;
			}
			if (cl.get(y).getName().matches("Dancer|Villager|Merchant|Manakete|Conqueror|Soldier|Lodestar|Taguel") && c.get(x).isPromoted()){
				//System.out.println("Morgan?");
				String lvs = str.substring(ind + 114, ind + 116);
				int lvn = 19 + Integer.parseInt(lvs, 16);
				if (lvn > 30){
					str.replace(ind + 114, ind + 116, Integer.toHexString(30));
				}
				else{
					str.replace(ind + 114, ind + 116, Integer.toHexString(lvn));
				}
			}
			else if (c.get(x).isPromoted() != cl.get(y).isPromoted()){
				int z = 0;
				//System.out.println("Panne?");
				//System.out.println(cl.get(y).getName());
				//System.out.println(cl.get(y).getBase());
				if (c.get(x).isPromoted()){
					//System.out.println("promoted");
					while (cl.get(z).isMale() != c.get(x).isMale() || cl.get(z).isPromoted() != c.get(x).isPromoted() || !cl.get(z).getBase().equals(cl.get(y).getName())){
						z = z + 1;
						//System.out.println(cl.get(z).getName());
					}
				}
				else {
					while (cl.get(z).isMale() != c.get(x).isMale() || cl.get(z).isPromoted() != c.get(x).isPromoted() || !cl.get(z).getName().equals(cl.get(y).getBase())){
						z = z + 1;
						//System.out.println(cl.get(z).getName());
					}
				}
				//System.out.println(cl.get(z).getName());
				//System.out.println(cl.get(z).getCid());
				tmp = new String[] {cl.get(z).getName(), "", ""};
				c.get(x).setClasses(tmp);
				String cid = cl.get(z).getCid();
				String ncid = cid.substring(2,4)+ cid.substring(0,2)+"0";
				str.replace(ind + 8, ind + 13, ncid);
			}
		}
		System.out.println("End Char Adjus");
		bin.writeStatic(str);
	}
}