package com.inolia_zaicek.wizard_terra_cuiros.Event.Goety;

import com.Polarice3.Goety.api.magic.ISpell;
import com.Polarice3.Goety.common.entities.projectiles.SoulBolt;
import com.Polarice3.Goety.common.events.spell.*;
import com.Polarice3.Goety.common.magic.SpellStat;
import com.Polarice3.Goety.common.magic.spells.BulwarkSpell;
import com.Polarice3.Goety.common.magic.spells.IronHideSpell;
import com.Polarice3.Goety.common.magic.spells.SoulBoltSpell;
import com.hollingsworth.arsnouveau.common.perk.ChillingPerk;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class GoetyCastMagicEvent {
    @SubscribeEvent//瞬发
    public static void event(CastMagicEvent event) {
        LivingEntity speller = event.getEntity();
        ISpell spell = event.getSpell();
        String name = spell.getSpellType().getName().getString();
        // 获取现有的SpellStat对象
        SpellStat stats = spell.defaultStats();
        //强效等级
        int potency = stats.getPotency();
        if (speller instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.translatable(String.valueOf(potency))
                    .withStyle(ChatFormatting.BLUE));
        }
        if(speller.getAttributes().hasAttribute(WTCAttributes.GOTEY_Potency_AMPLIFIER.get()) &&
                !(
                        spell instanceof IronHideSpell ||spell instanceof ChillingPerk ||spell instanceof BulwarkSpell
                )
        ) {
            int finalPotency= (int) (potency*speller.getAttributeValue(WTCAttributes.GOTEY_Potency_AMPLIFIER.get()))+10;
            if (speller instanceof ServerPlayer serverPlayer) {
                serverPlayer.sendSystemMessage(Component.translatable(String.valueOf(finalPotency))
                        .withStyle(ChatFormatting.BLUE));
            }
            stats.setPotency(finalPotency);
        }
    }
    @SubscribeEvent//需要蓄力——目前测试下来和StartMagicEvent一样
    public static void event(CastingMagicEvent event) {
        LivingEntity speller = event.getEntity();
        ISpell spell = event.getSpell();
        // 获取现有的SpellStat对象
        SpellStat stats = spell.defaultStats();
        //强效等级
        int potency = stats.getPotency();
        if(speller.getAttributes().hasAttribute(WTCAttributes.GOTEY_Potency_AMPLIFIER.get())) {
            int finalPotency= (int) (potency*speller.getAttributeValue(WTCAttributes.Summon_Damage.get()));
            stats.setPotency(finalPotency);
        }
    }
    //【右键方块】（瓦解————BlockMagicEvent
    //【TouchMagicEvent】需要选中（找不到=
}