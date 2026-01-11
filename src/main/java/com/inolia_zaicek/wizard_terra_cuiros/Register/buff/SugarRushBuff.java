package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.confluence.mod.misc.ModAttributes;

public class SugarRushBuff extends MobEffect {
    private static final String UUID = "74D48A57-059F-9F4E-8697-EE0082181D39";
    private static final double NUMBER = 0.2;
    public SugarRushBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID, NUMBER
                , AttributeModifier.Operation.MULTIPLY_BASE);
        this.addAttributeModifier(ModAttributes.getMiningSpeed(), UUID, NUMBER
                , AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
