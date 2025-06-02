package konhaiii.power_strike;

import konhaiii.power_strike.entities.LaserProjectileEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class LaserProjectileRenderer extends ProjectileEntityRenderer<LaserProjectileEntity> {
	public static final Identifier TEXTURE = new Identifier(PowerStrike.MOD_ID ,"textures/entity/projectiles/laser.png");

	public LaserProjectileRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(LaserProjectileEntity entity)  {
		return TEXTURE;
	}
}