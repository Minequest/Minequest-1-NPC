package com.theminequest.MQCoreNPC;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class MQCoreNPC extends JavaPlugin {
	private static PluginDescriptionFile description = null;
	
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
	
	@Override
	public void onEnable() {
		//TODO: Load Command front end.
		//TODO: Load Permanent npcs.
		//TODO: Load properties for npcs.
		//TODO: Check for Minequest core. If not present onDisable();
	}
	
}
