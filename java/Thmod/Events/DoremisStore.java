package Thmod.Events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import Thmod.Relics.GoodDreamPillow;

public class DoremisStore extends AbstractImageEvent {
    public static final String ID = "DoremisStore";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("DoremisStore");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;
    private AbstractCard offeredCard = null;
    private boolean cardSelect = false;


    private static enum CurScreen
    {
        INTRO,CONTINUE,LEAVE;

        private CurScreen() {}
    }

    public DoremisStore(){
        super(NAME, INTRO_MSG, "images/events/Ring.png");
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
    }

    public void update()
    {
        super.update();
        if ((this.cardSelect) && (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()))
        {
            this.offeredCard = (AbstractDungeon.gridSelectScreen.selectedCards.remove(0));
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(this.offeredCard, Settings.WIDTH / 2, Settings.HEIGHT / 2));

            AbstractDungeon.player.masterDeck.removeCard(this.offeredCard);
            this.screen = CurScreen.LEAVE;
            this.cardSelect = false;
        }
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.CONTINUE;
                        this.imageEventText.loadImage("images/events/DoremisStore.png");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        label2:
                        {
                            if (p.gold >= 123) {
                                this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                                break label2;
                            }
                            this.imageEventText.updateDialogOption(0, OPTIONS[5],true);
                        }
                        label2:
                        {
                            for (AbstractCard card : p.masterDeck.group) {
                                if (card.type == AbstractCard.CardType.CURSE) {
                                    this.imageEventText.updateDialogOption(1, OPTIONS[3]);
                                    break label2;
                                }
                            }
                            this.imageEventText.updateDialogOption(1, OPTIONS[6], true);
                        }
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
                break;
            case CONTINUE:
                switch (buttonPressed){
                    case 0:
                        this.screen = CurScreen.LEAVE;
                        p.loseGold(123);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic(GoodDreamPillow.ID).makeCopy());
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 1:
//                        this.screen = CurScreen.LEAVE;
                        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        for(AbstractCard card:p.masterDeck.group){
                            if(card.type == AbstractCard.CardType.CURSE){
                                retVal.group.add(card);
                            }
                        }
                        AbstractDungeon.gridSelectScreen.open(retVal, 1, OPTIONS[7], false, false, false, true);
                        this.cardSelect = true;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 2:
                        this.screen = CurScreen.LEAVE;
                        AbstractDungeon.player.heal(15);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[1]);
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
