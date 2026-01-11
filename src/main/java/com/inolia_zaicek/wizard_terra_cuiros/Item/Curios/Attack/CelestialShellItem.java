package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Attack;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.effect.ModEffects;
import org.confluence.mod.misc.ModAttributes;
import org.confluence.mod.misc.ModConfigs;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class CelestialShellItem extends Item implements ModRarity.Expert,ICurioItem {
    public CelestialShellItem() {
        super((new Item.Properties()).stacksTo(1).rarity(ModRarity.EXPERT).fireResistant());
    }
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return this.withColor(this.getDescriptionId());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        LivingEntity living = slotContext.entity();
        float night = 0;
        if (living != null && living.level().getDayTime() % 24000 > 12000) {
            night=1;
            attributes.put(WTCAttributes.Natural_Life_Regeneration.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmRe.get(), AttributeModifier.Operation.ADDITION));
            attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.MoonCharmSpeed.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        }else{
            night=0;
        }
        attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.CELESTIAL_STONE_SPEED.get()+night*WTCConfig.MoonCharmAtkSpeed.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.CELESTIAL_STONE_DAMAGE.get()+night*WTCConfig.MoonCharmAtk.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(ModAttributes.getCriticalChance(), new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.CELESTIAL_STONE_CRITICAL_CHANCE.get()+night*WTCConfig.MoonCharmAtkSpeed.get(), AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.ARMOR, new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.CELESTIAL_STONE_ARMOR.get()+night*WTCConfig.MoonCharmArmor.get(), AttributeModifier.Operation.ADDITION));
        attributes.put(ModAttributes.getMiningSpeed(), new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.CELESTIAL_STONE_MINING.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(ModAttributes.getRangedDamage(), new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.CELESTIAL_STONE_RANGED.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(ModAttributes.getMagicDamage(), new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.CELESTIAL_STONE_MAGIC.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(WTCAttributes.Summon_Damage.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.CelestialShellSummon.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));

        return attributes;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity living = slotContext.entity();
        if (living.level().getDayTime() % 24000 < 12000) return;
        ModEffects.healPerSecond(living, 2.0F);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.CelestialShell.get());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.confluence.celestial_stone.tooltip")
                .withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.neptunes_shell.text")
                .withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.moon_charm.text")
                .withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}