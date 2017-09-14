package com.example.hospital;

import java.util.ArrayList;

public class LoginManager {
	ArrayList<String> data;
	
	public LoginManager(ArrayList<String> data){
		this.data = data;
	}
	
	public String getID(String line){
		return line.split(",")[0];
	}
	
	public String getPass(String line){
		return line.split(",")[1];
	}
	
	public String getName(String line){
		return line.split(",")[2];
	}
	
	public boolean check(String user, String pass){
		for (String s: data){
			if (getID(s).equals(user) && getPass(s).equals(pass)){
				return true;
			}
		}
		return false;
	}
	
	public String getN(String user, String pass){
		for (String s: data){
			if (getID(s).equals(user) && getPass(s).equals(pass)){
				return getName(s);
			}
		}
		return "";
	}
}
