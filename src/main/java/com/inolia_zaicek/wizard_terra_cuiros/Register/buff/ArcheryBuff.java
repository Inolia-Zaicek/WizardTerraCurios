package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.misc.ModAttributes;

import java.util.Objects;

public class ArcheryBuff extends MobEffect {
    private static final String UUID = "35A06F3D-D3D8-EA6E-1DDF-B5D846F4A242";
    private static final double NUMBER = 0.05;
    public ArcheryBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        if (ModList.get().isLoaded("attributeslib")) {
            this.addAttributeModifier(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("attributeslib", "arrow_damage")))
                    , UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }else{
            this.addAttributeModifier(
                    ModAttributes.getRangedDamage()
                    , UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
    }
}
