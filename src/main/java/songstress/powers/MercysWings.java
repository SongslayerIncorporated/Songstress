package songstress.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import songstress.TheSongstressMod;

public class MercysWings extends AbstractPower {

	public static final String POWER_ID = "MercysWings";
	private static final PowerStrings powerStrings = AbstractSongstressPower.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public MercysWings(int amount) {
		ID = TheSongstressMod.withModID(POWER_ID);
		name = NAME;
		owner = AbstractDungeon.player;
		this.amount = amount;
		loadRegion("flight");
		isTurnBased = true;
		updateDescription();
	}

	@Override
	public void updateDescription() {
		if (amount == 1) {
			description = DESCRIPTIONS[0];
		} else {
			description = (DESCRIPTIONS[1] + amount + DESCRIPTIONS[2]);
		}
	}

	@Override
	public float atDamageFinalReceive(float damage, DamageInfo.DamageType dmgType) {
		return damage / 2;
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
