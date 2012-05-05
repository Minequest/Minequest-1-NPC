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

import com.theminequest.MQCoreNPC.MQCoreNPC;

public class QuestGiverManager {
	
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
	 * Creates a default file for the given npc name and location. 
	 * @param n
	 * @param l
	 * @throws IOException
	 */
	public void CreateQuestNPC(String n, Location l) throws IOException{
		name = n;
		id = 0; // Implement some sort of tracking mechanism in NPCManager
		location = l;
		skin = "http://www.minecraft.net/images/char.png";
		cape = "";
		noquestmessage = "Sorry, I don't have anything for ya.";
		havequestmessage = "Let's get started.";
		quests = new String[0];
		recommend = new long[0];
		file = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/"+name+".npc");
		file.createNewFile();
	//	save();
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

	public long[] getRecommend() {
		return recommend;
	}

	public void setRecommend(long[] recommend) {
		this.recommend = recommend;
	}
	
	
	
}