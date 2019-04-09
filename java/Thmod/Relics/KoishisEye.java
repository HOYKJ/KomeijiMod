package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

import Thmod.Cards.Defend_Koishi;
import Thmod.Cards.Defend_Komeiji;
import Thmod.Cards.DeriveCards.EasterEgg.ImClosing;
import Thmod.Cards.RareCards.SubterraneanRose;
import Thmod.Cards.RareCards.TerribleSouvenir;
import Thmod.Cards.Strike_Koishi;
import Thmod.Cards.Strike_Komeiji;

public class KoishisEye extends AbstractThRelic {
    public static final String ID = "KoishisEye";
    private ArrayList<AbstractCard> removeCard = new ArrayList<>();
    private ArrayList<AbstractCard> addCard = new ArrayList<>();

    public KoishisEye()
    {
        super("KoishisEye",  RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public void onEquip()
    {
        Settings.hideCombatElements = true;
        AbstractDungeon.player.state.setAnimation(0,"Closed",true);
        UnlockTracker.unlockCard(ImClosing.ID);
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group){
            if(card instanceof Strike_Komeiji){
                AbstractCard c = new Strike_Koishi();
                if(card.upgraded){
                    c.upgrade();
                }
                this.addCard.add(c);
                this.removeCard.add(card);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
            else if(card instanceof Defend_Komeiji){
                AbstractCard c = new Defend_Koishi();
                if(card.upgraded){
                    c.upgrade();
                }
                this.addCard.add(c);
                this.removeCard.add(card);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
            else if(card instanceof TerribleSouvenir){
                AbstractCard c = new SubterraneanRose();
                if(card.upgraded){
                    c.upgrade();
                }
                this.addCard.add(c);
                this.removeCard.add(card);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }
        if(this.removeCard.size() > 0){
            for (AbstractCard card : this.removeCard){
                AbstractDungeon.player.masterDeck.group.remove(card);
            }
            AbstractDungeon.player.masterDeck.group.addAll(this.addCard);
            this.addCard.clear();
            this.removeCard.clear();
        }
        UnlockTracker.unlockCard(ImClosing.ID);
    }

    public void onUnequip(){
        Settings.hideCombatElements = false;
        AbstractDungeon.player.state.setAnimation(0,"Normal",true);
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group){
            if(card instanceof Strike_Koishi){
                AbstractCard c = new Strike_Komeiji();
                if(card.upgraded){
                    c.upgrade();
                }
                this.addCard.add(c);
                this.removeCard.add(card);
            }
            else if(card instanceof Defend_Koishi){
                AbstractCard c = new Defend_Komeiji();
                if(card.upgraded){
                    c.upgrade();
                }
                this.addCard.add(c);
                this.removeCard.add(card);
            }
            else if(card instanceof SubterraneanRose){
                AbstractCard c = new TerribleSouvenir();
                if(card.upgraded){
                    c.upgrade();
                }
                this.addCard.add(c);
                this.removeCard.add(card);
            }
        }
        if(this.removeCard.size() > 0){
            for (AbstractCard card : this.removeCard){
                AbstractDungeon.player.masterDeck.group.remove(card);
            }
            AbstractDungeon.player.masterDeck.group.addAll(this.addCard);
            this.addCard.clear();
            this.removeCard.clear();
        }
    }

    public void atPreBattle() { /* compiled code */ }

    public void atBattleStart() {
        if(!(Settings.hideCombatElements))
            Settings.hideCombatElements = true;
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,5),5));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,5),5));
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new KoishisEye();
    }
}
