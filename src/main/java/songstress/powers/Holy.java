package songstress.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import songstress.TheSongstressMod;

public class Holy extends AbstractSongstressPower {
	public static final String POWER_ID = "Holy";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	// public static final String NAME = "Holy";
	// public static final String[] DESCRIPTIONS = { "Consumed by cards to grant
	// additional effects." };

	public Holy(int amount) {
		super(POWER_ID, NAME, AbstractDungeon.player, amount);
		updateDescription();
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0];
	}

	// buggy i think
	@Override
	public void reducePower(int reduceAmount) {
		stackPower(reduceAmount);
	}

	@Override
	public void stackPower(int stackAmount) {
		fontScale = 8.0f;
		amount += stackAmount;
		if (amount >= 999) {
			amount = 999;
		}
		if (amount <= 0) {
			AbstractDungeon.actionManager.addToTop(
					new RemoveSpecificPowerAction(owner, owner, TheSongstressMod.withModID(POWER_ID)));
		}
	}

	public static int holyAmount() {
		Holy power = (Holy) AbstractDungeon.player.getPower(TheSongstressMod.withModID(POWER_ID));
		return power != null ? power.amount : 0;
	}

	public static void reduceBy(int amount) {
		AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player,
				TheSongstressMod.withModID(POWER_ID), amount));
	}

	public static void flashOnce() {
		Holy power = (Holy) AbstractDungeon.player.getPower(TheSongstressMod.withModID(POWER_ID));
		if (power != null) {
			power.flash();
		}
	}

}
