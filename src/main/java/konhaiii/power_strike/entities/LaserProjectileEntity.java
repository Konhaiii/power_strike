package konhaiii.power_strike.entities;

import konhaiii.power_strike.PSEntities;
import konhaiii.power_strike.PSSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class LaserProjectileEntity extends PersistentProjectileEntity {
	private int ticksLived = 0;
	private boolean pierce = true;
	private static final double MIN_SPEED = 2.5;

	public LaserProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	public LaserProjectileEntity(World world, LivingEntity owner) {
		super(PSEntities.LASER_PROJECTILE, owner, world);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
	}

	@Override
	public void tick() {
		super.tick();

		Vec3d velocity = this.getVelocity();
		double speed = velocity.length();

		if (speed < MIN_SPEED) {
			this.discard();
			return;
		}

		Vector3f color = new Vector3f(1.0F, 0.0F, 0.0F);
		this.getWorld().addParticle(
				new DustParticleEffect(color, 1),
				this.getX(),
				this.getY(),
				this.getZ(),
				0.0, 0.0, 0.0
		);

		ticksLived++;
		if (ticksLived >= 60) {
			this.discard();
		}
	}

	@Override
	protected ItemStack asItemStack() {
		return null;
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		if (!this.pierce) {
			this.discard();
		}
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		super.onBlockHit(blockHitResult);
		this.discard();
	}

	@Override
	protected SoundEvent getHitSound() {
		return PSSounds.LASER_PROJECTILE_HIT;
	}

	public void setPierce(boolean value) {
		this.pierce = value;
	}
}