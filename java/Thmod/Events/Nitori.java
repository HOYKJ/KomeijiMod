package Thmod.Events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

public class Nitori extends AbstractImageEvent {
    public static final String ID = "Nitori";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Nitori");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];

    public Nitori() {
        super(NAME, INTRO_MSG, "images/events/Nitori.png");
    }

    protected void buttonEffect(int buttonPressed) {

    }
}
