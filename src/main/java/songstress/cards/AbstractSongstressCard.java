package songstress.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import basemod.abstracts.CustomCard;
import songstress.TheSongstressMod;
import songstress.patches.ColorEnum;

public abstract class AbstractSongstressCard extends CustomCard {

	public AbstractSongstressCard(String id, String name, int cost, String description, CardType type, CardRarity rarity,
			CardTarget target) {
		super(TheSongstressMod.withModID(id), name, getImgString(id), cost, description, type, ColorEnum.Cloud, rarity,
				target);
	}

	public static String getImgString(String id) {
		return "song/img/cards/" + id + ".png";
	}

	public static CardStrings getCardStrings(String id) {
		return CardCrawlGame.languagePack.getCardStrings(TheSongstressMod.withModID(id));
	}

}
