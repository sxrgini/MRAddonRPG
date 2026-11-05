package com.iiampanda.mrarpg;

import net.fabricmc.api.ClientModInitializer;

public class MrarpgFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        MrarpgClientSetup.apply();
    }
}
