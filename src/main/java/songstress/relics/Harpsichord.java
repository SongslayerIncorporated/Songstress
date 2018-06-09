package songstress.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Harpsichord extends AbstractSongstressRelic {

	public static final String ID = "Harpsichord";
	public static final int DAMAGE = 2;
	public static final RelicTier TIER = RelicTier.COMMON;
	public static final LandingSound SOUND = LandingSound.MAGICAL;

	public Harpsichord() {
		super(ID, TIER, SOUND);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + DAMAGE + DESCRIPTIONS[1];
	}

	public void onDexOrStrengthGain() {
		// code from Dark orb
		AbstractMonster weakestMonster = null;
		for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
			if (!m.isDeadOrEscaped() && (weakestMonster == null || m.currentHealth < weakestMonster.currentHealth)) {
				weakestMonster = m;
			}
		}
		if (weakestMonster != null) {
			flash();
			AbstractDungeon.actionManager.addToBottom(new DamageAction(weakestMonster,
					new DamageInfo(AbstractDungeon.player, DAMAGE, DamageInfo.DamageType.THORNS),
					AbstractGameAction.AttackEffect.FIRE));
		}
	}

	@Override
	public AbstractRelic makeCopy() {
		return new Harpsichord();
	}

}
