package songstress.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Bongos extends AbstractSongstressRelic {

	public static final String ID = "Bongos";
	public static final RelicTier TIER = RelicTier.COMMON;
	private static final int DRAW_AMT = 2;
	public static final LandingSound SOUND = LandingSound.MAGICAL;

	public Bongos() {
		super(ID, TIER, SOUND);
	}

	@Override
	public void onEquip() {
		counter = 0;
	}

	@Override
	public void atTurnStart() {
		if (counter == -1) {
			counter += 2;
		} else {
			counter += 1;
		}

		if (counter == 3) {
			counter = 0;
			flash();
			AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, DRAW_AMT));
		}
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + DRAW_AMT + DESCRIPTIONS[1];
	}

	@Override
	public AbstractRelic makeCopy() {
		return new Bongos();
	}

}
