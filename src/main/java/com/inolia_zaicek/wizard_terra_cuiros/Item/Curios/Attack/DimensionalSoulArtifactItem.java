package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Attack;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public class DimensionalSoulArtifactItem extends Item implements ModRarity.Expert,ICurioItem {
    public DimensionalSoulArtifactItem() {
        super((new Properties()).stacksTo(1).rarity(ModRarity.EXPERT).fireResistant());
    }
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return this.withColor(this.getDescriptionId());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.DimensionalSoulArtifact.get());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.dimensional_soul_artifact.text")
                .withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}