/*
 * MineQuest - Bukkit Plugin for adding RPG characteristics to Minecraft
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
 */
package com.theminequest.MQCoreNPC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;

import com.theminequest.MineQuest.MineQuest;
import com.topcat.npclib.NPCManager;
import com.topcat.npclib.entity.NPC;
import com.topcat.npclib.nms.NPCEntity;

public class NPCLoader {
	public String name;
	public NPCManager npcManager = new NPCManager(MQCoreNPC.activePlugin);
	
	private static String getName(String name) {
		return name.replaceAll(" ", "_");
	}
	
	public List<NPC> npcs() {
		return npcManager.getNPCs();
	}
	
	public void loadNPCs(World world, double x, double y, double z, float yaw, float pitch) {
		Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
		npcManager.spawnHumanNPC(name, spawnLocation);
	}
	
}