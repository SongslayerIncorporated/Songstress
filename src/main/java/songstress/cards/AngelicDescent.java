package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import songstress.powers.Holy;

public class AngelicDescent extends AbstractSongstressCard {

	public static final String ID = "AngelicDescent";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = -1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

	public AngelicDescent() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		isMultiDamage = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new AngelicDescent();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		damage = Holy.holyAmount();
		multiDamage = new int[] { damage, damage, damage, damage, damage, damage, damage, damage, damage };
		if (energyOnUse < EnergyPanel.totalCount) {
			energyOnUse = EnergyPanel.totalCount;
		}

		if (upgraded) {
			energyOnUse++;
		}
		for (int i = 0; i < energyOnUse; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(player, multiDamage, damageTypeForTurn,
					AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}
		if (!freeToPlayOnce) {
			player.energy.use(EnergyPanel.totalCount);
		}
	}

}
