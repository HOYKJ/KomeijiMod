package Thmod.Events;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import Thmod.Cards.BlessingCards.Determination;
import Thmod.Cards.Curses.Confused;
import Thmod.Characters.KomeijiSatori;
import Thmod.ThMod;

public class SpiritSuffering extends AbstractImageEvent {
    public static final String ID = "SpiritSuffering";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("SpiritSuffering");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;


    private static enum CurScreen
    {
        INTRO,CLOSE,FACE,LEAVE;

        private CurScreen() {}
    }

    public SpiritSuffering(){
        super(NAME, INTRO_MSG, "images/events/SpiritSuffering.png");
        if(AbstractDungeon.player.hasRelic("KomeijisEye"))
            this.imageEventText.setDialogOption(OPTIONS[0]);
        else
            this.imageEventText.setDialogOption(OPTIONS[2],true);
        this.imageEventText.setDialogOption(OPTIONS[1], CardLibrary.getCopy("Confused"));
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.LEAVE;
                        p.loseRelic("KomeijisEye");
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("KoishisEye").makeCopy());
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Confused(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        if(AbstractDungeon.player instanceof KomeijiSatori) {
                            AbstractDungeon.player.masterDeck.group.add(new Determination());
                            if(ThMod.blessingOfDetermination == 0)
                                ThMod.blessingOfDetermination += 1;
                        }
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case LEAVE:
                openMap();
                break;
        }
    }
}
