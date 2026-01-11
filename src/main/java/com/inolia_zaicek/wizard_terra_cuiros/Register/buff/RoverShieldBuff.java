package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RoverShieldBuff extends MobEffect {
    private static final String UUID = "243D315B-7E72-B1CA-7C7A-69B40A9ABBA2";
    private static final double NUMBER = 2.5;
    public RoverShieldBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ARMOR, UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
    }
}
