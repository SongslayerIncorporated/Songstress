package songstress.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import songstress.TheSongstressMod;

public class Holy extends AbstractSongstressPower {
	public static final String POWER_ID = "Holy";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public Holy(int amount) {
		super(POWER_ID, NAME, AbstractDungeon.player, amount);
		updateDescription();
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0];
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
