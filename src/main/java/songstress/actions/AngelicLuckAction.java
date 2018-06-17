/*
 * Decompiled with CFR.
 */
package songstress.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AngelicLuckAction extends AbstractGameAction {
	private int havocTimes;

	public AngelicLuckAction(AbstractCreature target, int times) {
		duration = Settings.ACTION_DUR_FAST;
		actionType = AbstractGameAction.ActionType.WAIT;
		source = AbstractDungeon.player;
		this.target = target;
		havocTimes = times;
	}

	@Override
	public void update() {
		if (duration == Settings.ACTION_DUR_FAST) {
			if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
				isDone = true;
				return;
			}
			if (AbstractDungeon.player.drawPile.isEmpty()) {
				AbstractDungeon.actionManager.addToTop(new AngelicLuckAction(target, havocTimes));
				AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
				isDone = true;
				return;
			}
			for (int i = 0; i < havocTimes; ++i) {
				if (AbstractDungeon.player.drawPile.isEmpty()) {
					continue;
				}
				AbstractCard card = AbstractDungeon.player.drawPile.getRandomCard(true);
				AbstractDungeon.player.drawPile.group.remove(card);
				AbstractDungeon.getCurrRoom().souls.remove(card);
				card.freeToPlayOnce = true;
				card.exhaustOnUseOnce = true;
				AbstractDungeon.player.limbo.group.add(card);
				card.current_y = -200.0f * Settings.scale;
				card.target_x = Settings.WIDTH / 2.0f + i * 200;
				card.target_y = Settings.HEIGHT / 2.0f;
				card.targetAngle = 0.0f;
				card.lighten(false);
				card.drawScale = 0.12f;
				card.targetDrawScale = 0.75f;
				if (!card.canUse(AbstractDungeon.player, (AbstractMonster) target)) {
					AbstractDungeon.actionManager
							.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.limbo));
					continue;
				}
				card.applyPowers();
				AbstractDungeon.actionManager.addToTop(new QueueCardAction(card, target));
				AbstractDungeon.actionManager.addToTop(new UnlimboAction(card));
			}
			isDone = true;
		}
	}
}
