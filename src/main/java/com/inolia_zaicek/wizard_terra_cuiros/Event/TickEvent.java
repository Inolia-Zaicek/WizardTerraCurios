package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = WizardTerraCurios.MODID)
public class TickEvent {
    @SubscribeEvent
    public static void playerOnTick(net.minecraftforge.event.TickEvent.PlayerTickEvent e) {
        if (!e.player.getCommandSenderWorld().isClientSide) {
            Player player = e.player;
            var pmg = MagicData.getPlayerMagicData(player);
            if (pmg != null) {
                // 条件满足时，做额外检测
                if (ModList.get().isLoaded("confluence")) {
                    // 魔力花
                    if (WTCUtil.isCurioEquipped(player, WTCItemRegister.IronManaFlower.get())
                            || WTCUtil.isCurioEquipped(player, WTCItemRegister.IronManaCloak.get())
                            || WTCUtil.isCurioEquipped(player, WTCItemRegister.IronMagnetFlower.get())
                    ) {
                        float maxMana = (float) player.getAttributeValue(AttributeRegistry.MAX_MANA.get());
                        if (pmg.getMana() <= maxMana * 0.5F) {
                            // 新增的检测和消耗逻辑
                            boolean consumed = false;

                            // 按顺序检测：主手、副手、物品栏、背包
                            // 检查主手
                            ItemStack mainHand = player.getMainHandItem();
                            if (mainHand.getItem() == Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("irons_spellbooks", "arcane_essence"))) && mainHand.getCount() > 0) {
                                mainHand.shrink(1); // 消耗1个
                                consumed = true;
                            }
                            // 如果没消耗，检测副手
                            if (!consumed) {
                                ItemStack offHand = player.getOffhandItem();
                                if (offHand.getItem() == Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("irons_spellbooks", "arcane_essence"))) && offHand.getCount() > 0) {
                                    offHand.shrink(1);
                                    consumed = true;
                                }
                            }
                            // 如果还没消耗，检测物品栏
                            if (!consumed) {
                                Inventory inventory = player.getInventory();
                                for (int i = 0; i < inventory.getContainerSize(); i++) {
                                    ItemStack stackInSlot = inventory.getItem(i);
                                    if (stackInSlot.getItem() == Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("irons_spellbooks", "arcane_essence"))) && stackInSlot.getCount() > 0) {
                                        stackInSlot.shrink(1); // 消耗1个
                                        consumed = true;
                                        break;
                                    }
                                }
                            }

                            // 最后，检测背包内
                            if (!consumed) {
                                Inventory inventory = player.getInventory();
                                for (int i = 0; i < inventory.getContainerSize(); i++) {
                                    ItemStack stackInSlot = inventory.getItem(i);
                                    if (stackInSlot.getItem() == Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("irons_spellbooks", "arcane_essence"))) && stackInSlot.getCount() > 0) {
                                        stackInSlot.shrink(1); // 消耗1个
                                        consumed = true;
                                        break;
                                    }
                                }
                            }

                            // 如果找到了物品并消耗了，增加法力
                            if (consumed) {
                                pmg.addMana(maxMana * 0.25F);
                                player.addEffect(new MobEffectInstance(WTCEEffectsRegister.ManaSickness.get(), 100, 0));
                            }
                        }
                    }
                }
            }
        }
    }
}