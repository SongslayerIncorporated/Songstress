package songstress.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import songstress.powers.Holy;

public class CureAction extends AbstractGameAction {

	public CureAction() {
		duration = Settings.ACTION_DUR_XFAST;
	}

	@Override
	public void update() {
		if (!isDone) {
			isDone = true;
			Holy.flashOnce();
			AbstractDungeon.player.heal(Holy.holyAmount());
			Holy.reduceBy(Holy.holyAmount());
		}
	}

}
