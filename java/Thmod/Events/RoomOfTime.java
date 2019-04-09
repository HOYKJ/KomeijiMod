package Thmod.Events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import Thmod.Cards.BlessingCards.BlessingOfTime;
import Thmod.Cards.DeriveCards.EasterEgg.TheHidden;
import Thmod.Cards.RewardCards.PerfectMaid;
import Thmod.Cards.RewardCards.VanishingEverything;
import Thmod.Characters.KomeijiSatori;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Events.Remiria.RoomOfTimeSpe;
import Thmod.Relics.remiria.SpecialStopwatch;
import Thmod.ThMod;

public class RoomOfTime extends AbstractImageEvent {
    public static final String ID = "RoomOfTime";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("RoomOfTime");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;
    public Hitbox hb;


    private static enum CurScreen
    {
        INTRO,READ,WOCCHI,LEAVE,HIDE;

        private CurScreen() {}
    }

    public RoomOfTime(){
        super(NAME, INTRO_MSG, "images/events/DiaryofTime.png");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        if(AbstractDungeon.player instanceof KomeijiSatori) {
            if (ThMod.blessingOfTime >= 1) {
                this.body = DESCRIPTIONS[1];
                //this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                this.imageEventText.setDialogOption(OPTIONS[2]);
            } else {
                this.imageEventText.setDialogOption(OPTIONS[1]);
            }
        }
        else {
            this.imageEventText.setDialogOption(OPTIONS[1]);
        }
        this.hb = new Hitbox(110.0F * Settings.scale, 100.0F * Settings.scale);
    }

    @Override
    public void onEnterRoom() {
        super.onEnterRoom();
        if((AbstractDungeon.player != null) && (AbstractDungeon.player instanceof RemiriaScarlet)){
            ThMod.logger.info("Replace Event");
            AbstractDungeon.getCurrRoom().event = new RoomOfTimeSpe();
            AbstractDungeon.getCurrRoom().event.onEnterRoom();
        }
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed){
                    case 0:
                        this.screen = CurScreen.READ;
                        if(AbstractDungeon.player instanceof KomeijiSatori) {
                            if (ThMod.blessingOfTime == 0) {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                            } else if (ThMod.blessingOfTime == 1) {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                            } else if (ThMod.blessingOfTime == 2) {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                            } else {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                            }
                        }
                        else {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        }
                        this.imageEventText.updateDialogOption(0,OPTIONS[3], CardLibrary.getCopy("PerfectMaid"));
                        this.imageEventText.updateDialogOption(1,OPTIONS[4], CardLibrary.getCopy("VanishingEverything"));
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        CardCrawlGame.sound.play("MAP_OPEN_2");
                        break;
                    case 1:
                        this.screen = CurScreen.WOCCHI;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                        this.imageEventText.updateDialogOption(0,OPTIONS[6]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case WOCCHI:
                this.screen = CurScreen.LEAVE;
                if(AbstractDungeon.player instanceof KomeijiSatori) {
                    if (ThMod.blessingOfTime == 0) {
                        this.imageEventText.updateBodyText(DESCRIPTIONS[7]);
                        AbstractDungeon.player.heal(10);
                    } else if (ThMod.blessingOfTime >= 1) {
                        this.imageEventText.updateBodyText(DESCRIPTIONS[8]);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.x, this.y,  RelicLibrary.getRelic("LinkosWocchi").makeCopy());
                        UnlockTracker.unlockCard("THsWorld");
                    }
                }
                else {
                    AbstractDungeon.player.heal(10);
                    this.imageEventText.updateBodyText(DESCRIPTIONS[7]);
                }
                this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                this.imageEventText.clearRemainingOptions();
                break;
            case READ:
                switch (buttonPressed){
                    case 0:
                        this.screen = CurScreen.LEAVE;
                        if(AbstractDungeon.player instanceof KomeijiSatori) {
                            if (ThMod.blessingOfTime == 1) {
                                ThMod.blessingOfTime = 0;
                                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                                    if (c instanceof BlessingOfTime)
                                        AbstractDungeon.player.masterDeck.group.remove(c);
                                }
                                this.imageEventText.updateBodyText(DESCRIPTIONS[10]);
                            } else {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[9]);
                            }
                        }
                        else {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[9]);
                        }
                        this.imageEventText.updateDialogOption(0,OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new PerfectMaid(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        if(AbstractDungeon.player instanceof KomeijiSatori) {
                            if (ThMod.blessingOfTime == 1) {
                                ThMod.blessingOfTime = 0;
                                AbstractCard removeCard = null;
                                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                                    if (c instanceof BlessingOfTime) {
                                        removeCard = c;
                                    }
                                }
                                if(removeCard != null) {
                                    AbstractDungeon.player.masterDeck.group.remove(removeCard);
                                }
                                this.imageEventText.updateBodyText(DESCRIPTIONS[10]);
                            } else {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[9]);
                            }
                        }
                        else {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[9]);
                        }
                        this.imageEventText.updateDialogOption(0,OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new VanishingEverything(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        break;
                    case 2:
                        this.screen = CurScreen.LEAVE;
                        if(AbstractDungeon.player instanceof KomeijiSatori) {
                            if (ThMod.blessingOfTime == 0) {
                                ThMod.blessingOfTime += 1;
                                AbstractDungeon.player.masterDeck.group.add(new BlessingOfTime());
                                this.imageEventText.updateBodyText(DESCRIPTIONS[11]);
                            } else if (ThMod.blessingOfTime == 1) {
                                ThMod.blessingOfTime += 1;
                                this.imageEventText.updateBodyText(DESCRIPTIONS[12]);
                            } else {
                                this.imageEventText.updateBodyText(DESCRIPTIONS[11]);
                            }
                        }
                        else {
                            this.imageEventText.updateBodyText(DESCRIPTIONS[11]);
                        }
                        this.imageEventText.updateDialogOption(0,OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                }
                break;
            case HIDE:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[14]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.x, this.y,  RelicLibrary.getRelic(SpecialStopwatch.ID).makeCopy());
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[15]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[7]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case LEAVE:
                openMap();
                break;
        }
    }

    @Override
    public void update() {
        super.update();
        if(this.hb != null) {
            this.calculateY();
            this.hoverAndClickLogic();
        }
    }

    private void calculateY() {
        this.hb.move(245 * Settings.scale, 600 * Settings.scale);
    }

    private void hoverAndClickLogic() {
        this.hb.update();
        if ((this.hb.hovered) && (InputHelper.justClickedLeft)) {
            InputHelper.justClickedLeft = false;
            this.hb.clickStarted = true;
        }
        if ((this.hb.hovered) && (CInputActionSet.select.isJustPressed())) {
            this.hb.clicked = true;
        }
        if (this.hb.clicked) {
            this.hb.clicked = false;
            this.screen = CurScreen.HIDE;
            this.imageEventText.updateBodyText(DESCRIPTIONS[13]);
            this.imageEventText.updateDialogOption(0,OPTIONS[8]);
            this.imageEventText.clearRemainingOptions();
            this.imageEventText.setDialogOption(OPTIONS[9]);
            this.hb = null;
            UnlockTracker.unlockCard(TheHidden.ID);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if(this.hb != null){
            this.hb.render(sb);
        }
    }
}
