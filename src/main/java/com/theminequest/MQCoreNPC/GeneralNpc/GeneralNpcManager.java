package com.theminequest.MQCoreNPC.GeneralNpc;

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
import com.theminequest.MQCoreNPC.GeneralNpc.GeneralNpc;
import com.theminequest.MineQuest.MineQuest;
import com.topcat.npclib.NPCManager;
import com.topcat.npclib.entity.NPC;

public class GeneralNpcManager {
	private File file;
	private String name;

	//Pathing - Should work.
	ArrayList<Location> pathing;
	private Location nextLoc;
	
	public NPCManager GeneralNPCManager = new NPCManager(MQCoreNPC.activePlugin);
	
	/**
	 * Creates a default file for the given npc name and location. 
	 * @param n
	 * @param l
	 * @throws IOException
	 */
	public void createQuestNPC(String n, Location l) throws IOException{
		name = n.replaceAll(" ", "_");;
		Location location = l;
		String skin = "http://www.minecraft.net/images/char.png";
		String cape = "";
		boolean vulnerable = false;
		boolean isAMerc = false;
		String[] hitMessages = new String[0];
		file = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/"+name+".npc");
		file.createNewFile();
		GeneralNpc newNpc = new GeneralNpc();
		newNpc.GeneralNpc(name, location);
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
		NPC npc = GeneralNPCManager.getNPC(npcName);
		return npc;
	}
	
	public List<NPC> npcs() {
		return GeneralNPCManager.getNPCs();
	}
	
	public void spawnNPC(World world, double x, double y, double z, float yaw, float pitch) {
		Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
		GeneralNPCManager.spawnHumanNPC(name, spawnLocation);
	}
	/*
	public void spawnExistingNPCs(){
		File f = new File(MQCoreNPC.activePlugin.getDataFolder()+"NPC/"+"General/");
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		int n = 0;
		while (n <= files.size()){
			try {
				GeneralNpc 
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
	*/
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws InvalidFileFormatException 
	 */

	
	/* Automatically generated getters and setters by Eclipse */

	public String getName() {
		return name.replaceAll(" ", "_");
	}

	public void setName(String name) {
		this.name = name;
	}
}