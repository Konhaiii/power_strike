package konhaiii.power_strike;

import konhaiii.power_strike.items.BlasterItem;
import konhaiii.power_strike.items.LaserRifleItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PSItems {

	private PSItems() {}
	public static final Item LASER_RIFLE = register("laser_rifle", new LaserRifleItem(new Item.Settings()));
	public static final Item BLASTER = register("blaster", new BlasterItem(new Item.Settings()));

	public static <T extends Item> T register(String path, T item) {
		return Registry.register(Registries.ITEM, Identifier.of(PowerStrike.MOD_ID, path), item);
	}

	public static final ItemGroup PS_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(LASER_RIFLE))
			.displayName(Text.translatable("itemGroup.power_strike.ps_group"))
			.entries((context, entries) -> {
				entries.add(LASER_RIFLE);
				entries.add(BLASTER);
			})
			.build();

	public static void initialize() {
		Registry.register(Registries.ITEM_GROUP, new Identifier(PowerStrike.MOD_ID, "ps_group"), PS_GROUP);
	}
}