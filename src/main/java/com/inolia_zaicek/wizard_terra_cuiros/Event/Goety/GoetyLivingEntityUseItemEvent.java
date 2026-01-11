package com.inolia_zaicek.wizard_terra_cuiros.Event.Goety;

import com.Polarice3.Goety.api.items.magic.IWand;
import com.Polarice3.Goety.common.items.ModItems;
import com.Polarice3.Goety.common.items.brew.BrewItem;
import com.Polarice3.Goety.common.items.curios.WitchHatItem;
import com.Polarice3.Goety.config.MainConfig;
import com.Polarice3.Goety.utils.CuriosFinder;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class GoetyLivingEntityUseItemEvent {
    @SubscribeEvent
    public static void UseItemEvent(LivingEntityUseItemEvent.Finish event) {
        float chance = 1;
        if (WTCUtil.isCurioEquipped(event.getEntity(), WTCItemRegister.BrewerHat.get())) {
            chance += 0.1F;
        }
        if (WTCUtil.isCurioEquipped(event.getEntity(), WTCItemRegister.MasterBrewerHat.get())) {
            chance += 0.2F;
        }
        if( chance>1 && new Random().nextInt(100)<=( (chance-1) *100 ) &&
                //药酿或者药水
                (event.getItem().getItem() instanceof PotionItem || event.getItem().getItem() instanceof BrewItem)
        ){
            event.setResultStack(event.getItem());
        }
    }
}
