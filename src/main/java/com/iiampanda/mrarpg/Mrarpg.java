package com.iiampanda.mrarpg;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.Set;
import java.util.function.Consumer;

public final class Mrarpg
{
    public static final String ID = "mrarpg";

    public static final Set<String> LOOT = Set.of(
        "simple_dungeon",
        "abandoned_mineshaft",
        "stronghold_corridor",
        "stronghold_crossing",
        "stronghold_library",
        "desert_pyramid",
        "jungle_temple",
        "woodland_mansion",
        "nether_bridge",
        "bastion_treasure",
        "bastion_other",
        "bastion_bridge",
        "bastion_hoglin_stable",
        "end_city_treasure",
        "ancient_city",
        "ancient_city_ice_box",
        "trial_chambers/reward",
        "trial_chambers/supply",
        "underwater_ruin_big",
        "ruined_portal"
    );

    public static volatile Consumer<Boolean> openMenu = shift -> {};

    private Mrarpg()
    {
    }

    public static ResourceLocation id(String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    public static boolean lootMatches(ResourceLocation table)
    {
        return table.getNamespace().equals("minecraft") && table.getPath().startsWith("chests/") && LOOT.contains(table.getPath().substring(7));
    }

    public static LootPool.Builder pool(Item item)
    {
        return LootPool.lootPool().when(LootItemRandomChanceCondition.randomChance(0.5F)).add(LootItem.lootTableItem(item));
    }
}
