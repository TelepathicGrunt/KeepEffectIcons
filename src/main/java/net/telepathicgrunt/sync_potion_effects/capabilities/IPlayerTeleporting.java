package net.telepathicgrunt.sync_potion_effects.capabilities;

import net.minecraft.nbt.CompoundNBT;

public interface IPlayerTeleporting {

	//what methods the capability will have and what the capability is
	
	void setTeleporting(boolean teleporting);
	boolean getTeleporting();

	CompoundNBT saveNBTData();
	void loadNBTData(CompoundNBT nbtTag);
}
