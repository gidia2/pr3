package tp1.control;

import java.util.ArrayList;
import tp1.logic.InitializationException;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import tp1.logic.gameobjects.GameObject;

import java.io.FileReader;
import java.io.BufferedReader;


public class InitialConfiguration {
	
	public static final InitialConfiguration NONE = new InitialConfiguration();
	
	public InitialConfiguration() {}
		
	public static InitialConfiguration readFromFile(String filename) throws InitializationException, IOException {
		List<String> descriptions = new ArrayList<>();
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(filename)));
			while (s.hasNextLine()) {
                String alien = s.nextLine();
                descriptions.add(alien);
			}
			return new InitialConfiguration(descriptions);
		}
		catch(FileNotFoundException e) {
			throw new InitializationException("File not found: '" + filename + "'");
		}
		finally{
			if (s != null) {
                s.close();
            }
		}
		
		
	}
	
	private List<String> descriptions;
	
	private InitialConfiguration(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public List<String> getShipDescription(){
		return Collections.unmodifiableList(descriptions);
	}
}
