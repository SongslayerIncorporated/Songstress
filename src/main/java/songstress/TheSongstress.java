package songstress;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import songstress.cards.Defend;
import songstress.cards.PowerChord;
import songstress.cards.Strike;
import songstress.patches.PlayerClassEnum;
import songstress.relics.Harpsichord;

public class TheSongstress extends CustomPlayer {

	public static final String[] NAMES = { "The Songstress", "the Songstress" };
	public static final String[] TEXT = { "An angel sent to bring joy through the tune of her songs." };
	public static final int START_HP = 65;
	public static final int CARD_DRAW = 5;
	public static final int MAX_ORBS = 0;
	public static final int ENERGY = 3;
	public static final int START_GOLD = 110;

	public TheSongstress(String name, AbstractPlayer.PlayerClass setClass) {
		super(name, setClass, getOrbTextures(), "song/img/char/orb/vfx.png", (String) null, null);
		initializeClass(null, "song/img/char/shoulder2.png", "song/img/char/shoulder1.png",
				"song/img/char/corpse.png", getLoadout(), 20.0f, -10.0f, 220.0f, 290.0f, new EnergyManager(ENERGY));
		loadAnimation("song/img/char/idle/skeleton.atlas", "song/img/char/idle/skeleton.json", 1.0f);
		AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
		e.setTime(e.getEndTime() * MathUtils.random());
	}

	public static CharSelectInfo getLoadout() {
		return new CharSelectInfo(NAMES[0], TEXT[0], START_HP, START_HP, MAX_ORBS, START_GOLD, CARD_DRAW,
				PlayerClassEnum.TheSongstress, getStartingRelics(), getStartingDeck(), false);
	}

	public static ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		addCard(retVal, Strike.ID);
		addCard(retVal, Strike.ID);
		addCard(retVal, Strike.ID);
		addCard(retVal, Strike.ID);
		addCard(retVal, Defend.ID);
		addCard(retVal, Defend.ID);
		addCard(retVal, Defend.ID);
		addCard(retVal, Defend.ID);
		addCard(retVal, Defend.ID);
		addCard(retVal, PowerChord.ID);
		return retVal;
	}

	private static void addCard(ArrayList<String> retVal, String id) {
		retVal.add(TheSongstressMod.withModID(id));
	}

	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		addRelicAndUnlock(retVal, Harpsichord.ID);
		return retVal;
	}

	private static void addRelicAndUnlock(ArrayList<String> retVal, String id) {
		retVal.add(TheSongstressMod.withModID(id));
		UnlockTracker.markRelicAsSeen(TheSongstressMod.withModID(id));
	}

	private static String[] getOrbTextures() {
		return new String[] { "song/img/char/orb/layer1.png", "song/img/char/orb/layer2.png",
				"song/img/char/orb/layer3.png", "song/img/char/orb/layer4.png", "song/img/char/orb/layer5.png",
				"song/img/char/orb/layer6.png", "song/img/char/orb/layer1d.png", "song/img/char/orb/layer2d.png",
				"song/img/char/orb/layer3d.png", "song/img/char/orb/layer4d.png", "song/img/char/orb/layer5d.png", };
	}
}
