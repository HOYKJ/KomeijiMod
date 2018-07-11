package Thmod.vfx.campfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Actions.common.GetSweepbleCards;
import Thmod.Cards.AbstractSweepCards;

public class CampfireSweepEffect extends AbstractGameEffect
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CampfireSweepEffect");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float DUR = 2.5F;
    private boolean openedScreen = false;
    private boolean openedScreen2 = false;
    private boolean selectedCard = false;
    private boolean selectedSweepCard = false;
    private AbstractCard chosencard;
    private Color screenColor = AbstractDungeon.fadeColor.cpy();
    private CardGroup sweepCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);;

    public CampfireSweepEffect()
    {
        this.duration = 2.5F;
        this.screenColor.a = 0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update()
    {
        Iterator localIterator;
        if (!(AbstractDungeon.isScreenUp)) {
            this.duration -= Gdx.graphics.getDeltaTime();
            updateBlackScreenColor();
        }

        if ((!(this.selectedCard)) && (!(AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())) ) {
            for (localIterator = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); localIterator.hasNext(); ) {
                AbstractSweepCards c = (AbstractSweepCards)localIterator.next();
                final ArrayList<AbstractSweepCards> newCard = c.getOpposite();
                for (localIterator = newCard.iterator(); localIterator.hasNext(); ) {
                    AbstractCard scard = (AbstractCard)localIterator.next();
                    sweepCards.group.add(scard);
                }
                this.chosencard = c;
                //AbstractDungeon.player.masterDeck.removeCard(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.selectedCard = true;
            ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
        }
        if ((this.selectedCard) && (!(AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())) && (!(this.selectedSweepCard)) ) {
            for (localIterator = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); localIterator.hasNext(); ) {
                AbstractSweepCards c = (AbstractSweepCards)localIterator.next();
                CardCrawlGame.metricData.addCampfireChoiceData("SWEEP", c.getMetricID());
                AbstractDungeon.player.masterDeck.removeCard(this.chosencard);
                AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(c, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.selectedSweepCard = true;
            ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
        }

        if ((this.duration < 2F) && (!(this.openedScreen))) {
            this.openedScreen = true;

            AbstractDungeon.gridSelectScreen.open(GetSweepbleCards.getSweepbleCards(), 1, "转换...", false, false, true, true);
        }

        if ((this.duration < 1F) && (this.openedScreen) && (this.selectedCard) && (!(this.openedScreen2))) {
        this.openedScreen2 = true;

        AbstractDungeon.gridSelectScreen.open(sweepCards, 1, "回想起来吧...", false, false, true, true);
        }

        if (this.duration < 0F) {
            this.isDone = true;
            if (CampfireUI.hidden) {
                com.megacrit.cardcrawl.rooms.AbstractRoom.waitTimer = 0F;
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
            }
        }
    }

    private void updateBlackScreenColor()
    {
        if (this.duration > 2F)
            this.screenColor.a = Interpolation.fade.apply(1F, 0F, (this.duration - 2F) * 2F);
        else
            this.screenColor.a = Interpolation.fade.apply(0F, 1F, this.duration / 2.5F);
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);

        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID)
            AbstractDungeon.gridSelectScreen.render(sb);
    }

    //&& (AbstractDungeon.gridSelectScreen.forUpgrade)
}
