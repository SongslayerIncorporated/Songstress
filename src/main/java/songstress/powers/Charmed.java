package songstress.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Charmed extends AbstractSongstressPower {
	public static final String POWER_ID = "Charmed";
	private static final PowerStrings powerStrings = getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public Charmed(AbstractCreature target) {
		super(POWER_ID, NAME, target, -1);
		updateDescription();
		type = AbstractPower.PowerType.DEBUFF;
		priority = 98;
	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0];
	}

	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type1) {
		if (type1 == DamageInfo.DamageType.NORMAL && !owner.isPlayer) {
			return damage * 0.5f;
		}
		return damage;
	}

	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		if (info.type == DamageInfo.DamageType.NORMAL && damageAmount > owner.currentBlock) {
			AbstractDungeon.actionManager
					.addToBottom(new RemoveSpecificPowerAction(owner, AbstractDungeon.player, this));
		}
		return super.onAttacked(info, damageAmount);
	}

}
