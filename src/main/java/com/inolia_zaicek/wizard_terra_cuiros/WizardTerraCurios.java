package com.inolia_zaicek.wizard_terra_cuiros;

import com.inolia_zaicek.wizard_terra_cuiros.Config.WTCConfig;
import com.inolia_zaicek.wizard_terra_cuiros.Event.*;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Ars.ArsHurtEvent;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Ars.*;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Goety.*;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Iron.*;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Iron.IronPlayerTickEvent;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Malum.MalumHurtEvent;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz.TaczHurtEvent;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz.TaczLivingEntityShootEvent;
import com.inolia_zaicek.wizard_terra_cuiros.Event.Tacz.TaczPlayerTickEvent;
import com.inolia_zaicek.wizard_terra_cuiros.Event.WTCEntityJoinLevelEvent;
import com.inolia_zaicek.wizard_terra_cuiros.ModelProvider.ZeroingModRecipesGen;
import com.inolia_zaicek.wizard_terra_cuiros.Register.*;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCItemRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.WTCEEffectsRegister;
import com.inolia_zaicek.wizard_terra_cuiros.Register.Tab;
import com.inolia_zaicek.wizard_terra_cuiros.loot.ModLootModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.*;


@Mod(WizardTerraCurios.MODID)
public class WizardTerraCurios {

    public static final String MODID = "wizard_terra_cuiros";
    public WizardTerraCurios() {
        init();
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, WTCConfig.SPEC);
    }

    public void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // 注册 Item、Tab、Entity 类型
        Tab.register(bus);
        WTCItemRegister.register(bus);
        ModLootModifiers.register(bus);
        WTCEEffectsRegister.INOEFFECT.register(bus);
        // 注册 CommonSetup 事件
        bus.addListener(this::commonSetup);
        // !!! 注册 ClientSetup 事件 !!!
        bus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(HurtEvent.class);
        WTCAttributes.ATTRIBUTES.register(bus);
        MinecraftForge.EVENT_BUS.register(WTCEntityAttributeGiveEvent.class);
        MinecraftForge.EVENT_BUS.register(DropsEvent.class);
        MinecraftForge.EVENT_BUS.register(WTCEntityJoinLevelEvent.class);
        //铁魔法增幅
        if(ModList.get().isLoaded("irons_spellbooks")) {
            MinecraftForge.EVENT_BUS.register(IronHurtEvent.class);
            MinecraftForge.EVENT_BUS.register(IronPlayerTickEvent.class);
            if(ModList.get().isLoaded("alshanex_familiars")) {
                MinecraftForge.EVENT_BUS.register(AFHurtEvent.class);
            }
            if(ModList.get().isLoaded("gtbcs_geomancy_plus")) {
                MinecraftForge.EVENT_BUS.register(GTBCHurtEvent.class);
            }
            if(ModList.get().isLoaded("traveloptics")) {
                MinecraftForge.EVENT_BUS.register(TOHurtEvent.class);
            }
            if(ModList.get().isLoaded("fantasy_ending")) {
                MinecraftForge.EVENT_BUS.register(FEHurtEvent.class);
            }
        }
        if(ModList.get().isLoaded("ars_nouveau")) {
            MinecraftForge.EVENT_BUS.register(ArsHurtEvent.class);
            MinecraftForge.EVENT_BUS.register(ArsPlayerTickEvent.class);
        }
        if(ModList.get().isLoaded("tacz")) {
            MinecraftForge.EVENT_BUS.register(TaczHurtEvent.class);
            MinecraftForge.EVENT_BUS.register(TaczPlayerTickEvent.class);
            MinecraftForge.EVENT_BUS.register(TaczLivingEntityShootEvent.class);
        }
        if(ModList.get().isLoaded("goety")) {
            //MinecraftForge.EVENT_BUS.register(GoetyCastMagicEvent.class);
            MinecraftForge.EVENT_BUS.register(GoetyHurtEvent.class);
        }
        if(ModList.get().isLoaded("goety")) {
            MinecraftForge.EVENT_BUS.register(GoetyLivingEntityUseItemEvent.class);
            MinecraftForge.EVENT_BUS.register(GoetyHurtEvent.class);
        }
        if(ModList.get().isLoaded("malum")) {
            MinecraftForge.EVENT_BUS.register(MalumHurtEvent.class);
        }
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
        });
    }

    // 客户端设置事件，用于注册渲染器和GUI屏幕
    // 加上 @SubscribeEvent，使其成为 Mod 事件总线上的监听器
    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }

    //注册掉落物
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        if (event.includeServer()) {
            generator.addProvider(event.includeServer(), new ZeroingModRecipesGen(output));
        }
    }

    public static ResourceLocation prefix(String name){
        return new ResourceLocation(MODID,name.toLowerCase(Locale.ROOT));
    }
    public static ResourceLocation getResource(String id) {
        return new ResourceLocation("wizard_terra_cuiros", id);
    }
}