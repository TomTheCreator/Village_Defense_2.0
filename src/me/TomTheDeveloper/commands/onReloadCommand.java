package me.TomTheDeveloper.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.TomTheDeveloper.GameAPI;
import me.TomTheDeveloper.Game.GameInstance;
import me.TomTheDeveloper.Game.GameState;

/**
 * Created by Tom on 10/08/2014.
 */
public class onReloadCommand implements CommandExecutor {

    private GameAPI plugin;

    public onReloadCommand(GameAPI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getLabel().equalsIgnoreCase("smartreload") && commandSender.isOp()) {
            GameAPI.setRestart();
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.sendMessage(ChatColor.DARK_GREEN + "RELOADING THE SERVER AFTER ALL THE GAMES ENDED!");
                commandSender.sendMessage(ChatColor.GRAY + "Reloading process started!");


            }
        }
        return true;
    }


    public void checkreload() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                boolean b = true;
                for (GameInstance gameInstance : plugin.getGameInstanceManager().getGameInstances()) {

                    if (gameInstance.getGameState() == GameState.INGAME || gameInstance.getGameState() == GameState.STARTING)
                        b = false;
                }
                if (b == true) {
                    plugin.getPlugin().getServer().reload();
                } else {
                    checkreload();
                }



            }
        }, 20L);
    }

}
