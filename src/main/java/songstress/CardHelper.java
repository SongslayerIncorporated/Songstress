package songstress;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import songstress.patches.ColorEnum;

public class CardHelper {

	public static AbstractCard getNonCloudCard() {
		ArrayList<AbstractCard> cards = new ArrayList<>();
		for (AbstractCard c : CardLibrary.cards.values()) {
			if (c.color != CardColor.CURSE && c.color != CardColor.COLORLESS && c.color != ColorEnum.Cloud) {
				cards.add(c);
			}
		}
		if (cards.isEmpty()) {
			return new Strike_Red();
		} else {
			return cards.get(AbstractDungeon.cardRandomRng.random(cards.size() - 1));
		}
	}
}
