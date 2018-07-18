package main.Program;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FileChecker{
	
	public FileChecker(){
		
	}
	
	public void checkFiles(File f) throws Exception{
		File statc = new File(f, "/data/person/static.bin");
		File gamed = new File(f, "data/GameData.bin");
		File dp1 = new File(f, "/data/dispos/P001.bin");
		File dp2 = new File(f, "/data/dispos/P002.bin");
		File sp1 = new File(f, "/scripts/P001.cmb");
		File sp2 = new File(f, "/scripts/P002.cmb");
		if (!statc.exists() || !gamed.exists() || !dp1.exists() || !dp2.exists() || !sp1.exists() || !sp2.exists()){
			throw new Exception();
		}
		for (int x = 0; x < 27; x++){
			if (x < 24 && x >0){
				String disposx = "/data/dispos/X" + String.format("%03d", x) + ".bin";
				String scriptx = "/scripts/X" + String.format("%03d", x) + ".cmb";
				File disx = new File(f, disposx);
				File scrx = new File(f, scriptx);
				if (!disx.exists() || !scrx.exists()){
					throw new Exception();
				}
			}
			String dispos = "/data/dispos/" + String.format("%03d", x) + ".bin";
			String script = "/scripts/" + String.format("%03d", x) + ".cmb";
			File dis = new File(f, dispos);
			File scr = new File(f, script);
			if (!dis.exists() || !scr.exists()){
				throw new Exception();
			}
		}
	}
	
}