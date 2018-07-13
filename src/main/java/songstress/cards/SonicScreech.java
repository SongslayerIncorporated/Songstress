package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.powers.Muted;

public class SonicScreech extends AbstractSongstressCard {

	public static final String ID = "SonicScreech";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int ATTACK_DMG = 16;
	private static final int UPGRADE_ATTACK_DMG = 5;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

	public SonicScreech() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseDamage = ATTACK_DMG;
		isMultiDamage = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new SonicScreech();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn,
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Muted(p, 1), 1));
	}

}
