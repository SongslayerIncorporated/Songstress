package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.actions.ExpungeAction;

public class Expunge extends AbstractSongstressCard {
	public static final String ID = "Expunge";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	private static final int COST_UPGRADED = 0;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int EXHAUST_AMT = 2;
	private static final int TAKE_DAMAGE = 3;
	private static final int UPGRADE_TAKE_DAMAGE = -1;
	private static final int HOLY_GAIN = 1;
	private static final int UPGRADE_HOLY_GAIN = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Expunge() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseMagicNumber = magicNumber = HOLY_GAIN;
		// we use damage here because we cant have 2 dynamic values
		baseDamage = TAKE_DAMAGE;
		exhaust = true;
		isHoly = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
			upgradeMagicNumber(UPGRADE_HOLY_GAIN);
			upgradeDamage(UPGRADE_TAKE_DAMAGE);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Expunge();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int hand = AbstractDungeon.player.hand.size() - 1; // This card was played
		AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, EXHAUST_AMT, false, true, true));
		AbstractDungeon.actionManager.addToBottom(new ExpungeAction(hand, magicNumber, damage));
	}

}
