package com.inolia_zaicek.wizard_terra_cuiros.Item.IronSpell;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class IronTarnishedCrownItem extends Item implements ICurioItem {
    public IronTarnishedCrownItem() {super((new Properties()).stacksTo(1).fireResistant());}
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put((Attribute) AttributeRegistry.MANA_REGEN.get(), new AttributeModifier(uuid, getTooltipItemName(), 0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put((Attribute) Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, getTooltipItemName(), -0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        attributes.put((Attribute) AttributeRegistry.MAX_MANA.get(), new AttributeModifier(uuid, getTooltipItemName(), 150, AttributeModifier.Operation.ADDITION));
        return attributes;
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.IronTarnishedCrown.get());
    }
}
