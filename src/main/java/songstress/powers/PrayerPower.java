package songstress.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PrayerPower extends AbstractSongstressPower {

	public static final String POWER_ID = "Prayer";
	// private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	// public static final String NAME = powerStrings.NAME;
	// public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	// TODO for some reason Prayer doesnt work in card and power strings
	// Figure out why by iterating over the PowerStrings or debugging
	public static final String NAME = "Prayer";
	public static final String[] DESCRIPTIONS = { "At the start of each turn, gain #b", " #yHoly." };

	public PrayerPower(AbstractCreature owner, int amount) {
		super(POWER_ID, NAME, owner, amount);
		updateDescription();
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}

	@Override
	public void atStartOfTurnPostDraw() {
		flash();
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new Holy(amount), amount));
	}
}
