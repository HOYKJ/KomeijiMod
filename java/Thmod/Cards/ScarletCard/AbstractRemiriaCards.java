package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import Thmod.Cards.AbstractSetCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Patches.AbstractCardEnum;
import Thmod.Power.remiria.ScarletLordPower;
import Thmod.ThMod;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;

public abstract class AbstractRemiriaCards extends CustomCard {
    public boolean isPlus = false;
    protected enum AttackType{
        LIGHT, HEAVY, CHAIN
    }
    protected AttackType attackType;
    public List<TooltipInfo> tips = new ArrayList<>();

    public AbstractRemiriaCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final boolean isPlus) {
        super(id, name, RemiriaScarlet.remiriaCardImage(id), cost, description, type, AbstractCardEnum.Remiria, rarity, target);
        if(isPlus){
            this.plusCard();
        }
        else {
            this.normalCard();
        }
        this.attackType = null;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if((this.isPlus) &&(!AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))) {
            this.normalCard();
        }
        if((AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID)) && (this.attackType != null) && (AbstractDungeon.player instanceof RemiriaScarlet) && (((RemiriaScarlet) AbstractDungeon.player).isLordAnimation)){
            switch (this.attackType){
                case LIGHT:
                    ((RemiriaScarlet) AbstractDungeon.player).changeState("N_ATTACK");
                    break;
                case CHAIN:
                    ((RemiriaScarlet) AbstractDungeon.player).changeState("C_ATTACK");
                    break;
                case HEAVY:
                    ((RemiriaScarlet) AbstractDungeon.player).changeState("H_ATTACK");
                    break;
            }
        }
    }

    public void plusCard(){
        if(!this.isPlus) {
            this.isPlus = true;
            this.addTips();
            this.rawDescription += this.tips.get(0).description;
            initializeDescription();
            this.tips.clear();
            String tmp = "";
            switch (this.type) {
                case ATTACK:
                    tmp += "bg_attack_plus";
                    break;
                case POWER:
                    tmp += "bg_power_plus";
                    break;
                case SKILL:
                    tmp += "bg_skill_plus";
                    break;
                default:
                    return;
            }
            tmp += ".png";
            setBackgroundTexture("images/cardui/remiria/512/" + tmp, "images/cardui/remiria/1024/" + tmp);
            setOrbTexture("images/cardui/remiria/512/card_plus_orb.png", "images/cardui/remiria/1024/card_plus_orb.png");
        }
    }

    public void normalCard(){
        if(this.isPlus) {
            this.isPlus = false;
            this.addTips();
            int index = this.rawDescription.lastIndexOf(this.tips.get(0).description);
            if (index > -1) {
                StringBuilder sb = new StringBuilder(this.rawDescription);
                sb.delete(index, index + this.tips.get(0).description.length());
                this.rawDescription = sb.toString();
            }
            initializeDescription();
            String tmp = "";
            switch (this.type) {
                case ATTACK:
                    tmp += "bg_attack_normal";
                    break;
                case POWER:
                    tmp += "bg_power_normal";
                    break;
                case SKILL:
                    tmp += "bg_skill_normal";
                    break;
                default:
                    return;
            }
            tmp += ".png";
            setBackgroundTexture("images/cardui/remiria/512/" + tmp, "images/cardui/remiria/1024/" + tmp);
            setOrbTexture("images/cardui/remiria/512/card_normal_orb.png", "images/cardui/remiria/1024/card_normal_orb.png");
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        ((AbstractRemiriaCards)card).isPlus = this.isPlus;
        return card;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return this.cardPlayable(m) && this.hasEnoughEnergy();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        return this.tips;
    }

    public abstract void addTips();
}
