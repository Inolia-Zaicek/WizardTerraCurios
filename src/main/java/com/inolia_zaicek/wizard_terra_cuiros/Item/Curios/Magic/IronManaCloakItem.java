package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Magic;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
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
import org.confluence.mod.item.curio.combat.IStarCloak;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class IronManaCloakItem extends Item implements ICurioItem, IStarCloak {
    public IronManaCloakItem() {
        super((new Item.Properties()).stacksTo(1).fireResistant());
    }
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        String itemName = getTooltipItemName(); // 获取物品 ID
        if (ModList.get().isLoaded("irons_spellbooks")) {
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.iron_mana_flower.text")
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        }
        pTooltipComponents.add(IStarCloak.TOOLTIP);
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        if (ModList.get().isLoaded("irons_spellbooks")) {
            attributes.put(
                    Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("irons_spellbooks", "mana_regen")))
                    , new AttributeModifier(uuid, getTooltipItemName(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return attributes;
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.IronManaCloak.get());
    }
}
