package com.inolia_zaicek.wizard_terra_cuiros.Event.Iron;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.alshanex.alshanex_familiars.datagen.AFDamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import org.confluence.mod.misc.ModAttributes;

public class AFHurtEvent {

    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("alshanex_familiars")) {
            LivingEntity attacked = event.getEntity();
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                ///增幅部分
                if (event.getSource().is(AFDamageTypes.SOUND_MAGIC)
                ) {
                    if(attacker.getAttributes().hasAttribute(ModAttributes.getMagicDamage())) {
                        float damageUp = (float) attacker.getAttributeValue(ModAttributes.getMagicDamage());
                        number *= damageUp;
                    }
                    //法力病
                    if (attacker.hasEffect(WTCEEffectsRegister.ManaSickness.get())&&!WTCUtil.isCurioEquipped(attacker, WTCItemRegister.ChaosStone.get())){
                        number*=0.75F;
                    }
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.GoldenShower.get())){
                        var map = attacked.getActiveEffectsMap();
                        attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20* WTCConfig.golden_shower_time.get()), (int) (WTCConfig.golden_shower_level.get()-1)));
                        if (!attacked.hasEffect(WTCEEffectsRegister.Ichor.get())) {
                            map.put(WTCEEffectsRegister.Ichor.get(), new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.golden_shower_time.get()), (int) (WTCConfig.golden_shower_level.get()-1)));
                        }
                    }
                }
                ///结算部分
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                if (event.getSource().is(AFDamageTypes.SOUND_MAGIC)
                ) {
                    //魔能谐振仪
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.ManaPolarizer.get())) {
                        float manaNumber = 1;
                        if(attacker instanceof Player player) {
                            //判断魔力值
                            float maxMana = (float) player.getAttributeValue(AttributeRegistry.MAX_MANA.get());
                            manaNumber = MagicData.getPlayerMagicData(player).getMana()/maxMana;
                        }
                        attacker.heal(damage*0.1F*manaNumber);
                    }
                }
                event.setAmount(damage);
            }
        }
    }
}