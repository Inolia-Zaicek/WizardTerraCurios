package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WTCEntityAttributeGiveEvent {
    @SubscribeEvent
    public static void addCustomAttributes(EntityAttributeModificationEvent event) {
        for (EntityType<? extends LivingEntity> entityType : event.getTypes()) {
            //有最大生命值时，为其新增
            if (event.has(entityType, Attributes.MAX_HEALTH)) {
                event.add(entityType, WTCAttributes.Natural_Life_Regeneration.get());
                event.add(entityType, WTCAttributes.Summon_Damage.get());
                event.add(entityType, WTCAttributes.DAMAGE_REDUCTION.get());
                if (ModList.get().isLoaded("goety")) {
                    event.add(entityType, WTCAttributes.GOTEY_Potency_AMPLIFIER.get());
                    event.add(entityType, WTCAttributes.GOTEY_Soul_Absorb.get());
                }
            }
        }
    }

}