package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class TheBastDefenseBuff extends MobEffect {
    private static final String UUID = "DA494B3F-C6D9-900E-DBB9-D5F31C0A707B";
    private static final double NUMBER = 5;
    public TheBastDefenseBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ARMOR, UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
    }
}
