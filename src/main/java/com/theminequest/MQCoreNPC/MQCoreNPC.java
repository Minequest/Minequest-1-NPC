package com.theminequest.MQCoreNPC;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.theminequest.MQCoreEvents.RegisterEvents;
import com.theminequest.MineQuest.MineQuest;

public class MQCoreNPC extends JavaPlugin {
	private static PluginDescriptionFile description = null;
	
	/**
	 * Quest Giver npcs
	 */
	public FileConfiguration npcQuestGivers = null;
	public File questGivers = null;
	public String questGiversFileName = "Quest_Givers.yml";
	
	/**
	 * Mercenaries npcs
	 */
	public FileConfiguration npcMercs = null;
	public File mercs = null;
	public String mercsFileName = "Mercenary_Npcs.yml";
	
	/**
	 * Merchant npcs
	 */
	public FileConfiguration npcMerchant = null;
	public File merchant = null;
	public String merchantFileName = "Merchant_Npcs.yml";
	
	/**
	 * General npcs
	 */
	public FileConfiguration npcGeneral = null;
	public File general = null;
	public String gerenalFileName = "General_Npcs.yml";
	
	/**
	 * Access Permissions via Vault
	 */
	public static Permission permission = null;
	/**
	 * Access Economy via Vault
	 */
	public static Economy economy = null;
	
	
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
	
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		return (economy != null);
	}
	
	@Override
	public void onEnable() {
		if (!getDataFolder().exists())
			getDataFolder().mkdirs();
		description = this.getDescription();
		
		/**
		 * Checks For MineQuest for obvious reasons.
		 */
		if (!getServer().getPluginManager().isPluginEnabled("MineQuest-Core")){
			MineQuest.log(Level.SEVERE, "[NPC] You need to have minequest enabled on the server.");
		}
		
		/**
		 * Check If Vault is active. This may prevent some of the dumb questions we get with people trying to use our plugin. 
		 */
		if (!getServer().getPluginManager().isPluginEnabled("Vault")){
			MineQuest.log(Level.SEVERE, "[Core] You Require Vault Vault to run this plugin. Minequest will now shut down.");
			onDisable();
		}
		
		if (!setupPermissions())
			log(Level.SEVERE,"[Vault] Permissions could not be setup!");
		
		if (!setupEconomy())
			log(Level.SEVERE,"[Vault] Economy could not be setup!");
		
		
		//TODO: Load Command front end.
		//TODO: Load Permanent npcs.
		//TODO: Load properties for npcs.
		//TODO: Check for Minequest core. If not present onDisable();
	}
	
}
