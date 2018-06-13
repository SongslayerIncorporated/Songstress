package songstress.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import songstress.TheSongstressMod;

public class AbstractSongstressPower extends AbstractPower {

	public AbstractSongstressPower(String id, String name, AbstractCreature owner) {
		ID = TheSongstressMod.withModID(id);
		this.name = name;
		this.owner = owner;
		img = getImg(id);
	}

	public AbstractSongstressPower(String id, String name, AbstractCreature owner, int amount) {
		this(id, name, owner);
		this.amount = amount;
	}

	private static Texture getImg(String id) {
		return ImageMaster.loadImage("song/img/powers/32/" + id + ".png");
	}

	public static PowerStrings getPowerStrings(String id) {
		return CardCrawlGame.languagePack.getPowerStrings(TheSongstressMod.withModID(id));
	}

	public String getID() {
		return ID;
	}
}
