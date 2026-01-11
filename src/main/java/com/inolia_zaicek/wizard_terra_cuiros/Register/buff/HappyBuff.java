package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HappyBuff extends MobEffect {
    private static final String UUID = "A87510D1-A38A-0F6E-4B7A-509298D9A814";
    private static final double NUMBER = 0.1;
    public HappyBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID, NUMBER
                , AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
