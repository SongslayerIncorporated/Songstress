package songstress.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.RestRoom;

import songstress.TheSongstressMod;

public class ForgedDrums extends AbstractSongstressRelic {

	public static final String ID = "ForgedDrums";
	public static final int HEAL_AMT = 8;
	public static final RelicTier TIER = RelicTier.RARE;
	public static final LandingSound SOUND = LandingSound.MAGICAL;
	private static boolean canHeal;

	public ForgedDrums() {
		super(ID, TIER, SOUND);
		canHeal = false;
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + HEAL_AMT + DESCRIPTIONS[1];
	}

	@Override
	public AbstractRelic makeCopy() {
		return new ForgedDrums();
	}

	@Override
	public void onSmith() {
		canHeal = true;
	}

	public static void onUpgrade() {
		try {
			if (AbstractDungeon.getCurrRoom() instanceof RestRoom && AbstractDungeon.screen == CurrentScreen.NONE
					&& canHeal) {
				canHeal = false;
				if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(TheSongstressMod.withModID(ID))) {
					AbstractDungeon.player.getRelic(TheSongstressMod.withModID(ID)).flash();
					AbstractDungeon.player.heal(HEAL_AMT, true);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
