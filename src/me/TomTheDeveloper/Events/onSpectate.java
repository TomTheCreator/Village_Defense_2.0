package me.TomTheDeveloper.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

import me.TomTheDeveloper.GameAPI;
import me.TomTheDeveloper.Handlers.UserManager;

/**
 * Created by Tom on 1/08/2014.
 */
public class onSpectate implements Listener {

    private GameAPI plugin;

    public onSpectate(GameAPI plugin) {
        this.plugin = plugin;
    }



    @EventHandler (priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event){
        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event){
        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDropItem(PlayerDropItemEvent event){
        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }



    @EventHandler(priority = EventPriority.HIGH)
    public void onspectate(PlayerBucketEmptyEvent event){
        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onspectate(PlayerInteractEntityEvent event){
        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpectate(PlayerShearEntityEvent event){
        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpectate(PlayerItemConsumeEvent event){
        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpectate(FoodLevelChangeEvent event){
        if(!(event.getEntity() instanceof Player))
            return;
        Player player = (Player) event.getEntity();
        if(UserManager.getUser(player.getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpectate(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player))
            return;
        Player player = (Player) event.getEntity();
        if(!UserManager.getUser(player.getUniqueId()).isSpectator())
            return;
        if(plugin.getGameInstanceManager().getGameInstance(player) == null)
            return;
        if(player.getLocation().getY() < 1)
            player.teleport(plugin.getGameInstanceManager().getGameInstance(player).getStartLocation());
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpectate(EntityDamageByBlockEvent event){
        if(!(event.getEntity() instanceof Player))
            return;
        Player player = (Player) event.getEntity();
        if(UserManager.getUser(player.getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpectate(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player))
            return;
        Player player = (Player) event.getDamager();
        if(UserManager.getUser(player.getUniqueId()).isSpectator())
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSpectate(PlayerPickupItemEvent event){

        if(UserManager.getUser(event.getPlayer().getUniqueId()).isSpectator())
            event.setCancelled(true);
    }









}
