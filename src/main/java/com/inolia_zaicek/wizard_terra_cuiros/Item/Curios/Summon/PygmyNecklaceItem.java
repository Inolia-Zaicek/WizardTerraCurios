package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Summon;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class PygmyNecklaceItem extends Item implements ModRarity.Expert,ICurioItem {
    public PygmyNecklaceItem() {
        super((new Item.Properties()).stacksTo(1).rarity(ModRarity.WHITE).fireResistant());
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
        attributes.put(WTCAttributes.Summon_Damage.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.PygmyNecklace.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.PygmyNecklace.get());
    }
}