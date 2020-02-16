package net.telepathicgrunt.sync_potion_effects.entities;

import java.util.ArrayList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.telepathicgrunt.sync_potion_effects.SyncPotionEffects;
import net.telepathicgrunt.sync_potion_effects.capabilities.IPlayerTeleporting;
import net.telepathicgrunt.sync_potion_effects.capabilities.PlayerTeleporting;


@Mod.EventBusSubscriber(modid = SyncPotionEffects.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayerTeleportationBehavior
{

	@CapabilityInject(IPlayerTeleporting.class)
	public static Capability<IPlayerTeleporting> TELEPORTING_CAP = null;

	@Mod.EventBusSubscriber(modid = SyncPotionEffects.MODID)
	private static class ForgeEvents
	{
		@SubscribeEvent
		public static void playerTick(PlayerTickEvent event)
		{
			PlayerEntity playerEntity = event.player;
			
			if(playerEntity.world instanceof ServerWorld)
			{
				//grabs the capability attached to player to know if player is teleporting to a new dimension
				PlayerTeleporting cap = (PlayerTeleporting) playerEntity.getCapability(TELEPORTING_CAP).orElseThrow(RuntimeException::new);
			
				if (cap.getTeleporting())
				{
					reAddPotionEffect(playerEntity);
					cap.setTeleporting(false); // Finished teleporting
				}
			}
		}
		


		// Fires just before the teleportation to new dimension begins
		@SubscribeEvent
		public static void entityTravelToDimensionEvent(EntityTravelToDimensionEvent event)
		{
			if(event.getEntity() instanceof PlayerEntity)
			{
				// Set capability to store the fact the player is dimension hopping rn
				PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
				PlayerTeleporting cap = (PlayerTeleporting) playerEntity.getCapability(TELEPORTING_CAP).orElseThrow(RuntimeException::new);
				cap.setTeleporting(true);
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Effects
	
	/**
	 * Temporary fix until Mojang patches the bug that makes potion effect icons disappear when changing dimension.
	 * To fix it ourselves, we remove the effect and re-add it to the player.
	 */
	private static void reAddPotionEffect(PlayerEntity playerEntity) 
	{
		//re-adds potion effects so the icon remains instead of disappearing when changing dimensions due to a bug
		ArrayList<EffectInstance> effectInstanceList = new ArrayList<EffectInstance>(playerEntity.getActivePotionEffects());
		for(int i = effectInstanceList.size() - 1; i >= 0; i--)
		{
			EffectInstance effectInstance = effectInstanceList.get(i);
			if(effectInstance != null) 
			{
				playerEntity.removeActivePotionEffect(effectInstance.getPotion());
				playerEntity.addPotionEffect(
						new EffectInstance(
								effectInstance.getPotion(), 
								effectInstance.getDuration(), 
								effectInstance.getAmplifier(), 
								effectInstance.isAmbient(), 
								effectInstance.doesShowParticles(), 
								effectInstance.isShowIcon()));
			}
		}
	}
}
