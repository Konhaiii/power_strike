package konhaiii.power_strike;

import java.text.DecimalFormat;
import java.util.Locale;

public class PSEnergySystem {
	public static final String EnergyAbbreviation = "E";
	public static final char[] magnitude = new char[] { 'k', 'M', 'G', 'T' };

	public static String getEnergyUnitDiminished(double energy) {
		return getFinalString(energy, true, true);
	}
	public static String getEnergyDiminished(double energy) {
		return getFinalString(energy, false, true);
	}
	public static String getEnergyUnit(double energy) {
		return getFinalString(energy, true, false);
	}
	public static String getEnergy(double energy) {
		return getFinalString(energy, false, false);
	}

	private static String getFinalString(double energy, boolean showUnit, boolean showDiminutive) {
		String returnValue = "";
		double finalValue = 0f;
		double value = energy;
		int i = 0;
		boolean doFormat = true;
		boolean showMagnitude = true;

		if (showDiminutive) {
			if (energy < 1000) {
				doFormat = false;
				showMagnitude = false;
				finalValue = value;
			} else if (value >= 1000) {
				for (i = 0; ; i++) {
					if (value < 10000 && value % 1000 >= 100) {
						finalValue = Math.floor(value / 1000);
						finalValue += ((float) value % 1000) / 1000;
						break;
					}
					value /= 1000;
					if (value < 1000) {
						finalValue = value;
						break;
					}
				}
			}

			if (i > 10) {
				doFormat = false;
				showMagnitude = false;
			} else if (i > 3) {
				finalValue = energy;
				showMagnitude = false;
			}
		} else {
			finalValue = value;
			showMagnitude = false;
		}

		if (doFormat) {
			DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.ENGLISH);
			returnValue += formatter.format(finalValue);
			int idx = returnValue.lastIndexOf(formatter.getDecimalFormatSymbols().getDecimalSeparator());
			if (idx > 0){
				returnValue = returnValue.substring(0, idx + 2);
			}
		}
		else {
			if (i>10){
				returnValue += "∞";
			}
			else {
				returnValue += value;
			}
		}

		if (showMagnitude) {
			returnValue += magnitude[i];
		}

		if (showUnit) {
			returnValue += " " + EnergyAbbreviation;
		}

		return returnValue;
	}
}