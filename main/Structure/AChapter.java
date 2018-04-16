package main.Structure;

import java.util.Random;
import java.util.Arrays;

public class AChapter {
	private String name;
	private String c1;
	private String c2;
	private String c3;
	private String[] c1i;
	private String[] c2i;
	private String c3i;
	
	private int cb1;
	private int cb2;
	private int cb3;
	
	public AChapter()
	{
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setC1(String c1){
		this.c1 = c1;
	}
	
	public void setC2(String c2){
		this.c2 = c2;
	}
	
	public void setC3(String c3){
		this.c3	= c3;
	}
	
	public void setC1i(String[] c1i){
		this.c1i = c1i;
	}
	
	public void setC2i(String[] c2i){
		this.c2i = c2i;
	}
	
	public void setC3i(String c3i){
		this.c3i = c3i;
	}
	
	public void setCb1(int cb1){
		this.cb1 = cb1;
	}
	
	public void setCb2(int cb2){
		this.cb2 = cb2;
	}
	
	public void setCb3(int cb3){
		this.cb3 = cb3;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public String getC1(){
		return this.c1;
	}
	
	public String getC2(){
		return this.c2;
	}
	
	public String getC3(){
		return this.c3;
	}
	
	public String[] getC1i(){
		return this.c1i;
	}
	
	public String[] getC2i(){
		return this.c2i;
	}
	
	public String getC3i(){
		return this.c3i;
	}
	public int getCb1(){
		return this.cb1;
	}
	
	public int getCb2(){
		return this.cb2;
	}
	
	public int getCb3(){
		return this.cb3;
	}
	
}