# SimplePlayerUtils

A plugin for Minecraft servers that provides various player utilities, including commands for toggling AFK status, enabling creative flight, and opening various workstations.

## Features

 - `/spureload` command for reloading the configuration
 - `/hat` command for putting something on your head
 - `/afk` command for toggling AFK status (Purpur-specific feature)
 - `/fly` command for enabling creative flight
 - `/flyspeed` and `/walkspeed` commands for adjusting fly and walk speed respectively
 - commands for opening various workstations, including `/anvil`, `/cartography`, `/craft`, `/grindstone`, `/loom`, `/smithing`, `/stonecutter`, and `/enderchest`
 - `/rename` command for renaming items

## Installation

 - Download the latest version of the plugin from the releases page.
 - Place the plugin in your server's plugins folder.
 - Start or restart your server.

## Permissions

| Permission                                | Description                                                                                            | Default |
|-------------------------------------------|--------------------------------------------------------------------------------------------------------|---------|
| `spu.command.hat`                         | Lets you put something on your head                                                                    | op      |
| `spu.command.afk`                         | Allows a player to toggle their AFK status                                                             | op      |
| `spu.command.other.afk`                   | Allows a player to toggle other players' afk status                                                    | op      |
| `spu.command.fly`                         | Allows a player to toggle their personal flight                                                        | op      |
| `spu.command.other.fly`                   | Allows a player to toggle other players' flight                                                        | op      |
| `spu.command.flyspeed`                    | Allows a player to set their own flyspeed                                                              | op      |
| `spu.command.other.flyspeed`              | Allows a player to set other players' flyspeeds                                                        | op      |
| `spu.command.walkspeed`                   | Allows a player to set their own walkspeed                                                             | op      |
| `spu.command.other.walkspeed`             | Allows a player to set other players' walkspeeds                                                       | op      |
| `spu.command.inventory.anvil`             | Allows a player to use the anvil menu                                                                  | op      |
| `spu.command.other.inventory.anvil`       | Allows a player to open an anvil menu for another player                                               | op      |
| `spu.command.inventory.cartography`       | Allows a player to open a cartography table                                                            | op      |
| `spu.command.other.inventory.cartography` | Allows a player to open a cartography table for another player                                         | op      |
| `spu.command.inventory.crafting`          | Allows a player to open a crafting menu                                                                | op      |
| `spu.command.other.inventory.crafting`    | Allows a player to open a crafting menu for other players                                              | op      |
| `spu.command.inventory.grindstone`        | Allows a player to open a grindstone                                                                   | op      |
| `spu.command.other.inventory.grindstone`  | Allows a player to open a grindstone for another player                                                | op      |
| `spu.command.inventory.loom`              | Allows a player to open a loom                                                                         | op      |
| `spu.command.other.inventory.loom`        | Allows a player to open a loom for another player                                                      | op      |
| `spu.command.inventory.smithing`          | Allows a player to open a smithing table                                                               | op      |
| `spu.command.other.inventory.smithing`    | Allows a player to open a smithing table for another player                                            | op      |
| `spu.command.inventory.stonecutter`       | Allows a player to open a stonecutter                                                                  | op      |
| `spu.command.other.inventory.stonecutter` | Allows a player to open a stonecutter for another player                                               | op      |
| `spu.command.inventory.enderchest`        | Allows a player to open their own enderchest                                                           | op      |
| `spu.command.other.inventory.enderchest`  | Allows a player to open another player's enderchest for them <br>(does not look into their enderchest) | op      |
| `spu.command.rename.basic`                | Allows a player to /rename their items, with no color codes                                            | op      |
| `spu.command.rename.minimessage`          | Allows a player to /rename their items using minimessage formatting                                    | op      |
| `spu.command.rename.character.max.bypass` | Allows a player to bypass the configured max character value for /rename                               | op      |