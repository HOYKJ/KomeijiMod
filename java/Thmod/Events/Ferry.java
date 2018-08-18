package Thmod.Events;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import Thmod.Cards.Curses.Exhaustion;
import Thmod.Characters.KomeijiSatori;
import Thmod.Relics.WordlessDocument;
import Thmod.ThMod;

public class Ferry extends AbstractImageEvent {
    public static final String ID = "Ferry";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Ferry");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;

    private static enum CurScreen
    {
        INTRO,CONTINUE,TALK,TAKED,CURSE,LEAVE;

        private CurScreen() {}
    }

    public Ferry(){
        super(NAME, INTRO_MSG, "images/events/Ferry.png");
        this.imageEventText.setDialogOption(OPTIONS[8]);
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.screen) {
            case INTRO:
                this.screen = CurScreen.CONTINUE;
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                this.imageEventText.updateDialogOption(0, OPTIONS[0] + AbstractDungeon.player.gold + OPTIONS[1]);
                this.imageEventText.setDialogOption(OPTIONS[2] + (AbstractDungeon.player.gold / 2) + OPTIONS[1]);
                this.imageEventText.setDialogOption(OPTIONS[3], CardLibrary.getCopy("Exhaustion"));
                break;
            case CONTINUE:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.TALK;
                        AbstractDungeon.player.loseGold(AbstractDungeon.player.gold);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.updateDialogOption(1, OPTIONS[5]);
                        this.imageEventText.updateDialogOption(2, OPTIONS[6]);
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 2:
                        this.screen = CurScreen.CURSE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Exhaustion(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        break;
                }
                break;
            case TALK:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
                        CardCrawlGame.sound.play("ATTACK_HEAVY");
                        AbstractDungeon.player.increaseMaxHp(10, false);
                        AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
                        break;
                    case 1:
                        this.screen = CurScreen.TAKED;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[8]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 2:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                        if(AbstractDungeon.player instanceof KomeijiSatori) {
                            if (ThMod.blessingOfRemission == 0) {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[8]);
                                ThMod.blessingOfRemission += 1;
                                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("WordlessDocument"));
                            }
                            else
                                this.imageEventText.updateBodyText(DESCRIPTIONS[9]);
                        }
                        else
                            this.imageEventText.updateBodyText(DESCRIPTIONS[10]);
                        break;
                }
                break;
            case TAKED:
                this.screen = CurScreen.LEAVE;
                this.imageEventText.updateBodyText(DESCRIPTIONS[7]);
                this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                AbstractDungeon.getCurrRoom().rewards.clear();
                AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractDungeon.returnRandomRelicTier());
                AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
                AbstractDungeon.getCurrRoom().addCardToRewards();
                AbstractDungeon.combatRewardScreen.open();
                break;
            case CURSE:
                this.screen = CurScreen.LEAVE;
                this.imageEventText.updateBodyText(DESCRIPTIONS[11]);
                this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.MED, false);
                CardCrawlGame.sound.play("BLUNT_FAST");
                break;
            case LEAVE:
                openMap();
                break;
        }
    }
}
