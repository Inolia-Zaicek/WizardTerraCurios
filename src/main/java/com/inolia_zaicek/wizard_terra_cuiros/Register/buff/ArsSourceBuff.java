package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.misc.ModAttributes;

import java.util.Objects;

public class ArsSourceBuff extends MobEffect {
    private static final String UUID = "6551B818-CB64-379D-92E7-5FD0DE0775B2";
    private static final double NUMBER = 0.05;
    private static final double NUMBER1 = 0.025;
    public ArsSourceBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        if (ModList.get().isLoaded("ars_nouveau")) {
            this.addAttributeModifier(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("ars_nouveau", "ars_nouveau.perk.max_mana")))
                    , UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        this.addAttributeModifier(
                ModAttributes.getMagicDamage()
                , UUID, NUMBER1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
