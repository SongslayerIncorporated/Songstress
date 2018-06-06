package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.Holy;

public class FaithsShield extends AbstractSongstressCard {

	public static final String ID = "FaithsShield";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int BLOCK_AMT = 7;
	private static final int UPGRADE_BLOCK_AMT = 3;
	private static final int HOLY_GAIN = 1;
	private static final int UPGRADE_HOLY_GAIN = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public FaithsShield() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		this.baseBlock = BLOCK_AMT;
		this.baseMagicNumber = this.magicNumber = HOLY_GAIN;
		this.isHoly = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK_AMT);
			upgradeMagicNumber(UPGRADE_HOLY_GAIN);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new FaithsShield();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Holy(magicNumber), magicNumber));
	}

}
