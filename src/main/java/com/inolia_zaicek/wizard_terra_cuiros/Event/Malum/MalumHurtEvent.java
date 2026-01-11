package com.inolia_zaicek.wizard_terra_cuiros.Event.Malum;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import com.sammy.malum.common.capability.MalumLivingEntityDataCapability;
import com.sammy.malum.compability.tetra.TetraCompat;
import com.sammy.malum.core.handlers.SoulDataHandler;
import com.sammy.malum.registry.common.DamageTypeTagRegistry;
import com.sammy.malum.registry.common.item.ItemTagRegistry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.sammy.malum.core.handlers.SoulDataHandler.getSoulHunterWeapon;

public class MalumHurtEvent {
    @SubscribeEvent
    public static void exposeSoul(LivingHurtEvent event) {
        if (!event.isCanceled() && !(event.getAmount() <= 0.0F)) {
            LivingEntity target = event.getEntity();
            DamageSource source = event.getSource();
            if (source.getEntity() instanceof LivingEntity attacker) {
                if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.SpiritBrooch.get())){
                    //赋予目标特殊时间（有这个时间就会掉落精魂
                    exposeSoul(target);
                }
            }
        }
    }
    public static void exposeSoul(LivingEntity entity) {
        SoulDataHandler soulData = MalumLivingEntityDataCapability.getCapability(entity).soulData;
        soulData.exposedSoulDuration = 200.0F;
    }
}
