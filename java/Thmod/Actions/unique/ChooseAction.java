package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ChooseAction extends AbstractGameAction
{
    public static final Logger logger;
    AbstractCard baseCard;
    AbstractMonster target;
    CardGroup choices;
    ArrayList<Runnable> actions;
    String message;
    boolean canCancel;
    int chooseNum;

    public ChooseAction(final AbstractCard baseCard, final AbstractMonster target, final String message,final boolean canCancel,final int chooceNum) {
        this.choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.actions = new ArrayList<>();
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, 1);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.baseCard = baseCard;
        this.message = message;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.canCancel = canCancel;
        this.chooseNum = chooceNum;
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

    public void update() {
        if (this.choices.isEmpty()) {
            this.tickDuration();
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
                    ChooseAction.logger.info("Picked option: " + i.get(0));
                    this.actions.get(i.get(0)).run();
                    pick.clear();
                    i.clear();
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
            this.tickDuration();
            return;
        }
        if (this.choices.size() > 1) {
            AbstractDungeon.gridSelectScreen.open(this.choices, this.chooseNum, this.canCancel,this.message);
            this.tickDuration();
            return;
        }
//        for(int i = 0;i < this.actions.size();i++)
            this.actions.get(0).run();
        this.tickDuration();
        this.isDone = true;
    }

    static {
        logger = LogManager.getLogger("yeah");
    }
}
