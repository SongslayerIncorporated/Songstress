package songstress.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class PurgeAction extends AbstractGameAction {

	private int strengthPerCard;

	public PurgeAction(int strength) {
		duration = Settings.ACTION_DUR_XFAST;
		strengthPerCard = strength;
	}

	@Override
	public void update() {
		if (!isDone) {
			isDone = true;
			int cardsExhausted = 0;
			ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
			for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
				if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
					cardsExhausted++;
					cardsToExhaust.add(c);
				}
			}
			for (AbstractCard c : cardsToExhaust) {
				AbstractDungeon.player.drawPile.moveToExhaustPile(c);
			}
			cardsToExhaust.clear();
			for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
				if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
					cardsExhausted++;
					cardsToExhaust.add(c);
				}
			}
			for (AbstractCard c : cardsToExhaust) {
				AbstractDungeon.player.discardPile.moveToExhaustPile(c);
			}
			cardsToExhaust.clear();
			for (AbstractCard c : AbstractDungeon.player.hand.group) {
				if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
					cardsExhausted++;
					cardsToExhaust.add(c);
				}
			}
			for (AbstractCard c : cardsToExhaust) {
				AbstractDungeon.player.hand.moveToExhaustPile(c);
			}
			if (cardsExhausted > 0) {
				AbstractDungeon.actionManager
						.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
								new StrengthPower(AbstractDungeon.player, cardsExhausted * strengthPerCard),
								strengthPerCard * cardsExhausted));
			}
		}
	}

}
