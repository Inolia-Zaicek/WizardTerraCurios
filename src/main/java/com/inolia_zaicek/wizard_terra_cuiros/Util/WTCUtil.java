package com.inolia_zaicek.wizard_terra_cuiros.Util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

@SuppressWarnings({"all", "removal"})
public class WTCUtil {
    public static WTCUtil INSTANCE;
    public static WTCUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WTCUtil();
        }
        return INSTANCE;
    }
    //获取周围敌人列表
    public static List<Mob> mobList(double range, LivingEntity entity){
        double x =entity.getX();
        double y =entity.getY();
        double z =entity.getZ();
        return entity.getCommandSenderWorld().getEntitiesOfClass(Mob.class,new AABB(x+range,y+range,z+range,x-range,y-range,z-range));
    }
    //获取周围玩家列表
    public static List<Player> PlayerList(double range, LivingEntity entity){
        double x =entity.getX();
        double y =entity.getY();
        double z =entity.getZ();
        return entity.getCommandSenderWorld().getEntitiesOfClass(Player.class,new AABB(x+range,y+range,z+range,x-range,y-range,z-range));
    }
    public static boolean isCurioEquipped(LivingEntity entity, Item itemStackSupplier) {
        Optional<SlotResult> slotResult = CuriosApi.getCuriosHelper().findFirstCurio(entity,itemStackSupplier);
        return slotResult.isPresent();
    }
    public static boolean isBossEntity(EntityType<?> entity) {
        // 检查 "flame_chase_artifacts:bosses" tag
        boolean isMoreTetraBoss = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.tags()).getTag(
                TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation("wizard_terra_cuiros", "bosses"))
        ).contains(entity);
        // 检查 "forge:bosses" tag
        boolean isForgeBoss = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.tags()).getTag(
                TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation("forge", "bosses"))
        ).contains(entity);
        // 只要满足其中一个 tag 即可
        return isMoreTetraBoss || isForgeBoss;
    }
    public static boolean noSameCurio(LivingEntity living, Item item) {
        return noSameCurio(living, (Predicate<ItemStack>) (itemStack) -> itemStack.getItem() == item);
    }

    public static boolean noSameCurio(LivingEntity living, Predicate<ItemStack> predicate) {
        AtomicBoolean isEmpty = new AtomicBoolean(true);
        CuriosApi.getCuriosInventory(living).ifPresent((handler) -> {
            for (ICurioStacksHandler curioStacksHandler : handler.getCurios().values()) {
                IDynamicStackHandler stackHandler = curioStacksHandler.getStacks();

                for (int i = 0; i < stackHandler.getSlots(); ++i) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty() && predicate.test(stack)) {
                        isEmpty.set(false);
                        return;
                    }
                }
            }
        });
        return isEmpty.get();
    }
    //获取玩家身上饰品xxNBT的最大数额
    public static int getMaxCommunityNumber(LivingEntity livingEntity, ResourceLocation resourceLocation, Item item) {
        int maxNumber = 0;
        Optional<ICuriosItemHandler> curiosOpt = CuriosApi.getCuriosInventory(livingEntity).resolve();
        if (!curiosOpt.isPresent()) {
            return 0;
        }

        ICuriosItemHandler curiosHandler = curiosOpt.get();
        IItemHandlerModifiable itemHandler = curiosHandler.getEquippedCurios();
        if (itemHandler == null) {
            return 0;
        }

        int slots = itemHandler.getSlots();
        for (int i = 0; i < slots; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() == item) { // 只处理Item相等的
                CompoundTag tag = stack.getTag();
                if (tag != null && tag.contains(resourceLocation.toString())) {
                    int value = tag.getInt(resourceLocation.toString());
                    if (value > maxNumber) {
                        maxNumber = value;
                    }
                }
            }
        }
        return maxNumber;
    }
    //为玩家身上所有有的Item饰品的xxNBT设置数额
    public static void setCommunityNumberForAllItems(LivingEntity livingEntity, ResourceLocation resourceLocation, int number, Item item) {
        Optional<ICuriosItemHandler> curiosOpt = CuriosApi.getCuriosInventory(livingEntity).resolve();
        if (!curiosOpt.isPresent()) {
            return;
        }

        ICuriosItemHandler curiosHandler = curiosOpt.get();
        IItemHandlerModifiable itemHandler = curiosHandler.getEquippedCurios();
        if (itemHandler == null) {
            return;
        }

        int slots = itemHandler.getSlots();
        for (int i = 0; i < slots; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() == item) { // 只处理Item相等的
                CompoundTag tag = stack.getOrCreateTag();
                tag.putInt(resourceLocation.toString(), number);
            }
        }
    }
    public static boolean isMeleeAttack(DamageSource source) {
        return !source.isIndirect() && (source.is(DamageTypes.MOB_ATTACK) || source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.MOB_ATTACK_NO_AGGRO));
    }
    //获取饰品栏第一个Item的CompoundTag（persistentData）
    public static CompoundTag getFirstCurioCompoundTag(LivingEntity livingEntity, Item item) {
        Optional<ICuriosItemHandler> curiosOpt = CuriosApi.getCuriosInventory(livingEntity).resolve();
        if (!curiosOpt.isPresent()) {
            return null; // 装备栏为空或无法访问
        }
        ICuriosItemHandler curiosHandler = curiosOpt.get();
        IItemHandlerModifiable itemHandler = curiosHandler.getEquippedCurios();
        if (itemHandler == null) {
            return null; // 没有装备
        }

        int slots = itemHandler.getSlots();
        for (int i = 0; i < slots; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (item == null || stack.getItem() == item) {
                    // 返回此 ItemStack 的 CompoundTag
                    return stack.getOrCreateTag();
                }
            }
        }
        return null; // 所有饰品都为空或没有匹配项
    }
}
