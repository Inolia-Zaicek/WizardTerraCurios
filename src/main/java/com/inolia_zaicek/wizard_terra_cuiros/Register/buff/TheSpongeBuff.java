package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class TheSpongeBuff extends MobEffect {
    private static final String UUID = "EE6AA354-DD75-7EF7-11C4-DAE7D5403ECD";
    private static final double NUMBER = 1.5;
    private static final double NUMBER1 = 0.01;
    public TheSpongeBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.ARMOR, UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(WTCAttributes.DAMAGE_REDUCTION.get(), UUID, NUMBER1
                , AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
