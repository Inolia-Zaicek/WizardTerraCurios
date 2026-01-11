package com.inolia_zaicek.wizard_terra_cuiros.Item.Curios;

import com.google.common.collect.Multimap;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.misc.ModAttributes;
import org.confluence.mod.misc.ModRarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TheCommunityItem extends Item implements ModRarity.Expert,ICurioItem {
    public TheCommunityItem() {
        super((new Item.Properties()).stacksTo(1).rarity(ModRarity.EXPERT).fireResistant());
    }
    public @NotNull Component getName(@NotNull ItemStack pStack) {
        return this.withColor(this.getDescriptionId());
    }

    protected String getTooltipItemName() {
        return BuiltInRegistries.ITEM.getKey(this).getPath();
    }

    public static final ResourceLocation the_community_number_nbt = new ResourceLocation(WizardTerraCurios.MODID, "the_community_number");
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.the_community.text",
                        String.valueOf(pStack.getOrCreateTag().getInt(String.valueOf(the_community_number_nbt)))
                ).withStyle(style -> style.withColor(ChatFormatting.WHITE)));
        pTooltipComponents.add(
                Component.translatable("tooltip.wizard_terra_cuiros.the_community.text_2",
                        String.valueOf(pStack.getOrCreateTag().getInt(String.valueOf((float) (WTCConfig.TheCommunityUpNumber.get()*1) ) ))
                ).withStyle(style -> style.withColor(ChatFormatting.GRAY))
        );
        pTooltipComponents.add(
                Component.translatable("tooltip.wizard_terra_cuiros.the_community.text_count",
                                String.valueOf(pStack.getOrCreateTag().getInt(String.valueOf(the_community_number_nbt)))
                        ).withStyle(style -> style.withColor(ChatFormatting.GOLD))
        );
        //击杀数量
        CompoundTag persistentData = pStack.getOrCreateTag();
        int killNumber =persistentData.getInt(String.valueOf(the_community_number_nbt));
        if(killNumber>=WTCConfig.TheCommunityKillCount.get()){
            pTooltipComponents.add(Component.translatable("tooltip.wizard_terra_cuiros.the_community.text_max")
                    .withStyle(style -> style.withColor(ChatFormatting.YELLOW)));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = ICurioItem.super.getAttributeModifiers(slotContext, uuid, stack);
        CompoundTag persistentData = stack.getOrCreateTag();
        //击杀数量
        double killNumber =persistentData.getInt(String.valueOf(the_community_number_nbt));
        //最大击杀数量
        int maxCount = WTCConfig.TheCommunityKillCount.get();
        if(killNumber>maxCount){
            killNumber = maxCount;
        }
        killNumber+=1;
        //乘以增长幅度
        killNumber*=WTCConfig.TheCommunityUpNumber.get();
        //属性结算
        attributes.put(ModAttributes.getCriticalChance(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityCri.get()*killNumber,AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityAtk.get()*killNumber, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(ModAttributes.getMagicDamage(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityMA.get()*killNumber, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(ModAttributes.getRangedDamage(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityRa.get()*killNumber, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(WTCAttributes.Summon_Damage.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunitySa.get()*killNumber, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityHp.get()*killNumber, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.ARMOR, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityAr.get()*killNumber, AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunitySp.get()*killNumber, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(WTCAttributes.Natural_Life_Regeneration.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityRe.get()*killNumber, AttributeModifier.Operation.ADDITION));
        attributes.put(WTCAttributes.DAMAGE_REDUCTION.get(), new AttributeModifier(uuid, getTooltipItemName(), WTCConfig.TheCommunityDr.get()*killNumber, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributes;
    }
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return WTCUtil.noSameCurio(slotContext.entity(), WTCItemRegister.TheCommunity.get());
    }
}