package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.DeriveCards.ArcherDoll;
import Thmod.Cards.DeriveCards.ShieldDoll;
import Thmod.Cards.DeriveCards.SpearDoll;
import Thmod.Cards.UncommonCards.LittleLegion;
import Thmod.Orbs.NingyouOrb;

public class NingyouSousou extends AbstractSweepCards {
    public static final String ID = "NingyouSousou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public NingyouSousou() {
        super("NingyouSousou", NingyouSousou.NAME,  1, NingyouSousou.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, CardSet_k.ALICE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        boolean hasDoll = false;
        for(AbstractOrb orb : p.orbs){
            if(orb instanceof NingyouOrb){
                hasDoll = true;
                break;
            }
        }
        if(hasDoll){
            final ChooseAction choice = new ChooseAction(this, m, LittleLegion.EXTENDED_DESCRIPTION[0], false, 1);
            choice.add(new SpearDoll(), () -> {
                for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                    if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 1));
                        break;
                    }
                }
            });
            choice.add(new ShieldDoll(), () -> {
                for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                    if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 2));
                        break;
                    }
                }
            });
            choice.add(new ArcherDoll(), () -> {
                for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                    if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 3));
                        break;
                    }
                }
            });
            AbstractDungeon.actionManager.addToBottom(choice);
        }
        else {
            int EmptyNum = 0;
            for (int i = 0; i < p.orbs.size(); i++) {
                if (p.orbs.get(i) instanceof EmptyOrbSlot)
                    EmptyNum += 1;
            }
            if (EmptyNum > 0) {
                AbstractOrb orb = new NingyouOrb();
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouKisou());
        opposite.add(new NingyouFukuhei());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouSousou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouSousou");
        NAME = NingyouSousou.cardStrings.NAME;
        DESCRIPTION = NingyouSousou.cardStrings.DESCRIPTION;
    }
}
