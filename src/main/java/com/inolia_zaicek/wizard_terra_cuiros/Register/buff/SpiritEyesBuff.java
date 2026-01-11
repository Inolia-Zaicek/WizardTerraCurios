package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class SpiritEyesBuff extends MobEffect {
    private static final String UUID = "38FECA96-0633-1757-84F6-980B588AB6E1";
    private static final double NUMBER = 2;
    public SpiritEyesBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        if (ModList.get().isLoaded("malum")) {
            this.addAttributeModifier(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("malum", "spirit_spoils")))
                    , UUID, NUMBER, AttributeModifier.Operation.ADDITION);
        }
    }
}
