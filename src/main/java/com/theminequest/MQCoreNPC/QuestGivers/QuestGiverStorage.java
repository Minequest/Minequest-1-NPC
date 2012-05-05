package com.theminequest.MQCoreNPC.QuestGivers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.spi.IniBuilder;


public class QuestGiverStorage {
	private File file;
	private String name;
	private long id;
	private Location location;
	private String skin;
	private String cape;
	private String noquestmessage;
	private String havequestmessage;
	private String[] quests;
	private long[] recommend;
	
	/**
	 * Load an NPC description object from a file.
	 * @param f
	 * @throws InvalidFileFormatException
	 * @throws IOException
	 */
	public void LoadNPCDescription(File f) throws InvalidFileFormatException, IOException{
		Ini ini = new Ini();
		ini.load(f);
		file = f;
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
		recommend = recommendsection.getAll("recommend", long[].class);
	}
	
	/**
	 * Save the NPC description to a file.
	 * @throws IOException if unable to save.
	 */
	private void save() throws IOException{
		Ini ini = new Ini();
		Ini.Section properties = ini.add("Properties");
		properties.put("name", name);
		properties.put("id",id);
		properties.put("world", location.getWorld().getName());
		properties.put("locX", location.getX());
		properties.put("locY", location.getY());
		properties.put("locZ", location.getZ());
		properties.put("skin", skin);
		properties.put("cape", cape);
		properties.put("noquest", noquestmessage);
		properties.put("havequest",havequestmessage);
		Ini.Section questsection = ini.add("Quests");
		questsection.add("give", quests);
		Ini.Section recommendsection = ini.get("Recommendations");
		recommendsection.add("recommend",recommend);
		ini.store(file);
	}
}
