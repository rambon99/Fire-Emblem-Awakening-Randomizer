package main.Swapping;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Structure.BinFiles;
import main.Structure.ACharacter;
import main.Structure.AChapter;
import main.Randomizer.CharacterShuffler;

public class CharSwapper{
	
	public CharSwapper(){
		
	}
	
	public void swapChars(ArrayList <ACharacter> c, StringBuilder stat, ArrayList <AChapter> ch){
		CharacterShuffler chash = new CharacterShuffler();
		chash.randomizeCharacters(c);
		BinFiles bin = new BinFiles();
		StringBuilder tstat = new StringBuilder(stat);
		System.out.println("CharSwapper begin");

		
		for (int x = 0; x < 35; x++){
			StringBuilder chap = new StringBuilder();
			int size = 0; 
			
			size = bin.getDispos(chap, ch.get(x).getName(), size);
			System.out.println(ch.get(x).getName());
			
			if (!ch.get(x).getC1().equals("")){
				int y = 0;
				int y2 = 0;
				int block = ch.get(x).getCb1();
				//System.out.println(block);
				while (!ch.get(x).getC1().equals(c.get(y).getActual())){
					y = y + 1;
				}
				while (!c.get(y2).getName().equals(c.get(y).getActual())){
					y2 = y2 + 1;
				}
				//System.out.println(c.get(y).getName());
				String fid1 = c.get(y).getFid();
				String nfid1 = fid1.substring(2,4)+fid1.substring(0,2);
				int ind1 = stat.lastIndexOf(("00" + nfid1.toLowerCase() + "0000")) + 2;
				String fid2 = c.get(y2).getFid();
				String nfid2 = fid2.substring(2,4)+fid2.substring(0,2);
				int ind2 = stat.lastIndexOf(("00" + nfid2.toLowerCase() + "0000")) + 2;
				stat.replace(ind1 + 32, ind1 + 48, tstat.substring(ind2 + 32, ind2 + 48));
				stat.replace(ind1 + 114, ind1 + 116, tstat.substring(ind2 + 114, ind2 + 116));
				stat.replace(ind1 + 96, ind1 + 100, tstat.substring(ind2 + 96, ind2 + 100));
				if (c. get(y).getName().equals("Chrom")){
					stat.delete(ind1 - 24, ind1 - 22);
					stat.insert(ind1 - 24, "00");
				}
				if (c.get(y).getActual().equals("Chrom")){
					stat.insert(ind1 - 24, "22");
					stat.delete(ind1 - 2, ind1);
				}
				//System.out.println("Crash");
				chap.append(c.get(y).getHpid());
				String p = "0" + Integer.toHexString(size - 32);
				String pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
				chap.replace(block, block + 4, pa);
				size = size + (c.get(y).getHpid().length()/2);
			}
			if (!ch.get(x).getC2().equals("")){
				int y = 0;
				int block = ch.get(x).getCb2();
				//System.out.println(block);
				while (!ch.get(x).getC2().equals(c.get(y).getActual())){
					y = y + 1;
				}
				int y2 = 0;
				while (!c.get(y2).getName().equals(c.get(y).getActual())){
					y2 = y2 + 1;
				}
				//System.out.println(c.get(y).getName());
				String fid1 = c.get(y).getFid();
				String nfid1 = fid1.substring(2,4)+fid1.substring(0,2);
				int ind1 = tstat.lastIndexOf(("00" + nfid1.toLowerCase() + "0000")) + 2;
				String fid2 = c.get(y2).getFid();
				String nfid2 = fid2.substring(2,4)+fid2.substring(0,2);
				int ind2 = tstat.lastIndexOf(("00" + nfid2.toLowerCase() + "0000")) + 2;
				stat.replace(ind1 + 32, ind1 + 48, tstat.substring(ind2 + 32, ind2 + 48));
				stat.replace(ind1 + 114, ind1 + 116, tstat.substring(ind2 + 114, ind2 + 116));
				stat.replace(ind1 + 96, ind1 + 100, tstat.substring(ind2 + 96, ind2 + 100));
				if (c. get(y).getName().equals("Chrom")){
					stat.delete(ind1 - 24, ind1 - 22);
					stat.insert(ind1 - 24, "00");
				}
				//System.out.println("Crash");
				chap.append(c.get(y).getHpid());
				String p = "0" + Integer.toHexString(size - 32);
				String pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
				chap.replace(block, block + 4, pa);
				size = size + (c.get(y).getHpid().length()/2);
			}
			if (!ch.get(x).getC3().equals("")){
				int y = 0;
				int block = ch.get(x).getCb3();
				//System.out.println(block);
				while (!ch.get(x).getC3().equals(c.get(y).getActual())){
					y = y + 1;
				}
				int y2 = 0;
				while (!c.get(y2).getName().equals(c.get(y).getActual())){
					y2 = y2 + 1;
				}
				//System.out.println(c.get(y).getName());
				String fid1 = c.get(y).getFid();
				String nfid1 = fid1.substring(2,4)+fid1.substring(0,2);
				int ind1 = tstat.lastIndexOf(("00" + nfid1.toLowerCase() + "0000")) + 2;
				String fid2 = c.get(y2).getFid();
				String nfid2 = fid2.substring(2,4)+fid2.substring(0,2);
				int ind2 = tstat.lastIndexOf(("00" + nfid2.toLowerCase() + "0000")) + 2;
				stat.replace(ind1 + 32, ind1 + 48, tstat.substring(ind2 + 32, ind2 + 48));
				stat.replace(ind1 + 114, ind1 + 116, tstat.substring(ind2 + 114, ind2 + 116));
				stat.replace(ind1 + 96, ind1 + 100, tstat.substring(ind2 + 96, ind2 + 100));
				if (c. get(y).getName().equals("Chrom")){
					stat.delete(ind1 - 24, ind1 - 22);
					stat.insert(ind1 - 24, "00");
				}
				//System.out.println("Crash");
				chap.append(c.get(y).getHpid());
				String p = "0" + Integer.toHexString(size - 32);
				String pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
				chap.replace(block, block + 4, pa);
				size = size + (c.get(y).getHpid().length()/2);
			}
			String end = "0" + Integer.toHexString(size);
			String enda = end.substring(end.length() - 2,end.length())+ end.substring(end.length() - 4, end.length() - 2) + "0";
			chap.replace(0, 5, enda);
			bin.writeDispos(chap, ch.get(x).getName());
		}
		System.out.println("End Swapper");
		bin.writeStatic(stat);
		System.out.println("Write main");
	}
	
}