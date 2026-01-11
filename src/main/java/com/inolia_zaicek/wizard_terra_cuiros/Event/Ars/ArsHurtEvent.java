package com.inolia_zaicek.wizard_terra_cuiros.Event.Ars;

import com.hollingsworth.arsnouveau.api.mana.IManaCap;
import com.hollingsworth.arsnouveau.setup.registry.CapabilityRegistry;
import com.hollingsworth.arsnouveau.setup.registry.DamageTypesRegistry;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class ArsHurtEvent {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("ars_nouveau")) {
            LivingEntity attacked = event.getEntity();
            if (event.getSource().getEntity() instanceof LivingEntity attacker && attacked != null) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                //是魔艺伤害
                if (event.getSource().is(DamageTypesRegistry.GENERIC_SPELL_DAMAGE) || event.getSource().is(DamageTypesRegistry.COLD_SNAP)
                        || event.getSource().is(DamageTypesRegistry.FLARE) || event.getSource().is(DamageTypesRegistry.WINDSHEAR) ||
                        event.getSource().is(DamageTypesRegistry.CRUSH)) {
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.GoldenShower.get())){
                        var map = attacked.getActiveEffectsMap();
                        attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20* WTCConfig.golden_shower_time.get()), (int) (WTCConfig.golden_shower_level.get()-1)));
                        if (!attacked.hasEffect(WTCEEffectsRegister.Ichor.get())) {
                            map.put(WTCEEffectsRegister.Ichor.get(), new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.golden_shower_time.get()), (int) (WTCConfig.golden_shower_level.get()-1)));
                        }
                    }
                    float manaNumber = 1;
                    //魔能谐振仪
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.ManaPolarizer.get())) {
                        if (attacker instanceof Player player) {
                            IManaCap mana = (IManaCap) CapabilityRegistry.getMana(player).orElse((IManaCap) null);
                            manaNumber = (float) (mana.getCurrentMana() / mana.getMaxMana());
                            if (manaNumber > 0.5) {
                                manaNumber *= 0.75F;
                            }
                        }
                        attacker.heal(damage * 0.1F * manaNumber);
                    }
                }
                event.setAmount(damage);
            }
        }
    }
}