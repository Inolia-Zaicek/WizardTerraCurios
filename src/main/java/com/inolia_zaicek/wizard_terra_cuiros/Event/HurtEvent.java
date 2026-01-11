package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;
import java.util.Random;

import static net.minecraft.tags.DamageTypeTags.IS_PROJECTILE;
import static net.minecraft.tags.DamageTypeTags.WITCH_RESISTANT_TO;

public class HurtEvent {
    //钨钢屏障计时器
    public static final String rover_drive_time_nbt = WizardTerraCurios.MODID + ":rover_drive_time";
    //量子计时器
    public static final String the_sponge_time_nbt = WizardTerraCurios.MODID + ":the_sponge_time";
    //护盾回复计时器
    public static final String the_sponge_shield_time_nbt = WizardTerraCurios.MODID + ":the_sponge_shield_time";
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
            LivingEntity attacked = event.getEntity();
            if(attacked!=null){
                //钨钢计时器清零
                attacked.getPersistentData().putInt(rover_drive_time_nbt,0);
                //化棉计时器
                attacked.getPersistentData().putInt(the_sponge_time_nbt,0);
                attacked.getPersistentData().putInt(the_sponge_shield_time_nbt,0);
                float baseDamage = event.getAmount();
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                //钨钢护盾
                if(attacked.hasEffect(WTCEEffectsRegister.RoverShield.get()) ){
                    //获取buff等级
                    int buffLevel = Objects.requireNonNull(attacked.getEffect(WTCEEffectsRegister.RoverShield.get())).getAmplifier()+1;
                    //如果伤害小于护盾值
                    if(baseDamage<=buffLevel*WTCConfig.rover_drive_shield_number.get()){
                        //如果是3.5，4伤害，那就直接扣干净（int不留余数，没问题）
                        int finalBuffLevel = (int) ((buffLevel*WTCConfig.rover_drive_shield_number.get()-baseDamage)/WTCConfig.rover_drive_shield_number.get());
                        if(!attacked.level().isClientSide()) {
                            attacked.removeEffect(WTCEEffectsRegister.RoverShield.get());
                        }
                        //新的buff等级添加【前提是剩余状态等级>1
                        if(finalBuffLevel>=1&&!attacked.level().isClientSide()) {
                            attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.RoverShield.get(), 20 * 60, finalBuffLevel - 1));
                        }
                        baseDamage = 0;
                        fixedNumber = 0;
                    }
                    //伤害大于——直接清除buff数额，并减少固定数额伤害
                    else{
                        fixedNumber -= (float) (buffLevel*WTCConfig.rover_drive_shield_number.get());
                        if(!attacked.level().isClientSide()) {
                            attacked.removeEffect(WTCEEffectsRegister.RoverShield.get());
                        }
                    }
                }
                //化棉留香石
                if(attacked.hasEffect(WTCEEffectsRegister.TheSponge.get()) ){
                    //获取buff等级
                    int buffLevel = Objects.requireNonNull(attacked.getEffect(WTCEEffectsRegister.TheSponge.get())).getAmplifier()+1;
                    //如果伤害小于护盾值
                    if(baseDamage<=buffLevel*WTCConfig.the_sponge_shield_number.get()){
                        //如果是3.5，4伤害，那就直接扣干净（int不留余数，没问题）
                        int finalBuffLevel = (int) ((buffLevel*WTCConfig.the_sponge_shield_number.get()-baseDamage)/WTCConfig.the_sponge_shield_number.get());
                        if(!attacked.level().isClientSide()) {
                            attacked.removeEffect(WTCEEffectsRegister.TheSponge.get());
                        }
                        //新的buff等级添加【前提是剩余状态等级>1
                        if(finalBuffLevel>=1&&!attacked.level().isClientSide()) {
                            attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.TheSponge.get(), 20 * 60, finalBuffLevel - 1));
                        }
                        baseDamage = 0;
                        fixedNumber = 0;
                    }
                    //伤害大于——直接清除buff数额，并减少固定数额伤害
                    else{
                        fixedNumber -= (float) (buffLevel*WTCConfig.the_sponge_shield_number.get());
                        if(!attacked.level().isClientSide()) {
                            attacked.removeEffect(WTCEEffectsRegister.TheSponge.get());
                        }
                    }
                }
                //伤害减免>1——如1.3————[1-(1.3-1)=1-0.3=0.7
                if(attacked.getAttributes().hasAttribute(WTCAttributes.DAMAGE_REDUCTION.get())) {
                    overNumber *= 1 - ((float) attacked.getAttributeValue(WTCAttributes.DAMAGE_REDUCTION.get()) - 1);
                }
                if (WTCUtil.isCurioEquipped(attacked, WTCItemRegister.DimensionalSoulArtifact.get())){
                    overNumber *= (float) (1+WTCConfig.DimensionalSoulArtifactDown.get());
                }
                float damage = Math.max(0,(baseDamage * number + fixedNumber) * overNumber);
                event.setAmount(damage);
            }
            if (event.getSource().getEntity() instanceof LivingEntity attacker&&attacked!=null) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.DimensionalSoulArtifact.get())){
                    overNumber *= (float) (1+WTCConfig.DimensionalSoulArtifactUp.get());
                }
                if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.LuckyCoin.get())
                        ||WTCUtil.isCurioEquipped(attacker, WTCItemRegister.CoinRing.get())
                        ||WTCUtil.isCurioEquipped(attacker, WTCItemRegister.GreedyRing.get()) ) {
                    Random chance = new Random();
                    if (chance.nextInt(100) <= WTCConfig.CoinChance.get()*100) {
                        Level level = attacked.level();
                        Random random = new Random();
                        //(0~9)+1
                        for (int i = 0; i < random.nextInt(10) + 1; i++) {
                            ItemEntity itementity = new ItemEntity(level, attacked.getX(),
                                    attacked.getY(), attacked.getZ(),
                                    Items.EMERALD
                                            .getDefaultInstance());
                            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F),
                                    (double) (level.random.nextFloat() * 0.05F), (double) ((level.random.nextFloat() - level.random.nextFloat()) * 0.1F)));
                            level.addFreshEntity(itementity);
                        }
                    }
                }
                //攻击者是随从
                if(attacker instanceof OwnableEntity ownableEntity&&ownableEntity.getOwner()!=null){
                    LivingEntity owner =ownableEntity.getOwner();
                    if(attacker.getAttributes().hasAttribute(WTCAttributes.Summon_Damage.get())) {
                        //获取主人的召唤伤害属性
                        float regeneration = (float) owner.getAttributeValue(WTCAttributes.Summon_Damage.get());
                        number *= regeneration;
                    }
                }
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                //法伤tag
                var map = attacked.getActiveEffectsMap();
                if(event.getSource().is(WITCH_RESISTANT_TO)){
                    //谐振
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.ManaPolarizer.get())){
                        attacker.heal(damage*0.1F);
                    }
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.GoldenShower.get())){
                        attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.golden_shower_time.get()), (int) (WTCConfig.golden_shower_level.get()-1)));
                        if (!attacked.hasEffect(WTCEEffectsRegister.Ichor.get())) {
                            map.put(WTCEEffectsRegister.Ichor.get(), new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.golden_shower_time.get()), (int) (WTCConfig.golden_shower_level.get()-1)));
                        }
                    }
                }
                //真近战伤害
                if(WTCUtil.isMeleeAttack(event.getSource())) {
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.FlaskOfIchor.get())){
                        attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.flask_of_ichor_time.get()), (int) (WTCConfig.flask_of_ichor_level.get()-1)));
                        if (!attacked.hasEffect(WTCEEffectsRegister.Ichor.get())) {
                            map.put(WTCEEffectsRegister.Ichor.get(), new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.flask_of_ichor_time.get()), (int) (WTCConfig.flask_of_ichor_level.get()-1)));
                        }
                    }
                }
                //弹射物伤害
                if(event.getSource().is(IS_PROJECTILE)){
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.IchorArrow.get()) ){
                        attacked.addEffect(new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.ichor_arrow_time.get()), (int) (WTCConfig.ichor_arrow_level.get()-1)));
                        if (!attacked.hasEffect(WTCEEffectsRegister.Ichor.get())) {
                            map.put(WTCEEffectsRegister.Ichor.get(), new MobEffectInstance(WTCEEffectsRegister.Ichor.get(), (int) (20*WTCConfig.ichor_arrow_time.get()), (int) (WTCConfig.ichor_arrow_level.get()-1)));
                        }
                    }
                }
                event.setAmount(damage);
        }
    }
}
