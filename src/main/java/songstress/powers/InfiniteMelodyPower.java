package songstress.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import songstress.cards.AbstractSongstressCard;

public class InfiniteMelodyPower extends AbstractSongstressPower {

	public static final String POWER_ID = "InfiniteMelody";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public InfiniteMelodyPower(AbstractCreature owner, int amount) {
		super(POWER_ID, NAME, owner, amount);
		updateDescription();
	}

	@Override
	public void updateDescription() {
		if (amount == 1) {
			description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
		} else {
			description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
		}
	}

	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card instanceof AbstractSongstressCard) {
			AbstractSongstressCard c = (AbstractSongstressCard) card;
			if (c.isSong) {
				AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, amount));
			}
		}
	}

}
