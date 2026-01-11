package com.inolia_zaicek.wizard_terra_cuiros.Item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.confluence.mod.misc.ModRarity;

import javax.annotation.Nullable;
import java.util.List;

public class BuffItem extends Item {

    public BuffItem(Rarity rarity) {
        super(rarity != ModRarity.GRAY && rarity != ModRarity.WHITE ? new Properties().rarity(rarity).fireResistant().stacksTo(1) : new Properties().rarity(rarity).stacksTo(1));
    }

    /**
     * 返回药水效果的实例，或者null。子类重写此方法以定义效果。
     */
    public MobEffectInstance getPotionEffect() {
        return null;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        MobEffectInstance effect = this.getPotionEffect();

        if (effect != null) {
            player.addEffect(effect);
            player.getCooldowns().addCooldown(this.asItem(), 20);
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        } else {
            return InteractionResultHolder.pass(itemstack);
        }
    }

    /**
     * 获取物品的显示名称颜色（RGB整数值）。子类重写返回不同颜色。默认返回白色。
     */
    public int getItemNameColor() {
        return 0xFFFFFF; // 白色
    }

    /**
     * 获取带颜色的物品名称，用于显示
     */
    public Component getColoredName() {
        String name = getTooltipItemName();

        // 生成带颜色的Component
        return Component.literal(name).withStyle(style -> style.withColor(TextColor.fromRgb(getItemNameColor())));
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        String itemName = getTooltipItemName(); // 获取物品 ID
        //if (Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip." + "more_mod_tetra" + "." + itemName + "_shift_text").withStyle(style -> style.withColor(TextColor.fromRgb(0xA0A0A0))));} else
        {
            // 鼠标经过时的默认文本
            // 翻译键格式: tooltip.<你的ModID>.<物品ID>_text
            pTooltipComponents.add(Component.translatable("tooltip." + "wizard_terra_cuiros" + "." + itemName + ".text")
                    .withStyle(style -> style.withColor(TextColor.fromRgb(0xFFFFFF))));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}