package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Magic;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

public class ChaosStoneItem extends Item implements ICurioItem {
    public ChaosStoneItem() {
        super((new Properties()).stacksTo(1).fireResistant());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.chaos_stone.text")
                .withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        if (ModList.get().isLoaded("ars_extensions")) {
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.chaos_stone.ars_text")
                    .withStyle(style -> style.withColor(ChatFormatting.LIGHT_PURPLE)));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.ChaosStone.get());
    }
}