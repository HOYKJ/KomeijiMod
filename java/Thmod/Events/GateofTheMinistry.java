package Thmod.Events;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.ThMod;

public class GateofTheMinistry extends AbstractImageEvent {
    public static final String ID = "GateofTheMinistry";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("GateofTheMinistry");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;

    private static enum CurScreen
    {
        INTRO,BATTLE,LEAVE;

        private CurScreen() {}
    }

    public GateofTheMinistry(){
        super(NAME, INTRO_MSG, "images/events/GateofTheMinistry.png");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.BATTLE;
                        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Shikieiki");
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractMonster.preloadBossStinger();
                        enterCombatFromImage();
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        ThMod.blessingOfRemission = 0;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
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
