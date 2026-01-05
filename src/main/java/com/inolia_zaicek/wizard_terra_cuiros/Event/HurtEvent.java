package com.inolia_zaicek.wizard_terra_cuiros.Event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.minecraft.tags.DamageTypeTags.WITCH_RESISTANT_TO;

public class HurtEvent {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
            LivingEntity attacked = event.getEntity();
            if (attacked != null) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
            }
            if (event.getSource().getEntity() instanceof LivingEntity attacker&&attacked!=null) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                event.setAmount(damage);
        }
    }
}
