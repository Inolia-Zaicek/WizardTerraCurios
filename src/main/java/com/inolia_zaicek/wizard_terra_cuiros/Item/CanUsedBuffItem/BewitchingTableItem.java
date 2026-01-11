package com.inolia_zaicek.wizard_terra_cuiros.Item.CanUsedBuffItem;

import com.inolia_zaicek.wizard_terra_cuiros.Item.BuffItem;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.confluence.mod.misc.ModRarity;

public class BewitchingTableItem extends BuffItem {

    public BewitchingTableItem() {
        super(ModRarity.LIGHT_PURPLE);
    }
    @Override
    public MobEffectInstance getPotionEffect() {
        return new MobEffectInstance(WTCEEffectsRegister.Bewitched.get(), 10 * 60 * 20, 0);
    }
}