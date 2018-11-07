package com.twt.ore.craft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventListener implements Listener {
	
	public OreCraftMain plugin;
	
	public EventListener(OreCraftMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent e) {
		plugin.getConfig().set("1", e.getItemInHand());
	}
}
