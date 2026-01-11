package com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import com.tacz.guns.init.ModDamageTypes;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.confluence.mod.misc.ModAttributes;

import java.util.Objects;

import static com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz.TaczLivingEntityShootEvent.assassin_scope_time;
import static com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz.TaczLivingEntityShootEvent.rapid_fire_scope_shoot_number;
import static net.minecraft.tags.DamageTypeTags.WITCH_RESISTANT_TO;

public class TaczHurtEvent {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("tacz")) {
            LivingEntity attacked = event.getEntity();
            if (event.getSource().getEntity() instanceof LivingEntity attacker && attacked != null) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                if (event.getSource().is(ModDamageTypes.BULLETS_TAG) || event.getSource().is(ModDamageTypes.BULLET)
                        || event.getSource().is(ModDamageTypes.BULLET_VOID) || event.getSource().is(ModDamageTypes.BULLET_IGNORE_ARMOR)
                        || event.getSource().is(ModDamageTypes.BULLET_VOID_IGNORE_ARMOR)) {
                    //暗杀
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.AssassinScope.get())
                    && attacker.getPersistentData().getInt(assassin_scope_time) > 0) {
                        //最高多少秒
                        float max = (float) (WTCConfig.assassin_scope_time.get()*1);
                        //当前等了多久
                        float now = attacker.getPersistentData().getInt(assassin_scope_time);
                        number *= (float) (1+WTCConfig.assassin_scope_damage.get()*now/(max*2) );
                        //造成伤害才清零
                        attacker.getPersistentData().putInt(assassin_scope_time, 0);
                    }
                    //速射
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.RapidFireScope.get())
                            && attacker.getPersistentData().getInt(rapid_fire_scope_shoot_number) > 0) {
                        //最高多少次
                        float max = (float) (WTCConfig.rapid_fire_scope_number.get()*1);
                        //当前射击次数
                        float now = attacker.getPersistentData().getInt(rapid_fire_scope_shoot_number);
                        number *= (float) (1+WTCConfig.rapid_fire_scope_damage.get()*now/max);
                    }
                    //灵液
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.IchorArrow.get())){
                        var map = attacked.getActiveEffectsMap();
                        attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20* WTCConfig.ichor_arrow_time.get()), (int) (WTCConfig.ichor_arrow_level.get()-1)));
                        if (!attacked.hasEffect(WTCEEffectsRegister.Ichor.get())) {
                            map.put(WTCEEffectsRegister.Ichor.get(), new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.ichor_arrow_time.get()), (int) (WTCConfig.ichor_arrow_level.get()-1)));
                        }
                    }
                    if(attacker.getAttributes().hasAttribute(ModAttributes.getRangedDamage())) {
                        float damageUp = (float) attacker.getAttributeValue(ModAttributes.getRangedDamage());
                        number *= damageUp;
                    }
                }
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                event.setAmount(damage);
            }
        }
    }
}