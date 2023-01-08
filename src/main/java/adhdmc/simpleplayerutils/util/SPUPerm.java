package adhdmc.simpleplayerutils.util;

import org.bukkit.permissions.Permission;

public enum SPUPerm {
    //Commands
    AFK(new Permission("spu.command.afk")),
    AFK_OTHERS(new Permission("spu.command.afk.other")),
    FLY(new Permission("spu.command.fly")),
    FLY_OTHERS(new Permission("spu.command.fly.other")),
    FLYSPEED(new Permission("spu.command.flyspeed")),
    FLYSPEED_OTHERS(new Permission("spu.command.flyspeed.other")),
    WALKSPEED(new Permission("spu.command.walkspeed")),
    WALKSPEED_OTHERS(new Permission("spu.command.walkspeed.other")),
    ANVIL(new Permission("spu.command.anvil")),
    ANVIL_OTHER(new Permission("spu.command.anvil.other")),
    CARTOGRAPHY(new Permission("spu.command.cartography")),
    CARTOGRAPHY_OTHER(new Permission("spu.command.cartography.other")),
    CRAFTING(new Permission("spu.command.crafting")),
    CRAFTING_OTHER(new Permission("spu.command.crafting.other")),
    ENDERCHEST(new Permission("spu.command.enderchest")),
    ENDERCHEST_OTHER(new Permission("spu.command.enderchest.other")),
    LOOM(new Permission("spu.command.loom")),
    LOOM_OTHER(new Permission("spu.command.loom.other")),
    SMITHING(new Permission("spu.command.smithing")),
    SMITHING_OTHER(new Permission("spu.command.smithing.other")),
    STONECUTTER(new Permission("spu.command.stonecutter")),
    STONECUTTER_OTHER(new Permission("spu.command.stonecutter.other")),
    GRINDSTONE(new Permission("spu.command.grindstone")),
    GRINDSTONE_OTHER(new Permission("spu.command.grindstone.other")),
    DYE_BASIC(new Permission("spu.command.dye.basic")),
    DYE_HEX(new Permission("spu.command.dye.hex")),
    RENAME_BASIC(new Permission("spu.command.rename.basic")),
    RENAME_MINIMESSAGE(new Permission("spu.command.rename.minimessage")),
    LORE_BASIC(new Permission("spu.command.lore.basic")),
    LORE_MINIMESSAGE(new Permission("spu.command.lore.minimessage"));

    final Permission perm;

    SPUPerm(Permission perm) {
        this.perm = perm;
    }
    public Permission getPerm(){
        return perm;
    }
}
