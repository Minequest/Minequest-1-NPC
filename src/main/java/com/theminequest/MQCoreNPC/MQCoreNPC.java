package com.theminequest.MQCoreNPC;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.theminequest.MineQuest.MineQuest;

public class MQCoreNPC extends JavaPlugin {
	private static PluginDescriptionFile description = null;
	
	//Quest Giver npcs
	public FileConfiguration npcQuestGivers = null;
	public File questGivers = null;
	public String questGiversFileName = "Quest_Givers.yml";
	//Mercenaries npcs
	public FileConfiguration npcMercs = null;
	public File mercs = null;
	public String mercsFileName = "Mercenary_Npcs.yml";
	//Merchant npcs
	public FileConfiguration npcMerchant = null;
	public File merchant = null;
	public String merchantFileName = "Merchant_Npcs.yml";
	//General npcs
	public FileConfiguration npcGeneral = null;
	public File general = null;
	public String gerenalFileName = "General_Npcs.yml";
	
	
	public static void log(String msg) {
		log(Level.INFO, msg);
	}

	public static void log(Level level, String msg) {
		Logger.getLogger("Minecraft").log(level, "[MineQuest] " + msg);
	}

	public static String getVersion() {
		return description.getVersion();
	}

	public static String getPluginName() {
		return description.getName();
	}
	
	public File loadNewConfig(String fileName, File storage) {
	    File npcPropFile = new File(MineQuest.activePlugin.getDataFolder().getAbsolutePath()+File.separator+"npcs", fileName);
	    FileConfiguration defaults = YamlConfiguration.loadConfiguration(storage);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = getResource(fileName+".yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        defaults.setDefaults(defConfig);
	    }
	    return npcPropFile;
	}
	
	@Override
	public void onEnable() {
		if (!getDataFolder().exists())
			getDataFolder().mkdirs();
		description = this.getDescription();
		
		//TODO: Load Command front end.
		//TODO: Load Permanent npcs.
		//TODO: Load properties for npcs.
		//TODO: Check for Minequest core. If not present onDisable();
	}
	
}
