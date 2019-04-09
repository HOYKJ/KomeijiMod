package Thmod.Events.Remiria;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import Thmod.Cards.Curses.Lonely;
import Thmod.Relics.remiria.SpecialStopwatch;

public class RoomOfTimeSpe extends AbstractImageEvent {
    public static final String ID = "RoomOfTimeSpe";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("RoomOfTimeSpe");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;


    private static enum CurScreen
    {
        INTRO, READ, WRITE, HIDDEN, OPEN, LETTER, LEAVE;

        private CurScreen() {}
    }

    public RoomOfTimeSpe(){
        super(NAME, INTRO_MSG, "images/events/DiaryofTime.png");
        this.imageEventText.setDialogOption(OPTIONS[0]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed){
                    case 0:
                        this.screen = CurScreen.READ;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0,OPTIONS[3]);
                        CardCrawlGame.sound.play("MAP_OPEN_2");
                        break;
                }
                break;
            case READ:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.WRITE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        break;
                }
            case WRITE:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.HIDDEN;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0,OPTIONS[1], CardLibrary.getCopy(Lonely.ID));
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                        break;
                }
                break;
            case HIDDEN:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.OPEN;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.imageEventText.updateDialogOption(0,OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.x, this.y,  RelicLibrary.getRelic(SpecialStopwatch.ID).makeCopy());
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Lonely(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[7]);
                        this.imageEventText.updateDialogOption(0,OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case OPEN:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.LETTER;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        break;
                }
                break;
            case LETTER:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                        this.imageEventText.updateDialogOption(0,OPTIONS[2]);
                        break;
                }
                break;
            case LEAVE:
                openMap();
                break;
        }
    }
}
