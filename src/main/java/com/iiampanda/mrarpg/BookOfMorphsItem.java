package com.iiampanda.mrarpg;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BookOfMorphsItem extends Item
{
    public BookOfMorphsItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
        if(level.isClientSide())
        {
            Mrarpg.openMenu.accept(player.isShiftKeyDown());
        }
        return InteractionResult.SUCCESS;
    }
}
