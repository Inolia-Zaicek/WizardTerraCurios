package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LuckyBuff extends MobEffect {
    private static final String UUID = "74D48A57-059F-9F4E-8697-EE0082181D39";
    private static final double NUMBER = 0.2;
    public LuckyBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.LUCK, UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
    }
}
