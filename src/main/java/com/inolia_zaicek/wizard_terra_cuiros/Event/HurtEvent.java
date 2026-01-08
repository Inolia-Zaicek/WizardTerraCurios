package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.Damage.WTCDamageType;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static net.minecraft.tags.DamageTypeTags.WITCH_RESISTANT_TO;

public class HurtEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event) {
            LivingEntity attacked = event.getEntity();
            if(attacked!=null){
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                //伤害减免>1——如1.3————[1-(1.3-1)=1-0.3=0.7
                overNumber *= 1 - ( (float) attacked.getAttributeValue(WTCAttributes.DAMAGE_REDUCTION.get())-1) ;
                float damage = Math.max(0,(event.getAmount() * number + fixedNumber) * overNumber);
                event.setAmount(damage);
            }
            if (event.getSource().getEntity() instanceof LivingEntity attacker&&attacked!=null) {
                float number = 1;
                float overNumber = 1;
                float fixedNumber = 0;
                if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.LuckyCoin.get())
                        ||WTCUtil.isCurioEquipped(attacker, WTCItemRegister.CoinRing.get())
                        ||WTCUtil.isCurioEquipped(attacker, WTCItemRegister.GreedyRing.get()) ) {
                    Random chance = new Random();
                    if (chance.nextInt(100) <= 10) {
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
                    //获取主人的召唤伤害属性
                    float regeneration = (float) owner.getAttributeValue(WTCAttributes.Summon_Damage.get());
                    number*=regeneration;
                }
                float damage = (event.getAmount() * number + fixedNumber) * overNumber;
                //法伤tag
                if(event.getSource().is(WITCH_RESISTANT_TO)){
                    //谐振
                    if (WTCUtil.isCurioEquipped(attacker, WTCItemRegister.ManaPolarizer.get())){
                        attacker.heal(damage*0.1F);
                    }
                }
                event.setAmount(damage);
        }
    }
}
