package com.theminequest.MQCoreNPC.QuestGivers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.spi.IniBuilder;

import com.theminequest.MQCoreNPC.MQCoreNPC;
import com.topcat.npclib.NPCManager;
import com.topcat.npclib.entity.NPC;
import com.topcat.npclib.pathing.NPCPath;

public class QuestGiverManager {
	private File file;
	private String name;
	private Location location;                                                         
	private int locX;                                                                  
	private int locY;                                                                  
	private int locZ;                                                                  
	private String skin;                                                               
	private String cape;                                                               
	private String noquestmessage;
	private String havequestmessage;
	private String[] quests;
	private String[] recommend;
	private QuestGiverStorage storage = new QuestGiverStorage();
	public NPCManager QuestGiverManager = new NPCManager(MQCoreNPC.activePlugin);
	
	/**
	 * Creates a default file for the given npc name and location. 
	 * @param n
	 * @param l
	 * @throws IOException
	 */
	public void createQuestNPC(String n, Location l) throws IOException{
		name = n.replaceAll(" ", "_");;
		location = l;
		skin = "http://www.minecraft.net/images/char.png";
		cape = "";
		noquestmessage = "Sorry, I don't have anything for ya.";
		havequestmessage = "Let's get started.";
		quests = new String[0];
		recommend = new String[0];
		file = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/"+name+".npc");
		file.createNewFile();
		storage.save(name, location, skin, cape, noquestmessage, havequestmessage, quests, recommend);
		spawnNPC(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}
	
	public void loadPathing(String n, ArrayList<Location> path){
		int x = 0;
		while ( x<= path.size()){
			NPC npc = getNpc(n);
			npc.walkTo(path.get(x));
			x++;
		}
	}
	
	public NPC getNpc(String npcName){
		NPC npc = QuestGiverManager.getNPC(npcName);
		return npc;
	}
	
	public List<NPC> npcs() {
		return QuestGiverManager.getNPCs();
	}
	
	public void spawnNPC(World world, double x, double y, double z, float yaw, float pitch) {
		Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
		QuestGiverManager.spawnHumanNPC(name, spawnLocation);
	}
	
	public void spawnExistingNPCs(){
		File f = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/");
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		int n = 0;
		while (n <= files.size()){
			
			try {
				storage.LoadNPCDescription(files.get(n));
				name = storage.getName();
				location = storage.getLocation();
				spawnNPC(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());				
			} catch (InvalidFileFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			n++;
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws InvalidFileFormatException 
	 */
	public void getDescription(String n){
		QuestGiverStorage storage = new QuestGiverStorage();
		try {
			storage.LoadNPCDescription(n);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		name = storage.getName();
		locX = storage.getLocation().getBlockX();
		locY = storage.getLocation().getBlockY();
		locZ = storage.getLocation().getBlockZ();
		skin = storage.getSkin();
		cape = storage.getCape();
		noquestmessage = storage.getNoquestmessage();
		havequestmessage = storage.getHavequestmessage();
		quests = storage.getQuests();
		recommend = storage.getRecommend();
	}
	
	/* Automatically generated getters and setters by Eclipse */

	public String getName() {
		return name.replaceAll(" ", "_");
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