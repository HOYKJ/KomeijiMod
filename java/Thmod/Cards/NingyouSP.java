package Thmod.Cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Orbs.NingyouOrb;
import basemod.DevConsole;

public class NingyouSP extends AbstractSweepCards {
    public static final String ID = "NingyouSP";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public NingyouSP() {
        super("NingyouSP", NingyouSP.NAME,  1, NingyouSP.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int EmptyNum = 0;
        for(int i = 0;i < p.orbs.size();i++){
            if(p.orbs.get(i) instanceof EmptyOrbSlot)
                EmptyNum += 1;
        }
        if(EmptyNum > 0) {
            int min = Math.min(this.magicNumber, EmptyNum);
            for(int i = 0;i < min;i++) {
                AbstractOrb orb = new NingyouOrb();
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
            }
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouShinki());
        opposite.add(new SeekerWire());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouSP();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouSP");
        NAME = NingyouSP.cardStrings.NAME;
        DESCRIPTION = NingyouSP.cardStrings.DESCRIPTION;
    }
}
