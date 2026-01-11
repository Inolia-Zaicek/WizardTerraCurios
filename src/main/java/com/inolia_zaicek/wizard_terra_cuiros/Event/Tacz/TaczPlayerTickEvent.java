package com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.Tacz.Tacz_WTC_Util;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.resource.pojo.data.gun.Bolt;
import com.tacz.guns.util.AttachmentDataUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import java.util.Optional;

import static com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz.TaczLivingEntityShootEvent.*;

public class TaczPlayerTickEvent {

    @SubscribeEvent
    public static void playerOnTick(TickEvent.PlayerTickEvent event) {
        //
        Player player = event.player;
        if (ModList.get().isLoaded("tacz")&&player.level().getGameTime() % 1 == 0) {
            //没有暗杀镜
            if (!WTCUtil.isCurioEquipped(player, WTCItemRegister.AssassinScope.get())) {
                player.getPersistentData().putInt(assassin_scope_time, 0);
            } else {
                //最长续
                player.getPersistentData().putInt(assassin_scope_time, (int) Math.min(WTCConfig.assassin_scope_time.get() * 2, player.getPersistentData().getInt(assassin_scope_time) + 1));
            }
            //没有速射
            if (!WTCUtil.isCurioEquipped(player, WTCItemRegister.RapidFireScope.get())) {
                //射击次数与计时器都归零
                player.getPersistentData().putInt(rapid_fire_scope_shoot_number, 0);
                player.getPersistentData().putInt(rapid_fire_scope_time, 0);
            } else {
                //提升计时器
                player.getPersistentData().putInt(rapid_fire_scope_time, (int) Math.min(WTCConfig.rapid_fire_scope_time.get() * 2, player.getPersistentData().getInt(rapid_fire_scope_time) + 1));
            }
            //速射计时器到达配置文件上限值————射击计数器归零，但计时器不变（等射击
            if (player.getPersistentData().getInt(rapid_fire_scope_time) >= WTCConfig.rapid_fire_scope_time.get() * 2) {
                player.getPersistentData().putInt(rapid_fire_scope_shoot_number, 0);
            }
            // 时间控制：每20个tick（1秒）执行一次
            if (player.level().getGameTime() % 20L == 0) {
                /*【回子弹
                    // 1. 判断玩家主手武器（枪）是否为枪械（模式内的Gun对象）
                    ItemStack mainHand = player.getMainHandItem();
                    if (IGun.getIGunOrNull(mainHand) != null) {
                        // 回复主手枪的子弹
                        Tacz_WTC_Util.reloadGunIfNeeded(mainHand, 1);
                    }
                    // 4. 判断副手
                    ItemStack offHand = player.getOffhandItem();
                    if (IGun.getIGunOrNull(offHand) != null) {
                        Tacz_WTC_Util.reloadGunIfNeeded(offHand, 1);
                    }
                   }
                 */
            }
        }
    }
}