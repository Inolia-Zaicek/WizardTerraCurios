package com.inolia_zaicek.wizard_terra_cuiros.Register;

import com.inolia_zaicek.wizard_terra_cuiros.Item.CanUsedBuffItem.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.CelestialOnionItem;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Armor.RoverDriveItem;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Attack.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Functionality.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.General.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Magic.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Range.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Curios.Summon.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Goety.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.IronSpell.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Malum.*;
import com.inolia_zaicek.wizard_terra_cuiros.Item.Tacz.*;
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
    public static final DeferredRegister<Item> IronItem = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<Item> ArsItem = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<Item> TACZItem = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<Item> GoetyItem = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<Item> MalumItem = DeferredRegister.create(Registries.ITEM, MODID);
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
    //灾厄
    public static RegistryObject<Item> TheCommunity=registerCommonMaterials(ZeroingITEM,"the_community", TheCommunityItem::new);
    public static RegistryObject<Item> CelestialOnion=registerCommonMaterials(ZeroingITEM,"celestial_onion", CelestialOnionItem::new);
    //增益站
    public static RegistryObject<Item> BewitchingTable=registerCommonMaterials(ZeroingITEM,"bewitching_table", BewitchingTableItem::new);
    public static RegistryObject<Item> WarTable=registerCommonMaterials(ZeroingITEM,"war_table", WarTableItem::new);
    public static RegistryObject<Item> CrystalBall=registerCommonMaterials(ZeroingITEM,"crystal_ball", CrystalBallItem::new);
    public static RegistryObject<Item> SliceOfCake=registerCommonMaterials(ZeroingITEM,"slice_of_cake", SliceOfCakeItem::new);
    public static RegistryObject<Item> SharpeningStation=registerCommonMaterials(ZeroingITEM,"sharpening_station", SharpeningStationItem::new);
    public static RegistryObject<Item> HeartLantern=registerCommonMaterials(ZeroingITEM,"heart_lantern", HeartLanternItem::new);
    public static RegistryObject<Item> Campfire=registerCommonMaterials(ZeroingITEM,"campfire", CampfireItem::new);
    public static RegistryObject<Item> BastStatue=registerCommonMaterials(ZeroingITEM,"bast_statue", BastStatueItem::new);
    public static RegistryObject<Item> GardenGnome=registerCommonMaterials(ZeroingITEM,"garden_gnome", GardenGnomeItem::new);
    public static RegistryObject<Item> BottledHoney=registerCommonMaterials(ZeroingITEM,"bottled_honey", BottledHoneyItem::new);
    public static RegistryObject<Item> ArrowStand=registerCommonMaterials(ZeroingITEM,"arrow_stand", ArrowStandItem::new);
    public static RegistryObject<Item> Sunflower=registerCommonMaterials(ZeroingITEM,"sunflower", SunflowerItem::new);
    ///泰拉本体
    //通用
    public static RegistryObject<Item> UniterEmblem=registerCommonMaterials(ZeroingITEM,"uniter_emblem", UniterEmblemItem::new);
    public static RegistryObject<Item> DoomEmblem=registerCommonMaterials(ZeroingITEM,"doom_emblem", DoomEmblemItem::new);
    public static RegistryObject<Item> NeptunesShell=registerCommonMaterials(ZeroingITEM,"neptunes_shell", NeptunesShellItem::new);
    public static RegistryObject<Item> MoonCharm=registerCommonMaterials(ZeroingITEM,"moon_charm", MoonCharmItem::new);
    public static RegistryObject<Item> MoonShell=registerCommonMaterials(ZeroingITEM,"moon_shell", MoonShellItem::new);
    public static RegistryObject<Item> CelestialShell=registerCommonMaterials(ZeroingITEM,"celestial_shell", CelestialShellItem::new);
    public static RegistryObject<Item> DimensionalSoulArtifact=registerCommonMaterials(ZeroingITEM,"dimensional_soul_artifact", DimensionalSoulArtifactItem::new);
    //战士
    public static RegistryObject<Item> FlaskOfIchor=registerCommonMaterials(IronItem,"flask_of_ichor", FlaskOfIchorItem::new);
    //法师
    public static RegistryObject<Item> IronManaFlower=registerCommonMaterials(IronItem,"iron_mana_flower", IronManaFlowerItem::new);
    public static RegistryObject<Item> IronManaCloak=registerCommonMaterials(IronItem,"iron_mana_cloak", IronManaCloakItem::new);
    public static RegistryObject<Item> IronMagnetFlower=registerCommonMaterials(IronItem,"iron_magnet_flower", IronMagnetFlowerItem::new);
    public static RegistryObject<Item> IronArcaneFlower=registerCommonMaterials(IronItem,"iron_arcane_flower", IronArcaneFlowerItem::new);
    public static RegistryObject<Item> IronCelestialMagnet=registerCommonMaterials(IronItem,"iron_celestial_magnet", IronCelestialMagnetItem::new);
    public static RegistryObject<Item> IronCelestialEmblem=registerCommonMaterials(IronItem,"iron_celestial_emblem", IronCelestialEmblemItem::new);
    public static RegistryObject<Item> GoldenShower=registerCommonMaterials(IronItem,"golden_shower", GoldenShowerItem::new);
    public static RegistryObject<Item> ChaosStone=registerCommonMaterials(ZeroingITEM,"chaos_stone", ChaosStoneItem::new);
    public static RegistryObject<Item> ManaPolarizer=registerCommonMaterials(ZeroingITEM,"mana_polarizer", ManaPolarizerItem::new);
    public static RegistryObject<Item> SigilOfCalamitas=registerCommonMaterials(ZeroingITEM,"sigil_of_calamitas", SigilOfCalamitasItem::new);
    public static RegistryObject<Item> EtherealTalisman=registerCommonMaterials(ZeroingITEM,"ethereal_talisman", EtherealTalismanItem::new);
    //射手
    public static RegistryObject<Item> ChlorophyteQuiver=registerCommonMaterials(ZeroingITEM,"chlorophyte_quiver", ChlorophyteQuiverItem::new);
    public static RegistryObject<Item> IchorArrow=registerCommonMaterials(IronItem,"ichor_arrow", IchorArrowItem::new);
    public static RegistryObject<Item> DeadshotBrooch=registerCommonMaterials(ZeroingITEM,"deadshot_brooch", DeadshotBroochItem::new);
    public static RegistryObject<Item> ElementalQuiver=registerCommonMaterials(ZeroingITEM,"elemental_quiver", ElementalQuiverItem::new);
    //召唤师
    public static RegistryObject<Item> WulfrumBattery=registerCommonMaterials(ZeroingITEM,"wulfrum_battery", WulfrumBatteryItem::new);
    public static RegistryObject<Item> RoverDrive=registerCommonMaterials(ZeroingITEM,"rover_drive", RoverDriveItem::new);
    public static RegistryObject<Item> SummonerEmblem=registerCommonMaterials(ZeroingITEM,"summoner_emblem", SummonerEmblemItem::new);
    public static RegistryObject<Item> PygmyNecklace=registerCommonMaterials(ZeroingITEM,"pygmy_necklace", PygmyNecklaceItem::new);
    public static RegistryObject<Item> NecromanticScroll=registerCommonMaterials(ZeroingITEM,"necromantic_scroll", NecromanticScrollItem::new);
    public static RegistryObject<Item> HerculesBeetle=registerCommonMaterials(ZeroingITEM,"hercules_beetle", HerculesBeetleItem::new);
    public static RegistryObject<Item> PapyrusScarab=registerCommonMaterials(ZeroingITEM,"papyrus_scarab", PapyrusScarabItem::new);
    //功能性
    public static RegistryObject<Item> DiscountCard=registerCommonMaterials(ZeroingITEM,"discount_card", DiscountCardItem::new);
    public static RegistryObject<Item> LuckyCoin=registerCommonMaterials(ZeroingITEM,"lucky_coin", LuckyCoinItem::new);
    public static RegistryObject<Item> GoldRing=registerCommonMaterials(ZeroingITEM,"gold_ring", GoldRingItem::new);
    public static RegistryObject<Item> CoinRing=registerCommonMaterials(ZeroingITEM,"coin_ring", CoinRingItem::new);
    public static RegistryObject<Item> GreedyRing=registerCommonMaterials(ZeroingITEM,"greedy_ring", GreedyRingItem::new);
    //合成素材
    public static final RegistryObject<Item> Ichor = registerCommonMaterials(ZeroingITEM,"ichor", () -> new Item(new Item.Properties().stacksTo(64).fireResistant() ));
    //TACZ
    public static RegistryObject<Item> AmmoBox;
    public static RegistryObject<Item> AssassinScope;
    public static RegistryObject<Item> RapidFireScope;
    //新生魔艺
    public static RegistryObject<Item> GlyphScribesTable;
    //铁魔法联动饰品
    public static RegistryObject<Item> ArcaneScrollForge;
    public static RegistryObject<Item> IronManaBand;
    public static RegistryObject<Item> IronMagicCuffs;
    public static RegistryObject<Item> IronCelestialCuffs;
    public static RegistryObject<Item> IronGoldCrown;
    public static RegistryObject<Item> IronTarnishedCrown;

    //诡厄
    public static RegistryObject<Item> DarkFocus;
    public static RegistryObject<Item> SoulFocus;
    public static RegistryObject<Item> WarpedFocus;
    public static RegistryObject<Item> GoetyFoucs;
    public static RegistryObject<Item> HauntedFocus;
    public static RegistryObject<Item> BrewerHat;
    public static RegistryObject<Item> MasterBrewerHat;
    public static RegistryObject<Item> CurseInjectionStation;

    //灵灾
    public static RegistryObject<Item> SpiritAltar;
    public static RegistryObject<Item> SpiritBrooch;
    public static RegistryObject<Item> NecklaceOfTheSoulstainedEdge;
    public static RegistryObject<Item> NecklaceOfTheDeliveranceEdge;


    public static void register(IEventBus bus){
        ZeroingITEM.register(bus);
        if (ModList.get().isLoaded("irons_spellbooks")) {
            IronItem.register(bus);
            ArcaneScrollForge=registerCommonMaterials(IronItem,"arcane_scroll_forge", ArcaneScrollForgeItem::new);
            IronManaBand=registerCommonMaterials(IronItem,"iron_mana_band", IronManaBandItem::new);
            IronMagicCuffs=registerCommonMaterials(IronItem,"iron_magic_cuffs", IronMagicCuffsItem::new);
            IronCelestialCuffs=registerCommonMaterials(IronItem,"iron_celestial_cuffs", IronCelestialCuffsItem::new);
            IronGoldCrown=registerCommonMaterials(IronItem,"iron_gold_crown", IronGoldCrownItem::new);
            IronTarnishedCrown=registerCommonMaterials(IronItem,"iron_tarnished_crown", IronTarnishedCrownItem::new);
        }
        if (ModList.get().isLoaded("ars_nouveau")) {
            ArsItem.register(bus);
            GlyphScribesTable=registerCommonMaterials(ArsItem,"glyph_scribes_table", GlyphScribesTableItem::new);
        }
        if (ModList.get().isLoaded("tacz")) {
            TACZItem.register(bus);
            AmmoBox=registerCommonMaterials(TACZItem,"ammo_box", AmmoBoxItem::new);
            AssassinScope=registerCommonMaterials(TACZItem,"assassin_scope", AssassinScopeItem::new);
            RapidFireScope=registerCommonMaterials(TACZItem,"rapid_fire_scope", RapidFireScopeItem::new);
        }
        if (ModList.get().isLoaded("goety")) {
            GoetyItem.register(bus);
            CurseInjectionStation=registerCommonMaterials(GoetyItem,"curse_injection_station", CurseInjectionStationItem::new);
            DarkFocus=registerCommonMaterials(GoetyItem,"dark_focus", DarkFocusItem::new);
            SoulFocus=registerCommonMaterials(GoetyItem,"soul_focus", SoulFocusItem::new);
            WarpedFocus=registerCommonMaterials(GoetyItem,"warped_focus", WarpedFocusItem::new);
            GoetyFoucs=registerCommonMaterials(GoetyItem,"goety_focus", GoetyFoucsItem::new);
            HauntedFocus=registerCommonMaterials(GoetyItem,"haunted_focus", HauntedFocusItem::new);
            BrewerHat=registerCommonMaterials(GoetyItem,"brewer_hat", BrewerHatItem::new);
            MasterBrewerHat=registerCommonMaterials(GoetyItem,"master_brewer_hat", MasterBrewerHatItem::new);
        }
        if (ModList.get().isLoaded("malum")) {
            MalumItem.register(bus);
            SpiritAltar=registerCommonMaterials(MalumItem,"spirit_altar", SpiritAltarItem::new);
            SpiritBrooch=registerCommonMaterials(MalumItem,"spirit_brooch", SpiritBroochItem::new);
            NecklaceOfTheSoulstainedEdge=registerCommonMaterials(MalumItem,"necklace_of_the_soulstained_edge", NecklaceOfTheSoulstainedEdgeItem::new);
            NecklaceOfTheDeliveranceEdge=registerCommonMaterials(MalumItem,"necklace_of_the_deliverance_edge", NecklaceOfTheDeliveranceEdgeItem::new);
        }
    }
}