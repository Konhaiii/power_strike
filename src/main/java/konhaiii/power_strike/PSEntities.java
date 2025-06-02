package konhaiii.power_strike;

import konhaiii.power_strike.entities.LaserProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PSEntities {
	public static final EntityType<LaserProjectileEntity> LASER_PROJECTILE = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier(PowerStrike.MOD_ID, "laser_projectile"),
			FabricEntityTypeBuilder.<LaserProjectileEntity>create(SpawnGroup.MISC, LaserProjectileEntity::new)
					.dimensions(EntityDimensions.fixed(0.5F, 0.5F))
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build()
	);
	public static void initialize() {}
}
