package me.evilterabite.covid19.Commands;

import me.evilterabite.covid19.Covid19;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class cough implements CommandExecutor {
    Plugin covid = Covid19.getPlugin(Covid19.class);
    String covidprefix = covid.getConfig().getString("msg_prefix");
    public List<Player> sickP = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!sickP.isEmpty()) {
                if (sickP.contains(p)) {
                    List<Player> nearby = new ArrayList<>();
                    double range = 6;
                    String justCoughed = covid.getConfig().getString("just_coughed");
                    justCoughed = justCoughed.replace("%prefix%", covidprefix);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',justCoughed));
                    for (Entity e : p.getNearbyEntities(range, range, range)) {
                        if (e instanceof Player) {
                            nearby.add((Player) e);
                        } else {
                            continue;
                        }
                    }
                    for (Player target : nearby) {
                        if (!sickP.contains(target)) {
                            sickP.add(target);
                            String infectedpfx = covid.getConfig().getString("infected_prefix");
                            String nowSick = covid.getConfig().getString("now_sick_msg");
                            nowSick = nowSick.replace("%prefix%", covidprefix);
                            target.setDisplayName(ChatColor.translateAlternateColorCodes('&', infectedpfx) + target.getDisplayName());
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&',nowSick));
                        } else {
                            continue;
                        }
                    }
                } else {
                    String notSick = covid.getConfig().getString("not_sick");
                    notSick = notSick.replace("%prefix%", covidprefix);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',notSick));
                }
            }
            else {
                sickP.add(p);
                String firstCase = covid.getConfig().getString("first_case");
                firstCase = firstCase.replace("%prefix%", covidprefix);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',firstCase));
                String infectedpfx = covid.getConfig().getString("infected_prefix");
                p.setDisplayName((ChatColor.translateAlternateColorCodes('&', infectedpfx) + p.getDisplayName()));
            }
        }
        else {
            System.out.println(covid.getConfig().getString("console_error"));
        }
        return true;
    }
}
