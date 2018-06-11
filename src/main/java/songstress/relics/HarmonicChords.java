package songstress.relics;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import songstress.TheSongstressMod;

// Copied from Necronomicon and Mummified Hand
public class HarmonicChords extends AbstractSongstressRelic {

	public static final String ID = "HarmonicChords";
	private static final int COST_THRESHOLD = 2;
	public static final RelicTier TIER = RelicTier.BOSS;
	public static final LandingSound SOUND = LandingSound.MAGICAL;

	public HarmonicChords() {
		super(ID, TIER, SOUND);
	}

	@Override
	public void onEquip() {
		if (AbstractDungeon.player != null
				&& AbstractDungeon.player.hasRelic(TheSongstressMod.withModID(AngelicChords.ID))) {
			AbstractDungeon.player.loseRelic(TheSongstressMod.withModID(AngelicChords.ID));
		}
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + COST_THRESHOLD + DESCRIPTIONS[1];
	}

	@Override
	public void onUseCard(AbstractCard targetCard, UseCardAction action) {
		if ((targetCard.type == CardType.ATTACK)
				&& ((targetCard.costForTurn >= 2) || ((targetCard.cost == -1) && (targetCard.energyOnUse >= 2)))) {
			flash();
			AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

			ArrayList<AbstractCard> groupCopy = new ArrayList<>();
			for (AbstractCard c : AbstractDungeon.player.hand.group) {
				if (c.cost > 0 && c.costForTurn > 0) {
					groupCopy.add(c);
				}
			}
			for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
				if (i.card == null) {
					continue;
				}
				groupCopy.remove(i.card);
			}
			AbstractCard c = null;
			if (!groupCopy.isEmpty()) {
				c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
			}
			if (c != null) {
				c.setCostForTurn(0);
			}
			pulse = false;
		}
	}

	@Override
	public boolean checkTrigger() {
		return true;
	}

	@Override
	public AbstractRelic makeCopy() {
		return new HarmonicChords();
	}

}
