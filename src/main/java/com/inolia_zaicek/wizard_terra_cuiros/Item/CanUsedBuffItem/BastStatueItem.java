package com.inolia_zaicek.wizard_terra_cuiros.Item.CanUsedBuffItem;

import com.inolia_zaicek.wizard_terra_cuiros.Item.BuffItem;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import org.confluence.mod.misc.ModRarity;

public class BastStatueItem extends BuffItem {

    public BastStatueItem() {
        super(ModRarity.YELLOW);
    }
    @Override
    public MobEffectInstance getPotionEffect() {
        return new MobEffectInstance(WTCEEffectsRegister.TheBastDefense.get(), 10 * 60 * 20, 0);
    }
}