package songstress.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModTags;
import basemod.helpers.CardTags;

public class Defend extends AbstractSongstressCard {

	public static final String ID = "Defend";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int BASE_BLOCK = 5;
	private static final int UPGRADE_BLOCK = 3;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Defend() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseBlock = BASE_BLOCK;

		CardTags.addTags(this, BaseModTags.BASIC_DEFEND);
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
		return new Defend();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, block));
	}

	@Override
	public boolean isDefend() {
		return true;
	}
}
