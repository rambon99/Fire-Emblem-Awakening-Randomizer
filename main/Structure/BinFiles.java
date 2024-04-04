package main.Structure;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.Arrays;
import javax.xml.parsers.*;
import java.nio.file.Files;  
import java.nio.file.Paths;

import main.Output.DebugBuilder;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Program.GUI;

public class BinFiles{
	
	public static File folder;
	public BinFiles(){
		
	}
	
	public static void SetFolder(File fold){
	folder = fold;
	}
	
	public void getStatic(StringBuilder str){
		try {
		//GUI folder = new GUI();
		File currentDir = folder;
		//System.out.println(currentDir);
		File file = new File(currentDir, "/data/person/static.bin");
		//System.out.println("File direct: " + file.getPath());
		FileInputStream fis = new FileInputStream(file);
		DataInputStream din = new DataInputStream(fis);
	
		byte[] bytes = Files.readAllBytes(Paths.get(file.getPath())); //50643 end bytes: 90 6C 00

		//din.read(bytes);
/*
		for (int x = 0; x < 50643; x++) {
			//String str  = Integer.toHexString((b & 0xff)+256).substring(1);
			str.append(Integer.toHexString((bytes[x] & 0xff)+256).substring(1));	
		}*/
		int ind = 0;
		for (byte b: bytes){
			str.append(Integer.toHexString((b & 0xff)+256).substring(1));
			ind++;
		}
		String staticEnd = "4A49445F837D8358835E815B838D815B8368926A004A49445F837D8358835E815B838D815B83688F97004A49445F905F8C528E74926A004A49445F905F8C528E748F97004A49445F83708389836683428393926A004A49445F837083898366834283938F97004A49445F834F838C815B83678369834383678F97004A49445F83578346836C8389838B926A004A49445F83578346836C8389838B8F97004A49445F836F815B8354815B834A815B926A004A49445F837B8345836983438367926A004A49445F837B83458369834383678F97004A49445F8358836983438370815B926A004A49445F8358836983438370815B8F97004A49445F8341835483568393926A004A49445F83418354835683938F97004A49445F83748340838B835283938369834383678F97004A49445F8368838983538393837D8358835E815B926A004A49445F8368838983538393837D8358835E815B8F97004A49445F834F838A837483488393836983438367926A004A49445F834F838A8374834883938369834383678F97004A49445F83948340838B834C8385838A83418F97004A49445F836F8367838B83568358835E815B8F97004A49445F8CAB8ED2926A004A49445F835F815B834E836983438367926A004A49445F835F815B834E8369834383678F97004A49445F835C815B83548389815B926A004A49445F835C815B83548389815B8F97004A49445F835C838B83578383815B926A00";
		str.append(staticEnd.toLowerCase());
		//Calculates the size of the static.bin so that it can insert the hex size at the start of the file
		int size = str.length()/2;
		String end = Integer.toHexString(size);
		String endPointer = end.substring(end.length() - 2,end.length())+ end.substring(end.length() - 4, end.length() - 2);
		str.replace(0, 4, endPointer);
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("Read bin fucked");
		}
	}
	
	public void writeStatic(StringBuilder str){
		try{
		//System.out.print(str);
		int len = str.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4)
								+ Character.digit(str.charAt(i+1), 16));
		}
		//GUI folder = new GUI();
		File currentDir = folder;
		File file = new File(currentDir, "/data/person/static.bin");
		FileOutputStream fooStream = new FileOutputStream(file, false);
		fooStream.write(data);
		fooStream.close();
				}
		catch (Exception e){
			DebugBuilder.DebugOutput("oh no write static");
		}
		}
		
	public int getGamedata(StringBuilder str){
		try {
		//GUI folder = new GUI();
		File currentDir = folder;
		//System.out.println(currentDir);
		File file = new File(currentDir, "/data/GameData.bin");
		FileInputStream fis = new FileInputStream(file);
		DataInputStream din1 = new DataInputStream(fis);
		DataInputStream din2 = new DataInputStream(fis);
		int size = 0;
		
	
		byte[] bytes = new byte[3]; 
		
		din1.read(bytes);
		StringBuilder bsize = new StringBuilder();

		for (int x = 0; x < bytes.length; x++) {
			//String str  = Integer.toHexString((b & 0xff)+256).substring(1);
			bsize.append(Integer.toHexString((bytes[x] & 0xff)+256).substring(1));	
		}
		
		String asize = bsize.substring(4,6) + bsize.substring(2,4)+ bsize.substring(0,2);
		//System.out.println(asize);
		size = Integer.decode("0x" + asize);
		//System.out.println(size);
		str.append(bsize);
		byte[] b2 = new byte [size - 3];
		din2.read(b2);
		for (int y = 0; y < b2.length; y++) {
			//String str  = Integer.toHexString((b & 0xff)+256).substring(1);
			str.append(Integer.toHexString((b2[y] & 0xff)+256).substring(1));	
		}
		return size;
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("Read gamedata fucked");
			return 0;
		}
		
	}
	
	public void writeGamedata(StringBuilder str){
		try{
		//System.out.print(str);
		int len = str.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4)
								+ Character.digit(str.charAt(i+1), 16));
		}
		//GUI folder = new GUI();
		File currentDir = folder;
		File file = new File(currentDir, "/data/GameData.bin");
		FileOutputStream fooStream = new FileOutputStream(file, false);
		fooStream.write(data);
		fooStream.close();
				}
		catch (Exception e){
			DebugBuilder.DebugOutput("gamedaata write");
		}
		}
		
	public int getDispos(StringBuilder str, String cname, int size){
		try{
		//GUI folder = new GUI();
		File currentDir = folder;
		//System.out.println(currentDir + "romfs/data/dispos/" + cname);
		File file = new File(currentDir, ("/data/dispos/" + cname));
		FileInputStream fis = new FileInputStream(file);
		DataInputStream din1 = new DataInputStream(fis);
		DataInputStream din2 = new DataInputStream(fis);
		
	
		byte[] bytes = new byte[2]; 
		
		din1.read(bytes);
		StringBuilder bsize = new StringBuilder();

		for (int x = 0; x < bytes.length; x++) {
			//String str  = Integer.toHexString((b & 0xff)+256).substring(1);
			bsize.append(Integer.toHexString((bytes[x] & 0xff)+256).substring(1));	
		}
		
		String asize = bsize.substring(2,4)+ bsize.substring(0,2);
		//System.out.println(asize);
		size = Integer.decode("0x" + asize);
		//System.out.println(size);
		str.append(bsize);
		byte[] b2 = new byte [size - 2];
		din2.read(b2);
		for (int y = 0; y < b2.length; y++) {
			//String str  = Integer.toHexString((b & 0xff)+256).substring(1);
			str.append(Integer.toHexString((b2[y] & 0xff)+256).substring(1));	
		}
		return size;
		//System.out.println(str);
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("dispos read");
			return 0;
		}
	}
	
		public void writeDispos(StringBuilder str, String cname){
		try{
		//System.out.print(str);
		int len = str.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4)
								+ Character.digit(str.charAt(i+1), 16));
		}
		//GUI folder = new GUI();
		File currentDir = folder;
		File file = new File(currentDir, ("/data/dispos/" + cname));
		FileOutputStream fooStream = new FileOutputStream(file, false);
		fooStream.write(data);
		fooStream.close();
				}
		catch (Exception e){
			DebugBuilder.DebugOutput("dispos write");
		}
		}

	public int getScript(StringBuilder str, String cname, int size){
		try{
		//GUI folder = new GUI();
		File currentDir = folder;
		//System.out.println(currentDir + "romfs/data/dispos/" + cname);
		File file = new File(currentDir, ("/scripts/" + cname));
		FileInputStream fis = new FileInputStream(file);
		DataInputStream din1 = new DataInputStream(fis);
		//DataInputStream din2 = new DataInputStream(fis);
		
	
		byte[] bytes = new byte[(int) file.length()]; 
		
		size = bytes.length;
		
		fis.read(bytes);
		StringBuilder bsize = new StringBuilder();

		for (int x = 0; x < bytes.length; x++) {
			//String str  = Integer.toHexString((b & 0xff)+256).substring(1);
			str.append(Integer.toHexString((bytes[x] & 0xff)+256).substring(1));	
		}
		return size;
		//System.out.println(str);
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("script read");
			return 0;
		}
	}
	
		public void writeScript(StringBuilder str, String cname){
		try{
		int len = str.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4)
								+ Character.digit(str.charAt(i+1), 16));
		}
		//GUI folder = new GUI();
		File currentDir = folder;
		File file = new File(currentDir, ("/scripts/" + cname));
		FileOutputStream fooStream = new FileOutputStream(file, false);
		fooStream.write(data);
		fooStream.close();
				}
		catch (Exception e){
			DebugBuilder.DebugOutput("script write");
		}
		}

		public String GetText(String fileName){
		//System.out.println("Reading: " + fileName);
		try{
			//GUI folder = new GUI();
			File currentDir = folder;
			Path path = Paths.get(currentDir.getPath() + "/m/E/" + fileName);
			Charset charset = StandardCharsets.UTF_8;

			return new String(Files.readAllBytes(path), charset);
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("could not get text: " + fileName);
			return "";
		}
		}

		public void WriteText(String fileName, String newText){
			DebugBuilder.DebugOutput("Writing text: " + fileName);
			try {
				//GUI folder = new GUI();
				File currentDir = folder;
				Path path = Paths.get(currentDir.getPath() + "/m/E/" + fileName);
				//some black magic occurred and it works now. I fear removing anything will make it not work
				Charset charset = StandardCharsets.UTF_8;
				/*
				String test = new String(Files.readAllBytes(path), charset);
				test = test.replaceAll("クロム", "ヴェイク");
				StringBuilder fuck = new StringBuilder();
				fuck.append(test);*/
				Files.write(path, newText.getBytes(charset));
			}
			catch (Exception e){
				DebugBuilder.DebugOutput("could not write text");
			}
		}

		public boolean TextExists(String fileName){
			//GUI folder = new GUI();
			File currentDir = folder;
			File file = new File(currentDir, ("/m/E/" + fileName));
			return file.exists();
		}
}