package songstress.relics;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class LootedLute extends AbstractSongstressRelic {

	public static final String ID = "LootedLute";
	private static final int HP_LOSS = 10;
	public static final RelicTier TIER = RelicTier.BOSS;
	public static final LandingSound SOUND = LandingSound.MAGICAL;

	public LootedLute() {
		super(ID, TIER, SOUND);
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + HP_LOSS + DESCRIPTIONS[1];
	}

	@Override
	public void onPlayerEndTurn() {
		int energy = EnergyPanel.getCurrentEnergy();
		if (energy > 0) {
			flash();
			AbstractDungeon.actionManager
					.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, HP_LOSS * energy));
		}
	}

	@Override
	public void onEquip() {
		AbstractDungeon.player.energy.energyMaster++;
	}

	@Override
	public void onUnequip() {
		AbstractDungeon.player.energy.energyMaster--;
	}

	@Override
	public boolean checkTrigger() {
		return true;
	}

	@Override
	public AbstractRelic makeCopy() {
		return new LootedLute();
	}

}
