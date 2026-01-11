package com.inolia_zaicek.wizard_terra_cuiros.Util.Tacz;

import com.tacz.guns.resource.pojo.data.gun.Bolt;
import com.tacz.guns.util.AttachmentDataUtils;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class Tacz_WTC_Util {
    // 工具方法：判断枪械，若子弹未满则补充，用于在需要时为枪械补充弹药
    public static void reloadGunIfNeeded(ItemStack gunStack, int number) {
        // 获取枪械的详细信息，返回一个Optional对象
        Optional<GunInfo> gunInfoOpt = Gunsmith.getGunInfo(gunStack);
        // 如果没有获取到枪的相关信息，直接返回，不进行任何操作
        if (!gunInfoOpt.isPresent()) {
            return;
        }
        // 获取实际的GunInfo对象，用于后续操作
        GunInfo gun = gunInfoOpt.get();

        // 获取当前枪械中的弹药数量
        int currentAmmo = gun.gunItem().getCurrentAmmoCount(gun.gunStack());
        // 获取枪械弹仓的最大弹药容量，可能会受到附件（如弹夹、弹匣等）的影响
        int maxAmmo = AttachmentDataUtils.getAmmoCountWithAttachment(gun.gunStack(), gun.index().getGunData());
        // 只有当前弹药少于最大容量时，才进行补充
        if (currentAmmo < maxAmmo) {
            // 设置要补充的弹药数量，通常为传入的number参数
            int fillAmount = number;

            // 根据枪的弹仓类型，判断是否需要额外考虑弹仓状态（如是否开锁弹）
            if (gun.index().getGunData().getBolt() != Bolt.OPEN_BOLT) {
                // 如果弹仓没有弹药（弹仓为空）
                if (!gun.gunItem().hasBulletInBarrel(gun.gunStack())) {
                    // 设置弹仓中装有弹药
                    gun.gunItem().setBulletInBarrel(gun.gunStack(), true);
                    // 补充弹药后，弹夹中的弹药数减少一个
                    fillAmount -= 1;
                }
            }
            // 计算补充后的弹药数，不超过最大容量
            int newAmmo = Math.min(maxAmmo, currentAmmo + fillAmount);
            // 设置枪械当前的弹药数量
            gun.gunItem().setCurrentAmmoCount(gun.gunStack(), newAmmo);
        }
    }
}
