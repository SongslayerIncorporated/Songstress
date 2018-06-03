package songstress.relics;

import com.badlogic.gdx.graphics.Texture;

import basemod.abstracts.CustomRelic;
import songstress.TheSongstressMod;

public abstract class AbstractSongstressRelic extends CustomRelic {

	public AbstractSongstressRelic(String id, RelicTier tier, LandingSound sfx) {
		super(TheSongstressMod.withModID(id), getImg(id), tier, sfx);

	}

	public static Texture getImg(String id) {
		return new Texture("song/img/relics/" + id + ".png");
	}
}
