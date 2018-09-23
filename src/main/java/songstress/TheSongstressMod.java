package songstress;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import songstress.cards.Abolish;
import songstress.cards.AngelicDescent;
import songstress.cards.AngelicLuck;
import songstress.cards.BlessedFury;
import songstress.cards.DeafenedStab;
import songstress.cards.Defend;
import songstress.cards.DestructiveDiscord;
import songstress.cards.Diminuendo;
import songstress.cards.Exorcism;
import songstress.cards.Expunge;
import songstress.cards.FaithsQuestioning;
import songstress.cards.FaithsSaber;
import songstress.cards.FaithsShield;
import songstress.cards.Guidance;
import songstress.cards.HarmonicMelody;
import songstress.cards.HeavenlyAscent;
import songstress.cards.HolyBolt;
import songstress.cards.InfiniteMelody;
import songstress.cards.Mend;
import songstress.cards.OnMercysWings;
import songstress.cards.PentagonalSolo;
import songstress.cards.PowerChord;
import songstress.cards.Prayer;
import songstress.cards.Purge;
import songstress.cards.Revival;
import songstress.cards.Serenade;
import songstress.cards.SheetMusic;
import songstress.cards.Shimmer;
import songstress.cards.Siphon;
import songstress.cards.SonicScreech;
import songstress.cards.SourNote;
import songstress.cards.Strike;
import songstress.cards.Symphony;
import songstress.cards.WingBuffet;
import songstress.patches.ColorEnum;
import songstress.patches.PlayerClassEnum;
import songstress.relics.AngelicChords;
import songstress.relics.Bongos;
import songstress.relics.ForgedDrums;
import songstress.relics.HarmonicChords;
import songstress.relics.Harpsichord;
import songstress.relics.LootedLute;
import songstress.relics.Metronome;

@SpireInitializer
public class TheSongstressMod implements EditKeywordsSubscriber, EditCharactersSubscriber, EditCardsSubscriber,
		PostInitializeSubscriber, EditStringsSubscriber, EditRelicsSubscriber {
	public static final Logger logger = LogManager.getLogger(TheSongstressMod.class.getName());
	public static final String MOD_ID = "thesongstress";
	public static final String MOD_NAME = "The Songstress";
	public static final String AUTHORS = "LuciusMaveric, Skrelpoid";

	public static String withModID(String id) {
		return MOD_ID + ":" + id;
	}

	public static void initialize() {
		logger.info("Initializing TheSongstress");
		logger.info("Registering Color Cloud");
		Color color = new Color(0x1E90FFFF);
		BaseMod.addColor(ColorEnum.Cloud, color, color, color, color, color, color, color,
				"song/img/cards/512/bg_attack_cloud.png", "song/img/cards/512/bg_skill_cloud.png",
				"song/img/cards/512/bg_power_cloud.png", "song/img/cards/512/card_cloud_orb2.png",
				"song/img/cards/1024/bg_attack_cloud.png", "song/img/cards/1024/bg_skill_cloud.png",
				"song/img/cards/1024/bg_power_cloud.png", "song/img/cards/1024/card_cloud_orb2.png");
		BaseMod.subscribe(new TheSongstressMod());
		BaseMod.subscribe(new HarpsichordHelper());
	}

	@Override
	public void receiveEditCharacters() {
		logger.info("Adding Character: The Songstress");
		String button = "song/img/char/microphone.png";
		String portrait = "song/img/char/portrait.jpg";

		BaseMod.addCharacter(TheSongstress.class, TheSongstress.NAMES[0], TheSongstress.NAMES[0], ColorEnum.Cloud,
				TheSongstress.NAMES[0], button, portrait, PlayerClassEnum.TheSongstress);
	}

	@Override
	public void receiveEditCards() {
		logger.info("Adding cards for the Songstress");

		// BASIC
		BaseMod.addCard(new Strike());
		BaseMod.addCard(new Defend());
		BaseMod.addCard(new PowerChord());

		// COMMON
		BaseMod.addCard(new HolyBolt());
		BaseMod.addCard(new FaithsShield());
		BaseMod.addCard(new Mend());
		BaseMod.addCard(new Shimmer());
		BaseMod.addCard(new Prayer());
		BaseMod.addCard(new Siphon());
		BaseMod.addCard(new Serenade());
		BaseMod.addCard(new SourNote());
		BaseMod.addCard(new Diminuendo());
		BaseMod.addCard(new SheetMusic());
		BaseMod.addCard(new HarmonicMelody());

		// UNCOMMON

		BaseMod.addCard(new Abolish());
		BaseMod.addCard(new Exorcism());
		BaseMod.addCard(new FaithsSaber());
		BaseMod.addCard(new WingBuffet());
		BaseMod.addCard(new OnMercysWings());
		BaseMod.addCard(new Guidance());
		BaseMod.addCard(new PentagonalSolo());
		BaseMod.addCard(new DestructiveDiscord());
		BaseMod.addCard(new SonicScreech());
		BaseMod.addCard(new Symphony());

		// RARE
		BaseMod.addCard(new BlessedFury());
		BaseMod.addCard(new AngelicDescent());
		BaseMod.addCard(new Expunge());
		BaseMod.addCard(new FaithsQuestioning());
		BaseMod.addCard(new Purge());
		BaseMod.addCard(new AngelicLuck());
		BaseMod.addCard(new Revival());
		BaseMod.addCard(new InfiniteMelody());
		BaseMod.addCard(new HeavenlyAscent());
		BaseMod.addCard(new DeafenedStab());

		// SPECIAL
	}

	@Override
	public void receiveEditRelics() {
		logger.info("Adding relics for the Songstress");

		// STARTER
		BaseMod.addRelicToCustomPool(new AngelicChords(), ColorEnum.Cloud);

		// COMMON
		BaseMod.addRelic(new Harpsichord(), RelicType.SHARED);
		BaseMod.addRelic(new Bongos(), RelicType.SHARED);

		// UNCOMMON

		// RARE
		BaseMod.addRelic(new ForgedDrums(), RelicType.SHARED);
		BaseMod.addRelicToCustomPool(new Metronome(), ColorEnum.Cloud);

		// BOSS
		BaseMod.addRelicToCustomPool(new HarmonicChords(), ColorEnum.Cloud);
		BaseMod.addRelic(new LootedLute(), RelicType.SHARED);

		// SPECIAL

	}

	@Override
	public void receiveEditStrings() {
		logger.info("Loading Strings for TheSongstress");
		// TODO OrderJson these
		BaseMod.loadCustomStrings(RelicStrings.class, loadJson("song/local/relics.json"));
		BaseMod.loadCustomStrings(CardStrings.class, loadJson("song/local/cards.json"));
		BaseMod.loadCustomStrings(PowerStrings.class, loadJson("song/local/powers.json"));
	}

	@Override
	public void receivePostInitialize() {
		Texture badgeTexture = new Texture(Gdx.files.internal("song/img/mod-badge.png"));
		BaseMod.registerModBadge(badgeTexture, MOD_NAME, AUTHORS, "", new ModPanel());
	}

	@Override
	public void receiveEditKeywords() {
		logger.info("Adding Keywords for the Songstress");
		// Note: KeywordStrings is a horrible hardcoded class, we can't use it
		// use a custom class instead
		// Copied from MadScienceMod
		Type typeToken = new TypeToken<Map<String, Keyword>>() {}.getType();
		Gson gson = new Gson();
		String strings = loadJson("song/local/keywords.json");
		@SuppressWarnings("unchecked")
		Map<String, Keyword> keywords = (Map<String, Keyword>) gson.fromJson(strings, typeToken);
		for (Keyword kw : keywords.values()) {
			BaseMod.addKeyword(kw.NAMES, kw.DESCRIPTION);
		}
	}

	// Copied from MadScienceMod
	private static String loadJson(String jsonPath) {
		return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
	}

}
