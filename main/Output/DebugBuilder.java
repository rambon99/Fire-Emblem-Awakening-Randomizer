package main.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DebugBuilder {

    public static ArrayList<String> debugOutput = new ArrayList<>();
    public static String GenerateNewFilename(String filename, String extension){
        int ind = 1;
        String filePath = GetOutputFolder() + "/" + filename + ind + extension;
        while (new File(filePath).exists() && ind < 100){
            ind++;
            filePath = GetOutputFolder() + "/" + filename + ind + extension;
        }
        return filePath;
    }

    public static String GetOutputFolder(){
        new File(System.getProperty("user.dir") +"/Output").mkdirs();
        return new File(System.getProperty("user.dir") + "/Output").getPath();
    }

    public static void DebugOutput(String output){
        System.out.println(output);
        debugOutput.add(output);
    }

    public static void PrintAllOutput() throws FileNotFoundException, UnsupportedEncodingException {
        String path = GenerateNewFilename("Debug", ".txt");
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (String out : debugOutput){
            //writes it all into a text file
            writer.println(out);
        }
        writer.close();
    }
}
