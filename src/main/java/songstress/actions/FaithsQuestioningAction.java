package songstress.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import songstress.cards.AbstractSongstressCard;

public class FaithsQuestioningAction extends AbstractGameAction {
	public FaithsQuestioningAction() {
		duration = Settings.ACTION_DUR_XFAST;
	}

	@Override
	public void update() {
		if (!isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			for (AbstractCard c : p.hand.group) {
				if (c instanceof AbstractSongstressCard) {
					AbstractSongstressCard asc = (AbstractSongstressCard) c;
					if (asc.isHoly || asc.isCure) {
						asc.setCostForTurn(-9);
					}
				}
			}
			isDone = true;
		}
	}

}
