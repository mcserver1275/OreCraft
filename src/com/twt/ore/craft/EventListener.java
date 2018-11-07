package com.twt.ore.craft;

import net.minecraft.server.v1_13_R2.ChatComponentText;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
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
		net.minecraft.server.v1_13_R2.ItemStack item = CraftItemStack.asNMSCopy(e.getItemInHand());
		NBTTagCompound nbtTagCompound = item.getTag();
		if(nbtTagCompound != null) {
			if(nbtTagCompound.getByte("ore") == 1 && !(nbtTagCompound.getCompound("type").isEmpty())) {
				this.plugin.itemStacks.put(e.getBlockPlaced().getLocation(), CraftItemStack.asBukkitCopy(net.minecraft.server.v1_13_R2.ItemStack.a(nbtTagCompound.getCompound("type"))));
			}
		}
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
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

}
