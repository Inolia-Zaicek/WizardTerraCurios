package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.General;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import org.confluence.mod.effect.ModEffects;
import org.confluence.mod.item.curio.BaseCurioItem;
import org.confluence.mod.misc.ModAttributes;
import org.confluence.mod.misc.ModRarity;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class MoonCharmItem extends BaseCurioItem {
    private static ImmutableMultimap<Attribute, AttributeModifier> ATTRIBUTES;

    public MoonCharmItem() {
        super(ModRarity.PINK);
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        if (ATTRIBUTES == null) {
            ATTRIBUTES = ImmutableMultimap.<Attribute, AttributeModifier>builder()
                    .put(ModAttributes.getCriticalChance(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmCri.get(), AttributeModifier.Operation.ADDITION))
                    .put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmAtk.get(), AttributeModifier.Operation.MULTIPLY_TOTAL))
                    .put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmAtkSpeed.get(), AttributeModifier.Operation.MULTIPLY_TOTAL))
                    .put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmSpeed.get(), AttributeModifier.Operation.MULTIPLY_TOTAL))
                    .put(Attributes.ARMOR, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmArmor.get(), AttributeModifier.Operation.ADDITION))
                    .put(WTCAttributes.Natural_Life_Regeneration.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmRe.get(), AttributeModifier.Operation.ADDITION))
                    .build();
        }
        LivingEntity living = slotContext.entity();
        if (living == null) return EMPTY_ATTRIBUTE;
        return living.level().getDayTime() % 24000 > 12000 ? ATTRIBUTES : EMPTY_ATTRIBUTE;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity living = slotContext.entity();
        if (living.level().getDayTime() % 24000 < 12000) return;
        ModEffects.healPerSecond(living, 2.0F);
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.MoonCharm.get());
    }
}
