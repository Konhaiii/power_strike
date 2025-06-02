package konhaiii.power_strike.items;

import konhaiii.power_strike.PSSounds;
import konhaiii.power_strike.PowerStrike;
import konhaiii.power_strike.entities.LaserProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LaserRifleItem extends PowerGunItem {

	public LaserRifleItem(Settings settings) {
		super(settings);
	}

	@Override
	public long getEnergyCapacity(ItemStack itemStack) {
		return PowerStrike.config.laserRifleMaxCapacity;
	}

	@Override
	public long getEnergyMaxInput(ItemStack itemStack) {
		return PowerStrike.config.laserRifleEnergyInput;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		if (itemStack.getItem() instanceof LaserRifleItem laserRifleItem && laserRifleItem.getStoredEnergy(itemStack) > 0) {
			user.setCurrentHand(hand);
			return TypedActionResult.consume(itemStack);
		}
		return TypedActionResult.fail(itemStack);
	}

	@Override
	public void usageTick(World world, net.minecraft.entity.LivingEntity user, ItemStack stack, int remainingUseTicks) {
		int i = this.getMaxUseTime(stack) - remainingUseTicks;
		float f = getPullProgress(i);
		if (f == 1) {
			if (remainingUseTicks % 5 == 0) {
				if (getStoredEnergy(stack) > 0) {
					LaserProjectileEntity laserProjectile = new LaserProjectileEntity(world, user);
					laserProjectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, f * 3.0F, 1.0F);
					laserProjectile.setPierceLevel((byte)63);
					laserProjectile.setNoGravity(true);
					laserProjectile.setDamage(PowerStrike.config.laserRifleDamage);
					world.spawnEntity(laserProjectile);
					world.playSound(null, user.getX(), user.getY(), user.getZ(), PSSounds.LASER_RIFLE_SHOT, SoundCategory.PLAYERS, 1.0F, 1.0F);
					extractEnergy(stack, PowerStrike.config.laserRifleEnergyCost);
				} else {
					user.stopUsingItem();
				}
			}
		}
	}

	public static float getPullProgress(int useTicks) {
		float f = useTicks / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}
}
