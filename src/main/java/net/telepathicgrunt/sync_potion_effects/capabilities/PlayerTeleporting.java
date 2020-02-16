package net.telepathicgrunt.sync_potion_effects.capabilities;

import net.minecraft.nbt.CompoundNBT;


public class PlayerTeleporting implements IPlayerTeleporting
{
	public boolean isTeleporting = false;


	@Override
	public void setTeleporting(boolean teleporting)
	{
		this.isTeleporting = teleporting;
	}

	@Override
	public boolean getTeleporting()
	{
		return this.isTeleporting;
	}


	@Override
	public CompoundNBT saveNBTData()
	{
		CompoundNBT nbt = new CompoundNBT();
		nbt.putBoolean("isTeleporting", this.getTeleporting());
		return nbt;
	}


	@Override
	public void loadNBTData(CompoundNBT nbtTag)
	{
		CompoundNBT cnbt = (CompoundNBT) nbtTag;
		boolean isteleporting = cnbt.getBoolean("isTeleporting");
		this.setTeleporting(isteleporting);
	}
}