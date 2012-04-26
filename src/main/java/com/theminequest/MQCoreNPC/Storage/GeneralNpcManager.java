package com.theminequest.MQCoreNPC.Storage;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.util.Java15Compat;
import org.yaml.snakeyaml.Yaml;

import com.theminequest.MQCoreNPC.MQCoreNPC;
import com.theminequest.MineQuest.MineQuest;

public class GeneralNpcManager {
	/**
	 * General npcs files
	 */
	private String generalFileName = "General_Npcs";
	private File general = new File(MineQuest.activePlugin.getDataFolder().getAbsolutePath()+File.separator+"npcs", generalFileName + ".yml");
	private YamlConfiguration generalFile;
	
	/**
	 * Loads new file from defaults.
	 */
	
	private void configCheck(){   
    	new File(generalFileName).mkdir();   
        if(!general.exists()){
            try {
                general.createNewFile();
                addDefaults();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            //Get Values
        }
    }
	
	private void loadDefault() {
		MQCoreNPC.log("Generating General NPC File...");
		
	}
	
	private void addDefaults(){
		
	}
	
   private YamlConfiguration load(){
        try {
            YamlConfiguration genNPC = new YamlConfiguration();
            genNPC.load(general);
            return genNPC;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
   private void write(String root, String x){
	   
   }
	
	private void writeNewNPC(String npcName){
		generalFile = load();
		if (generalFile == null){
			
		}
	}
	
	private void changeValue(String npcName, String valueToChange, String newValue){
		
	}
	
    private Boolean readBoolean(String root){
        YamlConfiguration config = load();
        return config.getBoolean(root);
    }

    private Double readDouble(String root){
        YamlConfiguration config = load();
        return config.getDouble(root);
    }
    
    private int readInt(String root) {
    	YamlConfiguration config = load();
    	return config.getInt(root);
    }
    
	private List<?> readStringList(String root){
        YamlConfiguration config = load();
        return config.getList(root);
    }
    private String readString(String root){
        YamlConfiguration config = load();
        return config.getString(root);
    }
}
