package songstress.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import basemod.abstracts.CustomCard;
import songstress.TheSongstressMod;
import songstress.patches.ColorEnum;
import songstress.powers.Holy;
import songstress.powers.Muted;

public abstract class AbstractSongstressCard extends CustomCard {

	public boolean isHoly = false;
	public int holyCost = 0;
	public boolean isCure = false;
	public boolean isSong = false;

	public AbstractSongstressCard(String id, String name, int cost, String description, CardType type,
			CardRarity rarity, CardTarget target) {
		super(TheSongstressMod.withModID(id), name, getImgString(id), cost, description, type, ColorEnum.Cloud, rarity,
				target);
	}

	public static String getImgString(String id) {
		return "song/img/cards/" + id + ".png";
	}

	public static CardStrings getCardStrings(String id) {
		return CardCrawlGame.languagePack.getCardStrings(TheSongstressMod.withModID(id));
	}

	public String getID() {
		return cardID;
	}

	@Override
	public boolean hasEnoughEnergy() {
		if (Holy.holyAmount() < holyCost) {
			cantUseMessage = Holy.NOT_ENOUGH_HOLY;
			return false;
		}
		boolean isMuted = AbstractDungeon.player.hasPower(TheSongstressMod.withModID(Muted.POWER_ID));
		if (isSong && isMuted) {
			cantUseMessage = Muted.MUTED;
			return false;
		}
		if (this instanceof DeafenedStab && !isMuted) {
			cantUseMessage = Muted.NOT_MUTED;
			return false;
		}
		if (!super.hasEnoughEnergy()) {
			return false;
		}
		return true;
	}

}
