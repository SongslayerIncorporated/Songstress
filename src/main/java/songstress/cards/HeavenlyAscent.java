package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.Risen;

public class HeavenlyAscent extends AbstractSongstressCard {

	public static final String ID = "HeavenlyAscent";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int BLOCK_AMT = 15;
	private static final int UPGRADE_BLOCK_AMT = 5;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;

	public HeavenlyAscent() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseBlock = BLOCK_AMT;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK_AMT);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new HeavenlyAscent();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Risen(1), 1));
	}

}
