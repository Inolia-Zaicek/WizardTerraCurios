package com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.Tacz.Tacz_WTC_Util;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios;
import com.tacz.guns.api.event.common.GunShootEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.confluence.mod.item.curio.CurioItems;

import java.util.Random;

import static com.inolia_zaicek.wizard_terra_cuiros.Event.TickEvent.mana_polarizer_iron_nbt;

public class TaczLivingEntityShootEvent {
    //暗杀瞄准镜时间
    public static final String assassin_scope_time = WizardTerraCurios.MODID + ":assassin_scope";
    public static final String rapid_fire_scope_shoot_number = WizardTerraCurios.MODID + ":rapid_fire_scope_number";
    public static final String rapid_fire_scope_time = WizardTerraCurios.MODID + ":rapid_fire_scope_time";
    @SubscribeEvent
    public static void hurt(GunShootEvent event) {
        LivingEntity shooter = event.getShooter();
        ItemStack gunItemStack = event.getGunItemStack();
        if (shooter != null && gunItemStack != null) {
            /// 速射
            //速射清除增伤的计时器归零
            shooter.getPersistentData().putInt(rapid_fire_scope_time,0);
            //当前射击次数+1
            int now = shooter.getPersistentData().getInt(rapid_fire_scope_shoot_number);
            shooter.getPersistentData().putInt(rapid_fire_scope_shoot_number, (int) Math.min(WTCConfig.rapid_fire_scope_number.get(),now+1));
            //弹药消耗减免部分
            Random random = new Random();
            float ammoReductionChance = 100;
            //有一个箭袋
            if (WTCUtil.hasIMagicQuiver(shooter)) {
                ammoReductionChance *= 1.3F;
            }
            if(shooter.hasEffect(WTCEEffectsRegister.AmmoBox.get())){
                ammoReductionChance *= 1.2F;
            }
            if (ammoReductionChance > 100) {
                float chance = ammoReductionChance-100;
                if(random.nextInt(100) <= chance) {
                    Tacz_WTC_Util.reloadGunIfNeeded(gunItemStack, 1);
                }
            }
        }
    }
}
