package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Magic;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.confluence.mod.misc.ModAttributes;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class IronMagnetFlowerItem extends Item implements ICurioItem {
    public IronMagnetFlowerItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        String itemName = getTooltipItemName(); // 获取物品 ID
        // 鼠标经过时的默认文本
        // 翻译键格式: tooltip.<你的ModID>.<物品ID>_text
        if (ModList.get().isLoaded("irons_spellbooks")) {
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.iron_mana_flower.text")
                    .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
        }
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put(ModAttributes.PICKUP_RANGE.get(), new AttributeModifier(uuid, getTooltipItemName(), 10.75F, AttributeModifier.Operation.ADDITION));
        return attributes;
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.IronMagnetFlower.get());
    }
}
