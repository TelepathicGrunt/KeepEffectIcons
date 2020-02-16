package net.telepathicgrunt.sync_potion_effects.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.telepathicgrunt.sync_potion_effects.SyncPotionEffects;

@EventBusSubscriber(modid=SyncPotionEffects.MODID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEventHandler
{
	public static final ResourceLocation PLAYER_TELEPORTING = new ResourceLocation(SyncPotionEffects.MODID, "player_teleporting");
	
	@SubscribeEvent
	public static void onAttachCapabilitiesToEntities(AttachCapabilitiesEvent<Entity> e)
	{
		Entity ent = e.getObject();
		if (ent instanceof PlayerEntity)
		{
			e.addCapability(PLAYER_TELEPORTING, new TeleportingProvider());
		}
	}
}