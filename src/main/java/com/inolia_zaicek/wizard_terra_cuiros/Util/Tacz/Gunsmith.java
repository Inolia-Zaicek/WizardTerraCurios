package com.inolia_zaicek.wizard_terra_cuiros.Util.Tacz;

import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IGun;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;


public class Gunsmith {
    /**
     * @since 1.0.0
     */
    public static Optional<GunInfo> getGunInfo(ItemStack gun) {
        var kun = IGun.getIGunOrNull(gun);
        if (kun == null) {
            return Optional.empty();
        }
        var gunId = kun.getGunId(gun);
        return TimelessAPI.getCommonGunIndex(gunId).map(index -> new GunInfo(gun, kun, gunId, index));
    }

}