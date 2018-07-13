package songstress.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Muted extends AbstractSongstressPower {
	public static final String POWER_ID = "Muted";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public static final String MUTED = "I cannot play Songs while Muted.";
	public static final String NOT_MUTED = "I can only play this card while Muted.";

	public Muted(AbstractCreature target, int amount) {
		super(POWER_ID, NAME, target, amount);
		updateDescription();
		type = AbstractPower.PowerType.DEBUFF;
		isTurnBased = true;
		// Logic is found in AbstractSongstressCard
	}

	@Override
	public void updateDescription() {
		if (amount == 1) {
			description = DESCRIPTIONS[0];
		} else {
			description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
		}
	}

	@Override
	public void atStartOfTurn() {
		if (amount == 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, 1));
		}
	}

}
