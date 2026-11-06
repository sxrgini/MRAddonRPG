package com.iiampanda.mrarpg;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Mrarpg.ID, dist = Dist.CLIENT)
public class MrarpgNeoForgeClient
{
    public MrarpgNeoForgeClient()
    {
        MrarpgClientSetup.apply();
    }
}
