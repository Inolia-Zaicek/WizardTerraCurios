package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Magic;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.misc.ModAttributes;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EtherealTalismanItem extends Item implements ModRarity.Expert,ICurioItem {
    public EtherealTalismanItem() {
        super((new Properties()).stacksTo(1).rarity(ModRarity.EXPERT).fireResistant());
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
        attributes.put(ModAttributes.getMagicDamage(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.EtherealTalismanMAtk.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        if (ModList.get().isLoaded("irons_spellbooks")) {
            attributes.put(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "max_mana")))
                    , new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.EtherealTalismanIronMax.get(), AttributeModifier.Operation.MULTIPLY_BASE));
            attributes.put(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "mana_regen")))
                    , new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.EtherealTalismanIronRe.get(), AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (ModList.get().isLoaded("ars_nouveau")) {
            attributes.put(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("ars_nouveau", "ars_nouveau.perk.max_mana")))
                    , new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.EtherealTalismanArsMax.get(), AttributeModifier.Operation.MULTIPLY_BASE));
            attributes.put(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("ars_nouveau", "ars_nouveau.perk.mana_regen")))
                    , new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.EtherealTalismanArsRe.get(), AttributeModifier.Operation.MULTIPLY_BASE));
        }
        attributes.put(ModAttributes.PICKUP_RANGE.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.CelestialMagnet.get(), AttributeModifier.Operation.ADDITION));
        return attributes;
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.iron_mana_flower.text")
                .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        if (!ModList.get().isLoaded("ars_extensions")) {
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.ars_extensions.text")
                    .withStyle(style -> style.withColor(ChatFormatting.DARK_GRAY)));
        }else{
            pTooltipComponents.add(Component.translatable("tooltip.ars_extensions.ars_mana_flower.text")
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        }
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.EtherealTalisman.get());
    }
}