package me.evilterabite.covid19;

import me.evilterabite.covid19.Commands.cough;
import me.evilterabite.covid19.Commands.covidreload;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Covid19 extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("covidreload").setExecutor(new covidreload());
        getCommand("cough").setExecutor(new cough());
        for(Player list : Bukkit.getServer().getOnlinePlayers()){
            list.setDisplayName(list.getPlayerListName());
        }
        this.saveDefaultConfig();
        System.out.println("--------------------");
        System.out.println("#  COVID19 Enabled #");
        System.out.println("--------------------");

    }

    @EventHandler
    void onFirstJoin(PlayerJoinEvent e) {
        if(getConfig().getBoolean("join_msg")) {
            Player p = e.getPlayer();
            p.sendMessage(ChatColor.RED + "[COVID-19] " + ChatColor.GREEN + "This server is running [v1.0]COVID-19 by EvilTerabite");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
