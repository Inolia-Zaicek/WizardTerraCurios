package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class BewitchedBuff extends MobEffect {
    private static final String UUID = "C6D78295-5030-F9A0-0D65-BEA55D7E42FD";
    private static final double NUMBER = 0.05;
    public BewitchedBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(WTCAttributes.Summon_Damage.get(), UUID, NUMBER
                , AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
