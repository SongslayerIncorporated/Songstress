package songstress.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Serenade extends AbstractSongstressCard {

	public static final String ID = "Serenade";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int BASE_BLOCK = 5;
	private static final int UPGRADE_BLOCK = 3;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Serenade() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseBlock = BASE_BLOCK;
		isSong = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Serenade();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, block));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, block));
	}

}
