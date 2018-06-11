package songstress.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Metronome extends AbstractSongstressRelic {

	public static final String ID = "Metronome";
	public static final RelicTier TIER = RelicTier.RARE;
	public static final LandingSound SOUND = LandingSound.MAGICAL;

	public Metronome() {
		super(ID, TIER, SOUND);
	}

	@Override
	public void atBattleStart() {
		// TODO Do something here
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public AbstractRelic makeCopy() {
		return new Metronome();
	}

}
