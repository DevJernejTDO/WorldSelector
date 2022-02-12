package eu.triler.WorldSelect.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import eu.triler.WorldSelect.WorldSelect;

public class worlds implements CommandExecutor , Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			((Player) sender).getPlayer().openInventory(WorldSelect.worldSelector);
		}
		return true;
	}

	@EventHandler
	public void Click(InventoryClickEvent event) {
		if(event.getView().getTitle().equalsIgnoreCase("World select") && event.getCurrentItem() != null) {
			event.getWhoClicked().closeInventory();
			if(event.getCurrentItem().getType() == Material.PAPER) {
				ItemMeta meta = event.getCurrentItem().getItemMeta();
				if(Bukkit.getWorlds().contains(Bukkit.getWorld(meta.getDisplayName()))) {
					Location loc = new Location(Bukkit.getWorld(meta.getDisplayName()), 0, Bukkit.getWorld(meta.getDisplayName()).getHighestBlockYAt(0, 0), 0);
					event.getWhoClicked().teleport(loc);
					event.getWhoClicked().sendMessage("�8[�b�lWorldSelector�8] �aYou were teleported to �c"+loc.getWorld().getName()+" �aon xyz �c"+loc.getBlockX()+" "+loc.getY()+" "+loc.getZ());
				} else {
					event.getWhoClicked().sendMessage("�8[�b�lWorldSelector�8] �cThis is no longer loaded.");
				}
			}
			event.setCancelled(true);
		}
	}
}
