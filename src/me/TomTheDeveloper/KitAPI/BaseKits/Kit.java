package me.TomTheDeveloper.KitAPI.basekits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Tom on 25/07/2014.
 */
public abstract class Kit {

    private String name;
    private boolean unlockedOnDefault = false;
    private String[] description = {""};

    protected Kit(){

    }

    public  abstract boolean isUnlockedByPlayer(Player p);

    public Kit(String name){
        this.name = name;
    }

    public boolean isUnlockedOnDefault() {
        return unlockedOnDefault;
    }

    public void setUnlockedOnDefault(boolean unlockedOnDefault) {
        this.unlockedOnDefault = unlockedOnDefault;
    }




    public String getName(){
        return name;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract void giveKitItems(Player player);

    public abstract Material getMaterial();

    protected ItemStack setItemNameAndLore(ItemStack item, String name, String[] lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        item.setItemMeta(im);
        return item;
    }

    public ItemStack getItemStack(){
       ItemStack itemStack = new ItemStack(getMaterial());
        setItemNameAndLore(itemStack, getName(), getDescription());
        return itemStack;
    }

    public abstract void reStock(Player player);










}
