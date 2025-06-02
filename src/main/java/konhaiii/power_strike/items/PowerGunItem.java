package konhaiii.power_strike.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.util.UseAction;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.function.Predicate;

public abstract class PowerGunItem extends RangedWeaponItem implements SimpleEnergyItem {

	public PowerGunItem(Settings settings) {
		super(settings.maxCount(1));
	}

	@Override
	public Predicate<ItemStack> getProjectiles() {
		return null;
	}

	@Override
	public int getRange() {
		return 15;
	}

	@Override
	public long getEnergyMaxOutput(ItemStack itemStack) {
		return 0;
	}

	@Override
	public boolean isItemBarVisible(ItemStack stack) {
		return true;
	}

	@Override
	public int getItemBarStep(ItemStack stack) {
		return Math.round(getStoredEnergy(stack) * 13.f / getEnergyCapacity(stack));
	}

	@Override
	public int getItemBarColor(ItemStack stack) {
		return 0xff8006;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	public void extractEnergy(ItemStack stack, int energyCost) {
		long energyStored = getStoredEnergy(stack);
		long energyExtracted = Math.min(energyStored, energyCost);
		setStoredEnergy(stack, energyStored - energyExtracted);
	}
}
