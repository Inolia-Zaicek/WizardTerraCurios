package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.misc.ModAttributes;

import java.util.Objects;

public class IronSourceBuff extends MobEffect {
    private static final String UUID = "9CF802A1-11C9-B585-FE01-CFEC3CEBBAF8";
    private static final double NUMBER = 0.05;
    private static final double NUMBER1 = 0.025;
    public IronSourceBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        if (ModList.get().isLoaded("irons_spellbooks")) {
            this.addAttributeModifier(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "max_mana")))
                    , UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        this.addAttributeModifier(
                ModAttributes.getMagicDamage()
                , UUID, NUMBER1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
