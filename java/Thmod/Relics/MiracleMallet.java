package Thmod.Relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Collections;

import Thmod.Actions.unique.PlayerTalkAction;

public class MiracleMallet extends AbstractThRelic {
    public static final String ID = "MiracleMallet";
    private boolean used;

    public MiracleMallet()
    {
        super("MiracleMallet",  RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.used = false;
    }

    public void atPreBattle() {
        beginPulse();
        //this.used = false;
        this.pulse = true;
    }

    protected  void onRightClick(){
        if(AbstractDungeon.currMapNode != null) {
            if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                ArrayList<AbstractCard> cardGroup = new ArrayList<>();
                cardGroup.addAll(AbstractDungeon.player.drawPile.group);
                cardGroup.addAll(AbstractDungeon.player.hand.group);
                cardGroup.addAll(AbstractDungeon.player.discardPile.group);
                cardGroup = whatCards(cardGroup);
                Collections.shuffle(cardGroup, new java.util.Random(AbstractDungeon.miscRng.randomLong()));
                if (!used) {
                    if (!cardGroup.isEmpty()) {
                        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        malletCard(cardGroup.get(0));
                        AbstractDungeon.effectList.add(new ShowCardBrieflyEffect((cardGroup.get(0)).makeStatEquivalentCopy()));
                    } else {
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[3]));
                    }
                } else {
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[4]));
                }
            }
        }
    }

    private ArrayList<AbstractCard> whatCards(ArrayList<AbstractCard> cards){
        ArrayList<AbstractCard> newCards = (ArrayList<AbstractCard>)cards.clone();
        for(AbstractCard c:cards){
            boolean can = false;
            if(c.baseDamage >= 2){
                can = true;
            }
            if(c.baseBlock >= 2){
                can = true;
            }
            if(c.baseMagicNumber >= 2){
                can = true;
            }
            if(c.cost > 0){
                can = true;
            }
            if(!can){
                newCards.remove(c);
            }
        }
        return newCards;
    }

    private void malletCard(AbstractCard c){
        boolean did = false;
        if(c.baseDamage >= 2){
            c.baseDamage += ((float)c.baseDamage * 0.5);
            c.isDamageModified = true;
            did = true;
        }
        if(c.baseBlock >= 2){
            c.baseBlock += ((float)c.baseBlock * 0.5);
            c.isBlockModified = true;
            did = true;
        }
        if(c.baseMagicNumber >= 2){
            c.baseMagicNumber += ((float)c.baseMagicNumber * 0.5);
            c.magicNumber += ((float)c.magicNumber * 0.5);
            c.isMagicNumberModified = true;
            did = true;
        }
        if(!did){
            c.modifyCostForCombat(-1);
        }
        c.name += "↑";
        if((!c.exhaust) && (!c.purgeOnUse)){
            c.exhaust = true;
            c.rawDescription += DESCRIPTIONS[1];
        }
        c.initializeDescription();
        this.used = true;
        this.pulse = false;
    }

    @Override
    public void onVictory() {
        super.onVictory();
        this.used = false;
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new MiracleMallet();
    }
}

