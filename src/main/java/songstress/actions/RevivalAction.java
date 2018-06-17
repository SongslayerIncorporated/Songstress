package songstress.actions;

import java.util.ArrayList;
import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RevivalAction extends AbstractGameAction {
	private AbstractPlayer p;
	private final boolean upgrade;
	public static final String[] TEXT = { "Return up to ", " cards" };
	private ArrayList<AbstractCard> exhumes = new ArrayList<>();

	public RevivalAction(boolean upgrade, int howMany) {
		this.upgrade = upgrade;
		p = AbstractDungeon.player;
		this.setValues(p, AbstractDungeon.player, howMany);
		if (amount > 11 - p.hand.size()) {
			amount = 11 - p.hand.size();
		}
		actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		duration = Settings.ACTION_DUR_FAST;
	}

	@Override
	public void update() {
		if (duration == Settings.ACTION_DUR_FAST) {
			if (AbstractDungeon.player.hand.size() == 10) {
				AbstractDungeon.player.createHandIsFullDialog();
				isDone = true;
				return;
			}
			if (p.exhaustPile.isEmpty() || amount <= 0) {
				isDone = true;
				return;
			}
			if (p.exhaustPile.size() == 1) {
				if (p.exhaustPile.group.get(0).cardID.equals("Exhume")) {
					isDone = true;
					return;
				}
				AbstractCard c = p.exhaustPile.getTopCard();
				c.unfadeOut();
				p.hand.addToHand(c);
				if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
					c.setCostForTurn(-9);
				}
				p.exhaustPile.removeCard(c);
				if (upgrade && c.canUpgrade()) {
					c.upgrade();
				}
				c.unhover();
				c.fadingOut = false;
				isDone = true;
				return;
			}
			for (AbstractCard c : p.exhaustPile.group) {
				c.stopGlowing();
				c.unhover();
				c.unfadeOut();
			}
			Iterator<AbstractCard> c = p.exhaustPile.group.iterator();
			while (c.hasNext()) {
				AbstractCard derp = c.next();
				if (!derp.cardID.equals("Exhume")) {
					continue;
				}
				c.remove();
				exhumes.add(derp);
			}
			if (p.exhaustPile.isEmpty()) {
				p.exhaustPile.group.addAll(exhumes);
				exhumes.clear();
				isDone = true;
				return;
			}
			AbstractDungeon.gridSelectScreen.open(p.exhaustPile, amount, TEXT[0] + amount + TEXT[1], false);
			tickDuration();
			return;
		}
		if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
				p.hand.addToHand(c);
				if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
					c.setCostForTurn(-9);
				}
				p.exhaustPile.removeCard(c);
				if (upgrade && c.canUpgrade()) {
					c.upgrade();
				}
				c.unhover();
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			p.hand.refreshHandLayout();
			p.exhaustPile.group.addAll(exhumes);
			exhumes.clear();
			for (AbstractCard c : p.exhaustPile.group) {
				c.unhover();
				c.target_x = CardGroup.DISCARD_PILE_X;
				c.target_y = 0.0f;
			}
		}
		tickDuration();
	}
}
