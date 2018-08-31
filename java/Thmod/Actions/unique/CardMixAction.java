package Thmod.Actions.unique;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import Thmod.Cards.DeriveCards.MixedItem;
import Thmod.Utils;
import basemod.DevConsole;

public class CardMixAction extends AbstractGameAction
{
    public static final Logger logger;
    private AbstractCard baseCard;
    private CardGroup choices;
    private CardGroup beroreChoices2;
    private CardGroup choices2;
    private CardGroup choices3;
    private int groupNums = 0;
    private boolean choose2Done = false;
    private boolean choose3Done = false;
    ArrayList<Runnable> actions;
    private String message;
    private ArrayList<AbstractCard> pick = new ArrayList<>();
    private ArrayList<Integer> i = new ArrayList<>();

    public CardMixAction(final AbstractCard baseCard, final String message) {
        this.choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.beroreChoices2 = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.choices2 = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.choices3 = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.actions = new ArrayList<>();
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, 1);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.baseCard = baseCard;
        this.message = message;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void add(final String name, final String description, final Runnable action) {
        final AbstractCard choice = this.baseCard.makeStatEquivalentCopy();
        choice.name = name;
        choice.rawDescription = description;
        choice.initializeDescription();
        this.groupNums += 1;
        if(this.groupNums <= 3)
            this.choices.addToTop(choice);
        else
            this.beroreChoices2.addToTop(choice);
        this.actions.add(action);
        DevConsole.logger.info("beroreChoices2Count" + this.beroreChoices2.size());
    }

    public void update() {
        if(pick.size() == 3){
            this.isDone = true;
            return;
        }
        if (this.duration != Settings.ACTION_DUR_FASTER) {
            if (AbstractDungeon.cardRewardScreen.codexCard != null) {
                pick.add(AbstractDungeon.cardRewardScreen.codexCard);
                AbstractDungeon.cardRewardScreen.codexCard = null;
                if(i.size() == 0) {
                    i.add(this.choices.group.indexOf(pick.get(0)));
                    this.choices.clear();
                    switch (i.get(0)){
                        case 0:
                            ArrayList<Integer> roll = new ArrayList<>();
                            int count = 0;
                            while(count < 6) {
                                int num = MathUtils.random(9);
                                boolean flag = true;
                                for (int j = 0; j < roll.size(); j++) {
                                    if(num == roll.get(j)){
                                        flag = false;
                                        break;
                                    }
                                }
                                if(flag){
                                    roll.add(num);
                                    count++;
                                }
                            }
                            for(int n = 0;n < 6;n++) {
                                if(n < 3) {
                                    this.choices2.addToTop(this.beroreChoices2.group.get(roll.get(n)));
                                }
                                else {
                                    this.choices3.addToTop(this.beroreChoices2.group.get(roll.get(n)));
                                }
                            }
                            break;
                        case 1:
                            roll = new ArrayList<>();
                            count = 0;
                            while(count < 6) {
                                int num = MathUtils.random(9,23);
                                boolean flag = true;
                                for (int j = 0; j < roll.size(); j++) {
                                    if(num == roll.get(j)){
                                        flag = false;

                                        break;
                                    }
                                }
                                if(flag){
                                    roll.add(num);
                                    count++;
                                }
                            }
                            for(int n = 0;n < 6;n++) {
                                if(n < 3) {
                                    this.choices2.addToTop(this.beroreChoices2.group.get(roll.get(n)));
                                }
                                else {
                                    this.choices3.addToTop(this.beroreChoices2.group.get(roll.get(n)));
                                }
                            }
                            break;
                        case 2:
                            roll = new ArrayList<>();
                            count = 0;
                            while(count < 6) {
                                int num = MathUtils.random(23,36);
                                boolean flag = true;
                                for (int j = 0; j < roll.size(); j++) {
                                    if(num == roll.get(j)){
                                        flag = false;
                                        break;
                                    }
                                }
                                if(flag){
                                    roll.add(num);
                                    count++;
                                }
                            }
                            for(int n = 0;n < 6;n++) {
                                if(n < 3) {
                                    this.choices2.addToTop(this.beroreChoices2.group.get(roll.get(n)));
                                }
                                else {
                                    this.choices3.addToTop(this.beroreChoices2.group.get(roll.get(n)));
                                }
                            }
                            break;
                    }
                }
                else if(i.size() == 1) {
                    i.add((this.beroreChoices2.group.indexOf(pick.get(1)) + 3));
                    this.choose2Done = true;
                }
                else {
                    i.add((this.beroreChoices2.group.indexOf(pick.get(2)) + 3));
                    this.choose3Done = true;
                }
                if(i.size() == 3) {
                    DevConsole.logger.info("actNum1" + i.get(1));
                    DevConsole.logger.info("actNum2" + i.get(2));
//                    this.actions.get(i.get(1)).run();
//                    this.actions.get(i.get(2)).run();
                    AbstractCard mixCard = new MixedItem(this.actions.get(i.get(1)),this.actions.get(i.get(2)),this.actions.get(i.get(0)));
                    mixCard.rawDescription += this.choices2.group.get(this.choices2.group.indexOf(pick.get(1))).rawDescription;
                    mixCard.rawDescription += this.choices3.group.get(this.choices3.group.indexOf(pick.get(2))).rawDescription;
                    mixCard.initializeDescription();
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(mixCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, true, true));
                    i.clear();
                    pick.clear();
                    actions.clear();
                    this.tickDuration();
                    return;
                }
                else
                    this.duration = Settings.ACTION_DUR_FASTER;

                AbstractDungeon.gridSelectScreen.selectedCards.clear();

            }

        }
        if (this.choices.size() > 1) {
            Utils.openCardRewardsScreen(this.choices.group, false,2);
            this.tickDuration();
            return;
        }
        else if(!(this.choose2Done)){
            Utils.openCardRewardsScreen(this.choices2.group, false,3);
            this.tickDuration();
            return;
        }
        else if(!(this.choose3Done)){
            Utils.openCardRewardsScreen(this.choices3.group,false,3);
            this.tickDuration();
            return;
        }
        this.tickDuration();
        this.isDone = true;
    }

    static {
        logger = LogManager.getLogger("yeah");
    }
}
