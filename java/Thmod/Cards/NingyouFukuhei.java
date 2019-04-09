package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
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

import Thmod.Actions.common.AddNewOrbAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.DeriveCards.ArcherDoll;
import Thmod.Cards.DeriveCards.ShieldDoll;
import Thmod.Cards.DeriveCards.SpearDoll;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;

public class NingyouFukuhei extends AbstractSweepCards {
    public static final String ID = "NingyouFukuhei";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public NingyouFukuhei() {
        super("NingyouFukuhei", NingyouFukuhei.NAME,  1, NingyouFukuhei.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, CardSet_k.ALICE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ChooseAction choice = new ChooseAction(this, m, NingyouFukuhei.EXTENDED_DESCRIPTION[0],false, 1);
        choice.add(new SpearDoll(), () -> {
            int EmptyNum = 0;
            for(int i = 0;i < p.orbs.size();i++){
                if(p.orbs.get(i) instanceof EmptyOrbSlot)
                    EmptyNum += 1;
            }
            if(EmptyNum > 0) {
                AbstractOrb orb = new YariNingyou();
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new AddNewOrbAction(this, NingyouFukuhei.EXTENDED_DESCRIPTION[0],1));
            }
        });
        choice.add(new ShieldDoll(), () -> {
            int EmptyNum = 0;
            for(int i = 0;i < p.orbs.size();i++){
                if(p.orbs.get(i) instanceof EmptyOrbSlot)
                    EmptyNum += 1;
            }
            if(EmptyNum > 0) {
                AbstractOrb orb = new TateNingyou();
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new AddNewOrbAction(this, NingyouFukuhei.EXTENDED_DESCRIPTION[0],2));
            }
        });
        choice.add(new ArcherDoll(), () -> {
            int EmptyNum = 0;
            for(int i = 0;i < p.orbs.size();i++){
                if(p.orbs.get(i) instanceof EmptyOrbSlot)
                    EmptyNum += 1;
            }
            if(EmptyNum > 0) {
                AbstractOrb orb = new YumiNingyou();
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new AddNewOrbAction(this, NingyouFukuhei.EXTENDED_DESCRIPTION[0],3));
            }
        });
        AbstractDungeon.actionManager.addToBottom(choice);

        final ChooseAction choice2 = new ChooseAction(this, m, NingyouFukuhei.EXTENDED_DESCRIPTION[1],false, 1);
        choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2],NingyouFukuhei.EXTENDED_DESCRIPTION[3], () -> {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.discardPile));
        });
        choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2],NingyouFukuhei.EXTENDED_DESCRIPTION[4], () -> {

        });
        AbstractDungeon.actionManager.addToBottom(choice2);
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouSousou());
        opposite.add(new NingyouKisou());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouFukuhei();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouFukuhei");
        NAME = NingyouFukuhei.cardStrings.NAME;
        DESCRIPTION = NingyouFukuhei.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NingyouFukuhei.cardStrings.EXTENDED_DESCRIPTION;
    }
}
