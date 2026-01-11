package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class CozyFireBuff extends MobEffect {
    private static final String UUID = "A8A322BE-4E66-8775-FAE8-69DB6A263614";
    private static final double NUMBER = 0.1;
    public CozyFireBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(WTCAttributes.Natural_Life_Regeneration.get(), UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
    }
}
