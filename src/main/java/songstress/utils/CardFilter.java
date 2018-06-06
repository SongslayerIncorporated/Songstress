package songstress.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;

@FunctionalInterface
public interface CardFilter {

	public boolean accept(AbstractCard c);
}
