package main.Structure;

import java.util.Random;
import java.util.Arrays;

public class ACharacter implements Cloneable{
	private String name;
	private String pid;
	private String ppid;
	private String fid;
	private String actual;
	private String[] classes;
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
	
	public void setHpid(String hpid){
		this.hpid = hpid;
		//System.out.println( list );
	}
	
	public void setMale(boolean male){
		this.male = male;
	}
	
	public void setPromoted(boolean promoted){
		this.promoted = promoted;
	}
	
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
	
	public String[] getClasses(){
		return this.classes;
	}
	
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