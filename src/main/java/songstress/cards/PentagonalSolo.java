package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class PentagonalSolo extends AbstractSongstressCard {

	public static final String ID = "PentagonalSolo";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int WEAK_AND_VULNERABLE_AMT = 1;
	private static final int UPGRADE_WEAK_AND_VULNERABLE = 1;
	private static final int DMG = 5;
	private static final int UPGRADE_DMG = 5;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public PentagonalSolo() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		magicNumber = baseMagicNumber = WEAK_AND_VULNERABLE_AMT;
		baseDamage = DMG;
		exhaust = true;
		isSong = true;
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_WEAK_AND_VULNERABLE);
			upgradeDamage(UPGRADE_DMG);
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new PentagonalSolo();
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		AbstractDungeon.actionManager.addToBottom(
				new DamageAction(monster, new DamageInfo(player, damage, damageTypeForTurn), AttackEffect.BLUNT_LIGHT));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, player,
				new WeakPower(monster, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
		AbstractDungeon.actionManager
				.addToBottom(new ApplyPowerAction(monster, player, new VulnerablePower(monster, magicNumber, false),
						magicNumber, true, AbstractGameAction.AttackEffect.NONE));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new StrengthPower(player, 1), 1,
				true, AbstractGameAction.AttackEffect.NONE));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new DexterityPower(player, 1), 1,
				true, AbstractGameAction.AttackEffect.NONE));
	}

}
