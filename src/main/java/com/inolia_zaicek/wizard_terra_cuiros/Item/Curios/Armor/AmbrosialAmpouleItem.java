package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Armor;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class AmbrosialAmpouleItem extends Item implements ICurioItem {
    public AmbrosialAmpouleItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.wizard_terra_cuiros.radiant_ooze.text"));
        list.add(Component.translatable("tooltip.wizard_terra_cuiros.honey_dew.text"));
        list.add(Component.translatable("tooltip.wizard_terra_cuiros.honey_dew.text1"));
        list.add(Component.translatable("tooltip.wizard_terra_cuiros.honey_dew.text2"));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.AmbrosialAmpoule.get());
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        int number = 0;
        if (slotContext.entity() != null) {
            number += Math.min(8,(int) ((slotContext.entity().getMaxHealth()-slotContext.entity().getHealth()) / slotContext.entity().getMaxHealth() * 100 / 10));
        }
        attributes.put(WTCAttributes.Natural_Life_Regeneration.get(), new AttributeModifier(uuid, getTooltipItemName(),
                WTCConfig.radiant_ooze_base.get()+WTCConfig.radiant_ooze_number.get()*number, AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.honey_dew_hp.get(), AttributeModifier.Operation.ADDITION));
        return attributes;
    }
}