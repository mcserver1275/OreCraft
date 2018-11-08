package com.twt.ore.craft;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_13_R2.ChatComponentText;

public class OreCraftMain extends JavaPlugin {

	public static Map<Location, ItemStack> itemStacks = new HashMap<Location, ItemStack>();
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
		Recipe.addRecipe(this);
		for(String key : getConfig().getKeys(false)) {
			String WorldName = (String) getConfig().get(key + ".location.world");
			int X = getConfig().getInt(key + ".location.x");
			int Y = getConfig().getInt(key + ".location.y");
			int Z = getConfig().getInt(key + ".location.z");
			net.minecraft.server.v1_13_R2.ItemStack itemStack = CraftItemStack.asNMSCopy(getConfig().getItemStack(key + ".item.stack"));
			itemStack.a(new ChatComponentText(getConfig().getString(key + ".item.name")));
			itemStacks.put(new Location(Bukkit.getWorld(WorldName), X, Y, Z), CraftItemStack.asBukkitCopy(itemStack));
		}
		this.reloadConfig();
	}

	@Override
	public void onDisable() {
		int i = 0;
		for(Location location : itemStacks.keySet()) {
			getConfig().set(i + ".location.world", location.getWorld().getName());
			getConfig().set(i + ".location.x", location.getBlockX());
			getConfig().set(i + ".location.y", location.getBlockY());
			getConfig().set(i + ".location.z", location.getBlockZ());
			ItemStack item = new ItemStack(itemStacks.get(location).getType());
			getConfig().set(i + ".item.stack", item);
			getConfig().set(i + ".item.name", CraftItemStack.asNMSCopy(itemStacks.get(location)).getName());
			i++;
		}
		this.saveConfig();
	}
}
