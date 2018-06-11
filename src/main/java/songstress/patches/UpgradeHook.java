package songstress.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import songstress.relics.ForgedDrums;

@SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "upgradeName")
public class UpgradeHook {
	public static void Prefix() {
		ForgedDrums.onUpgrade();
	}
}
