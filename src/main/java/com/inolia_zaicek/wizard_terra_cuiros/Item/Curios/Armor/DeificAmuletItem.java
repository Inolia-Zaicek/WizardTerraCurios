package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Armor;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.item.curio.BaseCurioItem;
import org.confluence.mod.item.curio.combat.IInvulnerableTime;
import org.confluence.mod.item.curio.combat.IStarCloak;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class DeificAmuletItem extends BaseCurioItem implements IStarCloak, IInvulnerableTime {
    public DeificAmuletItem() {
        super(ModRarity.LIGHT_PURPLE);
    }

    public int getTime() {
        return (int) (WTCConfig.deific_amulet.get()*1);
    }

    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(IStarCloak.TOOLTIP);
        list.add(Component.translatable("item.confluence.cross_necklace_up.tooltip"));
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.DeificAmulet.get());
    }
}