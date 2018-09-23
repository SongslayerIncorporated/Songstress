# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

Dates in this Changelog are yyyy-mm-dd (Year-Month-Day)

## [Unreleased]

## [0.4.4] - 2018-09-23
### Compatibility Update

## [0.4.3] - 2018-08-12
### Compatibility Update
   
## [0.4.2] - 2018-07-19
### Added
 - New Images for: Forged Drums, Bongos!
 
### Changed
 - Images for: Character Model, Portrait, Button

## [0.4.1] - 2018-07-13
### Changed
 - Energy Orb is now blue instead of gray

## [0.4.0] - 2018-07-13
### Added
 - Common Card Sour Note: Deal 7(10) damage and apply 2(3) Weak to all enemies. Song.
 - Common Card Diminuendo: Apply 3(5) Weak to all enemies and remove their Strength. Exhaust.
 - Common Card Harmonic Melody: Deal 4(7) damage and apply Charmed to all enemies.
 - Uncommon Card Pentagonal Solo: Deal 5(10) damage and apply 1(2) Weak and 1(2) Vulnerable. Gain 1 Strength and 1 Dexterity. Song. Exhaust.
 - Uncommon Card Sheet Music: Draw 1 Card. Draw 1 additional card for each Song you've played this turn.
 - Uncommon Card Destructive Discord: Deal 11(17) damage. If the enemy is Charmed, deal 14 more damage. Song.
 - Uncommon Card Sonic Screech: Deal 16(21) damage to all enemies. Gain 1 Muted.
 - Uncommon Card Sonic Screech: Deal 3(4) damage to all enemies for each Song in your deck. Gain 2 Muted.
 - Rare Card Deafened Stab: Can only play while Muted. Deal 40(50) damage.
 
 - Charmed Power: Charmed enemies deal 50% less damage. Removed when the enemy takes attack damage.
 - Muted Power: Cannot play Songs until the start of your next turn.
 
 ### Changed
 - If you don't have enough Holy to pay for a card you now can't play it.
 - Wing Buffet -> Deal 1(+U) damage 5(+U) times. Can be upgraded any number of times.

## [0.3.2] - 2018-07-08
### Added
 - Rare Card Heavenly Ascent: Gain 15(20) Block. Gain Risen.
 - Common Card Serenade: Gain 6(8) Block twice. Song.
 - Power Risen: The next Angelic Descent you play will refund all energy it costed.
 
 ### Changed
 - Power Chord: No longer Exhausts: Deal 8(11) damage. Gain 1 Strength. Song.

## [0.3.1] - 2018-07-06
### Added
 - Portrait for Abolish
 - Rare Card Infinite Melody: Whenever you play a Power, draw 1 card.
 - Keyword Song

## [0.3.0] - 2018-06-17
### Added
 - Rare Card Purge: Exhaust ALL Curses and Statuses. Gain !M! Strength for each card exhausted. Exhaust.
 - Rare Card Angelic Luck: Play and exhaust 3(5) random cards from your draw pile. Exhaust.
 - Rare Card Revival: Return up to 2(3) cards from Exhaust.

## [pre-0.2.5] - 2018-06-15
### Added
 - Rare Card Blessed Fury: Costs 2(1) Holy. Until the end of turn, whenever you play a card, play it twice. Exhaust.
 - Rare Card Angelic Descent: Deal damage equal to your current Holy to all enemies X(X+1) times. Exhaust.
 - Automatic update using ModTheSpire
 
 ### Changed
 - Expunge -> Rare Card. 1(0) Cost. Exhaust up to 2 cards. Take 3(2) damage for each card exhausted this way. Exhaust.
 - Faith's Questioning -> Rare

## [pre-0.2.4] - 2018-06-13
### Added
 - Uncommon Card On Mercy's Wings: Gain 1(2) Holy. Take half damage until the start of your next turn.
 - Uncommon Card Guidance: On your next turn, gain X(X+1) Holy and draw 2(3) cards.
 
 ### Fixed
  - Prayer ID is now spelled the right way in powers.json

## [pre-0.2.3] - 2018-06-12
### Added
 - Rare Relic Metronome: At the beginning of combat, play a random card from any class, then Exhaust it.
 - Boss Relic Looted Lute: Gain energy at the start of each turn. Lose 10 HP for every energy not spent at the end of the turn.

## [pre-0.2.2] - 2018-06-11
### Added
 - Rare Relic Forged Drums: Whenever you smith at a rest site, heal 8 HP
 - Boss Relic Harmonic Chords: Whenever you play an Attack that costs 2 or more, a random card in your hand costs 0 for the turn.
 - Common Relic Bongos!: Every three turns, draw 2 additional cards.


## [pre-0.2.1] - 2018-06-09
### Added
 - Starting Relic Angelic Chords: Each turn, the first time you play an Attack that costs 2 or more, a random card in your hand costs 0 for the turn.

### Changed
 - Abolish -> Deal 30(40) damage. Deal 15 damage to all enemies. Lose all Holy. Lose 3 Strength and Dexterity. Exhaust.
 - Harpsichord: Starter -> Common.

## [pre-0.2.0] - 2018-06-06
### Added
- Powers:
- Holy: Consumed by cards to grant additional effects.
- Prayer: At the start of each turn, gain 1 Holy.
- Effects:
- Cure: Use all your Holy to heal equal to the amount you lost.
- Cards:
- Common Holy Bolt: Deal 3(4) damage. Gain 1 Holy.
- Common Faith's Shield: Gain 7(10) Block. Gain 1(2) Holy.
- Common Mend: Cure. Draw 1(2) card(s). Exhaust.
- Common Shimmer: Apply 2 Weak to an enemy. Use 1 Holy to gain 8(10) Block.
- Common Prayer: At the start of each turn, gain 1 Holy.
- Common Siphon: Cure. Deal 13(16) damage.
- Uncommon Expunge: Take 8(6) damage. Exhaust 1(2) Curse(s) or Status(es).
- Uncommon Abolish: Deal 30(42) damage to all enemies. Lose all Holy. Lose 3 Strength and Dexterity. Exhaust.
- Uncommon Faith's Questioning: Draw 3(5) cards. Holy and Cure cards cost 0 for the rest of the turn.
- Uncommon Exorcism: Apply 3 Vulnerable and Weak to all enemies. Gain 2(4) Holy.
- Uncommon Faith's Saber: Deal 13(16) damage. Use 1 Holy to attack again.
- Uncommon WingBuffet: Deal 2(3) damage 6(7) times.

## [pre-0.1.0] - 2018-06-04
### Added
- Basic Mod Logic
- Character TheSongstress
- Basic Card Strike
- Basic Card Defend
- Basic Card Power Chord: Deal 8(10) damage. Gain 1(2) Strength. Exhaust.
- Starting Relic Harpsichord: Whenever you gain Strength or Dexterity, deal 2 damage to the enemy with the lowest HP.

## TEMPLATE

### Added

### Changed

### Deprecated

### Removed

### Fixed
