package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Armor;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

import static com.inolia_zaicek.wizard_terra_cuiros.Event.HurtEvent.rover_drive_time_nbt;
import static com.inolia_zaicek.wizard_terra_cuiros.Event.HurtEvent.the_sponge_time_nbt;

public class TheSpongeItem extends Item implements ModRarity.Expert,ICurioItem {
    public TheSpongeItem() {
        super((new Item.Properties()).stacksTo(1).rarity(ModRarity.EXPERT).fireResistant());
    }
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return this.withColor(this.getDescriptionId());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.wizard_terra_cuiros.the_sponge.text"));
        list.add(Component.translatable("tooltip.wizard_terra_cuiros.the_sponge.text1"));
        list.add(Component.translatable("tooltip.wizard_terra_cuiros.the_sponge.text2"));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.TheSponge.get());
    }
    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        slotContext.entity().getPersistentData().putInt(the_sponge_time_nbt, 0);
    }
}