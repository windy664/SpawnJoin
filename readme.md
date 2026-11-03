<div align="center">

<img src="assets/banner.svg" alt="SpawnJoin" width="100%">

# SpawnJoin

**进服自动落点插件** —— 玩家进入服务器时自动传送到配置好的出生点，频率可控。

![Platform](https://img.shields.io/badge/Platform-Spigot%20%2F%20Paper-62bda8?style=flat-square&logo=minecraft&logoColor=white)
![Minecraft](https://img.shields.io/badge/MC-1.20.x-8a9a5b?style=flat-square)
![Java](https://img.shields.io/badge/Java-17-f89820?style=flat-square&logo=openjdk&logoColor=white)

[功能](#-功能) · [配置](#️-配置) · [命令](#-命令)

</div>

## ✨ 功能

- **进服落点**：玩家加入服务器后自动传送到 `config.yml` 指定的世界坐标
- **频率控制**：`frequency` 控制触发节奏，避免重连刷屏式传送
- **在线管理出生点**：站好位置执行 `/sj setspawn`，一键把当前坐标写入配置

## ⚙️ 配置

```yaml
location:
  Spawnworld: world
  x: 1
  y: 2
  z: 3
frequency: 2
```

## 📜 命令

| 命令 | 说明 | 别名 |
|------|------|------|
| `/sj reload` | 重载配置 | `spawnjoin` |
| `/sj setspawn` | 将当前坐标设为进服出生点 | |

## 🔨 构建

```bash
./gradlew build
```
