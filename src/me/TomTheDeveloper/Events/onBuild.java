package me.TomTheDeveloper.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.TomTheDeveloper.GameAPI;

/**
 * Created by Tom on 9/08/2014.
 */
public class onBuild  implements Listener{

    private GameAPI plugin;

    public onBuild(GameAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(plugin.getAllowBuilding())
            return;
        if(plugin.getGameInstanceManager().getGameInstance(event.getPlayer()) == null)
            return;
        event.setCancelled(true);


    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(plugin.getAllowBuilding())
            return;
        if(plugin.getGameInstanceManager().getGameInstance(event.getPlayer()) == null)
            return;
        event.setCancelled(true);
    }
}
