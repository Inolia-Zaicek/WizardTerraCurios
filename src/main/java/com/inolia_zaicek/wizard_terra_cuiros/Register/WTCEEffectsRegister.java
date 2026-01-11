package com.inolia_zaicek.wizard_terra_cuiros.Register;

import com.inolia_zaicek.wizard_terra_cuiros.Register.buff.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios.MODID;


public class WTCEEffectsRegister {
    public static final DeferredRegister<MobEffect> INOEFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,MODID);
    //决斗
    public static final RegistryObject<MobEffect> ManaSickness = INOEFFECT.register("mana_sickness", ManaSicknessBuff::new);
    public static final RegistryObject<MobEffect> Bewitched = INOEFFECT.register("bewitched", BewitchedBuff::new);
    public static final RegistryObject<MobEffect> Strategist = INOEFFECT.register("strategist", StrategistBuff::new);
    public static final RegistryObject<MobEffect> Clairvoyance = INOEFFECT.register("clairvoyance", ClairvoyanceBuff::new);
    public static final RegistryObject<MobEffect> SugarRush = INOEFFECT.register("sugar_rush", SugarRushBuff::new);
    public static final RegistryObject<MobEffect> Sharpened = INOEFFECT.register("sharpened", SharpenedBuff::new);
    public static final RegistryObject<MobEffect> HeartLamp = INOEFFECT.register("heart_lamp", HeartLampBuff::new);
    public static final RegistryObject<MobEffect> CozyFire = INOEFFECT.register("cozy_fire", CozyFireBuff::new);
    public static final RegistryObject<MobEffect> TheBastDefense = INOEFFECT.register("the_bast_defense", TheBastDefenseBuff::new);
    public static final RegistryObject<MobEffect> Lucky = INOEFFECT.register("lucky", LuckyBuff::new);
    public static final RegistryObject<MobEffect> Honey = INOEFFECT.register("honey", HoneyBuff::new);
    public static final RegistryObject<MobEffect> Archery = INOEFFECT.register("archery", ArcheryBuff::new);
    public static final RegistryObject<MobEffect> Happy = INOEFFECT.register("happy", HappyBuff::new);
    public static final RegistryObject<MobEffect> AmmoBox = INOEFFECT.register("ammo_box", AmmoBoxBuff::new);
    public static final RegistryObject<MobEffect> ArsSource = INOEFFECT.register("ars_source", ArsSourceBuff::new);
    public static final RegistryObject<MobEffect> IronSource = INOEFFECT.register("iron_arcane", IronSourceBuff::new);
    public static final RegistryObject<MobEffect> Merfolk = INOEFFECT.register("merfolk", MerfolkBuff::new);
    public static final RegistryObject<MobEffect> CursePower = INOEFFECT.register("curse_power", CursePowerBuff::new);
    public static final RegistryObject<MobEffect> SpiritEyes = INOEFFECT.register("spirit_eyes", SpiritEyesBuff::new);
    public static final RegistryObject<MobEffect> Ichor = INOEFFECT.register("ichor", IchorBuff::new);
    public static final RegistryObject<MobEffect> RoverShield = INOEFFECT.register("rover_shield", RoverShieldBuff::new);
    public static final RegistryObject<MobEffect> TheSponge = INOEFFECT.register("the_sponge_shield", TheSpongeBuff::new);
}
