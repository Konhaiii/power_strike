package konhaiii.power_strike;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerStrike implements ModInitializer {
	public static final String MOD_ID = "power_strike";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static PSConfigs config;

	@Override
	public void onInitialize() {
		config = PSConfigs.loadConfig();
		PSItems.initialize();
		PSSounds.initialize();
		PSEntities.initialize();
		LOGGER.info("Initialization completed.");
	}
}