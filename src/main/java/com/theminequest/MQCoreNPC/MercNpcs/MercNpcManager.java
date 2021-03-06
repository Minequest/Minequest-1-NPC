package com.theminequest.MQCoreNPC.MercNpcs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.util.Java15Compat;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.yaml.snakeyaml.Yaml;

import com.theminequest.MQCoreNPC.MQCoreNPC;
import com.topcat.npclib.NPCManager;
import com.topcat.npclib.entity.NPC;

public class MercNpcManager {
	private File file;
	private String name;
	private String skin;
	private String cape;
	private boolean vulnerable;
	private boolean isAMerc;
	private boolean retaliate;
	private Location location;     
	private double locX;
	private double locY;
	private double locZ;
	private boolean wander;
	private double wanderDistance;
	private String[] hitMessages;
	private double killexp;
	private double killgold;
	private String[] itemDrops;
	
	//Pathing - Should work.
	ArrayList<Location> pathing;
	private Location nextLoc;
	
	private MercNpcLoader storage = new MercNpcLoader();
	public NPCManager MercNpcManager = new NPCManager(MQCoreNPC.activePlugin);
	
	/**
	 * Creates a default file for the given npc name and location. 
	 * @param n
	 * @param l
	 * @throws IOException
	 */
	public void createMercNPC(String n, Location l) throws IOException{
		name = n.replaceAll(" ", "_");;
		location = l;
		skin = "http://www.minecraft.net/images/char.png";
		cape = "";
		vulnerable = false;
		isAMerc = false;
		hitMessages = new String[0];
		file = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/"+name+".npc");
		file.createNewFile();
		storage.save(name, location, skin, cape, hitMessages, vulnerable);
		spawnNPC(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}
	
	public void loadPathing(String n, ArrayList<Location> pathing){
		int x = 0;
		while ( x<= pathing.size()){
			NPC npc = getNpc(n);
			npc.walkTo(pathing.get(x));
			x++;
		}
	}
	
	public NPC getNpc(String npcName){
		NPC npc = MercNpcManager.getNPC(npcName);
		return npc;
	}
	
	public List<NPC> npcs() {
		return MercNpcManager.getNPCs();
	}
	
	public void spawnNPC(World world, double x, double y, double z, float yaw, float pitch) {
		Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
		MercNpcManager.spawnHumanNPC(name, spawnLocation);
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
		hitMessages = storage.getQuests();
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
}