package songstress.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import songstress.TheSongstressMod;
import songstress.cards.AngelicDescent;

public class Risen extends AbstractSongstressPower {
	public static final String POWER_ID = "Risen";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public Risen(int amount) {
		super(POWER_ID, NAME, AbstractDungeon.player, amount);
		updateDescription();
		img = null;
		loadRegion("flight");
	}

	@Override
	public void updateDescription() {
		if (amount == 1) {
			description = DESCRIPTIONS[0];
		} else {
			description = DESCRIPTIONS[1] + DESCRIPTIONS[2];
		}
	}

	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		// Actual code to do stuff is in AngelicDescent
		if (card.cardID.equals(TheSongstressMod.withModID(AngelicDescent.ID))) {
			flash();
		}
	}

}
