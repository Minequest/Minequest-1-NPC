package com.theminequest.MQCoreNPC.GeneralNpc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.util.Java15Compat;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.yaml.snakeyaml.Yaml;

import com.theminequest.MQCoreNPC.MQCoreNPC;
import com.theminequest.MineQuest.MineQuest;

public class GeneralNpcManager {
	
	private File file;
	private String name;
	private long id;
	private Location location;
	private String skin;
	private String cape;
	private String[] rumors;
	
	public void CreateGeneralNPC(String n, Location l) throws IOException{
		name = n;
		id = 0; // Implement some sort of tracking mechanism in NPCManager
		location = l;
		skin = "http://www.minecraft.net/images/char.png";
		cape = "";
		rumors = null;
		file = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/"+name+".npc");
		file.createNewFile();
	}

	/**
	 * Create an NPC description object from a file.
	 * @param f
	 * @throws InvalidFileFormatException
	 * @throws IOException
	 */
	public void NPCDescription(File f) throws InvalidFileFormatException, IOException{
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
	}
	
	/**
	 * Save the NPC description to a file.
	 * @throws IOException if unable to save.
	 */
	public void save() throws IOException{
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
		ini.store(file);
	}
	
	/* Automatically generated getters and setters by Eclipse */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
