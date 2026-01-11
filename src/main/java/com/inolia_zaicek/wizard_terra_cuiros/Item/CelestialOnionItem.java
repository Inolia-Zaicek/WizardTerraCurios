//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.inolia_zaicek.wizard_terra_cuiros.Item;

import java.util.List;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.misc.ModConfigs;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

public class CelestialOnionItem extends Item implements ModRarity.Expert {
    public CelestialOnionItem() {
        super((new Item.Properties()).stacksTo(1).rarity(ModRarity.EXPERT).fireResistant());
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        CuriosApi.getCuriosInventory(pPlayer).ifPresent((iCuriosItemHandler) -> {
            ICurioStacksHandler iCurioStacksHandler = (ICurioStacksHandler)iCuriosItemHandler.getCurios().get("accessory");
            if (iCurioStacksHandler != null
                    //栏位小于上限
                    && iCurioStacksHandler.getSlots() < WTCConfig.OverMaxACCESSORIES.get()
                    //栏位大于等于泰拉饰品本身上限
                    && iCurioStacksHandler.getSlots() >= ModConfigs.MAX_ACCESSORIES.get() ) {
                itemStack.shrink(1);
                iCurioStacksHandler.grow(1);
            }

        });
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide);
    }

    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.confluence.celestial_onion.tooltip"));
    }

    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return this.withColor(this.getDescriptionId());
    }
}
