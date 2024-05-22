package main.Program;

import java.io.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.*;
import java.lang.reflect.Member;
import java.util.concurrent.Flow;
import java.util.Random;

import main.Main;
import main.Output.DebugBuilder;
import org.apache.commons.io.IOUtils;

public class GUI extends JPanel implements ActionListener{

	JButton go;
	JButton Randomize;
	JButton fileRandomButt;
	JButton cancelFile;
	
	JCheckBox Chara;
	JCheckBox reverseRecruitment;
	JCheckBox Classes;
	JCheckBox Cutscenes;
	JCheckBox Supports;
	JCheckBox createText;
	JCheckBox createXML;
	JCheckBox createDebug;
	//Stat Checkboxes
	JRadioButton statsUnchanged;
	JRadioButton statsGrowthScale;
	JRadioButton statsGrowthRed;
	JRadioButton statsBaseRed;
	JRadioButton statShuffle;
	JRadioButton statRand;
	
	JLabel Finished;
	JLabel Err;
	JLabel xmlFileMsg;

	JLabel bigNah;
	JLabel bigNahLeft;
	JFrame Rando;

	//cool nah images :)
	public  ImageIcon nahIcon = new ImageIcon(Main.class.getResource("Resources/NahIconBig3.png"));
	public  ImageIcon nahOops = new ImageIcon(Main.class.getResource("Resources/NahOops.png"));
	public static ImageIcon nahChilling = new ImageIcon(Main.class.getResource("Resources/NahIdle.gif"));
	public ImageIcon nahBack = new ImageIcon(Main.class.getResource("Resources/NahLost.gif"));
	public ImageIcon nahSelect = new ImageIcon(Main.class.getResource("Resources/NahSelect.gif"));
	public ImageIcon nahDead = new ImageIcon(Main.class.getResource("Resources/NahDead.png"));
	public ImageIcon evilNah = new ImageIcon(Main.class.getResource("Resources/NahKnightEvil.gif"));
	public ImageIcon evilNah2 = new ImageIcon(Main.class.getResource("Resources/NahMerc2.gif"));
	public ImageIcon evilNahLeft = new ImageIcon(Main.class.getResource("Resources/NahKnightEvilFlip.gif"));


	JFileChooser chooser;
	String choosertitle;
	
	ImageIcon nah;
	public static File folder = new File ("nah way");
	File xmlFile = new File("naht gonna happen");
	
	boolean ch;
	boolean cl;
	boolean fileRandomization;
	private JButton button1;

	public GUI() {
	  //Check folder button
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
		DebugBuilder.DebugOutput("getCurrentDirectory(): "
				+  main.Program.GUI.folder);
		try {
			//checks that its not named romfs
			if (!GUI.folder.getName().equals("romfs")) {
				fc.checkFiles(main.Program.GUI.folder);
				((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
				secondWindow();
			}
			else FourthWinodow();
		}
		catch (Exception e1){
			thirdWindow();
		}
      }
    else {
		DebugBuilder.DebugOutput("romfs chooser failed");
      }
     }
	 
	 public void secondWindow(){
		Rando = new JFrame("Odd Rhythm - FEA Randomizer");
		Rando.setIconImage(nahIcon.getImage());
		Rando.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rando.getContentPane().setLayout(new BoxLayout(Rando.getContentPane(), BoxLayout.Y_AXIS));
		
		Main man = new Main();
		//Initializes everything to do with the top part of the grid
		 JPanel optionsPanel = new JPanel();
		 optionsPanel.setLayout(new GridLayout(2, 1));
		 //top panel that will let me divide things better
		 JPanel topOptions = new JPanel();
		 topOptions.setLayout(new GridLayout(1, 3));
		 //Character panel
		 JPanel characterPanel = new JPanel();
		 characterPanel.setBorder(new TitledBorder("Recruitment order"));
		 characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));
		 //panel for character and rr
		JPanel charOption = new JPanel();
		charOption.setLayout(new FlowLayout());
		 //Character Panel elements
		 Chara = new JCheckBox("Randomize fully");
		 Chara.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent a1){
                 ch = Chara.isSelected();
				 EnableCharsContent();
				 reverseRecruitment.setSelected(false);
				 reverseRecruitment.setEnabled(!Chara.isSelected());
				 EnableRandomizeButton();
			 }
		 });
		reverseRecruitment = new JCheckBox("Reverse recruitment");
		 reverseRecruitment.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent a){
				 Chara.setSelected(false);
				 Chara.setEnabled(!reverseRecruitment.isSelected());
				 EnableCharsContent();
				 fileRandomization = reverseRecruitment.isSelected();
				 EnableRandomizeButton();
			 }
		 });
		charOption.add(Chara);
		charOption.add(reverseRecruitment);
		 //rest of char elements
		 Cutscenes = new JCheckBox("Swap Story & Cutscenes");
		 Supports = new JCheckBox("Swap Supports Convos");
		 EnableCharsContent();
		 //fill out Character panel and add to option panel
		 characterPanel.add(Box.createVerticalGlue());
		 characterPanel.add(charOption);
		 Cutscenes.setAlignmentX(CENTER_ALIGNMENT);
		 characterPanel.add(Cutscenes);
		 Supports.setAlignmentX(CENTER_ALIGNMENT);
		 characterPanel.add(Supports);
		 characterPanel.add(Box.createVerticalGlue());
		 topOptions.add(characterPanel);

		 //classes panel
		 JPanel classesPanel = new JPanel();
		 classesPanel.setBorder(new TitledBorder("Class Sets"));
		 classesPanel.setLayout(new BoxLayout(classesPanel, BoxLayout.Y_AXIS));
		 //Class panel elements
		 Classes = new JCheckBox("Randomize");
		 Classes.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent a1){
                 cl = Classes.isSelected();
				 EnableRandomizeButton();
			 }
		 });
		 //Fill out class panel and add to options panel
		 classesPanel.add(Box.createVerticalGlue());
		 classesPanel.add(Classes);
		 classesPanel.add(Box.createVerticalGlue());
		 topOptions.add(classesPanel);
		 //stats panel
		 JPanel statsPanel = new JPanel();
		 statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
		 statsPanel.setBorder(new TitledBorder("Stat Modification"));
		 //stats panel elements
		 statsUnchanged = new JRadioButton("Default", true);
		 statsGrowthRed = new JRadioButton("Growth Redistribution", false);
		 statsBaseRed = new JRadioButton("Base Stats Redistribution", false);
		 statsGrowthScale = new JRadioButton("Level Scaled", false);
		 statShuffle = new JRadioButton("Shuffle Base", false);
		 statRand = new JRadioButton("Randomize from Base", false);
		 ButtonGroup statGroup = new ButtonGroup();
		 statGroup.add(statsUnchanged);
		 statGroup.add(statsGrowthRed);
		 statGroup.add(statsBaseRed);
		 statGroup.add(statsGrowthScale);
		 statGroup.add(statShuffle);
		 statGroup.add(statRand);
		 ///RR out text panel and add to options panel
		 statsPanel.add(Box.createVerticalGlue());
		 statsPanel.add(statsUnchanged);
		 statsPanel.add(statsGrowthRed);
		 statsPanel.add(statsBaseRed);
		 statsPanel.add(statsGrowthScale);
		 statsPanel.add(statShuffle);
		 statsPanel.add(statRand);
		 statsPanel.add(Box.createVerticalGlue());
		 topOptions.add(statsPanel);
		 //top part of options is done, we add to the options panel
		 optionsPanel.add(topOptions);

		 //botom panels
		 JPanel bottomPanel = new JPanel();
		 bottomPanel.setLayout(new GridLayout(1, 2));
		 //File panel
		 JPanel filePanel = new JPanel();
		 filePanel.setLayout(new FlowLayout());
		 filePanel.setBorder(new TitledBorder("Randomize from File"));
		 //Text panel elements
		 fileRandomButt = new JButton("Select file");
		 fileRandomButt.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent a3){
				 try {
					 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				 }
				 catch (Exception e) {
					 DebugBuilder.DebugOutput("Failed to read xml");
				 }
				 JFileChooser xmlChooser = new JFileChooser();
				 xmlChooser.setCurrentDirectory(new java.io.File("."));
				 xmlChooser.setDialogTitle(choosertitle);
				 xmlChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				 FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				 xmlChooser.setFileFilter(xmlFilter);
				 if (xmlChooser.showOpenDialog(fileRandomButt) == JFileChooser.APPROVE_OPTION) {
					 try {
						xmlFile = xmlChooser.getSelectedFile();
						xmlFileMsg.setText(xmlFile.getName() + " has been selected for randomization.");
						ToggleFileRandomization(true);
					 }
					 catch (Exception e1){
						 DebugBuilder.DebugOutput("Failed to open xml file");
					 }
				 }
			 }
		 });
		 cancelFile = new JButton("Cancel");
		 cancelFile.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent a2){
					 ToggleFileRandomization(false);
		 }
		 });
		 xmlFileMsg = new JLabel("lol");
		 ///RR out text panel and add to options panel
		 filePanel.add(Box.createVerticalGlue());
		 filePanel.add(fileRandomButt);
		 filePanel.add(xmlFileMsg);
		 filePanel.add(cancelFile);
		 filePanel.add(Box.createVerticalGlue());
		 bottomPanel.add(filePanel);
		 //Other panel
		 JPanel otherPanel = new JPanel();
		 otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.Y_AXIS));
		 otherPanel.setBorder(new TitledBorder("Other Options"));
		 //Text panel elements
		 createText = new JCheckBox("Generate text file");
		 createXML = new JCheckBox("Generate XML file");
		 createDebug = new JCheckBox("Generate debug file");
		 ///RR out text panel and add to options panel
		 otherPanel.add(Box.createVerticalGlue());
		 otherPanel.add(createText);
		 otherPanel.add(createXML);
		 otherPanel.add(createDebug);
		 otherPanel.add(Box.createVerticalGlue());
		 bottomPanel.add(otherPanel);

		 optionsPanel.add(bottomPanel);
		 //options panel finished, so we add to main panel
		 Rando.add(optionsPanel);

		 //lower grid start
		 JPanel randomizePanel = new JPanel();
		 randomizePanel.setLayout(new BoxLayout(randomizePanel, BoxLayout.Y_AXIS));
		 //Combines buttons and images into single panel
		 JPanel nahSection = new JPanel();
		 nahSection.setLayout(new FlowLayout());
		 //nahSection elements
		 Randomize = new JButton("Randomize");
		 Randomize.setEnabled(false);
		 Randomize.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent a2){
				 try {
					 Finished.setVisible(false);
					 Err.setVisible(false);
					 man.randomizeShit(SetRandomizationBools(), main.Program.GUI.folder, xmlFile);
					 Finished.setVisible(true);
					 ImageIcon[] randomNahs = RandomizeNah();
					 ChangeNah(randomNahs[0], randomNahs[1]);
					 UpdateRandomizeButton();
				 }
				 catch (Exception e) {
					 DebugBuilder.DebugOutput("Couldn't run main");
					 ChangeNah(evilNah, evilNahLeft);
					 Err.setVisible(true);
					 UpdateRandomizeButton();
				 }
				 //debug placed here instead of main in case main explodes, I still want the debug
				 if (createDebug.isSelected()){
                     try {
                         DebugBuilder.PrintAllOutput();
                     } catch (FileNotFoundException | UnsupportedEncodingException e) {
                         throw new RuntimeException(e);
                     }
                 }
			 }
		 });
		 bigNah = new JLabel(ResizeImage(nahSelect, 128));
		 bigNahLeft = new JLabel(ResizeImage(nahDead, 128));
		 //adds all elements to nah section
		 nahSection.add(bigNahLeft);
		 nahSection.add(Randomize);
		 nahSection.add(bigNah);
		 //message Section
		 Finished = new JLabel("<html><font color='green'>Game successfully randomized!</font></html>");
		 Err = new JLabel("<html><font color='red'>Oops! Randomization failed. Please try again with clean files</font></html>");
		Finished.setVisible(false);
		Err.setVisible(false);
		//empty panel cus I can't get the stupid messages to align
		 JPanel emptyPanel = new JPanel();
		 emptyPanel.add(Finished);
		 emptyPanel.add(Err);
		//adds all elements to randomize panel
		 randomizePanel.add(Box.createVerticalGlue());
		 randomizePanel.add(nahSection);
		 randomizePanel.add(emptyPanel);
		 randomizePanel.add(Box.createVerticalGlue());

		 //randomize panel is done so we add to main panel
		 Rando.add(randomizePanel);


		 //initializes randomization in false (mainly for UI purposes)
		 ToggleFileRandomization(false);

		Rando.setSize(775, 550);
		Rando.setLocation(600, 250);
		Rando.setVisible(true);
	 }

	 private GUIBools SetRandomizationBools(){
		GUIBools randoBools = new GUIBools();
		randoBools.characters = ch;
		randoBools.classes = cl;
		randoBools.cutscenes = Cutscenes.isSelected();
		randoBools.supports = Supports.isSelected();
		randoBools.xml = fileRandomization;
		randoBools.textFile = createText.isSelected();
		randoBools.newXml = createXML.isSelected();
		randoBools.debugFile = createDebug.isSelected();
		randoBools.reverseRecruitment = reverseRecruitment.isSelected();
		int stats = 0;
		if (statsGrowthRed.isSelected()) stats = 1;
		else if (statsBaseRed.isSelected()) stats = 2;
		else if (statsGrowthScale.isSelected()) stats = 3;
		else if (statShuffle.isSelected()) stats = 4;
		else if (statRand.isSelected()) stats = 5;
		randoBools.statState = stats;
		return randoBools;
	 }

	 private void EnableRandomizeButton(){
         Randomize.setEnabled(ch || cl || fileRandomization);
	 }

	 private void EnableCharsNClasses(boolean toggle){
		Chara.setEnabled(toggle);
		Chara.setSelected(false);
		ch = false;
		Classes.setEnabled(toggle);
		Classes.setSelected(false);
		cl = false;
		reverseRecruitment.setEnabled(toggle);
		reverseRecruitment.setSelected(false);
		EnableCharsContent();
	 }

	 private void EnableCharsContent(){
		boolean isEnabled = Chara.isSelected() || reverseRecruitment.isSelected();
		Cutscenes.setEnabled(isEnabled);
		Supports.setEnabled(isEnabled);
		if (!isEnabled) {
			Cutscenes.setSelected(false);
			Supports.setSelected(false);
		}
	 }

	 private void EnableStatButts(Boolean toggle){
		statsGrowthScale.setEnabled(toggle);
		statRand.setEnabled(toggle);
		statShuffle.setEnabled(toggle);
		statsBaseRed.setEnabled(toggle);
		statsUnchanged.setEnabled(toggle);
		statsGrowthRed.setEnabled(toggle);
	 }

	 private void ToggleFileRandomization(boolean toggle){
		//disables/enables characters and classes depending on whether it's active
		EnableCharsNClasses(!toggle);
		EnableStatButts(!toggle);
		//disables buttons and message depending on scenario
		fileRandomButt.setVisible(!toggle);
		cancelFile.setVisible(toggle);
		xmlFileMsg.setVisible(toggle);
		 //toggles file randomization variable for main program
		 fileRandomization = toggle;
		 //enables the randomizatoin button if appropriate
		 EnableRandomizeButton();
	 }
	 
	 public void thirdWindow(){
		JFrame Windo = new JFrame("Error");
		Windo.setIconImage(nahOops.getImage());
		//Windo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Windo.getContentPane().setLayout(new BoxLayout(Windo.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		//panel.setBounds(15, 40, 1250, 200);
		JLabel text = new JLabel("Error: Correct files not found. Please make sure you have the correct folder structure and all of the correct files and then try again.");
		
		panel.add(text);
		//nah gif
		 JLabel nahLost = new JLabel(ResizeImage(nahBack, 128));
		 panel.add(nahLost);

		 Windo.add(Box.createVerticalGlue());
		Windo.getContentPane().add(panel);
		Windo.add(Box.createVerticalGlue());
		Windo.setSize(810, 200);
		Windo.setLocation(500, 415);
		Windo.setVisible(true);
	 }
	public void FourthWinodow(){
		JFrame Windo = new JFrame("Error");
		Windo.setIconImage(nahOops.getImage());
		//Windo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Windo.getContentPane().setLayout(new BoxLayout(Windo.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		//panel.setBounds(15, 40, 1250, 200);
		JLabel text = new JLabel("Error: Please do not name your input folder romfs. NAME IT LITERALLY ANYTHING ELSE");

		panel.add(text);
		//nah gif
		JLabel nahLost = new JLabel(ResizeImage(evilNah2, 128));
		panel.add(nahLost);

		Windo.add(Box.createVerticalGlue());
		Windo.getContentPane().add(panel);
		Windo.add(Box.createVerticalGlue());
		Windo.setSize(810, 200);
		Windo.setLocation(500, 415);
		Windo.setVisible(true);
	}

	 public void UpdateRandomizeButton(){
		for(ActionListener al :Randomize.getActionListeners()){
			Randomize.removeActionListener(al);
		}

		Randomize.setText("Return");
		Randomize.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent a2){
				 try {
					 Rando.dispose();
					 FirstWindow();
				 }
				 catch (Exception e) {
					 DebugBuilder.DebugOutput("idk how this happened");
				 }
			 }
		 });
	 }

	 public ImageIcon ResizeImage(ImageIcon img, int size){
		Image newImage = img.getImage();
		Image resizedImage = newImage.getScaledInstance(size, size,Image.SCALE_DEFAULT);
		return new ImageIcon(resizedImage);
	 }

	 public void ChangeNah(ImageIcon rightNah, ImageIcon leftNah){
		bigNah.setIcon(ResizeImage(rightNah, 128));
		bigNahLeft.setIcon(ResizeImage(leftNah, 128));
	 }

	 public ImageIcon[] RandomizeNah(){
		String[] randomNahs = new String[]{"Assasin", "GreatKnight", "Griffon", "Pegasus", "Priest", "Sage", "Sniper", "Tactician", "Thief", "Troubadour"};
		Random rand = new Random();
		int randInt = rand.nextInt(10);
		String firstNah = "Nah" + randomNahs[randInt];
		String secondNah = firstNah + "Flipped";

		return new ImageIcon[]{new ImageIcon(Main.class.getResource("Resources/NahRandom/" + firstNah + ".gif")), new ImageIcon(Main.class.getResource("Resources/NahRandomFlipped/" + secondNah + ".gif"))};
	 }
	 
//	public void setFolder(File folder){
//		this.folder = folder;
//	}
	 
//	public File getFolder(){
//		return this.folder;
//	}

  public static void main(String s[]){
		FirstWindow();
    }

	public static void FirstWindow(){
		//sets title and app icon
		JFrame frame = new JFrame("Odd Rhythm - FEA Randomizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		GUI panel = new GUI();
		frame.setIconImage(panel.nahIcon.getImage());
		frame.addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				}
		);
		//Central panel
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new FlowLayout());
		//sets panel content
		//panel.setLocation(75, 50);
		//panel.setBounds(90, 50, 250, 50);
		//frame.getContentPane().add(panel);
		centralPanel.add(panel);
		//sets nah gif
		JLabel nahIdle = new JLabel(panel.ResizeImage(nahChilling, 128));
		//nahIdle.setBounds(0, 0, 128, 128);
		//frame.getContentPane().add(nahIdle);
		centralPanel.add(nahIdle);
		frame.add(Box.createVerticalGlue());
		frame.add(centralPanel);
		frame.add(Box.createVerticalGlue());
		//frame size and visibility
		frame.setSize(400, 250);
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

