package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DeafenedStab extends AbstractSongstressCard {

	public static final String ID = "DeafenedStab";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int ATTACK_DMG = 40;
	private static final int UPGRADE_ATTACK_DMG = 10;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public DeafenedStab() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseDamage = ATTACK_DMG;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
			upgradeBaseCost(UPGRADED_COST);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new DeafenedStab();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		// Use Logic is in AbstractSongstressCard
		AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
				new DamageInfo(player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}

}
