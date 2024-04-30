package main.Structure;

import java.util.Random;
import java.util.Arrays;

public class AClasses {
	private String name;
	private String cid;
	private String base;
	private String oc;
	private String wa;
	private String wb;
	private String wc;
	private String fSkill;
	private String sSkill;
	
	private Boolean male;
	private Boolean promoted;
	private int[] classGrowths;
	
	public AClasses()
	{
		
	}
	
	public void setName(String name){
		this.name = name;
		//System.out.println( name );
	}
	
	public void setCid(String cid){
		this.cid = cid;
		//System.out.println( pid );
	}
	
	public void setBase(String base){
		this.base = base;
	}
	
	public void setOc(String oc){
		this.oc = oc;
	}
	
	public void setWa(String wa){
		this.wa = wa;
	}
	
	public void setWb(String wb){
		this.wb = wb;
	}
	
	public void setWc(String wc){
		this.wc = wc;
	}
	
	public void setFSkill(String fSkill){
		this.fSkill = fSkill;
	}
	
	public void setSSkill(String sSkill){
		this.sSkill = sSkill;
	}
	
	public void setMale(boolean male){
		this.male = male;
	}
	
	public void setPromoted(boolean promoted){
		this.promoted = promoted;
	}

	public void setClassGrowths(int[] classGrowths) {this.classGrowths = classGrowths;}
	
	public String getName(){
		return this.name;
	}
	
	public String getCid(){
		return this.cid;
	}
	
	public String getBase(){
		return this.base;	
	}
	
	public String getOc(){
		return this.oc;	
	}
	
	public String getWa(){
		return this.wa;	
	}
	
	public String getWb(){
		return this.wb;	
	}
	
	public String getWc(){
		return this.wc;	
	}
	
	public String getFSkill(){
		return this.fSkill;	
	}
	
	public String getSSkill(){
		return this.sSkill;	
	}
	
	public boolean isMale(){
		return this.male;
	}
	
	public boolean isPromoted(){
		return this.promoted;
	}

	public int[] getClassGrowths() {return this.classGrowths;}
}