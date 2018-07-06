package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.InfiniteMelodyPower;

public class InfiniteMelody extends AbstractSongstressCard {

	public static final String ID = "InfiniteMelody";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	private static final int COST_UPGRADED = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int DRAW_AMT = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;

	public InfiniteMelody() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		magicNumber = baseMagicNumber = DRAW_AMT;
		isSong = true;
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
		return new InfiniteMelody();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(p, p, new InfiniteMelodyPower(p, magicNumber), magicNumber));
	}

}
