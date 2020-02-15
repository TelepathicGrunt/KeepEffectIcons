package net.telepathicgrunt.sync_effect_icons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.telepathicgrunt.sync_effect_icons.capabilities.CapabilityPlayerPosAndDim;
import net.telepathicgrunt.sync_effect_icons.config.BzConfig;

@Mod(KeepEffectIcons.MODID)
public class KeepEffectIcons
{
	public static final String MODID = "sync_effect_icons";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

    public KeepEffectIcons() {
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

		modEventBus.addListener(this::setup);
        
		//generates/handles config
		modEventBus.addListener(this::modConfig);
		modLoadingContext.registerConfig(ModConfig.Type.SERVER, BzConfig.SERVER_SPEC);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
		CapabilityPlayerPosAndDim.register();
    }

	public void modConfig(final ModConfig.ModConfigEvent event)
	{
		ModConfig config = event.getConfig();
		if (config.getSpec() == BzConfig.SERVER_SPEC)
			BzConfig.refreshServer();
	}
}
