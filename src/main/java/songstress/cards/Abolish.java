package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import songstress.powers.Holy;

public class Abolish extends AbstractSongstressCard {

	public static final String ID = "Abolish";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 3;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int MULTI_ATTACK_DMG = 15;
	private static final int ATTACK_DMG = 15;
	private static final int UPGRADE_ATTACK_DMG = 10;
	private static final int LOSE_STRENGTH_AND_DEXTERITY = 3;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Abolish() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseDamage = MULTI_ATTACK_DMG;
		isMultiDamage = true;
		// use block as means to have dynamic variable
		baseBlock = ATTACK_DMG;
		baseMagicNumber = magicNumber = LOSE_STRENGTH_AND_DEXTERITY;
		exhaust = true;
		isHoly = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_ATTACK_DMG);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Abolish();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		Holy.flashOnce();
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, block, damageTypeForTurn),
				AbstractGameAction.AttackEffect.SMASH));
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn,
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		Holy.reduceBy(Holy.holyAmount());
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -magicNumber), -magicNumber));
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -magicNumber), -magicNumber));
	}

}
