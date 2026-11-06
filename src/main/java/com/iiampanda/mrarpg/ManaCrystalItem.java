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
import net.minecraft.world.InteractionResultHolder;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if(!(player instanceof ServerPlayer sp) || !(level instanceof ServerLevel sl))
        {
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), true);
        }
        if(!MorphManager.mode().usesBiomass())
        {
            sp.displayClientMessage(Component.translatable("mrarpg.message.manaNone"), true);
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
        var data = MorphManager.data(sp);
        if(data.biomass >= Upgrades.maxBiomass(data))
        {
            sp.displayClientMessage(Component.translatable("mrarpg.message.manaFull"), true);
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
        ItemStack stack = player.getItemInHand(hand);
        MorphManager.addBiomass(sp, MANA);
        sl.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(this)), sp.getX(), sp.getEyeY() - 0.2D, sp.getZ(), 12, 0.2D, 0.2D, 0.2D, 0.05D);
        sl.playSound(null, sp.getX(), sp.getY(), sp.getZ(), SoundEvents.ENDER_EYE_DEATH, SoundSource.PLAYERS, 1.0F, 1.0F);
        if(!player.getAbilities().instabuild)
        {
            stack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(stack, false);
    }
}
