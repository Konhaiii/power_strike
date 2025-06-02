package konhaiii.power_strike;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PSConfigs {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path CONFIG_PATH = Path.of("config", "power_strike.json");

	public int blasterMaxCapacity = 100000;
	public int blasterEnergyInput = 2048;
	public int blasterEnergyCost = 256;
	public double blasterDamage = 2.0;
	public int laserRifleMaxCapacity = 1000000;
	public int laserRifleEnergyInput = 8192;
	public int laserRifleEnergyCost = 2048;
	public double laserRifleDamage = 4.0;

	public static PSConfigs loadConfig() {
		if (!Files.exists(CONFIG_PATH)) {
			PSConfigs defaultConfig = new PSConfigs();
			defaultConfig.saveConfig();
			return defaultConfig;
		}

		try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
			return GSON.fromJson(reader, PSConfigs.class);
		} catch (IOException | JsonSyntaxException exception) {
			PowerStrike.LOGGER.error(exception.getMessage());
			return new PSConfigs();
		}
	}

	public void saveConfig() {
		try {
			Files.createDirectories(CONFIG_PATH.getParent());
			try (Writer writer = Files.newBufferedWriter(CONFIG_PATH, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
				GSON.toJson(this, writer);
			}
		} catch (IOException exception) {
			PowerStrike.LOGGER.error(exception.getMessage());
		}
	}
}