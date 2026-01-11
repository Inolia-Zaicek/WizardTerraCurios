package com.inolia_zaicek.wizard_terra_cuiros.Event.Goety;

import com.Polarice3.Goety.api.items.magic.ITotem;
import com.Polarice3.Goety.config.MainConfig;
import com.Polarice3.Goety.utils.TotemFinder;
import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import static com.Polarice3.Goety.utils.SEHelper.*;

public class GoetyHurtEvent {
    @SubscribeEvent
    public static void hurt(LivingHurtEvent event) {
        if (ModList.get().isLoaded("goety")) {
            //攻击者是玩家
            if (event.getSource().getEntity() instanceof Player player && player.getAttributes().hasAttribute(WTCAttributes.GOTEY_Soul_Absorb.get())){
                int soul = (int) (event.getAmount() * player.getAttributeValue(WTCAttributes.GOTEY_Soul_Absorb.get()));
                    //判断是否有灵魂方舟
                    if (getSEActive(player)) {
                        sendSEUpdatePacket(player);
                        //灵魂方舟灵魂上限*最大汲取百分比
                        increaseSESouls(player,
                                //无法超过当前容器的一定比例
                                (int) Math.min(MainConfig.MaxArcaSouls.get() * WTCConfig.AbsorbMax.get(),
                                        Math.min(MainConfig.MaxArcaSouls.get()* WTCConfig.MaxAbsorb.get(),soul) )
                        );
                    } else {
                        ItemStack foundStack = TotemFinder.FindTotem(player);
                        if (foundStack != null&&foundStack.getTag() != null) {
                            ITotem.increaseSouls(foundStack, (int) Math.min(MainConfig.MaxSouls.get() * WTCConfig.AbsorbMax.get(),
                                    Math.min(MainConfig.MaxSouls.get()* WTCConfig.MaxAbsorb.get() ,soul)));
                        }
                    }
                }
            }
    }
}
