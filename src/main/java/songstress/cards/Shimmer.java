package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import songstress.powers.Holy;

public class Shimmer extends AbstractSongstressCard {

	public static final String ID = "Shimmer";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	private static final int COST_UPGRADED = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int BLOCK_AMT = 8;
	private static final int UPGRADE_BLOCK_AMT = 2;
	private static final int HOLY_COST = 1;
	private static final int WEAK_AMT = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Shimmer() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		this.baseBlock = BLOCK_AMT;
		this.baseMagicNumber = this.magicNumber = WEAK_AMT;
		this.isHoly = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK_AMT);
			upgradeBaseCost(COST_UPGRADED);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Shimmer();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
		.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
		if (Holy.holyAmount() >= HOLY_COST) {
			Holy.flashOnce();
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
			Holy.reduceBy(HOLY_COST);
		}
	}


}
