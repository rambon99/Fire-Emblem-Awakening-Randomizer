package main.Structure;

import java.util.Random;
import java.util.Arrays;

public class ACharacter implements Cloneable{
	private String name;
	private String pid;
	private String ppid;
	private String fid;
	private String actual;
	private String jName;
	private String voice;
	private String[] classes;
	private String[] skills;
	private int internalLevel;
	private int[] baseStats;
	private int[] growths;
	private String hpid;
	
	private Boolean male;
	private Boolean promoted;
	
	public ACharacter()
	{
		
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException{
      return super.clone();
    }
	
	public void setName(String name){
		this.name = name;
		//System.out.println( name );
	}
	
	public void setActual(String actual){
		this.actual = actual;
		//System.out.println( name );
	}
	
	public void setPid(String pid){
		this.pid = pid;
		//System.out.println( pid );
	}
	
	public void setFid(String fid){
		this.fid = fid;
		//System.out.println( fid );
	}
	
	public void setPpid(String ppid){
		this.ppid = ppid;
	}
	
	public void setClasses(String[] classes){
		this.classes = classes;
		//System.out.println(Arrays.toString(classes));
	}

	public void setSkills(String[] skills){
		this.skills = skills;
	}
	public void setStats(int[] stats){baseStats = stats;}
	public void setGrowths(int[] growths){this.growths = growths;}
	
	public void setHpid(String hpid){
		this.hpid = hpid;
		//System.out.println( list );
	}

	public void setjName(String jName){this.jName = jName;}

	public void setVoice(String voice){this.voice = voice;}
	
	public void setMale(boolean male){
		this.male = male;
	}
	
	public void setPromoted(boolean promoted){
		this.promoted = promoted;
	}

	public void setInternalLevel(int internalLevel) {this.internalLevel = internalLevel;}
	
	public String getName(){
		return this.name;
	}
	
	public String getActual(){
		return this.actual;
	}
	
	public String getPid(){
		return this.pid;
	}
	
		public String getFid(){
		return this.fid;
	}
	
	public String getPpid(){
		return this.ppid;
	}
	public String getjName(){ return this.jName;}
	public String getVoice(){return this.voice;}
	
	public String[] getClasses(){
		return this.classes;
	}
	public String[] getSkills() {return this.skills;}
	public int getInternalLevel() {return this.internalLevel;}
	public int[] getGrowths(){return this.growths;}
	public int[] getBaseStats(){return this.baseStats;}
	
	public String getHpid(){
		return this.hpid;
	}
	
	public boolean isMale(){
		return this.male;
	}
	
	public boolean isPromoted(){
		return this.promoted;
	}
}