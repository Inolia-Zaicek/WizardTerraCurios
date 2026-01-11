package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.General;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.item.curio.CurioItems;
import org.confluence.mod.misc.ModAttributes;
import org.confluence.mod.misc.ModConfigs;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;
import java.util.UUID;

public class UniterEmblemItem extends Item implements ICurioItem {
    public UniterEmblemItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.AVENGER_EMBLEM_DAMAGE.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(ModAttributes.getMagicDamage(), new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.AVENGER_EMBLEM_RANGED.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(ModAttributes.getRangedDamage(), new AttributeModifier(uuid, getTooltipItemName(), ModConfigs.AVENGER_EMBLEM_MAGIC.get(), AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(WTCAttributes.Summon_Damage.get(), new AttributeModifier(uuid, getTooltipItemName(), 0.06, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if(WTCUtil.isCurioEquipped(slotContext.entity(), WTCItemRegister.UniterEmblem.get())){
            return false;
        }else if(WTCUtil.isCurioEquipped(slotContext.entity(), CurioItems.AVENGER_EMBLEM.get())){
            return false;
        }else{
            return true;
        }
    }
}