package me.tomthedeveloper.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tomthedeveloper.VillageDefense;
import me.tomthedeveloper.game.GameInstance;
import me.tomthedeveloper.utils.ItemBuilder;

/**
 * Created by Tom on 15/06/2015.
 */
public class SetupInventory {

    private GameInstance gameInstance;
    private Inventory inventory;
    private HashMap<ItemStack, String> linkedcommands = new HashMap<ItemStack, String>();
    public static List<ItemStack> ITEMS_TO_ADD = new ArrayList<ItemStack>();

    public SetupInventory(GameInstance gameInstance){
        this.gameInstance = gameInstance;

        this.inventory = Bukkit.createInventory(null,9*2, "Arena: " + gameInstance.getID());

        addItem(new ItemBuilder(new ItemStack(Material.REDSTONE_BLOCK))
                    .name(ChatColor.GOLD + "Set End Location")
                    .lore(ChatColor.GRAY + "Click to set the end location.")
                    .lore(ChatColor.GRAY + "on the place where you are standing")
                    .build(), gameInstance.getPlugin().getAbreviation() + " " + gameInstance.getID() + " set ENDLOC");
        addItem(new ItemBuilder(new ItemStack(Material.LAPIS_BLOCK))
                    .name(ChatColor.GOLD + "Set Lobby Location")
                    .lore(ChatColor.GRAY + "Click to set the lobby location")
                    .lore(ChatColor.GRAY + "on the place where you are standing")
                    .build(), gameInstance.getPlugin().getAbreviation() + " " + gameInstance.getID() + " set LOBBYLOC");

        addItem(new ItemBuilder(new ItemStack(Material.EMERALD_BLOCK))
                .name(ChatColor.GOLD + "Set Start Location")
                .lore(ChatColor.GRAY + "Click to set the start location.")
                .lore(ChatColor.GRAY + "on the place where you are standing")
                .build(), gameInstance.getPlugin().getAbreviation() + " " + gameInstance.getID() + " set STARTLOC");
        addItem(new ItemBuilder(new ItemStack(Material.EMERALD, gameInstance.getMIN_PLAYERS()))
                .name(ChatColor.GOLD + "Set min players")
                .lore(ChatColor.GRAY + "LEFT click to decrease")
                .lore(ChatColor.GRAY + "RIGHT click to increase")
                .build(), gameInstance.getPlugin().getAbreviation() + " " + gameInstance.getID() + " set MINPLAYERS " + gameInstance.getMIN_PLAYERS());
        addItem(new ItemBuilder(new ItemStack(Material.EMERALD, gameInstance.getMAX_PLAYERS()))
                .name(ChatColor.GOLD + "Set max players")
                .lore(ChatColor.GRAY + "LEFT click to decrease")
                .lore(ChatColor.GRAY + "RIGHT click to increase")
                .build(), gameInstance.getPlugin().getAbreviation() + " " + gameInstance.getID() + " set MAXPLAYERS " + gameInstance.getMAX_PLAYERS());
        if(!gameInstance.getPlugin().isBungeeActivated()){
            addItem(new ItemBuilder(new ItemStack(Material.SIGN))
                    .name(ChatColor.GOLD + "Add signs")
                    .lore(ChatColor.GRAY + "Select a region with your")
                    .lore(ChatColor.GRAY + "world edit wand and click this.")
                    .lore(ChatColor.RED + "Be aware that you have bungee disabled!")
                    .build(), "addsigns");
        }
        addItem(new ItemBuilder(new ItemStack(Material.NAME_TAG))
                .name(ChatColor.GOLD + "Set a mapname (currently: " + gameInstance.getMapName() == null? "not set yet":gameInstance.getMapName())
                .lore(ChatColor.GRAY + "Replace this nametag with another nametag")
                .lore(ChatColor.GRAY + "that is named as the mapname")
                .build(), gameInstance.getPlugin().getAbreviation() + " " + gameInstance.getID() + " set MAPNAME <NAME>");
       
        addItem(new ItemBuilder(new ItemStack(Material.MONSTER_EGG, 1, (byte) 120))
                .name(ChatColor.GOLD + "Add villager spawns")
                .lore(ChatColor.GRAY + "Click to add a villager spawn")
                .lore(ChatColor.GRAY + "on the place you're standing").build());

        inventory.addItem((new ItemBuilder(new ItemStack(Material.APPLE))
                .name(ChatColor.GOLD + "Add villager spawns")
                .lore(ChatColor.GRAY + "Click to add a villager spawn")
                .lore(ChatColor.GRAY + "on the place you're standing").build()));
        inventory.addItem((new ItemBuilder(new ItemStack(Material.ROTTEN_FLESH))
                .name(ChatColor.GOLD + "Add zombie spawns")
                .lore(ChatColor.GRAY + "Click to add a zombie spawn")
                .lore(ChatColor.GRAY + "on the place you're standing").build()));
        inventory.addItem((new ItemBuilder(new ItemStack(Material.WOOD_DOOR))
                .name(ChatColor.GOLD + "Add doors")
                .lore(ChatColor.GRAY + "Select a region with you WE")
                .lore(ChatColor.GRAY + "wand and click")
                .build()));
        inventory.addItem(new ItemBuilder(new ItemStack(Material.CHEST))
                .name(ChatColor.GOLD +"Set the chest shop")
                .lore(ChatColor.GRAY + "Set the chest where in you ")
                .lore(ChatColor.GRAY + "will put the shop items.")
                .lore(ChatColor.GRAY + "Look at the chest")
                .lore(ChatColor.GRAY + "and click this")
                .build());
        if(VillageDefense.isDebugged()) {
        	System.out.println("[Village Debugger] Setup items generated!");
        }

    }

    public void addItem(ItemStack itemStack, String command){
        inventory.addItem(new ItemBuilder(itemStack).lore(ChatColor.RED + "Command: " + ChatColor.GRAY + "/" + command)
                .build());
    }

    public void addItem(ItemStack itemStack){
        inventory.addItem(itemStack);
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void openInventory(Player player){
        player.openInventory(inventory);
    }

}
