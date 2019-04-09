package Thmod.Events.Remiria;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import Thmod.Cards.BlessingCards.Determination;
import Thmod.Cards.Curses.Confused;
import Thmod.Characters.KomeijiSatori;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Rewards.AllUpgrade;
import Thmod.Rewards.FullHealing;
import Thmod.Rewards.RemoveCurse;
import Thmod.ThMod;

public class SpiritSufferingRemi extends AbstractImageEvent {
    public static final String ID = "SpiritSufferingRemi";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("SpiritSufferingRemi");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;


    private static enum CurScreen
    {
        INTRO, FACE, MID, FLAN, LEAVE
    }

    public SpiritSufferingRemi(){
        super(NAME, INTRO_MSG, "images/events/SpiritSuffering.png");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.MID;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);

                        CardCrawlGame.sound.play("BLUNT_FAST");

                        AbstractDungeon.player.damage(new DamageInfo(null, 16, DamageInfo.DamageType.HP_LOSS));

                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case MID:
                this.screen = CurScreen.FACE;
                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                break;
            case FACE:
                this.screen = CurScreen.FLAN;
                AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Flandre");

                AbstractDungeon.getCurrRoom().rewards.clear();
                AbstractDungeon.getCurrRoom().rewardAllowed = false;
                enterCombatFromImage();
                break;
            case FLAN:
                this.screen = CurScreen.LEAVE;
                AbstractDungeon.getCurrRoom().rewards.clear();
                AbstractDungeon.getCurrRoom().rewards.add(new FullHealing());
                AbstractDungeon.getCurrRoom().rewards.add(new AllUpgrade());
                AbstractDungeon.getCurrRoom().rewards.add(new RemoveCurse());
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                AbstractDungeon.combatRewardScreen.open();
                break;
            case LEAVE:
                openMap();
                break;
        }
    }

    public void reopen()
    {
        if (this.screen != CurScreen.LEAVE)
        {
            AbstractDungeon.resetPlayer();
            AbstractDungeon.player.drawX = (Settings.WIDTH * 0.25F);
            AbstractDungeon.player.preBattlePrep();
            enterImageFromCombat();
            this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
            this.imageEventText.updateDialogOption(0, OPTIONS[3]);
        }
    }
}
