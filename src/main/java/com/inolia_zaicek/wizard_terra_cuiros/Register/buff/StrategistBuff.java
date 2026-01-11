package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class StrategistBuff extends MobEffect {
    private static final String UUID = "B84FAA0E-A598-910F-0AA2-B59711AC0496";
    private static final double NUMBER = 0.05;
    public StrategistBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        this.addAttributeModifier(WTCAttributes.Summon_Damage.get(), UUID, NUMBER
                , AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
