package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCTargetMode;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;
import java.util.function.Predicate;

public class WTCEntityJoinLevelEvent {
    @SubscribeEvent
    public static void addMode(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Projectile projectile) {
            // 如果实体是投射物（如箭），尝试找到它的发射者
            Entity owner = projectile.getOwner();
            if (owner instanceof LivingEntity shooter &&
                    // 叶绿系列饰品
                    (
                            WTCUtil.isCurioEquipped(shooter, WTCItemRegister.ChlorophyteQuiver.get())
                                    || WTCUtil.isCurioEquipped(shooter, WTCItemRegister.ElementalQuiver.get())
                    )
            ) {
                Random chance = new Random();
                if (chance.nextInt(100) <= (100 * WTCConfig.TrackChance.get() ) ) {
                    //设置目标筛选
                    Predicate<Entity> targetPredicate = (target) -> target instanceof LivingEntity && !(target instanceof Player)
                            //自己随从不锁
                            && !(target instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() != null && ownableEntity.getOwner() == shooter);

                    if (projectile instanceof WTCTargetMode modeObj) {
                        modeObj.mmt$setMode(targetPredicate);
                    }
                }
            }
        }
    }
}
