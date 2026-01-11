package com.inolia_zaicek.wizard_terra_cuiros.Mixins;

import com.google.common.collect.ImmutableMultimap;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.confluence.mod.item.curio.combat.CelestialStone;
import org.confluence.mod.item.curio.combat.SunStone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

@Pseudo
@Mixin(CelestialStone.class)
public class WTCCelestialStoneMixin {
    @Inject(
            method = "getAttributeModifiers",
            at = @At("RETURN"),
            cancellable = true, // 允许修改返回值
            remap = false
    )
    public void injectAdditionalAttributes(SlotContext slotContext, UUID uuid, ItemStack stack, CallbackInfoReturnable<ImmutableMultimap<Object, Object>> cir) {
            // 获取原始返回的Multimap
            ImmutableMultimap<Object, Object> originalAttrs = cir.getReturnValue();

            // 创建一个可变的Builder
            ImmutableMultimap.Builder<Object, Object> builder = ImmutableMultimap.builder();

            // 复制原始的属性到builder
            builder.putAll(originalAttrs);

            // 添加你想额外添加的AttributeModifier
            Attribute additionalAttribute = WTCAttributes.Summon_Damage.get(); // 你的自定义Attribute
            AttributeModifier myModifier = new AttributeModifier(
                    UUID.randomUUID(), // 可以用固定UUID，也可以为每个实例随机一个
                    "celestial_stone_extra",
                    0.05,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );
            builder.put(additionalAttribute, myModifier);
            // 设置新的返回值
            cir.setReturnValue(builder.build());
    }
}