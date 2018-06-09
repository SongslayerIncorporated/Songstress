package songstress;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.interfaces.PostPowerApplySubscriber;
import songstress.relics.Harpsichord;

public class HarpsichordHelper implements PostPowerApplySubscriber {

	@Override
	public void receivePostPowerApplySubscriber(AbstractPower p, AbstractCreature target, AbstractCreature source) {
		if (target == AbstractDungeon.player
				&& AbstractDungeon.player.hasRelic(TheSongstressMod.withModID(Harpsichord.ID))
				&& (p instanceof StrengthPower || p instanceof DexterityPower) && p.amount > 0) {
			((Harpsichord) AbstractDungeon.player.getRelic(TheSongstressMod.withModID(Harpsichord.ID)))
					.onDexOrStrengthGain();
		}
	}
}
