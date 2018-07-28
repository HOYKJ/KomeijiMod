package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import Thmod.Orbs.Penglai;

public class PenglaiNingyou extends AbstractSpellCards {
    public static final String ID = "PenglaiNingyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private int pointcost;

    public PenglaiNingyou() {
        super("PenglaiNingyou", PenglaiNingyou.NAME,  2, PenglaiNingyou.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.pointcost = 4;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                int EmptyNum = 0;
                for(int i = 0;i < p.orbs.size();i++){
                    if(p.orbs.get(i) instanceof EmptyOrbSlot)
                        EmptyNum += 1;
                }
                if(EmptyNum > 0) {
                    AbstractOrb orb = new Penglai();
                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                }
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                return true;
            }
        }
        this.cantUseMessage = "我没有足够的P点";
        return false;
    }

    public AbstractCard makeCopy() {
        return new PenglaiNingyou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("PenglaiNingyou");
        NAME = PenglaiNingyou.cardStrings.NAME;
        DESCRIPTION = PenglaiNingyou.cardStrings.DESCRIPTION;
    }
}
