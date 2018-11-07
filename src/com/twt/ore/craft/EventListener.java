package com.twt.ore.craft;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
	
	public OreCraftMain plugin;
	
	public EventListener(OreCraftMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent e) {
		ItemStack itemStack = e.getItemInHand();
		net.minecraft.server.v1_13_R2.ItemStack nmsitemstack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound nbtTagCompound = nmsitemstack.getTag();
		if(nbtTagCompound != null) {
			if(nmsitemstack.getTag().getByte("ore") == 1) {
				OreCraftMain.itemStacks.put(e.getBlockPlaced().getLocation(), itemStack);
			}
		}
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		OreCraftMain.itemStacks.remove(e.getBlock().getLocation());
	}
}
