package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.Holy;

public class HolyBolt extends AbstractSongstressCard {

	public static final String ID = "HolyBolt";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int ATTACK_DMG = 3;
	private static final int UPGRADE_ATTACK_DMG = 1;
	private static final int HOLY_GAIN = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public HolyBolt() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = HOLY_GAIN;
		this.magicNumber = HOLY_GAIN;
		this.isHoly = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
			upgradeBaseCost(COST_UPGRADED);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new HolyBolt();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(p, p, new Holy(magicNumber), magicNumber));
	}

}
