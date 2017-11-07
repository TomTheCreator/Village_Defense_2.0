package me.tomthedeveloper.kits;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.tomthedeveloper.kitapi.basekits.LevelKit;
import me.tomthedeveloper.handlers.ChatManager;
import me.tomthedeveloper.handlers.UserManager;
import me.tomthedeveloper.utils.ArmorHelper;
import me.tomthedeveloper.utils.Util;
import me.tomthedeveloper.utils.WeaponHelper;

/**
 * Created by Tom on 19/07/2015.
 */
public class WorkerKit extends LevelKit {

    public WorkerKit() {
        this.setLevel(15);
        this.setName(ChatManager.colorMessage("kits.Worker.Kit-Name"));
        List<String> description = Util.splitString(ChatManager.colorMessage("kits.Worker.Kit-Description"), 40);
        this.setDescription(description.toArray(new String[description.size()]));
    }


    @Override
    public boolean isUnlockedByPlayer(Player player) {
        return UserManager.getUser(player.getUniqueId()).getInt("level") >= this.getLevel() || player.hasPermission("villagefense.kit.door");
    }

    @Override
    public void giveKitItems(Player player) {
        ArmorHelper.setColouredArmor(Color.PURPLE, player);
        player.getInventory().addItem(WeaponHelper.getUnBreakingSword(WeaponHelper.ResourceType.WOOD, 10));
        player.getInventory().addItem(WeaponHelper.getEnchantedBow(Enchantment.DURABILITY, 10));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 10));
        player.getInventory().addItem(new ItemStack(Material.WOOD_DOOR, 2));
    }

    @Override
    public Material getMaterial() {
        return Material.WOOD_DOOR;
    }

    @Override
    public void reStock(Player player) {
        player.getInventory().addItem(new ItemStack(Material.WOOD_DOOR));
    }
}
