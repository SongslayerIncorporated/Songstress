package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class PowerChord extends AbstractSongstressCard {

	public static final String ID = "PowerChord";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int ATTACK_DMG = 8;
	private static final int UPGRADE_ATTACK_DMG = 3;
	private static final int STRENGTH_GAIN = 1;
	private static final int UPGRADE_STRENGTH_GAIN = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public PowerChord() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseDamage = ATTACK_DMG;
		baseMagicNumber = STRENGTH_GAIN;
		magicNumber = baseMagicNumber;
		isSong = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
			upgradeMagicNumber(UPGRADE_STRENGTH_GAIN);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new PowerChord();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
				new DamageInfo(player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(player, player, new StrengthPower(player, magicNumber), magicNumber));
	}

}
