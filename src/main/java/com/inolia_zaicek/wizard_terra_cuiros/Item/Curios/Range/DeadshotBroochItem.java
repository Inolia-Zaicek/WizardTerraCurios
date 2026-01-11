package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Range;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.item.curio.BaseCurioItem;
import org.confluence.mod.item.curio.combat.IMagicQuiver;
import org.confluence.mod.misc.ModAttributes;
import org.confluence.mod.util.CuriosUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class DeadshotBroochItem extends BaseCurioItem implements IMagicQuiver {
    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    private static ImmutableMultimap<Attribute, AttributeModifier> ATTRIBUTES;
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        if (ATTRIBUTES == null) {
            ATTRIBUTES = ImmutableMultimap.of(
                    ModAttributes.getRangedDamage(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.DeadshotBroochDamage.get(), AttributeModifier.Operation.MULTIPLY_TOTAL),
                    ModAttributes.getRangedVelocity(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.DeadshotBroochVelocity.get(), AttributeModifier.Operation.MULTIPLY_TOTAL),
                    ModAttributes.getCriticalChance(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.DeadshotBroochCri.get(), AttributeModifier.Operation.ADDITION));
        }
        return ATTRIBUTES;
    }
    //继承自魔法箭袋的弹药消耗减免
    public static boolean shouldConsume(LivingEntity living) {
        return (double)living.getRandom().nextFloat() >= 0.2F || CuriosUtils.noSameCurio(living, IMagicQuiver.class);
    }
    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.confluence.magic_quiver.tooltip2"));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.DeadshotBrooch.get());
    }
}
