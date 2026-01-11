package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class CursePowerBuff extends MobEffect {
    private static final String UUID = "2019910B-5036-30AB-461D-1B80EC0BE215";
    private static final double NUMBER = 0.5;
    private static final double NUMBER1 = -0.2;
    public CursePowerBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(WTCAttributes.GOTEY_Potency_AMPLIFIER.get(), UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ARMOR, UUID, NUMBER1
                , AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
