package songstress.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import songstress.powers.NextTurnHoly;

public class Guidance extends AbstractSongstressCard {

	public static final String ID = "Guidance";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = -1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int BASE_DRAW = 2;
	private static final int UPGRADE_DRAW = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Guidance() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		magicNumber = baseMagicNumber = BASE_DRAW;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_DRAW);
			rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Guidance();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		AbstractDungeon.actionManager.addToBottom(
				new ApplyPowerAction(player, player, new DrawCardNextTurnPower(player, magicNumber), magicNumber));
		if (energyOnUse < EnergyPanel.totalCount) {
			energyOnUse = EnergyPanel.totalCount;
		}

		if (upgraded) {
			energyOnUse++;
		}
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(player, player, new NextTurnHoly(energyOnUse), energyOnUse));
		if (!freeToPlayOnce) {
			player.energy.use(EnergyPanel.totalCount);
		}
	}

}
