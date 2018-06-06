package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.actions.ExhaustFilterAction;
import songstress.utils.CardFilter;

public class Expunge extends AbstractSongstressCard {
	public static final String ID = "Expunge";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int EXHAUST_AMT = 1;
	private static final int UPGRADE_EXHAUST_AMT = 1;
	private static final int TAKE_DAMAGE = 8;
	private static final int UPGRADE_TAKE_DAMAGE = 8;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	private static final CardFilter filter = Expunge::exhaustFilter;

	public Expunge() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		this.baseMagicNumber = this.magicNumber = EXHAUST_AMT;
		// we use damage here because we cant have 2 dynamic values
		this.baseDamage = TAKE_DAMAGE;
		this.exhaust = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
			upgradeMagicNumber(UPGRADE_EXHAUST_AMT);
			upgradeDamage(-UPGRADE_TAKE_DAMAGE);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Expunge();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
		.addToBottom(new DamageAction(p, new DamageInfo(p, damage, DamageType.THORNS), AttackEffect.FIRE));
		AbstractDungeon.actionManager.addToBottom(new ExhaustFilterAction(p, p, magicNumber, false, true, filter));
	}

	public static boolean exhaustFilter(AbstractCard c) {
		return c != null && (c.type == CardType.STATUS || c.type == CardType.CURSE);
	}

}
