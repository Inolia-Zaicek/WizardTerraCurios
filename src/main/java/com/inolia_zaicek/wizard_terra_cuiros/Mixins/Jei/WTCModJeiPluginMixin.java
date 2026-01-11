package com.inolia_zaicek.wizard_terra_cuiros.Mixins.Jei;

import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.confluence.mod.integration.jei.ModJeiPlugin;
import org.confluence.mod.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(ModJeiPlugin.class)
public class WTCModJeiPluginMixin {
    @Inject(
            method = "registerRecipes", // 目标方法名称
            at = @At("RETURN"), // 在方法返回点插入（即调用getLevels后）
            remap = false // 不进行混淆映射
    )
    public void registerRecipes(IRecipeRegistration registration, CallbackInfo ci) {
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.ManaPolarizer.get()),
                new Component[]{Component.translatable("item.confluence.mana_polarizer.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.NecromanticScroll.get()),
                new Component[]{Component.translatable("item.confluence.necromantic_scroll.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.HerculesBeetle.get()),
                new Component[]{Component.translatable("item.confluence.hercules_beetle.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.ChaosStone.get()),
                new Component[]{Component.translatable("item.confluence.chaos_stone.info")});

        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.BewitchingTable.get()),
                new Component[]{Component.translatable("item.confluence.bewitching_table.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.WarTable.get()),
                new Component[]{Component.translatable("item.confluence.war_table.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.CrystalBall.get()),
                new Component[]{Component.translatable("item.confluence.crystal_ball.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.BastStatue.get()),
                new Component[]{Component.translatable("item.confluence.bast_statue.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.GardenGnome.get()),
                new Component[]{Component.translatable("item.confluence.garden_gnome.info")});

        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.PygmyNecklace.get()),
                new Component[]{Component.translatable("item.confluence.pygmy_necklace.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.CelestialOnion.get()),
                new Component[]{Component.translatable("item.confluence.celestial_onion.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.NeptunesShell.get()),
                new Component[]{Component.translatable("item.confluence.neptunes_shell.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.MoonCharm.get()),
                new Component[]{Component.translatable("item.confluence.moon_charm.info")});

        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.DiscountCard.get()),
                new Component[]{Component.translatable("item.confluence.coin_curios.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.LuckyCoin.get()),
                new Component[]{Component.translatable("item.confluence.coin_curios.info")});
        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.GoldRing.get()),
                new Component[]{Component.translatable("item.confluence.coin_curios.info")});

        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.DimensionalSoulArtifact.get()),
                new Component[]{Component.translatable("item.confluence.dimensional_soul_artifact.info")});

        registration.addItemStackInfo(new ItemStack((ItemLike) WTCItemRegister.Ichor.get()),
                new Component[]{Component.translatable("item.confluence.ichor.info")});
    }
}
