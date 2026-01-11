package com.inolia_zaicek.wizard_terra_cuiros.Register.buff;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.misc.ModAttributes;

import java.util.Objects;

public class ClairvoyanceBuff extends MobEffect {
    private static final String UUID = "5CC87A36-E08D-F254-5891-B51B4634F99D";
    private static final double NUMBER = 0.05;
    public ClairvoyanceBuff() {
        super(MobEffectCategory.BENEFICIAL, 0);
        if (ModList.get().isLoaded("irons_spellbooks")) {
        this.addAttributeModifier(
                Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "max_mana")))
                , UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        if (ModList.get().isLoaded("ars_nouveau")) {
            this.addAttributeModifier(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("ars_nouveau", "ars_nouveau.perk.max_mana")))
                    , UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        this.addAttributeModifier(
                ModAttributes.getMagicDamage()
                , UUID, NUMBER, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
