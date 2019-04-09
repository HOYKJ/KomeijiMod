package Thmod.Actions.unique;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
import java.util.HashMap;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.UncommonCards.SenseofElegance;
import Thmod.ThMod;
import Thmod.Utils;
import basemod.helpers.TooltipInfo;

public class UpgradeEffectChoose extends AbstractGameEffect {
    private AbstractKomeijiCards cardToUpgrade;
    private HashMap<String, Integer> upgradeCode = new HashMap<>();
    private CardGroup upgradeEffect = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private int amount;
    private boolean canContinue = false;

    public UpgradeEffectChoose(AbstractKomeijiCards card) {
        String[] EXTENDED_DESCRIPTION;
        this.cardToUpgrade = card;
        this.duration = 0.5f;

        EXTENDED_DESCRIPTION = SenseofElegance.EXTENDED_DESCRIPTION;
//        for(int i = 1; i * 2 < EXTENDED_DESCRIPTION.length; i++){
//            upgradeCode.put(EXTENDED_DESCRIPTION[i * 2 - 1], i - 1);
//        }
        for (int i : ((SenseofElegance) this.cardToUpgrade).remainEffect) {
            upgradeCode.put(EXTENDED_DESCRIPTION[i * 2 + 2], i);
        }

        this.amount = 1;
        addUpgradeEffect(EXTENDED_DESCRIPTION);
    }

    public void update() {
        if (this.duration == 0.5f) {
            if (AbstractDungeon.cardRewardScreen.codexCard != null) {
                final AbstractCard c = AbstractDungeon.cardRewardScreen.codexCard.makeStatEquivalentCopy();
                ThMod.logger.info("DO EFFECT CHOOSE " + upgradeCode.get(c.name));

                if(!(c instanceof SenseofElegance)){
                    this.isDone = true;
                }

                ((SenseofElegance) this.cardToUpgrade).extraEffect[upgradeCode.get(c.name)] = 1;
                ((SenseofElegance) this.cardToUpgrade).remainEffect.remove(upgradeCode.get(c.name));
                AbstractDungeon.cardRewardScreen.codexCard = null;
                canContinue = true;
                if(upgradeCode.get(c.name) <= 4) {
                    ((SenseofElegance) this.cardToUpgrade).baseDamage += 8;
                }
                ((SenseofElegance) this.cardToUpgrade).tips.add(new TooltipInfo(SenseofElegance.EXTENDED_DESCRIPTION[upgradeCode.get(c.name) * 2 + 2],
                        SenseofElegance.EXTENDED_DESCRIPTION[upgradeCode.get(c.name) * 2 + 3]));
                //((SenseofElegance) this.cardToUpgrade).addDescription();
            }
            if (this.amount > 0) {
                --this.amount;
                ArrayList<AbstractCard> canChooseEffect;
                if(this.allEffectToChoose().group.size() >= 3) {
                    canChooseEffect = getRandomSpellCards(3);
                }
                else {
                    canChooseEffect = getRandomSpellCards(this.allEffectToChoose().group.size());
                }
                Utils.openCardRewardsScreen(canChooseEffect, true, SenseofElegance.EXTENDED_DESCRIPTION[0]);
                return;
            }
            if(!canContinue){
                return;
            }
        }
        this.isDone = true;
    }

    private ArrayList<AbstractCard> getRandomSpellCards(final int amount) {
        final ArrayList<AbstractCard> cards = new ArrayList<>();
        while (cards.size() < amount) {
            final AbstractCard card = getRandomEffect();
            if (!cards.contains(card)) {
                cards.add(card);
            }
        }
        return cards;
    }

    private AbstractCard getRandomEffect() {
        return allEffectToChoose().getRandomCard(true);
    }

    private CardGroup allEffectToChoose() {
        return this.upgradeEffect;
    }

    private void addUpgradeEffect(String[] EXTENDED_DESCRIPTION){
        for (int i : ((SenseofElegance) this.cardToUpgrade).remainEffect) {
            final SenseofElegance choice = (SenseofElegance)this.cardToUpgrade.makeCopy();
            choice.name = EXTENDED_DESCRIPTION[i * 2 + 2];
            choice.rawDescription = EXTENDED_DESCRIPTION[i * 2 + 3];
            if (i <= 4) {
                choice.rawDescription += EXTENDED_DESCRIPTION[26];
            }
            choice.initializeDescription();
            choice.isChoosing = true;
            this.upgradeEffect.addToTop(choice);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
