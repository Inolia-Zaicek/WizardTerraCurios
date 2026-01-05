package com.inolia_zaicek.wizard_terra_cuiros.Item.IronSpell;

import com.google.common.collect.Multimap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.confluence.mod.misc.ModAttributes;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class IronCelestialEmblemItem extends Item implements ICurioItem {
    public IronCelestialEmblemItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put(ModAttributes.PICKUP_RANGE.get(), new AttributeModifier(uuid, getTooltipItemName(), 10.75F, AttributeModifier.Operation.ADDITION));
        attributes.put(ModAttributes.MAGIC_DAMAGE.get(), new AttributeModifier(uuid, getTooltipItemName(), 0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;
    }
}
