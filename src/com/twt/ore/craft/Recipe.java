package com.twt.ore.craft;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_13_R2.ChatComponentText;
import net.minecraft.server.v1_13_R2.Items;
import net.minecraft.server.v1_13_R2.NBTTagCompound;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class Recipe {

	static String[] material = new String[]{ "LEGACY_EMERALD", "LEGACY_DIAMOND", "LEGACY_GOLD_INGOT", "LEGACY_IRON_INGOT", "LAPIS_LAZULI", "LEGACY_COAL", "LEGACY_NETHER_STAR", "DRAGON_BREATH", "EXPERIENCE_BOTTLE" };
	private static Map<String, String> ItemName = new HashMap<String, String>();
	
	public static void addRecipe(Plugin plugin) {
		for(String name : material) {
			ItemStack item =  new ItemStack(Material.OAK_SAPLING);
			net.minecraft.server.v1_13_R2.ItemStack i = CraftItemStack.asNMSCopy(item);
			i.a(new ChatComponentText("§b"+replace(name)+"树苗"));
			NBTTagCompound nbt = i.getTag();
			nbt.setByte("ore", (byte)1);
			net.minecraft.server.v1_13_R2.ItemStack is = CraftItemStack.asNMSCopy(new ItemStack(Material.getMaterial(name)));
			is.a(i.getName());
			nbt.set("type", is.save(new NBTTagCompound()));
			ShapelessRecipe sapling = new ShapelessRecipe(CraftItemStack.asBukkitCopy(i));
			sapling.addIngredient(2, Material.getMaterial(name));
			sapling.addIngredient(1, Material.OAK_SAPLING);
			plugin.getServer().addRecipe(sapling);
		}
		
	}
	
	public static String replace(String name) {
		String xgname = "";
		switch(name) {
			case "LEGACY_EMERALD" :
				xgname = "绿宝石";
				break;
			case "LEGACY_DIAMOND" :
				xgname = "钻石";
				break;
			case "LEGACY_GOLD_INGOT":
				xgname = "金锭";
				break;
			case "LEGACY_IRON_INGOT":
				xgname = "铁锭";
				break;
			case "LAPIS_LAZULI":
				xgname = "青金石";
				break;
			case "LEGACY_COAL":
				xgname = "煤炭";
				break;
			case "LEGACY_NETHER_STAR":
				xgname = "下界之星";
				break;
			case "DRAGON_BREATH":
				xgname = "龙息";
				break;
			case "EXPERIENCE_BOTTLE":
				xgname = "附魔之瓶";
				break;
				default:
					xgname = "";
					break;
		}
		return xgname;
	}

}
