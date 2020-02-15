package net.telepathicgrunt.sync_effect_icons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.telepathicgrunt.sync_effect_icons.capabilities.CapabilityPlayerTeleporting;

@Mod(KeepEffectIcons.MODID)
public class KeepEffectIcons
{
	public static final String MODID = "sync_effect_icons";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

    public KeepEffectIcons() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

		modEventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
		CapabilityPlayerTeleporting.register();
    }
}
