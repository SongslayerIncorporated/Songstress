package songstress.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WingBuffet extends AbstractSongstressCard {

	public static final String ID = "WingBuffet";
	private static final CardStrings cardStrings = getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	private static final int COST = 1;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int ATTACK_DMG = 1;
	private static final int ATTACK_TIMES = 5;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public WingBuffet() {
		this(0);
	}

	public WingBuffet(int upgrades) {
		super(ID, NAME, COST, DESCRIPTION, TYPE, RARITY, TARGET);
		timesUpgraded = upgrades;
		baseDamage = ATTACK_DMG;
		magicNumber = baseMagicNumber = ATTACK_TIMES;
	}

	@Override
	public void upgrade() {
		upgradeDamage(1);
		upgradeMagicNumber(1);
		++timesUpgraded;
		upgraded = true;
		name = NAME + "+" + timesUpgraded;
		initializeTitle();
	}

	@Override
	public AbstractCard makeCopy() {
		return new WingBuffet(timesUpgraded);
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster monster) {
		int i;
		// for every 2 attack times we want to strike once vert and once horz
		for (i = 0; i < magicNumber / 2; i++) {
			AbstractDungeon.actionManager
					.addToBottom(new DamageAction(monster, new DamageInfo(player, damage, damageTypeForTurn),
							AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
					new DamageInfo(player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		}
		// if there is still one left (magicNumber is odd) we do a diagonal
		if (magicNumber % 2 != 0) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
					new DamageInfo(player, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}

}
