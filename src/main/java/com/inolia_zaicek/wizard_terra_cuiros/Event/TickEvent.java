package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Damage.WTCDamageType;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.inolia_zaicek.wizard_terra_cuiros.Event.HurtEvent.rover_drive_time_nbt;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = WizardTerraCurios.MODID)
public class TickEvent {
    public static final String mana_polarizer_ars_nbt = WizardTerraCurios.MODID + ":mana_polarizer_ars";
    public static final String mana_polarizer_iron_nbt = WizardTerraCurios.MODID + ":mana_polarizer_iron";
    @SubscribeEvent
    public static void tick(LivingEvent.LivingTickEvent event) {
        if (!event.getEntity().isAlive())
            return;
        LivingEntity livingEntity = event.getEntity();
        //贝壳————水下呼吸
        if (livingEntity.isUnderWater()
        && (WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.NeptunesShell.get())||WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.MoonShell.get())
                ||WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.CelestialShell.get()))
        ){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,40,0));
            livingEntity.addEffect(new MobEffectInstance(WTCEEffectsRegister.Merfolk.get(),40,0));
        }else{
            livingEntity.removeEffect(WTCEEffectsRegister.Merfolk.get());
        }
        if (WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.DiscountCard.get())||WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.GreedyRing.get()) ){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE,40,0));
        }
        if (livingEntity.level().getGameTime() % 20L == 0) {
            //有钨钢电池，开始计时【客户端卡显示了】
            if (WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.RoverDrive.get())){
                if (livingEntity instanceof ServerPlayer serverPlayer) {
                    serverPlayer.sendSystemMessage(Component.translatable(String.valueOf(livingEntity.getPersistentData().getInt(rover_drive_time_nbt)))
                            .withStyle(ChatFormatting.BLUE));
                }
                //提供护盾值
                if(livingEntity.getPersistentData().getInt(rover_drive_time_nbt)>=10&&!livingEntity.level().isClientSide()){
                    livingEntity.addEffect(new MobEffectInstance(WTCEEffectsRegister.RoverShield.get(), 20 * 60, 3));
                }else {
                    //每tick计数器+1
                    livingEntity.getPersistentData().putInt(rover_drive_time_nbt, livingEntity.getPersistentData().getInt(rover_drive_time_nbt)+1);
                }
                //没有钨钢电池直接清除buff
            }else{
                livingEntity.removeEffect(WTCEEffectsRegister.RoverShield.get());
            }
            //实体是随从————钨钢电池发光
            if(livingEntity instanceof OwnableEntity ownableEntity&&ownableEntity.getOwner()!=null){
                LivingEntity owner =ownableEntity.getOwner();
                //主人带了电池
                if (WTCUtil.isCurioEquipped(owner, WTCItemRegister.WulfrumBattery.get())){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING,200,0,false,false));
                }
            }
            var TrueDamageType = WTCDamageType.source(livingEntity.level(), WTCDamageType.TRUEDAMAGE);
            //自然生命回复计算
            float regeneration = (float) livingEntity.getAttributeValue(WTCAttributes.Natural_Life_Regeneration.get());
            //混乱石
            if (WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.ChaosStone.get())){
                if (ModList.get().isLoaded("ars_extensions")) {
                    if (livingEntity.hasEffect(WTCEEffectsRegister.ManaSickness.get()) || livingEntity.hasEffect(
                            Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation("ars_extensions", "mana_sickness"))))) {
                        regeneration -= WTCConfig.ChaosStone.get();
                    }
                }else{
                    if (livingEntity.hasEffect(WTCEEffectsRegister.ManaSickness.get()) ) {
                        regeneration -= WTCConfig.ChaosStone.get();
                    }
                }
            }
            //谐振
            CompoundTag compoundTag = livingEntity.getPersistentData();
            if(WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.ManaPolarizer.get())){
                ///都true（都大于50%
                //两个模组都有
                if(ModList.get().isLoaded("irons_spellbooks")&&ModList.get().isLoaded("ars_nouveau")
                    && compoundTag.getBoolean(mana_polarizer_ars_nbt)&&compoundTag.getBoolean(mana_polarizer_iron_nbt)) {
                    regeneration -= WTCConfig.ManaPolarizerURe.get();
                }
                //只有铁魔法
                else if(ModList.get().isLoaded("irons_spellbooks")&&!ModList.get().isLoaded("ars_nouveau")
                        &&compoundTag.getBoolean(mana_polarizer_iron_nbt)) {
                    regeneration -= WTCConfig.ManaPolarizerURe.get();
                }
                //只有魔艺
                else if(!ModList.get().isLoaded("irons_spellbooks")&&ModList.get().isLoaded("ars_nouveau")
                        &&compoundTag.getBoolean(mana_polarizer_ars_nbt)) {
                    regeneration -= WTCConfig.ManaPolarizerURe.get();
                }
            }
            if(regeneration>0){
                livingEntity.heal(regeneration);
            }
            if(regeneration<0){
                //创造模式/旁观模式，不扣血
                if(livingEntity instanceof Player player&&(player.isSpectator()||player.isCreative() ) ){return;}
                livingEntity.hurt(TrueDamageType,regeneration*-1);
            }
        }
    }
}
