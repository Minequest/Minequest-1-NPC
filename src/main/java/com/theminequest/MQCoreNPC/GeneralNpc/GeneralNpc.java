package com.theminequest.MQCoreNPC.GeneralNpc;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.theminequest.MQCoreNPC.MQCoreNPC;
import com.theminequest.MQCoreNPC.GeneralNpc.GeneralNpcManager;

public class GeneralNpc {
	private GeneralNpcManager manager;
	private File file;
	private String name;
	private Location location;
	private String skin;
	private String cape;
	private boolean vulnerable;
	private boolean retaliate;
	private boolean wander;
	private double wanderDistance;
	private String[] hitMessages;
	private double killexp;
	private double killgold;
	private String[] itemDrops;
	
	/**
	 * Used to initially spawn npcs. 
	 * Sets everything to default other than name and location.
	 * @param name, location
	 */
	public void GeneralNpc(String name1, Location location1){
		name = name1; 
		location = location1;
	}
	
	/**
	 * Set npcs base values from file
	 * Use GeneralNpc(string, Location) to spawn NPCs.
	 * 
	 * @param name1, location1, skin1, cape1, vulnerable1, retaliate1, wander1, wanderDistance1, hitMessages1, killexp1
	 */
	public void GeneralNpc(String name1, Location location1, String skin1, String cape1, boolean vulnerable1, boolean retaliate1,
		boolean wander1, double wanderDistance1, String[] hitMessages1, double killexp1, double killgold1, String[] itemsDrops1){
		
		name = name1;
		location = location1;
		skin = skin1;
		vulnerable = vulnerable1;
		retaliate = retaliate1;
		wander = wander1;
		wanderDistance = wanderDistance1;
		hitMessages = hitMessages1;
		killexp = killexp1;
		itemDrops = itemsDrops1;
		
	}
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
	
	public void spawnNPC(World world, double x, double y, double z, float yaw, float pitch) {
		Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
		manager.GeneralNPCManager.spawnHumanNPC(name, spawnLocation);
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

}