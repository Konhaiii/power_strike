package konhaiii.power_strike;

import konhaiii.power_strike.items.PowerGunItem;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class PSStackToolTipHandler implements ItemTooltipCallback {
	@Override
	public void getTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> tooltipLines) {
		Item item = itemStack.getItem();
		if (item instanceof PowerGunItem powerGunItem) {

			if (Screen.hasShiftDown()) {
				MutableText line1 = Text.literal(PSEnergySystem.getEnergy(powerGunItem.getStoredEnergy(itemStack)));
				line1.append("/");
				line1.append(PSEnergySystem.getEnergyUnit(powerGunItem.getEnergyCapacity(itemStack)));
				line1.formatted(Formatting.GOLD);

				tooltipLines.add(1, line1);

				int percentage = percentage(powerGunItem.getStoredEnergy(itemStack), powerGunItem.getEnergyCapacity(itemStack));
				MutableText line2  = Text.literal(String.valueOf(percentage)).append("%");
				line2.append(" ");
				line2.formatted(Formatting.GRAY);
				line2.append(I18n.translate("tooltip.power_strike.power_charged"));
				tooltipLines.add(2, line2);

				double inputRate = powerGunItem.getEnergyMaxInput(itemStack);
				double outputRate = powerGunItem.getEnergyMaxOutput(itemStack);

				MutableText line3 = Text.literal("");
				if (inputRate != 0 && inputRate == outputRate){
					line3.append(I18n.translate("tooltip.power_strike.transfer_rate"));
					line3.append(" : ");
					line3.formatted(Formatting.GRAY);
					line3.append(PSEnergySystem.getEnergyUnitDiminished(inputRate));
					line3.formatted(Formatting.GOLD);
				}
				else if(inputRate != 0){
					line3.append(I18n.translate("tooltip.power_strike.input_rate"));
					line3.append(" : ");
					line3.formatted(Formatting.GRAY);
					line3.append(PSEnergySystem.getEnergyUnitDiminished(inputRate));
					line3.formatted(Formatting.GOLD);
				}
				else if (outputRate !=0){
					line3.append(I18n.translate("tooltip.power_strike.output_rate"));
					line3.append(" : ");
					line3.formatted(Formatting.GRAY);
					line3.append(PSEnergySystem.getEnergyUnitDiminished(outputRate));
					line3.formatted(Formatting.GOLD);
				}
				tooltipLines.add(3, line3);
			} else {
				MutableText line1 = Text.literal(PSEnergySystem.getEnergyDiminished(powerGunItem.getStoredEnergy(itemStack)));
				line1.append("/");
				line1.append(PSEnergySystem.getEnergyUnitDiminished(powerGunItem.getEnergyCapacity(itemStack)));
				line1.formatted(Formatting.GOLD);

				tooltipLines.add(1, line1);
			}
		}
	}

	private int percentage(double CurrentValue, double MaxValue) {
		if (CurrentValue == 0)
			return 0;
		return (int) ((CurrentValue * 100.0f) / MaxValue);
	}
}