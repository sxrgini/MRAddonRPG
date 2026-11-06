package com.iiampanda.mrarpg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MrarpgFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Item book = Registry.register(BuiltInRegistries.ITEM, Mrarpg.id("book_of_morphs"), new BookOfMorphsItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
        Item crystal = Registry.register(BuiltInRegistries.ITEM, Mrarpg.id("mana_crystal"), new ManaCrystalItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries ->
        {
            entries.accept(book);
            entries.accept(crystal);
        });
        LootTableEvents.MODIFY.register((key, builder, source, registries) ->
        {
            if(Mrarpg.lootMatches(key.location()))
            {
                builder.withPool(Mrarpg.pool(book));
            }
        });
    }
}
