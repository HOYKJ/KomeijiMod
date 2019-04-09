package Thmod.Events.Remiria;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;

import Thmod.Relics.remiria.SpecialStopwatch;
import Thmod.Relics.remiria.TepesBloodVial;

public class VampiresRemi extends AbstractImageEvent
{
    public static final String ID = "VampiresRemi";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("VampiresRemi");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private int screenNum = 0;

    public VampiresRemi()
    {
        super(NAME, DESCRIPTIONS[0], "images/events/vampires.jpg");

        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    protected void buttonEffect(int buttonPressed)
    {
        switch (this.screenNum)
        {
            case 0:
                switch (buttonPressed)
                {
                    case 0:
                        this.screenNum = 1;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 1:
                        this.screenNum = 2;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case 1:
                this.screenNum = 2;
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY,  RelicLibrary.getRelic(TepesBloodVial.ID).makeCopy());
                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                break;
            case 2:
                openMap();
                break;
            default:
                openMap();
        }
    }
}
