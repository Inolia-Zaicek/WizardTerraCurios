package com.inolia_zaicek.wizard_terra_cuiros.Item.IronSpell;

import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.item.curio.combat.IStarCloak;
import org.confluence.mod.misc.ModAttributes;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
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
        // 鼠标经过时的默认文本
        // 翻译键格式: tooltip.<你的ModID>.<物品ID>_text
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.iron_mana_flower.text")
                .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        pTooltipComponents.add(IStarCloak.TOOLTIP);
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put((Attribute) AttributeRegistry.MANA_REGEN.get(), new AttributeModifier(uuid, getTooltipItemName(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
}
