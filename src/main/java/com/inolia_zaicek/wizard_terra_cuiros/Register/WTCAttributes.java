package com.inolia_zaicek.wizard_terra_cuiros.Register;

import com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WTCAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, WizardTerraCurios.MODID);
    //自然生命再生
    public static final RegistryObject<Attribute> Natural_Life_Regeneration = ATTRIBUTES.register("natural_life_regeneration",
            () -> new RangedAttribute(WizardTerraCurios.MODID + ".natural_life_regeneration",
                    0,//基础值
                    -1000000,//最小值
                    1000000//最大值
            ).setSyncable(true));
    //召唤伤害
    public static final RegistryObject<Attribute> Summon_Damage = ATTRIBUTES.register("summon_damage",
            () -> new RangedAttribute(WizardTerraCurios.MODID + ".summon_damage",
                    1,//基础值
                    -1000000,//最小值
                    1000000//最大值
            ).setSyncable(true));
    //伤害减免使用加算
    public static final RegistryObject<Attribute> DAMAGE_REDUCTION = ATTRIBUTES.register("damage_reduction",
            () -> new RangedAttribute(WizardTerraCurios.MODID + ".damage_reduction",
                    1,//基础值
                    0,//最小值
                    10000//最大值
            ).setSyncable(true));
    //强效增幅
    public static final RegistryObject<Attribute> GOTEY_Potency_AMPLIFIER = ATTRIBUTES.register("gotey_potency_amplifier",
            () -> new RangedAttribute(WizardTerraCurios.MODID + ".gotey_potency_amplifier",
                    1,//基础值
                    0,//最小值
                    1000000//最大值
            ).setSyncable(true));
    public static final RegistryObject<Attribute> GOTEY_Soul_Absorb = ATTRIBUTES.register("goety_soul_absorb",
            () -> new RangedAttribute(WizardTerraCurios.MODID + ".goety_soul_absorb",
                    0,//基础值
                    0,//最小值
                    1000000//最大值
            ).setSyncable(true));
}