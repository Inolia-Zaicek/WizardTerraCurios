package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

public class MerfolkBuff extends MobEffect {
    private static final String UUID = "AB4A4EBE-CC52-C75F-C1F0-E918B9EE950D";
    private static final double NUMBER = 0.5;
    public MerfolkBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(ForgeMod.SWIM_SPEED.get(), UUID, NUMBER
                , AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
