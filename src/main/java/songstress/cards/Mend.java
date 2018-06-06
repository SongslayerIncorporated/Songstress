package songstress.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.actions.CureAction;

public class Mend extends AbstractSongstressCard {

	public static final String ID = "Mend";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 0;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int DRAW_AMT = 1;
	private static final int UPGRADE_DRAW_AMT = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Mend() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DRAW_AMT;
		this.exhaust = true;
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
		return new Mend();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new CureAction());
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
	}

}
