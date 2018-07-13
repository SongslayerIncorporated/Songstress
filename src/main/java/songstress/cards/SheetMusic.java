package songstress.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SheetMusic extends AbstractSongstressCard {

	public static final String ID = "SheetMusic";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int DRAW_AMT = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public SheetMusic() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		magicNumber = baseMagicNumber = DRAW_AMT;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new SheetMusic();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int drawAmount = magicNumber + getSongsPlayed();
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, drawAmount));
		rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void onMoveToDiscard() {
		rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		int count = getSongsPlayed();
		rawDescription = DESCRIPTION;
		rawDescription = rawDescription + EXTENDED_DESCRIPTION[0] + count;
		rawDescription = count == 1 ? rawDescription + EXTENDED_DESCRIPTION[1]
				: rawDescription + EXTENDED_DESCRIPTION[2];
		initializeDescription();
	}

	private static int getSongsPlayed() {
		int songs = 0;
		for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
			if (c instanceof AbstractSongstressCard && ((AbstractSongstressCard) c).isSong) {
				songs++;
			}
		}
		return songs;
	}

}
