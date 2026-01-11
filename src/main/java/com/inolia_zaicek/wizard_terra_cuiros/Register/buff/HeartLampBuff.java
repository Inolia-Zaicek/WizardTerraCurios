package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class HeartLampBuff extends MobEffect {
    private static final String UUID = "74C9405D-C8C4-48E1-191D-D92D4963C2B6";
    private static final double NUMBER = 0.2;
    public HeartLampBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(WTCAttributes.Natural_Life_Regeneration.get(), UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
    }
}
