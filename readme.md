# SpawnJoin 插件

这是一个 Bukkit/Spigot 服务器的插件，用于在玩家加入服务器时将其传送到指定的生成点。

## 功能

- 当玩家加入服务器时，将其传送到配置文件指定的生成点。
- 允许管理员使用 `/sj setspawn` 命令在游戏中设置生成点。
- 支持重新加载插件配置。

## 安装

1. 将插件 `.jar` 文件放入服务器的 `plugins` 文件夹中。
2. 重启服务器或使用插件管理命令重新加载插件。

## 使用

- 默认生成点是配置文件中指定的坐标和世界。
- 管理员可以使用 `/sj setspawn` 命令在游戏中设置生成点。

**命令：**

- `/sj setspawn` - 在游戏中设置生成点。

**权限：**

- `SpawnJoin.setspawn` - 允许玩家使用 `/sj setspawn` 命令设置生成点。
- `SpawnJoin.reload` - 允许玩家使用 `/sj reload` 命令重新加载插件配置。

## 配置

在 `plugins/SpawnJoin` 文件夹中的 `config.yml` 文件中可以找到插件的配置。

```yaml
location:
  Spawnworld: world
  x: 0.0
  y: 64.0
  z: 0.0
