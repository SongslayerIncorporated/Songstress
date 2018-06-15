package songstress.powers;

import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.GeneticAlgorithm;
import com.megacrit.cardcrawl.cards.red.Rampage;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlessedFuryPower extends AbstractSongstressPower {
	public static final String POWER_ID = "BlessedFury";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public BlessedFuryPower(int amount) {
		super(POWER_ID, NAME, AbstractDungeon.player, amount);
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
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if ((!card.purgeOnUse) && (amount > 0)) {
			flash();
			AbstractMonster m = null;

			if (action.target != null) {
				m = (AbstractMonster) action.target;
			}

			AbstractCard tmp = card.makeStatEquivalentCopy();
			AbstractDungeon.player.limbo.addToBottom(tmp);
			tmp.current_x = card.current_x;
			tmp.current_y = card.current_y;
			tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
			tmp.target_y = (Settings.HEIGHT / 2.0F);
			tmp.freeToPlayOnce = true;

			if (m != null) {
				tmp.calculateCardDamage(m);
			}

			tmp.purgeOnUse = true;
			AbstractDungeon.actionManager.cardQueue
					.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m, card.energyOnUse));
			if (tmp.cardID.equals(Rampage.ID)) {
				AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(card, card.magicNumber));
			}
			if (tmp.cardID.equals(GeneticAlgorithm.ID)) {
				AbstractDungeon.actionManager
						.addToBottom(new IncreaseMiscAction(tmp.cardID, tmp.misc + tmp.magicNumber, tmp.magicNumber));
			}
		}
	}

	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if (!isPlayer) {
			return;
		}
		if (amount == 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, ID, 1));
		}
	}

}
