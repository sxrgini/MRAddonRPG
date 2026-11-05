package com.iiampanda.mrarpg;

import com.iiampanda.morph.api.AddonApi;
import com.iiampanda.morph.client.ClientState;
import com.iiampanda.morph.client.gui.BiomassScreen;
import com.iiampanda.morph.client.gui.MorphSelectorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class MrarpgClientSetup
{
    private MrarpgClientSetup()
    {
    }

    public static void apply()
    {
        AddonApi.keysDisabled = true;
        AddonApi.resourceNameKey = "mrarpg.hud.mana";
        AddonApi.resourceAmountKey = "mrarpg.gui.mana.amount";
        AddonApi.barFill = 0xFF2E6BD6;
        AddonApi.barBack = 0xFF101A33;
        AddonApi.barLabel = 0xFFCCDDFF;
        AddonApi.costUnit = "M";
        Mrarpg.openMenu = shift ->
        {
            Minecraft mc = Minecraft.getInstance();
            if(shift && ClientState.usesBiomass() && ClientState.biomassUnlocked)
            {
                mc.gui.setScreen(new BiomassScreen(BiomassScreen.Tab.UPGRADES));
            }
            else if(ClientState.canSelect)
            {
                mc.gui.setScreen(new MorphSelectorScreen());
            }
            else if(mc.player != null)
            {
                mc.player.sendOverlayMessage(Component.translatable("mrarpg.message.selectorDisabled"));
            }
        };
    }
}
