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

import main.Main;
import main.Program.FileChecker;

public class GUI extends JPanel implements ActionListener{
	
	JButton go;
	JButton Randomize;
	
	JCheckBox Chara;
	JCheckBox Classes;
	
	JLabel Finished;
	JLabel Err;

	JFileChooser chooser;
	String choosertitle;
	public static File folder = new File ("gary");
	
	boolean ch;
	boolean cl;
	

  public GUI() {
    go = new JButton("Select Folder");
    go.addActionListener(this);
    add(go);
   }

  public void actionPerformed(ActionEvent e){  
	try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
    chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle(choosertitle);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
		//GUI folda = new GUI();
		FileChecker fc = new FileChecker();
		main.Program.GUI.folder = chooser.getSelectedFile();
		 System.out.println("getCurrentDirectory(): " 
         +  main.Program.GUI.folder);
		try {
			fc.checkFiles(main.Program.GUI.folder);
			((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
			secondWindow();
		}
		catch (Exception e1){
			thirdWindow();
		}
      }
    else {
      System.out.println("");
      }
     }
	 
	 public void secondWindow(){
		JFrame Rando = new JFrame("Fire Emblem Awakening Randomizer");
		Rando.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rando.getContentPane().setLayout(null);
		
		Main man = new Main();
		
		Chara = new JCheckBox("Characters");
		Classes = new JCheckBox("Classes");
		Randomize = new JButton("Randomize");
		Finished = new JLabel("<html><font color='green'>Game successfully randomized!</font></html>");
		Err = new JLabel("<html><font color='red'>Oops! Randomization failed. Please try again with clean files</font></html>");
		
		Randomize.setEnabled(false);
		Finished.setVisible(false);
		Err.setVisible(false);
		
		Classes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a1){
				if (Classes.isSelected()){
					Randomize.setEnabled(true);
					cl = true;
				}
				else {
					if (ch == false){
						Randomize.setEnabled(false);
					}
					cl = false;
				}
			}
		});
		
		Chara.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a1){
				if (Chara.isSelected()){
					ch = true;
					Randomize.setEnabled(true);
				}
				else {
					if (cl == false){
						Randomize.setEnabled(false);
					}
					ch = false;
				}
			}
		});
		
		JPanel panel3 = new JPanel();
		panel3.add(Finished);
		panel3.add(Err);
		
		
		
		Randomize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a2){
				try {
					Finished.setVisible(false);
					Err.setVisible(false);
                    man.randomizeShit(cl, ch);
					Finished.setVisible(true);
                }
				catch (Exception e) {
					System.out.println("Couldn't run main");
					Err.setVisible(true);
                }
				}
			});
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.setBounds(15, 40, 350, 175);
		
		JPanel panel = new JPanel();
		panel.add(Classes);
		panel.add(Chara);
		//panel.setLocation(75, 90);
		//panel.setBounds(600, 600, 150, 50);
		
		JPanel panel2 = new JPanel();
		panel2.add(Randomize);
		//panel.setBounds(100, 100, 250, 250);
		
		container.add(panel, BorderLayout.NORTH);
		container.add(panel2, BorderLayout.SOUTH);
		container.add(panel3, BorderLayout.CENTER);
		
		
		Rando.getContentPane().add(container);
		Rando.setSize(400, 250);
		Rando.setLocation(700, 350);
		Rando.setVisible(true);
	 }
	 
	 public void thirdWindow(){
		JFrame Windo = new JFrame("Error");
		//Windo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Windo.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBounds(15, 40, 1150, 175);
		JLabel text = new JLabel("Error: Correct files not found. Please make sure you have the correct folder structure and all of the correct files and then try again.");
		
		panel.add(text, BorderLayout.CENTER);
		
		Windo.getContentPane().add(panel);		
		Windo.setSize(790, 150);
		Windo.setLocation(500, 415);
		Windo.setVisible(true);
	 }
	 
//	public void setFolder(File folder){
//		this.folder = folder;
//	}
	 
//	public File getFolder(){
//		return this.folder;
//	}

  public static void main(String s[]){
    JFrame frame = new JFrame("Fire Emblem Awakening Randomizer");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
    GUI panel = new GUI();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
          }
        }
      );
	panel.setLocation(75, 50);
	panel.setBounds(90, 50, 150, 50);
    frame.getContentPane().add(panel);
    frame.setSize(350, 200);
	frame.setLocation(700, 350);
    frame.setVisible(true);
    }

//    public static void main(String[] args) {
		//Create and set up the window.
//        JFrame frame = new JFrame("Fire Emblem Awakening Randomizer");
 //       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	
	//	createAndShowGUI panel =  new createAndShowGUI();
		//
		//frame.getContentPane().setLayout(null);
	//	frame.add(panel);
	//	
	//	frame.setSize(350, 200); 
	//	frame.setLocation(700, 350);
	//	//frame.pack();
     //   frame.setVisible(true);
     //   javax.swing.SwingUtilities.invokeLater(new Runnable() {
     //       public void run() {
     //           createAndShowGUI();
     //       }
     //   });
    }