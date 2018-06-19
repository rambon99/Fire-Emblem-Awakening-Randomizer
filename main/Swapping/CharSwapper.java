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
		CharSwapper script = new CharSwapper();
		System.out.println("CharSwapper begin");
		
		int z = 0;
		while (!c.get(z).getName().equals("Chrom")){
			z = z + 1;
		}
		
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
				stat.replace(ind1 + 120, ind1 + 140, tstat.substring(ind2 + 120, ind2 + 140));
				if (c. get(y).getName().equals("Chrom")){
					stat.delete(ind1 - 24, ind1 - 22);
					stat.insert(ind1 - 24, "00");
				}
				if (c.get(y).getActual().equals("Chrom")){
					stat.insert(ind1 - 24, "22");
					stat.delete(ind1 - 10, ind1 - 8);
				}
				//System.out.println("Crash");
				chap.append(c.get(y).getHpid());
				String p = "0" + Integer.toHexString(size - 32);
				String pa = p.substring(p.length() - 2, p.length())+ p.substring(p.length() - 4, p.length() - 2);
				chap.replace(block, block + 4, pa);
				size = size + (c.get(y).getHpid().length()/2);
				
				if (c.get(y).getActual().matches("Kellam|Libra|Say'ri|Anna|Gangrel") || !c.get(y).getPpid().equals("")){ //Say'ri, Anna|Gangrel
					script.fixScript(c.get(y), c.get(y2), ch.get(x));
					script.fixScript(c.get(0), c.get(z), ch.get(x));
				}
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
				stat.replace(ind1 + 120, ind1 + 140, tstat.substring(ind2 + 120, ind2 + 140));
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
				
				if (c.get(y).getActual().matches("Gaius|Tharja")){
					script.fixScript(c.get(y), c.get(y2), ch.get(x));
					script.fixScript(c.get(0), c.get(z), ch.get(x));
				}
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
				stat.replace(ind1 + 120, ind1 + 140, tstat.substring(ind2 + 120, ind2 + 140));
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
		System.out.println("End Char Swapper");
		bin.writeStatic(stat);
		System.out.println("Write main");
	}
	
	public void fixScript(ACharacter chr1, ACharacter chr2, AChapter chp){
		
		String cname = chp.getName().substring(0, chp.getName().length() - 3) + "cmb";
		StringBuilder cstring =  new StringBuilder();
		BinFiles script = new BinFiles();
		int size = 0;
		size = script.getScript(cstring, cname, size);
		String table = cstring.substring(66, 68) + cstring.substring(64, 66);
		int enc = Integer.parseInt(table, 16);
		System.out.println(table);
		//System.out.println(size);
		String ad11t = "000" + Integer.toHexString(size - enc);
		String ad11 = ad11t.substring(ad11t.length()-4, ad11t.length());
		String ad12 = ad11.substring(ad11.length() - 2, ad11.length()) + ad11.substring(ad11.length() - 4, ad11.length() - 2);
		//System.out.println(chr1.getName());
		int tind = cstring.indexOf(chr2.getHpid().toLowerCase())/2;
		//System.out.println(chr2.getName());
		//System.out.println(chr2.getHpid());
		//System.out.println(Integer.toHexString(tind));
		String ad21t = "000" + Integer.toHexString(tind - enc);
		String ad21 = ad21t.substring(ad21t.length()-4, ad21t.length());
		//System.out.println(size);
		String ad22 = ad21.substring(ad21.length() - 2, ad21.length()) + ad21.substring(ad21.length() - 4, ad21.length() - 2);
		int ind1 = cstring.indexOf("d1" + ad21.toLowerCase()) + 2;
		int ind2 = cstring.indexOf(ad22.toLowerCase()+"0000");
		//System.out.println(ad11);
		//System.out.println(ad12);
		//System.out.println(ad21);
		//System.out.println(ad22);
		cstring.append(chr1.getHpid());
		size = size + (chr1.getHpid().length()/2);
		
		while ( ind1!= -1 ||  ind2!= -1){
			System.out.println(cstring.indexOf(ad21)+ " " + cstring.indexOf(ad22));
			//System.out.println(cstring.substring(ind1, ind1+4));
			//System.out.println(cstring.substring(ind2, ind2+4));
			if (ind1 != -1){
				//System.out.println(cstring.substring(ind1, ind1+4));
				cstring.replace(ind1, ind1 + 4, ad11);
				//System.out.println(cstring.substring(ind1, ind1+4));
				ind1 = cstring.indexOf(ad21.toLowerCase());
				
			}
			
			if (ind2 != -1){
				//System.out.println(cstring.substring(ind2, ind2+4));
				cstring.replace(ind2, ind2 + 4, ad12);
				//System.out.println(cstring.substring(ind2, ind2+4));
				ind2 = cstring.indexOf(ad22.toLowerCase());
				
			}
		}
		int tind2 = cstring.indexOf((chr2.getHpid().substring(8, chr2.getHpid().length()-2) + "96A195FB00").toLowerCase());
		//System.out.println(chr2.getHpid().substring(8, chr2.getHpid().length()-2) + "96A195FB00");
		//System.out.println(cstring.indexOf((chr2.getHpid().substring(8, chr2.getHpid().length()-2) + "96A195FB00").toLowerCase()));
		if (tind2 != -1){
			tind2 = tind2/2;
			String nname1 = chr1.getHpid().substring(8, chr1.getHpid().length()-2) + "96A195FB00";
			String ad31t = "000" + Integer.toHexString(size - enc);
			String ad31 = ad31t.substring(ad31t.length()-4, ad31t.length());
			String ad32 = ad31.substring(ad31.length() - 2, ad31.length()) + ad31.substring(ad31.length() - 4, ad31.length() - 2);
			
			String ad41t = "000" + Integer.toHexString(tind2 - enc);
			String ad41 = ad41t.substring(ad41t.length()-4, ad41t.length());
			String ad42 = ad41.substring(ad41.length() - 2, ad41.length()) + ad41.substring(ad41.length() - 4, ad41.length() - 2);
			int ind3 = cstring.indexOf("d1" + ad41.toLowerCase()) + 2;
			int ind4 = cstring.indexOf(ad42.toLowerCase() + "0000");
			
			//System.out.println(ad31);
			//System.out.println(ad32);
			//System.out.println(ad41);
			//System.out.println(ad42);
			while ( ind3!= -1 ||  ind3!= -1){
				System.out.println(cstring.indexOf(ad41)+ " " + cstring.indexOf(ad42));
				//System.out.println(cstring.substring(ind1, ind1+4));
				//System.out.println(cstring.substring(ind2, ind2+4));
				if (ind3 != -1){
					//System.out.println(cstring.substring(ind3, ind3+4));
					cstring.replace(ind3, ind3 + 4, ad31);
					//System.out.println(cstring.substring(ind3, ind3+4));
					ind3 = cstring.indexOf(ad41.toLowerCase());	
				}
				
				if (ind4 != -1){
					//System.out.println(cstring.substring(ind4, ind4+4));
					cstring.replace(ind4, ind4 + 4, ad32);
					//System.out.println(cstring.substring(ind4, ind4+4));
					ind4 = cstring.indexOf(ad42.toLowerCase());
				}
			}
			cstring.append(nname1);
			size = size + (nname1.length());
		}
		//if (c.getActual().equals("Tharja")){
		//	cstring.replace(4790, 4794, "0A19")
			//cstring.replace(5062, 5066, "0276")
		//}
		script.writeScript(cstring, cname);
	}
	
}