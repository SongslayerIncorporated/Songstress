package songstress.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class NextTurnHoly extends AbstractSongstressPower {
	public static final String POWER_ID = "NextTurnHoly";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public NextTurnHoly(int amount) {
		super(POWER_ID, NAME, AbstractDungeon.player, amount);
		updateDescription();
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}

	@Override
	public void atStartOfTurn() {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new Holy(amount), amount));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
	}

}
