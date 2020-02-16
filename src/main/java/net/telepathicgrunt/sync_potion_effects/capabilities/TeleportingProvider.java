package net.telepathicgrunt.sync_potion_effects.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TeleportingProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {

	//the capability itself
	@CapabilityInject(IPlayerTeleporting.class)
	public static Capability<IPlayerTeleporting> TELEPORTING_CAP = null;
	
	//The instance of the capability? I think?
	private PlayerTeleporting instance = (PlayerTeleporting) TELEPORTING_CAP.getDefaultInstance();


	//returns the capability attached to player
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == TELEPORTING_CAP) {
			if(instance == null) {
				instance = new PlayerTeleporting();
			}
			
			return LazyOptional.of(() -> instance).cast();
		} else {
			return LazyOptional.empty();
		}
	}


	@Override
	public CompoundNBT serializeNBT() {
		return instance.saveNBTData();
	}


	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		instance.loadNBTData(nbt);
	}
}
