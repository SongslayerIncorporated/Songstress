package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.PrayerPower;

public class Prayer extends AbstractSongstressCard {

	public static final String ID = "Prayer";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 3;
	private static final int COST_UPGRADED = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int HOLY_GAIN = 1;
	private static final CardType TYPE = CardType.POWER;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Prayer() {
		super(ID, NAME, COST, getDescription(), TYPE, RARITY, TARGET);
		this.baseMagicNumber = this.magicNumber = HOLY_GAIN;
		this.isHoly = true;
	}

	// Hack because for some reason Prayer description isn't working
	private static String getDescription() {
		if (DESCRIPTION == null) {
			return "At the start of each turn, gain !M! Holy.";
		} else {
			return DESCRIPTION;
		}
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Prayer();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
		.addToBottom(new ApplyPowerAction(p, p, new PrayerPower(p, magicNumber), magicNumber));
	}

}