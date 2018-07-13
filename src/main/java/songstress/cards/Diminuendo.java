package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Diminuendo extends AbstractSongstressCard {

	public static final String ID = "Diminuendo";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int WEAK_AMT = 3;
	private static final int UPGRADE_WEAK = 2;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

	public Diminuendo() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		magicNumber = baseMagicNumber = WEAK_AMT;
		exhaust = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_WEAK);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Diminuendo();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, player,
					new WeakPower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
			if (mo.hasPower(StrengthPower.POWER_ID) && mo.getPower(StrengthPower.POWER_ID).amount > 0) {
				AbstractDungeon.actionManager
						.addToBottom(new RemoveSpecificPowerAction(mo, player, StrengthPower.POWER_ID));
			}
		}
	}

}
