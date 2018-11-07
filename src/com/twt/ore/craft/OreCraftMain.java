package com.twt.ore.craft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class OreCraftMain extends JavaPlugin {

	public static Map<Location, ItemStack> itemStacks = new HashMap<>();
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
		Recipe.addRecipe(this);

		reloadConfig();
		for(String key : getConfig().getKeys(false)) {
			String WorldName = (String) getConfig().get(key + ".location.world");
			int X = (int) getConfig().get(key + ".location.x");
			int Y = (int) getConfig().get(key + ".location.y");
			int Z = (int) getConfig().get(key + ".location.z");
			ItemStack itemStack = (ItemStack) getConfig().get(key + ".item");
			itemStacks.put(new Location(Bukkit.getWorld(WorldName), X, Y, Z), itemStack);
		}
	}

	@Override
	public void onDisable() {
		int i = 0;
		for(Location location : itemStacks.keySet()) {
			i++;
			getConfig().set(i + ".location.world", location.getWorld().getName());
			getConfig().set(i + ".location.x", location.getBlockX());
			getConfig().set(i + ".location.y", location.getBlockY());
			getConfig().set(i + ".location.z", location.getBlockZ());
			getConfig().set(i + ".item", itemStacks.get(location));
			saveConfig();
		}
	}
}
