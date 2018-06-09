package songstress.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;

import songstress.utils.CardFilter;

/**
 *
 * An ExhaustAction that has a CardFilter so only specific cards can be
 * exhausted (mostly copied from ExhaustAction)
 */
public class ExhaustFilterAction extends ExhaustAction {

	private CardFilter filter;
	private AbstractPlayer p;
	private int amount;
	private boolean isRandom;
	private boolean anyNumber;
	private boolean canPickZero;

	private ArrayList<AbstractCard> removedCards;

	public ExhaustFilterAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom,
			CardFilter filter) {
		this(target, source, amount, isRandom, false, false, filter);
	}

	public ExhaustFilterAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom,
			boolean anyNumber, boolean canPickZero, CardFilter filter) {
		super(target, source, amount, isRandom, anyNumber, canPickZero);
		this.filter = filter;
		this.amount = amount;
		this.isRandom = isRandom;
		this.anyNumber = anyNumber;
		this.canPickZero = canPickZero;
		p = AbstractDungeon.player;
		removedCards = new ArrayList<>();
	}

	public ExhaustFilterAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom,
			boolean anyNumber, CardFilter filter) {
		this(target, source, amount, isRandom, anyNumber, false, filter);
	}

	// Copied from ArmamentsAction and ExhaustAction
	@Override
	public void update() {
		if (duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST)
		{
			filterCards();
			if (p.hand.size() == 0 || removedCards.size() == p.hand.size()) {
				isDone = true;
				returnCards();
				return;
			}

			if ((!anyNumber) &&
					(p.hand.size() <= amount)) {
				amount = p.hand.size();
				numExhausted = amount;
				int tmp = p.hand.size();
				for (int i = 0; i < tmp; i++) {
					AbstractCard c = p.hand.getTopCard();
					p.hand.moveToExhaustPile(c);
				}
				CardCrawlGame.dungeon.checkForPactAchievement();
				returnCards();
				return;
			}


			if (isRandom) {
				for (int i = 0; i < amount; i++) {
					p.hand.moveToExhaustPile(p.hand.getRandomCard(true));
				}
				CardCrawlGame.dungeon.checkForPactAchievement();
			} else {
				numExhausted = amount;
				AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, anyNumber, canPickZero);
				tickDuration();
				returnCards();
				return;
			}
		}
		HandCardSelectScreen handCardSelectScreen = AbstractDungeon.handCardSelectScreen;


		if (!handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c : handCardSelectScreen.selectedCards.group) {
				p.hand.moveToExhaustPile(c);
			}
			CardCrawlGame.dungeon.checkForPactAchievement();
			handCardSelectScreen.wereCardsRetrieved = true;
			returnCards();
		}
		tickDuration();
	}

	private void filterCards() {
		for (AbstractCard c : p.hand.group) {
			// remove the card if the filter doesn't accept it
			if (!filter.accept(c)) {
				removedCards.add(c);
			}
		}
		p.hand.group.removeAll(removedCards);
	}

	private void returnCards() {
		for (AbstractCard c : removedCards) {
			p.hand.addToTop(c);
		}
		p.hand.refreshHandLayout();
		removedCards.clear();
	}

}
