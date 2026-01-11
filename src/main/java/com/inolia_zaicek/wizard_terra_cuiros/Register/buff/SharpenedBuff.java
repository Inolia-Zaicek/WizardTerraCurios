package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.confluence.mod.misc.ModAttributes;

public class SharpenedBuff extends MobEffect {
    private static final String UUID = "F52E78E5-13F3-9A40-064B-402EFC691D82";
    private static final double NUMBER = 12;
    public SharpenedBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(ModAttributes.ARMOR_PASS.get(), UUID, NUMBER
                , AttributeModifier.Operation.ADDITION);
    }
}
