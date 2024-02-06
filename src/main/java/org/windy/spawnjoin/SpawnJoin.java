package org.windy.spawnjoin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;


import java.io.File;

public final class SpawnJoin extends JavaPlugin implements Listener {
    private File configFile;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // 初始化配置文件
        configFile = new File(getDataFolder(), "config.yml");

        // 检查配置文件是否存在，如果不存在则创建
        if (!configFile.exists()) {
            saveDefaultConfig();
        }

        // 加载配置文件
        config = YamlConfiguration.loadConfiguration(configFile);

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getConsoleSender().sendMessage(Texts.logo);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        double x = config.getDouble("location.x");
        double y = config.getDouble("location.y");
        double z = config.getDouble("location.z");
        String worldName = config.getString("location.Spawnworld");
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            Location location = new Location(world, x, y, z);
            // 将玩家传送到指定位置
            player.teleport(location);
        } else {
            // 处理无效的世界名称的情况，可能需要给出错误提示或者使用默认世界等
            this.getServer().getConsoleSender().sendMessage(Texts.logo);
            this.getServer().getConsoleSender().sendMessage("该世界不存在！");
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sj")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("SpawnJoin.reload")) {
                    sender.sendMessage("§c你没有权限来执行这个指令!");
                    return true;
                }
                this.reloadConfig();
                sender.sendMessage("§aTeleportOffset 配置已重新加载!");
                return true;
            } else if(args.length > 0 && args[0].equalsIgnoreCase("setspawn")) {
                if (!sender.hasPermission("SpawnJoin.setspawn")) {
                    sender.sendMessage("§c你没有权限来执行这个指令!");
                    return true;
                }
                Player player = (Player) sender;
                Location location = player.getLocation();
                // 更新配置文件中的值
                config.set("location.Spawnworld", location.getWorld().getName());
                config.set("location.x", location.getX());
                config.set("location.y", location.getY());
                config.set("location.z", location.getZ());

                return true;
            }else{
                sender.sendMessage("§c用法: /teleportoffset reload");
                return true;
            }
        }
        return false;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getConsoleSender().sendMessage(Texts.logo);
    }
}
