package konhaiii.power_strike;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class PowerStrikeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ItemTooltipCallback.EVENT.register(new PSStackToolTipHandler());
		EntityRendererRegistry.register(PSEntities.LASER_PROJECTILE, LaserProjectileRenderer::new);
	}
}