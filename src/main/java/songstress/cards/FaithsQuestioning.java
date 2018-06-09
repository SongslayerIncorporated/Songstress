package songstress.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.actions.FaithsQuestioningAction;

public class FaithsQuestioning extends AbstractSongstressCard {

	public static final String ID = "FaithsQuestioning";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 3;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int DRAW_AMT = 3;
	private static final int UPGRADE_DRAW_AMT = 2;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public FaithsQuestioning() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		this.baseMagicNumber = this.magicNumber = DRAW_AMT;
		this.exhaust = true;
		this.isHoly = true;
		this.isCure = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_DRAW_AMT);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new FaithsQuestioning();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
		AbstractDungeon.actionManager.addToBottom(new FaithsQuestioningAction());
	}

}
