package eu.triler.WorldSelect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import eu.triler.WorldSelect.commands.worlds;

public class WorldSelect extends JavaPlugin {

	public static Inventory worldSelector = Bukkit.createInventory(null, 54, "§e§lWorld select");;

	@Override
	public void onEnable() {
		GuiUpdater();
		Bukkit.getPluginManager().registerEvents(new worlds(), this);
		this.getCommand("worlds").setExecutor(new worlds());
	}

	public void GuiUpdater() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		    @Override
		    public void run() {
		    	worldSelector.clear();
		    	int i = 0;
		        for(World world : Bukkit.getWorlds()) {
		        	ItemStack item = new ItemStack(Material.BOOK);
		        	ItemMeta meta = item.getItemMeta();
		        	meta.setDisplayName(world.getName());
		        	List<String> lore = new ArrayList<>();
		        	lore.add("§eRight click to teleport.");
		        	lore.add("§eLeft to unload the world.");
		        	meta.setLore(lore);
		        	item.setItemMeta(meta);
		        	worldSelector.setItem(i, item);
		        	i++;
		        }
		    }
		}, 0L, 20L);
	}
}
