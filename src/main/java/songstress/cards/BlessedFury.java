package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.BlessedFuryPower;
import songstress.powers.Holy;

public class BlessedFury extends AbstractSongstressCard {

	public static final String ID = "BlessedFury";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int HOLY_COST = 2;
	private static final int UPGRADE_HOLY_COST = -1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;

	public BlessedFury() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = HOLY_COST;
		exhaust = true;
		isHoly = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_HOLY_COST);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new BlessedFury();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (Holy.holyAmount() > magicNumber) {
			Holy.flashOnce();
			Holy.reduceBy(magicNumber);
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BlessedFuryPower(1), 1));
		}
	}

}
