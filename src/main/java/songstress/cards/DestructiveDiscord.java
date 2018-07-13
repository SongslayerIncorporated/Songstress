package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import songstress.TheSongstressMod;
import songstress.powers.Charmed;

public class DestructiveDiscord extends AbstractSongstressCard {

	public static final String ID = "DestructiveDiscord";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int ADDITIONAL_ATTACK_DMG = 14;
	private static final int ATTACK_DMG = 11;
	private static final int UPGRADE_ATTACK_DMG = 6;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public DestructiveDiscord() {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		baseDamage = ATTACK_DMG;
		baseMagicNumber = magicNumber = ADDITIONAL_ATTACK_DMG;
		isSong = true;
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
		return new DestructiveDiscord();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		boolean isCharmed = m.hasPower(TheSongstressMod.withModID(Charmed.POWER_ID));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
				AbstractGameAction.AttackEffect.SMASH));
		if (isCharmed) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
					new DamageInfo(p, magicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		}
	}

}
