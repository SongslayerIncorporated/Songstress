package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.Holy;

public class Exorcism extends AbstractSongstressCard {

	public static final String ID = "Exorcism";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int VULNERABLE_AND_WEAK_AMT = 3;
	private static final int HOLY_GAIN = 2;
	private static final int UPGRADE_HOLY_GAIN = 2;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

	public Exorcism() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		this.baseMagicNumber = this.magicNumber = HOLY_GAIN;
		this.isHoly = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_HOLY_GAIN);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Exorcism();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters)
		{
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p,
					new com.megacrit.cardcrawl.powers.WeakPower(monster, VULNERABLE_AND_WEAK_AMT, false),
					VULNERABLE_AND_WEAK_AMT, true,
					AbstractGameAction.AttackEffect.NONE));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p,
					new com.megacrit.cardcrawl.powers.VulnerablePower(monster, VULNERABLE_AND_WEAK_AMT, false),
					VULNERABLE_AND_WEAK_AMT, true,
					AbstractGameAction.AttackEffect.NONE));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Holy(magicNumber), magicNumber));
	}
}
