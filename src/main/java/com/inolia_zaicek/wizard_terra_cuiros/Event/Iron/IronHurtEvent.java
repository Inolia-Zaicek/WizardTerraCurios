package com.inolia_zaicek.wizard_terra_cuiros.Event.Iron;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import org.confluence.mod.misc.ModAttributes;

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
                ///增幅部分
                if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.getSource().is(ISSDamageTypes.ICE_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.NATURE_MAGIC)
                ) {
                    float damageUp = (float) attacker.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get());
                    number *= damageUp;
                    //法力病
                    if (attacker.hasEffect(WTCEEffectsRegister.ManaSickness.get())&&!WTCUtil.isCurioEquipped(attacker, WTCItemRegister.ChaosStone.get())){
                        number*=0.75F;
                    }
                }
                ///结算部分
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                if (event.getSource().is(ISSDamageTypes.FIRE_MAGIC)||event.getSource().is(ISSDamageTypes.ICE_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.LIGHTNING_MAGIC)||event.getSource().is(ISSDamageTypes.EVOCATION_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.BLOOD_MAGIC)||event.getSource().is(ISSDamageTypes.HOLY_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.ELDRITCH_MAGIC)||event.getSource().is(ISSDamageTypes.ENDER_MAGIC)
                        ||event.getSource().is(ISSDamageTypes.NATURE_MAGIC)
                ) {
                    //魔能谐振仪
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.ManaPolarizer.get())) {
                        float manaNumber = 1;
                        if(attacker instanceof Player player) {
                            //判断魔力值
                            float maxMana = (float) player.getAttributeValue(AttributeRegistry.MAX_MANA.get());
                            manaNumber = MagicData.getPlayerMagicData(player).getMana()/maxMana;
                            if(manaNumber>0.5){
                                manaNumber*=0.75F;
                            }
                        }
                        attacker.heal(damage*0.1F*manaNumber);
                    }
                }
                event.setAmount(damage);
            }
        }
    }
}