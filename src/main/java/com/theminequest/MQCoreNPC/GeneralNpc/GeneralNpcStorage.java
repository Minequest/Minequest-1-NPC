package com.theminequest.MQCoreNPC.GeneralNpc;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.theminequest.MQCoreNPC.MQCoreNPC;
import com.theminequest.MQCoreNPC.QuestGivers.QuestGiverManager;

public class GeneralNpcStorage {
	private File file;
	private String name;
	private long id;
	private Location location;
	private String skin;
	private String cape;
	private String noquestmessage;
	private String havequestmessage;
	private String[] quests;
	private String[] recommend;
	
	/**
	 * Load an NPC description object from a name.
	 * @param f
	 * @throws InvalidFileFormatException
	 * @throws IOException
	 */
	public void LoadNPCDescription(String name) throws InvalidFileFormatException, IOException{
		Ini ini = new Ini();
		file = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/"+name+".npc");
		ini.load(file);
		
		/*
		 * General NPC Properties. This includes the name, ID number,
		 * location of the NPC, and messages it tells the user.
		 * In addition, if you have Spoutcraft, you can give the NPC
		 * a skin and cape.
		 */
		Ini.Section properties = ini.get("Properties");
		name = properties.get("name");
		id = Long.parseLong(properties.get("id"));
		String world = properties.get("world");
		double locationX = Double.parseDouble(properties.get("locX"));
		double locationY = Double.parseDouble(properties.get("locY"));
		double locationZ = Double.parseDouble(properties.get("locZ"));
		location = new Location(Bukkit.getWorld(world),locationX,locationY,locationZ);
		if (properties.containsKey("skin")) skin = properties.get("skin");
		else skin = "http://www.minecraft.net/images/char.png";
		if (properties.containsKey("cape")) cape = properties.get("cape");
		else cape = "";
		if (properties.containsKey("noquest")) noquestmessage = properties.get("noquest");
		else noquestmessage = "Sorry, I don't have anything for ya.";
		if (properties.containsKey("havequest")) havequestmessage = properties.get("havequest");
		else havequestmessage = "Let's get started.";
		
		/*
		 * Quests - What quests does this NPC make available?
		 */
		Ini.Section questsection = ini.get("Quests");
		quests = questsection.getAll("give", String[].class);
		
		/*
		 * Recommendations - When all quests are completed (all
		 * are non-repeating and this NPC has no more to give),
		 * which other NPCs do you want to recommend the player
		 * go to for quests? (by ID number)
		 */
		Ini.Section recommendsection = ini.get("Recommendations");
		recommend = recommendsection.getAll("recommend", String[].class);
	}
	
	public void LoadNPCDescription(File file) throws InvalidFileFormatException, IOException{
		Ini ini = new Ini();
		file = new File(file.getAbsolutePath());
		ini.load(file);
		
		/*
		 * General NPC Properties. This includes the name, ID number,
		 * location of the NPC, and messages it tells the user.
		 * In addition, if you have Spoutcraft, you can give the NPC
		 * a skin and cape.
		 */
		Ini.Section properties = ini.get("Properties");
		name = properties.get("name");
		id = Long.parseLong(properties.get("id"));
		String world = properties.get("world");
		double locationX = Double.parseDouble(properties.get("locX"));
		double locationY = Double.parseDouble(properties.get("locY"));
		double locationZ = Double.parseDouble(properties.get("locZ"));
		location = new Location(Bukkit.getWorld(world),locationX,locationY,locationZ);
		if (properties.containsKey("skin")) skin = properties.get("skin");
		else skin = "http://www.minecraft.net/images/char.png";
		if (properties.containsKey("cape")) cape = properties.get("cape");
		else cape = "";
		if (properties.containsKey("noquest")) noquestmessage = properties.get("noquest");
		else noquestmessage = "Sorry, I don't have anything for ya.";
		if (properties.containsKey("havequest")) havequestmessage = properties.get("havequest");
		else havequestmessage = "Let's get started.";
		
		/*
		 * Quests - What quests does this NPC make available?
		 */
		Ini.Section questsection = ini.get("Quests");
		quests = questsection.getAll("give", String[].class);
		
		/*
		 * Recommendations - When all quests are completed (all
		 * are non-repeating and this NPC has no more to give),
		 * which other NPCs do you want to recommend the player
		 * go to for quests? (by ID number)
		 */
		Ini.Section recommendsection = ini.get("Recommendations");
		recommend = recommendsection.getAll("recommend", String[].class);
	}
	/**
	 * Save the NPC description to a file.
	 * @throws IOException if unable to save.
	 */
	public void save(String name, Location location, String skin, String cape, String[] hitMessage, boolean vulnerable) throws IOException{
		Ini ini = new Ini();
		Ini.Section properties = ini.add("Properties");
		properties.put("name", name);
		properties.put("Vulnerable", vulnerable);
		properties.put("world", location.getWorld().getName());
		properties.put("locX", location.getX());
		properties.put("locY", location.getY());
		properties.put("locZ", location.getZ());
		properties.put("skin", skin);
		properties.put("cape", cape);
		Ini.Section pathing = ini.add("Pathing");
		pathing.put("Waypoint1", location.getX() + "," + location.getBlockY() + "," + location.getBlockZ());
		ini.store(file);
	}
	
	public String getFile(int n){
		
		return name;
	}
	
	//Getters and setters. 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getCape() {
		return cape;
	}

	public void setCape(String cape) {
		this.cape = cape;
	}

	public String getNoquestmessage() {
		return noquestmessage;
	}

	public void setNoquestmessage(String noquestmessage) {
		this.noquestmessage = noquestmessage;
	}

	public String getHavequestmessage() {
		return havequestmessage;
	}

	public void setHavequestmessage(String havequestmessage) {
		this.havequestmessage = havequestmessage;
	}

	public String[] getQuests() {
		return quests;
	}

	public void setQuests(String[] quests) {
		this.quests = quests;
	}

	public String[] getRecommend() {
		return recommend;
	}

	public void setRecommend(String[] recommend) {
		this.recommend = recommend;
	}
}