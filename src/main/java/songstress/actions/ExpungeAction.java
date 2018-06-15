package songstress.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import songstress.powers.Holy;

public class ExpungeAction extends AbstractGameAction {

	private int handBefore;
	private int holy;
	private int damage;

	public ExpungeAction(int hand, int holy, int damage) {
		handBefore = hand;
		this.holy = holy;
		this.damage = damage;
		duration = Settings.ACTION_DUR_XFAST;
	}

	@Override
	public void update() {
		if (!isDone) {
			isDone = true;
			int hand = AbstractDungeon.player.hand.size();
			if (hand < handBefore) {
				int exhausted = handBefore - hand;
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,
						AbstractDungeon.player, new Holy(holy * exhausted), holy * exhausted));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player,
						new DamageInfo(AbstractDungeon.player, damage * exhausted, DamageType.THORNS)));
			}
		}
	}

}
