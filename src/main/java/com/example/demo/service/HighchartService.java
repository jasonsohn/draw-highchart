package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class HighchartService {

	public String drawHighchart() {
		String value = "";
		//writeJSON();
		readJSON();
		System.out.println("출력");
		return value;
	}
	
	public void writeJSON() {
		JSONObject personInfo = new JSONObject();
		personInfo.put("name", "Garry");
		personInfo.put("age", 20);
		personInfo.put("jobInfo", "programmer");
		
		JSONArray hobbyList = new JSONArray();
		hobbyList.add("programming");
		hobbyList.add("baking");
		hobbyList.add("typing");
		
		personInfo.put("messages", hobbyList);
		
		try {
			FileWriter file = new FileWriter("D:\\workspace\\openiot\\draw-highchart\\test.json");
			file.write(personInfo.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(personInfo);
	}
	
	public void readJSON() {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("D:\\workspace\\openiot\\draw-highchart\\test.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			
			String name = (String) jsonObject.get("name");
			System.out.println("name :: " +name);
			
			long id = (Long) jsonObject.get("id");
			System.out.println("id :: " + id);
			
			// Array
			JSONArray phoneNum = (JSONArray) jsonObject.get("phoneNumbers");
			Iterator<String> iterator = phoneNum.iterator();
			while (iterator.hasNext()) {
				System.out.println("phoneNumbers :: " + iterator.next());
			}
			
			// Object
			JSONArray array = (JSONArray) jsonObject.get("address");
			for(int i = 0; i < array.size(); i++) {
				JSONObject result = (JSONObject) array.get(i);
				System.out.println("result :: " + result.get("zipcode"));
			}
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
}
