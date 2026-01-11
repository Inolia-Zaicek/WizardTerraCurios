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
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ManaPolarizerItem extends Item implements ICurioItem {
    public ManaPolarizerItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.mana_polarizer.text")
                .withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        if (ModList.get().isLoaded("irons_spellbooks")) {
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.mana_polarizer.iron_text")
                    .withStyle(style -> style.withColor(ChatFormatting.AQUA)));
        }
        if (ModList.get().isLoaded("ars_nouveau")) {
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.mana_polarizer.ars_text")
                    .withStyle(style -> style.withColor(ChatFormatting.LIGHT_PURPLE)));
        }
        if (ModList.get().isLoaded("irons_spellbooks")||ModList.get().isLoaded("ars_nouveau")) {
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.mana_polarizer.tooltip_text")
                    .withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        if (ModList.get().isLoaded("irons_spellbooks")) {
            attributes.put(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "max_mana")))
                    , new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.ManaPolarizerIron.get(), AttributeModifier.Operation.MULTIPLY_BASE));
        }
        if (ModList.get().isLoaded("ars_nouveau")) {
            attributes.put(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("ars_nouveau", "ars_nouveau.perk.max_mana")))
                    , new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.ManaPolarizerArs.get(), AttributeModifier.Operation.MULTIPLY_BASE));
        }
        attributes.put(ModAttributes.getMagicDamage(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.ManaPolarizerMAtk.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.ManaPolarizer.get());
    }
}