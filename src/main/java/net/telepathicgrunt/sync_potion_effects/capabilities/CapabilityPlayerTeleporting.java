package net.telepathicgrunt.sync_potion_effects.capabilities;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityPlayerTeleporting {
		//the capability itself
		@CapabilityInject(IPlayerTeleporting.class)
		public static Capability<IPlayerTeleporting> TELEPORTING_CAP = null;

		//registers the capability and defines how it will read/write data from nbt
		public static void register() {
			CapabilityManager.INSTANCE.register(IPlayerTeleporting.class, new Capability.IStorage<IPlayerTeleporting>() 
			{
				@Nullable
				public INBT writeNBT(Capability<IPlayerTeleporting> capability, IPlayerTeleporting instance, Direction side) 
				{
					return instance.saveNBTData();
				}

				public void readNBT(Capability<IPlayerTeleporting> capability, IPlayerTeleporting instance, Direction side, INBT nbt) 
				{
					instance.loadNBTData((CompoundNBT) nbt);
				}
			}, () -> new PlayerTeleporting());
		}
}
