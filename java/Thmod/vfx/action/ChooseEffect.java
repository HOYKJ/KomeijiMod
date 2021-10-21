package Thmod.vfx.action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import Thmod.Actions.unique.ChooseAction;
import Thmod.ThMod;

public class ChooseEffect extends AbstractGameEffect
{
    private AbstractCard baseCard;
    private AbstractMonster target;
    private CardGroup choices;
    private ArrayList<Runnable> actions;
    private String message;
    private boolean canCancel;
    private int chooseNum;
    private boolean oneAndCancel;

    public ChooseEffect(final AbstractCard baseCard, final AbstractMonster target, final String message,final boolean canCancel,final int chooceNum) {
        this.choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.actions = new ArrayList<>();
        this.baseCard = baseCard;
        this.message = message;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.canCancel = canCancel;
        this.chooseNum = chooceNum;
        this.target = target;
        this.oneAndCancel = false;
    }

    public ChooseEffect(final AbstractCard baseCard, final AbstractMonster target, final String message,final boolean canCancel,final int chooceNum, final boolean oneAndCancel) {
        this.choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.actions = new ArrayList<>();
        this.baseCard = baseCard;
        this.message = message;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.canCancel = canCancel;
        this.chooseNum = chooceNum;
        this.target = target;
        this.oneAndCancel = oneAndCancel;
    }

    public void add(final String name, final String description, final Runnable action) {
        final AbstractCard choice = this.baseCard.makeStatEquivalentCopy();
        choice.name = name;
        choice.rawDescription = description;
        choice.initializeDescription();
        if (this.target != null) {
            choice.calculateCardDamage(this.target);
        }
        else {
            choice.applyPowers();
        }
        this.choices.addToTop(choice);
        this.actions.add(action);
    }

    public void add(final AbstractCard card, final Runnable action) {
        final AbstractCard choice = card.makeStatEquivalentCopy();
        if (this.target != null) {
            choice.calculateCardDamage(this.target);
        }
        else {
            choice.applyPowers();
        }
        this.choices.addToTop(choice);
        this.actions.add(action);
    }

    public void update() {
        if (this.choices.isEmpty()) {
            this.duration -= Gdx.graphics.getDeltaTime();
            this.isDone = true;
            return;
        }
        if (this.duration != Settings.ACTION_DUR_FASTER) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (int i1 = 0; i1 < AbstractDungeon.gridSelectScreen.selectedCards.size(); i1++) {
                    final ArrayList<AbstractCard> pick = new ArrayList<>();
                    pick.add(AbstractDungeon.gridSelectScreen.selectedCards.get(i1)) ;
                    final ArrayList<Integer> i = new ArrayList<>();
                    i.add(this.choices.group.indexOf(pick.get(0))) ;
                    this.actions.get(i.get(0)).run();
                    pick.clear();
                    i.clear();
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.isDone = true;
            }
            //this.tickDuration();
            return;
        }
        if(!this.oneAndCancel) {
            if (this.choices.size() > 1) {
                //AbstractDungeon.gridSelectScreen.open(this.choices, this.chooseNum,this.message,false, false, this.canCancel, false);
                if (this.canCancel) {
                    AbstractDungeon.gridSelectScreen.open(this.choices, this.chooseNum, true, this.message);
                } else {
                    AbstractDungeon.gridSelectScreen.open(this.choices, this.chooseNum, this.message, false, false, true, false);
                }
                this.duration -= Gdx.graphics.getDeltaTime();
            }
            else {
                this.actions.get(0).run();
                this.isDone = true;
            }
        }
        else {
            if (this.choices.size() > 0) {
                //AbstractDungeon.gridSelectScreen.open(this.choices, this.chooseNum,this.message,false, false, this.canCancel, false);
                if (this.canCancel) {
                    AbstractDungeon.gridSelectScreen.open(this.choices, this.chooseNum, true, this.message);
                } else {
                    AbstractDungeon.gridSelectScreen.open(this.choices, this.chooseNum, this.message, false, false, true, false);
                }
                this.duration -= Gdx.graphics.getDeltaTime();
            }
        }
//        for(int i = 0;i < this.actions.size();i++)
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
