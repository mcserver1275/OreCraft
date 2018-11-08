package com.twt.ore.craft;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_13_R2.NBTTagCompound;

public class EventListener implements Listener {
	
	public OreCraftMain plugin;
	
	public EventListener(OreCraftMain plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerBlockPlace(BlockPlaceEvent e) {
		net.minecraft.server.v1_13_R2.ItemStack item = CraftItemStack.asNMSCopy(e.getItemInHand());
		NBTTagCompound nbtTagCompound = item.getTag();
		if(nbtTagCompound != null) {
			if(nbtTagCompound.getByte("ore") == 1 && !(nbtTagCompound.getCompound("type").isEmpty())) {
				this.plugin.itemStacks.put(e.getBlockPlaced().getLocation(), CraftItemStack.asBukkitCopy(net.minecraft.server.v1_13_R2.ItemStack.a(nbtTagCompound.getCompound("type"))));
			}
		}
	}

	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent e) {
		Location loc = e.getBlock().getLocation();
		if(this.plugin.itemStacks.containsKey(loc)) {
			ItemStack item =  new ItemStack(Material.OAK_SAPLING);
			net.minecraft.server.v1_13_R2.ItemStack i = CraftItemStack.asNMSCopy(item);
			i.a(CraftItemStack.asNMSCopy(this.plugin.itemStacks.get(loc)).getName());
			NBTTagCompound nbt = i.getTag();
			nbt.setByte("ore", (byte)1);
			net.minecraft.server.v1_13_R2.ItemStack is = CraftItemStack.asNMSCopy(this.plugin.itemStacks.get(loc));
			nbt.set("type", is.save(new NBTTagCompound()));
			e.setDropItems(false);
			loc.getWorld().dropItem(loc, CraftItemStack.asBukkitCopy(i));
			this.plugin.itemStacks.remove(loc);
		}
	}
	
	@EventHandler
	public void onStructureGrow(StructureGrowEvent e) {
		if(this.plugin.itemStacks.containsKey(e.getLocation())) {
			if(!(e.isFromBonemeal())) {
				for(BlockState block : e.getBlocks()) {
					if(block.getType().equals(Material.OAK_LEAVES)) {
						
					}
				}
			}
		}
	}
	
	public void addArmorStand(Location loc, ItemStack item) {
		
	}

}
