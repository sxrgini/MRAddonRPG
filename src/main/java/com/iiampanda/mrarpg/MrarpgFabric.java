package com.iiampanda.mrarpg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MrarpgFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Item book = Registry.register(BuiltInRegistries.ITEM, Mrarpg.id("book_of_morphs"), new BookOfMorphsItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Mrarpg.id("book_of_morphs"))).stacksTo(1).rarity(Rarity.RARE)));
        Item crystal = Registry.register(BuiltInRegistries.ITEM, Mrarpg.id("mana_crystal"), new ManaCrystalItem(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Mrarpg.id("mana_crystal"))).stacksTo(16).rarity(Rarity.UNCOMMON)));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output ->
        {
            output.accept(book);
            output.accept(crystal);
        });
        LootTableEvents.MODIFY.register((key, builder, source, registries) ->
        {
            if(Mrarpg.lootMatches(key.identifier()))
            {
                builder.withPool(Mrarpg.pool(book));
            }
        });
    }
}
