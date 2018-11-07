package com.twt.ore.craft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class OreCraftMain extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
		Recipe.addRecipe(this);
	}
	
	
}
