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

public class Symphony extends AbstractSongstressCard {

	public static final String ID = "Symphony";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int ATTACK_DMG = 3;
	private static final int UPGRADE_ATTACK_DMG = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Symphony() {
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
		return new Symphony();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < getSongCount(); i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn,
					AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Muted(p, 2), 2));
		rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void onMoveToDiscard() {
		rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		int count = getSongCount();
		rawDescription = DESCRIPTION;
		rawDescription = rawDescription + EXTENDED_DESCRIPTION[0] + count;
		rawDescription = count == 1 ? rawDescription + EXTENDED_DESCRIPTION[1]
				: rawDescription + EXTENDED_DESCRIPTION[2];
		initializeDescription();
	}

	private static int getSongCount() {
		int count = 0;
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c instanceof AbstractSongstressCard && ((AbstractSongstressCard) c).isSong) {
				count++;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			if (c instanceof AbstractSongstressCard && ((AbstractSongstressCard) c).isSong) {
				count++;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if (c instanceof AbstractSongstressCard && ((AbstractSongstressCard) c).isSong) {
				count++;
			}
		}
		return count;
	}

}
