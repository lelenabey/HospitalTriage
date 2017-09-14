package com.example.hospital;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager{

	private File file;
	
	public FileManager(File file){
		this.file = file;
	}
	
	public ArrayList<String> readFromFile() throws FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream(file));
		ArrayList<String> content = new ArrayList<String>();
		while(sc.hasNextLine()){
			content.add(sc.nextLine());
		}
		sc.close();
		return content;
	}
	
	public String readToPos(int pos) throws FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream(file));
		String line = "";
		int c = 0;
		while(sc.hasNextLine()){
			line = sc.nextLine();
			if (c == pos){
				break;
			}
			c++;
		}
		sc.close();
		return line;
	}
	
	public void saveToFile(String save){
		try{
			FileOutputStream os = new FileOutputStream(file, true);
			os.write((save).getBytes());
			os.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void createFileIfNotExist(){
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modifyFile(String line, int pos, File patientFile, File temp) throws FileNotFoundException{
		if (!temp.exists()){
			try {
				temp.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList<String> content = this.readFromFile();
		FileManager m = new FileManager(temp);
		FileManager p = new FileManager(patientFile);
		p.saveToFile(content.get(pos) + "\n");
		content.set(pos, line);
		for(String s: content){
			m.saveToFile(s);
		}
	}
}
