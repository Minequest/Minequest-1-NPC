package com.theminequest.MQCoreNPC;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.theminequest.MQCoreEvents.RegisterEvents;
import com.theminequest.MQCoreNPC.GeneralNpc.GeneralNpcManager;
import com.theminequest.MQCoreNPC.MercNpcs.MercNpcManager;
import com.theminequest.MQCoreNPC.QuestGivers.QuestGiverManager;
import com.theminequest.MineQuest.MineQuest;

public class MQCoreNPC extends JavaPlugin {
	private static PluginDescriptionFile description = null;
	public static MQCoreNPC activePlugin;
    public static GeneralNpcManager generalNPCs;
    public static QuestGiverManager questNPCs;
    public static MercNpcManager mercNPCs;
	
	/**
	 * Access Permissions via Vault
	 */
	public static Permission permission = null;
	/**
	 * Access Economy via Vault
	 */
	public static Economy economy = null;
	
	
	public static void log(String msg) {
		log(Level.INFO, "[MineQuest-NPC]" + msg);
	}

	public static void log(Level level, String msg) {
		Logger.getLogger("Minecraft").log(level, "[MineQuest-NPC] " + msg);
	}

	public static String getVersion() {
		return description.getVersion();
	}

	public static String getPluginName() {
		return description.getName();
	}
	
	public InputStream resource(String fileName){
		return getResource(fileName+".yml");
	}
	
	private boolean setupPermissions() {
		return (MineQuest.permission != null);
	}

	private boolean setupEconomy() {
		return (MineQuest.economy != null);
	}
	
	@Override
	public void onEnable() {
		if (!getDataFolder().exists())
			getDataFolder().mkdirs();
		description = this.getDescription();
		activePlugin = this;
		
		if (!setupPermissions())
			log(Level.SEVERE,"[Vault] Permissions could not be setup!");
		
		if (!setupEconomy())
			log(Level.SEVERE,"[Vault] Economy could not be setup!");
		
		
		questNPCs = new QuestGiverManager();
		if(getDataFolder().exists()){
			File file = new File(getDataFolder()+"General/");
			if(file.exists()){
				questNPCs.spawnExistingNPCs();
			}
			file = new File(getDataFolder()+"Merc/");
			if(file.exists()){
				mercNPCs.spawnExistingNPCs();
			}
			file = new File(getDataFolder()+"Quest/");
			if(file.exists()){
				questNPCs.spawnExistingNPCs();
			}
		}
		//TODO: Load Command front end.
		//TODO: Load Permanent npcs.
		//TODO: Load properties for npcs.
		//TODO: Check for Minequest core. If not present onDisable();
	}
	
}
