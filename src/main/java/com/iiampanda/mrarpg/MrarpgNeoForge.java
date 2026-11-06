package com.iiampanda.mrarpg;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Mrarpg.ID)
public class MrarpgNeoForge
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mrarpg.ID);
    public static final DeferredItem<Item> BOOK = ITEMS.register("book_of_morphs", () -> new BookOfMorphsItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> CRYSTAL = ITEMS.register("mana_crystal", () -> new ManaCrystalItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public MrarpgNeoForge(IEventBus modBus)
    {
        ITEMS.register(modBus);
        modBus.addListener(BuildCreativeModeTabContentsEvent.class, event ->
        {
            if(event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
            {
                event.accept(BOOK.get());
                event.accept(CRYSTAL.get());
            }
        });
        NeoForge.EVENT_BUS.addListener(LootTableLoadEvent.class, event ->
        {
            if(Mrarpg.lootMatches(event.getName()))
            {
                event.getTable().addPool(Mrarpg.pool(BOOK.get()).build());
            }
        });
    }
}
