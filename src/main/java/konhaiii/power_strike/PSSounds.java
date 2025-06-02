package konhaiii.power_strike;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class PSSounds {
	public static final SoundEvent LASER_RIFLE_SHOT = registerSoundEvent("laser_rifle_shot");
	public static final SoundEvent BLASTER_SHOT = registerSoundEvent("blaster_shot");
	public static final SoundEvent LASER_PROJECTILE_HIT = registerSoundEvent("laser_projectile_hit");

	private static SoundEvent registerSoundEvent(String name) {
		Identifier id = new Identifier(PowerStrike.MOD_ID, name);
		return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}

	public static void initialize() {
	}
}