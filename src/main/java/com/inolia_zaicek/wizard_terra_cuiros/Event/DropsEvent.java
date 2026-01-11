package com.inolia_zaicek.wizard_terra_cuiros.Event;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.TheCommunityItem.the_community_number_nbt;
import static com.inolia_zaicek.wizard_terra_cuiros.Util.WTCUtil.isBossEntity;

public class DropsEvent {
    //用于判断击杀名称
    private static final String the_community_kill_id_nbt = "the_community_kill_id";
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void drop(LivingDropsEvent event) {
        if (isBossEntity(event.getEntity().getType())) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity && WTCUtil.isCurioEquipped(livingEntity, WTCItemRegister.TheCommunity.get())) {
                //记录
                /*
                if (livingEntity instanceof ServerPlayer serverPlayer) {
                    serverPlayer.sendSystemMessage(Component.translatable("message.myosotis.level_up")
                            .withStyle(ChatFormatting.BLUE));
                }
                */
                //被杀的实体的记录（是ID
                String killedEntityId = EntityType.getKey(event.getEntity().getType()).toString();
                // 构建用于存储击杀实体ID的NBT键
                String nbtKeyForKilledId = "killed_entity_id_" + killedEntityId;
                CompoundTag compoundTag = WTCUtil.getFirstCurioCompoundTag(livingEntity,WTCItemRegister.TheCommunity.get());
                // 存储这个击杀实体ID
                compoundTag.putString(nbtKeyForKilledId, killedEntityId);
                // 统计所有被杀实体ID的数量（即不同ID的数量）
                int killedIdCount = 0;
                for (String existingKey : compoundTag.getAllKeys()) {
                    if (existingKey.startsWith("killed_entity_id_")) {
                        killedIdCount++;
                    }
                }
                //记录
                compoundTag.putInt(String.valueOf(the_community_number_nbt),killedIdCount);
            }
        }
    }
}
