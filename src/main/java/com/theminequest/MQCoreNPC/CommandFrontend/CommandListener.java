/**
 * This file, CommandListener.java, is part of MineQuest:
 * A full featured and customizable quest/mission system.
 * Copyright (C) 2012 The MineQuest Team
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 **/
package com.theminequest.MQCoreNPC.CommandFrontend;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.server.Direction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.theminequest.MQCoreNPC.MQCoreNPC;
import com.topcat.npclib.NPCManager;

public class CommandListener implements CommandExecutor{
	private String npcName;

	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		Player player = null;
		if (sender instanceof Player)
			player = (Player) sender;
		

		if (args.length == 0){
			if(cmd.getName().equalsIgnoreCase("minequest") && player != null){
				sender.sendMessage("    /npc - (NPC Plugin only)");
				return true;
			}
			
			if(cmd.getName().equalsIgnoreCase("npc") && player != null){
				sender.sendMessage("Npc Commands:");
				sender.sendMessage("    /npc create <Type of Npc> <Name of Npc> - Creates a npc at your location.");
				sender.sendMessage("    /npc remove <Name of Npc> - Removes the npc.");
				sender.sendMessage("    /npc reload - Reloads all properties from the npc flatfile. ");
				return true;
			}
		}

		/*     Npc Commands:
		 * ---------------------
		 * npc create [npc type] [npc name]
		 * npc delete [npc name]
		 * npc reload
		 */

		if(command.equalsIgnoreCase("npc")){
			Location location = player.getLocation();
			if(args[0].equalsIgnoreCase("create")){
				if(args[1].equalsIgnoreCase("quest")){
					npcName = args[2].toString();
					
					if(!args[2].isEmpty()){
						try {
							MQCoreNPC.questNPCs.createQuestNPC(npcName, location);
						} catch (IOException e) {
							player.sendMessage("Creation failed.");
						}
					}
				}
			}
		}
		return false;
	}
}
