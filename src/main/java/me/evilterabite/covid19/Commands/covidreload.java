package me.evilterabite.covid19.Commands;

import me.evilterabite.covid19.Covid19;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class covidreload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin covid = Covid19.getPlugin(Covid19.class);
        if(sender instanceof Player) {
            Player larry = (Player) sender;
            covid.reloadConfig();
            System.out.println("---COVID19 Reloaded!---");
            larry.sendMessage(ChatColor.RED + "[COVID-19] " + ChatColor.GREEN + "Reloaded!");
            larry.sendMessage(ChatColor.RED + "[COVID-19] " + ChatColor.GREEN + "You need to restart the server to reload user prefixes.");
        }
        else {
            covid.reloadConfig();
            System.out.println("---COVID19 Reloaded!---");
        }
        return true;
    }
}
