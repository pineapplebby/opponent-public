package me.pineapple.opponent.api.utils;

import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.UUID;

public class CapeUtil {

    public static final HashMap<UUID, ResourceLocation> CAPED_USERS = Maps.newHashMap();

    static {
        ResourceLocation mamavegetta = new ResourceLocation("capes/nike.png");

        CAPED_USERS.put(UUID.fromString("05b85b3a-f053-4dd1-8a1e-62c20f0b81eb"), mamavegetta);


        CAPED_USERS.put(UUID.fromString("35527a71-3f79-4d83-9aa3-0d2e01d9a256"), new ResourceLocation("capes/nike.png"));
    }

}
