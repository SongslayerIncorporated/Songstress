package songstress.relics;

import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import songstress.CardHelper;

public class Metronome extends AbstractSongstressRelic {

	public static final String ID = "Metronome";
	public static final RelicTier TIER = RelicTier.RARE;
	public static final LandingSound SOUND = LandingSound.MAGICAL;

	public Metronome() {
		super(ID, TIER, SOUND);
	}

	// Copied from Havoc and Necronomicon
	@Override
	public void atBattleStart() {
		AbstractCard card = CardHelper.getNonCloudCard();
		UnlockTracker.markCardAsSeen(card.cardID);
		card = card.makeStatEquivalentCopy();
		card.freeToPlayOnce = true;
		card.applyPowers();
		card.purgeOnUse = true;
		AbstractDungeon.actionManager.addToTop(new QueueCardAction(card, AbstractDungeon.getRandomMonster()));
		AbstractDungeon.actionManager.addToTop(new UnlimboAction(card));
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
