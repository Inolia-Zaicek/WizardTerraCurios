package com.inolia_zaicek.wizard_terra_cuiros.Event.Ars;

import com.hollingsworth.arsnouveau.api.mana.IManaCap;
import com.hollingsworth.arsnouveau.setup.config.ServerConfig;
import com.hollingsworth.arsnouveau.setup.registry.CapabilityRegistry;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.wizard_terra_cuiros.Event.TickEvent.mana_polarizer_ars_nbt;

public class ArsPlayerTickEvent {

    @SubscribeEvent
    public static void playerOnTick(net.minecraftforge.event.TickEvent.PlayerTickEvent e) {
        if (!e.player.getCommandSenderWorld().isClientSide && e.player.getCommandSenderWorld().getGameTime() % (long) (Integer) ServerConfig.REGEN_INTERVAL.get() == 0L) {
            Player player = e.player;
            IManaCap mana = (IManaCap) CapabilityRegistry.getMana(player).orElse((IManaCap) null);
            if (mana != null) {
                //魔源大于50%
                if (mana.getCurrentMana() <= mana.getMaxMana() * 0.5F) {
                    player.getPersistentData().putBoolean(mana_polarizer_ars_nbt,false);
                }else {
                    player.getPersistentData().putBoolean(mana_polarizer_ars_nbt,true);
                }
            }
        }
    }
}
