package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class IchorBuff extends MobEffect {
    private static final String UUID = "EA50BDFA-A6EC-7B08-50E0-8E3B23889153";
    private static final double NUMBER = -5;
    public IchorBuff() {
        super(MobEffectCategory.HARMFUL, 0);
        this.addAttributeModifier(Attributes.ARMOR, UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
    }
}
