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

import java.io.File;
import java.io.IOException;

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

        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getConsoleSender().sendMessage(Texts.logo);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        double x = config.getDouble("location.x");
        double y = config.getDouble("location.y");
        double z = config.getDouble("location.z");
        int n = config.getInt("frequency");
        String worldName = config.getString("location.Spawnworld");
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            Location location = new Location(world, x, y, z);
            int i = 0;
            while (player.getLocation().equals(location)) {
                // 将玩家传送到指定位置
                player.teleport(location);
                i++;
                if(i >= n){
                    break;
                }
            }

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
                sender.sendMessage("§aSpawnJoin 配置已重新加载!");
                return true;
            } else if (args.length > 0 && args[0].equalsIgnoreCase("setspawn")) {
                if (!sender.hasPermission("SpawnJoin.setspawn")) {
                    sender.sendMessage("§c你没有权限来执行这个指令!");
                    return true;
                }
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Location location = player.getLocation();

                    // 更新配置文件中的值
                    config.set("location.Spawnworld", location.getWorld().getName());
                    config.set("location.x", location.getX());
                    config.set("location.y", location.getY());
                    config.set("location.z", location.getZ());

                    // 保存配置文件
                    try {
                        config.save(configFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage("§b传送§3系统 §f➢ 进服位置已设置在" + location.getWorld().getName() + "：" + location.getX() + "," +location.getY() + "，"+location.getZ());
                    return true;
                } else {
                    sender.sendMessage("§c只有玩家可以执行这个指令!");
                    return true;
                }
            } else {
                sender.sendMessage("§c用法: /sj reload 或 /sj setspawn");
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getConsoleSender().sendMessage(Texts.logo);
        this.getServer().getConsoleSender().sendMessage("插件已被卸载，感谢使用！");
    }
}
