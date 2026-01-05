package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class IronHurtEvent {

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            LivingEntity attacked = event.getEntity();
            if (attacked != null) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                //手铐
                var pmg = MagicData.getPlayerMagicData(attacked);
                if (WTCUtil.isCurioEquipped(attacked, WTCItemRegister.IronMagicCuffs.get())
                        || WTCUtil.isCurioEquipped(attacked, WTCItemRegister.IronCelestialCuffs.get())) {
                    pmg.addMana(damage * 10);
                }
            }
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                /// 新生魔艺增幅铁魔法
                if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.getSource().is(ISSDamageTypes.ICE_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.NATURE_MAGIC)
                ) {
                    //法力病
                    if (attacker.hasEffect(WTCEEffectsRegister.ManaSickness.get())){
                        number*=0.75F;
                    }
                }
                //铁魔法增幅
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                event.setAmount(damage);
            }
        }
    }
}