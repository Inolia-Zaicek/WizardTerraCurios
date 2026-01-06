package com.inolia_zaicek.wizard_terra_cuiros.Register;

import com.inolia_zaicek.wizard_terra_cuiros.Item.IronSpell.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.inolia_zaicek.wizard_terra_cuiros.WizardTerraCurios.MODID;

public class WTCItemRegister {
    public static final DeferredRegister<Item> ZeroingITEM = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<Item> ConfluenceITEM = DeferredRegister.create(Registries.ITEM, MODID);
    public static List<RegistryObject<Item>> CommonItem = new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register, String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        CommonItem.add(object);
        return object;
    }
    //伤害：剑A的情况下，斧头A+2，镐A-2，锹A-1.5，锄固定为1
    // 攻击速度（镐统一-2.8，斧头-3.1，剑-2.4，锹-3，锄0
    ///铜
    //tag
    public static final TagKey<Item> copper = TagKey.create(Registries.ITEM,new ResourceLocation("forge","ingots/copper"));

    public static RegistryObject<Item> TheCommunity=registerCommonMaterials(ConfluenceITEM,"the_community", TheCommunityItem::new);
    public static RegistryObject<Item> ManaPolarizer=registerCommonMaterials(ConfluenceITEM,"mana_polarizer", ManaPolarizerItem::new);
    public static RegistryObject<Item> SummonerEmblem=registerCommonMaterials(ConfluenceITEM,"summoner_emblem", SummonerEmblemItem::new);
    public static RegistryObject<Item> UniterEmblem=registerCommonMaterials(ConfluenceITEM,"uniter_emblem", UniterEmblemItem::new);
    public static RegistryObject<Item> DoomEmblem=registerCommonMaterials(ConfluenceITEM,"doom_emblem", DoomEmblemItem::new);
    public static RegistryObject<Item> NecromanticScroll=registerCommonMaterials(ConfluenceITEM,"necromantic_scroll", NecromanticScrollItem::new);
    public static RegistryObject<Item> HerculesBeetle=registerCommonMaterials(ConfluenceITEM,"hercules_beetle", HerculesBeetleItem::new);
    public static RegistryObject<Item> PapyrusScarab=registerCommonMaterials(ConfluenceITEM,"papyrus_scarab", PapyrusScarabItem::new);
    public static RegistryObject<Item> ChaosStone=registerCommonMaterials(ConfluenceITEM,"chaos_stone", ChaosStoneItem::new);
    //铁魔法联动饰品
    public static RegistryObject<Item> IronManaBand;
    public static RegistryObject<Item> IronManaFlower;
    public static RegistryObject<Item> IronManaCloak;
    public static RegistryObject<Item> IronMagicCuffs;
    public static RegistryObject<Item> IronCelestialMagnet;
    public static RegistryObject<Item> IronCelestialCuffs;
    public static RegistryObject<Item> IronMagnetFlower;
    public static RegistryObject<Item> IronCelestialEmblem;
    public static RegistryObject<Item> IronArcaneFlower;

    public static void register(IEventBus bus){
        ZeroingITEM.register(bus);
        if (ModList.get().isLoaded("irons_spellbooks")) {
            ConfluenceITEM.register(bus);
            IronManaBand=registerCommonMaterials(ConfluenceITEM,"iron_mana_band", IronManaBandItem::new);
            IronManaFlower=registerCommonMaterials(ConfluenceITEM,"iron_mana_flower", IronManaFlowerItem::new);
            IronManaCloak=registerCommonMaterials(ConfluenceITEM,"iron_mana_cloak", IronManaCloakItem::new);
            IronMagicCuffs=registerCommonMaterials(ConfluenceITEM,"iron_magic_cuffs", IronMagicCuffsItem::new);
            IronCelestialMagnet=registerCommonMaterials(ConfluenceITEM,"iron_celestial_magnet", IronCelestialMagnetItem::new);
            IronCelestialCuffs=registerCommonMaterials(ConfluenceITEM,"iron_celestial_cuffs", IronCelestialCuffsItem::new);
            IronMagnetFlower=registerCommonMaterials(ConfluenceITEM,"iron_magnet_flower", IronMagnetFlowerItem::new);
            IronCelestialEmblem=registerCommonMaterials(ConfluenceITEM,"iron_celestial_emblem", IronCelestialEmblemItem::new);
            IronArcaneFlower=registerCommonMaterials(ConfluenceITEM,"iron_arcane_flower", IronArcaneFlowerItem::new);
        }
    }
}