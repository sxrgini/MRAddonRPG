package com.iiampanda.mrarpg;

import com.iiampanda.morph.common.biomass.Upgrades;
import com.iiampanda.morph.common.morph.MorphManager;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ManaCrystalItem extends Item
{
    public static final double MANA = 15.0D;

    public ManaCrystalItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
        if(!(player instanceof ServerPlayer sp) || !(level instanceof ServerLevel sl))
        {
            return InteractionResult.SUCCESS;
        }
        if(!MorphManager.mode().usesBiomass())
        {
            sp.sendOverlayMessage(Component.translatable("mrarpg.message.manaNone"));
            return InteractionResult.FAIL;
        }
        var data = MorphManager.data(sp);
        if(data.biomass >= Upgrades.maxBiomass(data))
        {
            sp.sendOverlayMessage(Component.translatable("mrarpg.message.manaFull"));
            return InteractionResult.FAIL;
        }
        ItemStack stack = player.getItemInHand(hand);
        MorphManager.addBiomass(sp, MANA);
        sl.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, this), sp.getX(), sp.getEyeY() - 0.2D, sp.getZ(), 12, 0.2D, 0.2D, 0.2D, 0.05D);
        sl.playSound(null, sp.getX(), sp.getY(), sp.getZ(), SoundEvents.ENDER_EYE_DEATH, SoundSource.PLAYERS, 1.0F, 1.0F);
        if(!player.getAbilities().instabuild)
        {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
