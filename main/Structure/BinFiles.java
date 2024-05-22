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

import com.sun.source.tree.ReturnTree;
import main.FEATCompression.CompressionUtils;
import main.Output.DebugBuilder;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.*;
import org.xml.sax.*;

import main.Program.GUI;

public class BinFiles{
	
	public static File folder;
	//variables related to game data
	static StringBuilder staticBin = null;
	static StringBuilder gameData = null;
	//list variable for multiple files
	static List<String> disposNames;
	static List<StringBuilder> disposFiles;
	static List<String> scriptNames;
	static List<StringBuilder> scriptFiles;
	static List<String> textFileNames;
	static List<String> textFiles;

	public BinFiles(){
		
	}
	
	public static void InitiateBin(File fold){
		folder = fold;
		disposNames = new ArrayList<>();
		disposFiles = new ArrayList<>();
		scriptNames = new ArrayList<>();
		scriptFiles = new ArrayList<>();
		textFileNames = new ArrayList<>();
		textFiles = new ArrayList<>();
		staticBin = null;
		gameData = null;
	}
	public static StringBuilder GetStatic(){
		if (staticBin == null){
			//if there is no static, then creates a new one
			staticBin = new StringBuilder();
			//finds location of file
			File currentDir = folder;
			File file = new File(currentDir, "/data/person/static.bin.lz");
			//decompresses file
			byte[] bytes = CompressionUtils.decompress(file);
			//reads bytes into a string
			int ind = 0;
			for (byte b: bytes){
				staticBin.append(Integer.toHexString((b & 0xff)+256).substring(1));
				ind++;
			}
			//string contains all the JIDs for classes not originally in static.bin, so we add those
			String staticEnd = "4A49445F837D8358835E815B838D815B8368926A004A49445F837D8358835E815B838D815B83688F97004A49445F905F8C528E74926A004A49445F905F8C528E748F97004A49445F83708389836683428393926A004A49445F837083898366834283938F97004A49445F834F838C815B83678369834383678F97004A49445F83578346836C8389838B926A004A49445F83578346836C8389838B8F97004A49445F836F815B8354815B834A815B926A004A49445F837B8345836983438367926A004A49445F837B83458369834383678F97004A49445F8358836983438370815B926A004A49445F8358836983438370815B8F97004A49445F8341835483568393926A004A49445F83418354835683938F97004A49445F83748340838B835283938369834383678F97004A49445F8368838983538393837D8358835E815B926A004A49445F8368838983538393837D8358835E815B8F97004A49445F834F838A837483488393836983438367926A004A49445F834F838A8374834883938369834383678F97004A49445F83948340838B834C8385838A83418F97004A49445F836F8367838B83568358835E815B8F97004A49445F8CAB8ED2926A004A49445F835F815B834E836983438367926A004A49445F835F815B834E8369834383678F97004A49445F835C815B83548389815B926A004A49445F835C815B83548389815B8F97004A49445F835C838B83578383815B926A00";
			staticBin.append(staticEnd.toLowerCase());
			//Calculates the size of the static.bin so that it can insert the hex size at the start of the file
			int size = staticBin.length()/2;
			String end = Integer.toHexString(size);
			String endPointer = end.substring(end.length() - 2,end.length())+ end.substring(end.length() - 4, end.length() - 2);
			staticBin.replace(0, 4, endPointer);
		}
		return staticBin;
	}

	public static void SetStatic(StringBuilder str){
		staticBin = str;
	}

	public static  StringBuilder GetGameData(){
		//similar process to static, except no additions
		if (gameData == null){
			try {
				gameData = new StringBuilder();
				File currentDir = folder;
				File file = new File(currentDir, "/data/GameData.bin.lz");
				byte[] bytes = CompressionUtils.decompress(file);

				for (int x = 0; x < bytes.length; x++) {
					gameData.append(Integer.toHexString((bytes[x] & 0xff)+256).substring(1));
				}
			}
			catch (Exception e){
				DebugBuilder.DebugOutput("Read gamedata fucked");
				return null;
			}
		}
		return gameData;
	}

	public static void SetGameData(StringBuilder str) { gameData = str;}

	public static StringBuilder GetDispos(String chapterName)
	{
		if (!disposNames.contains(chapterName)){
			try {
				File currentDir = folder;
				File file = new File(currentDir, "/data/dispos/" + chapterName + ".lz");
				byte[] bytes = CompressionUtils.decompress(file);

				StringBuilder newDispos = new StringBuilder();
				for (int x = 0; x < bytes.length; x++) {
					newDispos.append(Integer.toHexString((bytes[x] & 0xff)+256).substring(1));
				}
				return newDispos;
			}
			catch (Exception e){
				DebugBuilder.DebugOutput("Read dispos fucked");
				return null;
			}
		}
		else{
			return new StringBuilder(disposFiles.get(disposNames.indexOf(chapterName)));
		}
	}

	public static void SetDispos(StringBuilder newData, String chapterName){
		if (disposNames.contains(chapterName)){
			disposFiles.set(disposNames.indexOf(chapterName), newData);
		}
		else{
			disposFiles.add(newData);
			disposNames.add(chapterName);
		}
	}

	public static StringBuilder GetScript(String scriptName){
		if (!scriptNames.contains(scriptName)){
			try{
				File currentDir = folder;
				File file = new File(currentDir, ("/scripts/" + scriptName));
				FileInputStream fis = new FileInputStream(file);
				byte[] bytes = new byte[(int) file.length()];
				fis.read(bytes);
				StringBuilder newScript = new StringBuilder();
				for (int x = 0; x < bytes.length; x++) {
					newScript.append(Integer.toHexString((bytes[x] & 0xff)+256).substring(1));
				}
				return newScript;
			}
			catch (Exception e){
				DebugBuilder.DebugOutput("script read error");
				return null;
			}
		}
		else{
			return new StringBuilder(scriptFiles.get(scriptNames.indexOf(scriptName)));
		}
	}

	public static void SetScript(StringBuilder scriptData, String scriptName){
		if (scriptNames.contains(scriptName)){
			scriptFiles.set(scriptNames.indexOf(scriptName), scriptData);
		}
		else{
			scriptNames.add(scriptName);
			scriptFiles.add(scriptData);
		}
	}
	public static StringBuilder GetText(String fileName){
		if (!textFileNames.contains(fileName)){
		try{
			File file = new File(folder, "/m/E/" + fileName + ".bin.lz");
			byte[] bytes = CompressionUtils.decompress(file);
			String[] textContent = CompressionUtils.extractMessageArchive(bytes);
			StringBuilder textFile = new StringBuilder();
			for (String line : textContent){
				textFile.append(line);
				textFile.append(System.getProperty("line.separator"));
			}
			return textFile;
		}
		catch (Exception e){
			DebugBuilder.DebugOutput("could not get text: " + fileName);
			return null;
		}}
		else return new StringBuilder(textFiles.get(textFileNames.indexOf(fileName)));
	}
	//adds all modified text files to a list that will be written in the end
	public static void SetText(String fileName, String newText){
		if (textFileNames.contains(fileName)){
			textFiles.set(textFileNames.indexOf(fileName), newText);
		}
		else{
			textFileNames.add(fileName);
			textFiles.add(newText);
		}
	}
		public static boolean TextExists(String fileName){
			//GUI folder = new GUI();
			File currentDir = folder;
			File file = new File(currentDir, ("/m/E/" + fileName + ".bin.lz"));
			return file.exists();
		}



		//writes all modified files to new folder
		public static void WriteAll() throws IOException {
			//checks if romfs folder already exists. If it does, we delete it
			if (Files.exists(Path.of(System.getProperty("user.dir") + "/romfs"))){
				FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "/romfs"));
			}
			//Creates new romfs folder where all the files will be copied into
			String newPath = System.getProperty("user.dir") +"/romfs";
			File romfs = new File(newPath);
			FileUtils.copyDirectory(folder, romfs);
			//overwrites all relevant files
			WriteBinFile(staticBin, "static.bin.lz", "/data/person/", romfs);
			if (gameData != null) WriteBinFile(gameData, "GameData.bin.lz", "/data/", romfs);
			DebugBuilder.DebugOutput("Gamedata written");
			//loop for dispos
			for (int d = 0; d < disposFiles.size(); d++){
				WriteBinFile(disposFiles.get(d), disposNames.get(d) + ".lz", "/data/dispos/", romfs);
			}
			DebugBuilder.DebugOutput("dispos written");
			//loop for script files
			for (int s = 0; s < scriptFiles.size(); s++){
				WriteScriptFile(scriptFiles.get(s), scriptNames.get(s), romfs);
			}
			DebugBuilder.DebugOutput("scripts written");
			//loop for text files
			for (int t = 0; t < textFiles.size(); t++){
				WriteTextFile(textFiles.get(t), textFileNames.get(t), romfs);
			}
			DebugBuilder.DebugOutput("text written");
		}

		//debugging function to list all files that have been edited
		public static void ListAllFiles(){
			DebugBuilder.DebugOutput("DisposFiles");
			for (String dispos : disposNames){
				DebugBuilder.DebugOutput(dispos);
			}
			DebugBuilder.DebugOutput("ScriptFiles");
			for (String scripts : scriptNames){
				DebugBuilder.DebugOutput(scripts);
			}
			DebugBuilder.DebugOutput("ScriptFiles");
			for (String texts : textFileNames){
				DebugBuilder.DebugOutput(texts);
			}
		}

		private static void WriteBinFile(StringBuilder fileContents, String fileName, String fileDirectory, File romfsFolder) throws IOException {
			//turns string into byte data
			int len = fileContents.length();
			byte[] data = new byte[len / 2];
			for (int i = 0; i < len; i += 2) {
				data[i / 2] = (byte) ((Character.digit(fileContents.charAt(i), 16) << 4)
						+ Character.digit(fileContents.charAt(i+1), 16));
			}
			//compresses all that data
			byte[] compressedData = CompressionUtils.compress(data);
			//creates new file
			File file = new File(romfsFolder, (fileDirectory + fileName));
			FileOutputStream fooStream = new FileOutputStream(file, false);
			fooStream.write(compressedData);
			fooStream.close();
		}

		private static void WriteScriptFile(StringBuilder scriptContents, String fileName, File romfsFolder){
			try{
				//turns string into byte data
				int len = scriptContents.length();
				byte[] data = new byte[len / 2];
				for (int i = 0; i < len; i += 2) {
					data[i / 2] = (byte) ((Character.digit(scriptContents.charAt(i), 16) << 4)
							+ Character.digit(scriptContents.charAt(i+1), 16));
				}
				//new file
				File file = new File(romfsFolder, ("/scripts/" + fileName));
				FileOutputStream fooStream = new FileOutputStream(file, false);
				fooStream.write(data);
				fooStream.close();
			}
			catch (Exception e){
				DebugBuilder.DebugOutput("script write");
			}
		}

		private static void WriteTextFile(String text, String fileName, File romfsFolder) throws IOException {
		//we do this to get rid of two extra lines that ruin the archive building
			text = text.replaceFirst(System.getProperty("line.separator"), "");
			text = text.replaceFirst(System.getProperty("line.separator"), "");
			//splits string into text array, which is what compression reads
			String[] textArray = text.split(System.getProperty("line.separator"));
			//turns string array into bytes
			byte[] textBytes = CompressionUtils.makeMessageArchive(textArray);
			//compresses bytes
			byte [] compressedData = CompressionUtils.compress(textBytes);
			// creates new file
			File file = new File(romfsFolder, ("/m/E/" + fileName + ".bin.lz"));
			FileOutputStream fooStream = new FileOutputStream(file, false);
			fooStream.write(compressedData);
			fooStream.close();
		}
}