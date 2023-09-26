# SimplePlayerUtils

A plugin for Minecraft servers that provides various player utilities, including commands for toggling AFK status, enabling creative flight, and opening various workstations.

## Features

 - `/spureload` command for reloading the configuration
 - `/hat` command for putting something on your head
 - `/afk` command for toggling AFK status (Purpur-specific feature)
 - `/fly` command for enabling creative flight
 - `/flyspeed` and `/walkspeed` commands for adjusting fly and walk speed respectively
   - `/flyspeed reset` or `/walkspeed reset` can be used to reset the fly or walk speed respectively
 - commands for opening various workstations, including `/anvil`, `/cartography`, `/craft`, `/grindstone`, `/loom`, `/smithing`, `/stonecutter`, and `/enderchest`
 - `/rename` command for renaming items
 - `/trash` for deleting unwanted items (comes with a configurable blacklist)

## Installation

 - Download the latest version of the plugin from the releases page.
 - Place the plugin in your server's plugins folder.
 - Start or restart your server.

## Permissions


### Reload permission
| Permission | Description | Command | Default |
|----|----|----|----|
| `spu.reload` | Gives permission to reload the<br>Plugin's config and locale | `/spureload` | op |


### Command permissions
| spu.command.\<permission> | Description | Command | Default |
|----|----|----|----|
| `spu.command.trash` | Allows a player to open a trash menu | `/trash` | op |
| `spu.command.hat` | Lets you put something on your head | `/hat` | op |
| `spu.command.afk` | Allows a player to toggle their AFK status | `/afk` | op |
| `spu.command.fly` | Allows a player to toggle their personal flight | `/fly` | op |
| `spu.command.flyspeed` | Allows a player to set their own flyspeed | `/flyspeed [<float>\|reset\|get]` | op |
| `spu.command.walkspeed` | Allows a player to set their own walkspeed | `/walkspeed [<float>\|reset\|get]` | op |


### Inventory permissions
| spu.command.inventory.\<permission> | Description | Command | Default |
|----|----|----|----|
| `spu.command.inventory.anvil` | Allows a player to use the anvil menu | `/anvil` | op |
| `spu.command.inventory.cartography` | Allows a player to open a cartography table | `/carto` | op |
| `spu.command.inventory.crafting` | Allows a player to open a crafting menu | `/craft` | op |
| `spu.command.inventory.grindstone` | Allows a player to open a grindstone | `/grind` | op |
| `spu.command.inventory.loom` | Allows a player to open a loom | `/loom` | op |
| `spu.command.inventory.smithing` | Allows a player to open a smithing table | `/smithing` | op |
| `spu.command.inventory.stonecutter` | Allows a player to open a stonecutter | `/stonecutter` | op |
| `spu.command.inventory.enderchest` | Allows a player to open their own enderchest | `/ec` | op |

### Rename permissions
| spu.command.rename.\<permission> | Description | Command | Default |
|----|----|----|----|
| `spu.command.rename.basic` | Allows a player to /rename their items, with no color codes | `/rename <new name>` | op |
| `spu.command.rename.minimessage` | Allows a player to /rename their items using minimessage formatting | `/rename <new name>` | op |
| `spu.command.rename.character.max.bypass` | Allows a player to bypass the configured max character value for /rename | `/rename <new name>` | op |


### Permissions for running commands on other players
| spu.command.other.\<permission> | Description | Command | Default |
|----|----|----|----|
| `spu.command.other.afk` | Allows a player to toggle other players' afk status | `/afk <player>` | op |
| `spu.command.other.fly` | Allows a player to toggle other players' flight | `/fly <player>` | op |
| `spu.command.other.trash` | Allows a player to open a trash menu for another player | `/trash <player>` | op |
| `spu.command.other.flyspeed` | Allows a player to set other players' flyspeeds | `/flyspeed <player> [<float>\|reset\|get]` | op |
| `spu.command.other.walkspeed` | Allows a player to set other players' walkspeeds | `/walkspeed <player> [<float>\|reset\|get]` | op |


### Permissions for opening inventories for other players
| spu.command.other.inventory.\<permission> | Description | Command | Default |
|----|----|----|----|
| `spu.command.other.inventory.anvil` | Allows a player to open an anvil menu for another player | `/anvil <player>` | op |
| `spu.command.other.inventory.cartography` | Allows a player to open a cartography table for another player | `/carto <player>` | op |
| `spu.command.other.inventory.crafting` | Allows a player to open a crafting menu for other players | `/craft <player>` | op |
| `spu.command.other.inventory.grindstone` | Allows a player to open a grindstone for another player | `/grind <player>` | op |
| `spu.command.other.inventory.loom` | Allows a player to open a loom for another player | `/loom <player>` | op |
| `spu.command.other.inventory.smithing` | Allows a player to open a smithing table for another player | `/smithing <player>` | op |
| `spu.command.other.inventory.stonecutter` | Allows a player to open a stonecutter for another player | `/stonecutter <player>` | op |
| `spu.command.other.inventory.enderchest` | Allows a player to open another player's enderchest for them <br>(does not look into their enderchest) | `/ec <player>` | op |
