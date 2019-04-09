package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.ArrayList;
import java.util.List;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.DeriveCards.ArcherDoll;
import Thmod.Cards.DeriveCards.HeLanDoll;
import Thmod.Cards.DeriveCards.NormalDoll;
import Thmod.Cards.DeriveCards.PengLaiDoll;
import Thmod.Cards.DeriveCards.ShangHaiDoll;
import Thmod.Cards.DeriveCards.ShieldDoll;
import Thmod.Cards.DeriveCards.SpearDoll;
import Thmod.Cards.NingyouFukuhei;
import Thmod.Cards.NingyouShinki;
import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;
import basemod.helpers.TooltipInfo;

public class NingyouYunhei extends AbstractSweepCards {
    public static final String ID = "NingyouYunhei";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public NingyouYunhei() {
        super("NingyouYunhei", NingyouYunhei.NAME,  1, NingyouYunhei.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, CardSet_k.ALICE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 0;
    }

    public void applyPowers()
    {
        this.baseDamage = 0;
        for(AbstractOrb orb : AbstractDungeon.player.orbs){
            if(!(orb instanceof EmptyOrbSlot)){
                if(orb instanceof NingyouOrb){
                    this.baseDamage += 4;
                }
                else if((orb instanceof YariNingyou) || (orb instanceof TateNingyou) || (orb instanceof YumiNingyou)){
                    this.baseDamage += 6;
                }
                else if((orb instanceof Shanghai) || (orb instanceof Penglai) || (orb instanceof Helan)){
                    this.baseDamage += 9;
                }
            }
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseDamage = 0;
        for(AbstractOrb orb : AbstractDungeon.player.orbs){
            if(!(orb instanceof EmptyOrbSlot)){
                if(orb instanceof NingyouOrb){
                    this.baseDamage += 4;
                }
                else if((orb instanceof YariNingyou) || (orb instanceof TateNingyou) || (orb instanceof YumiNingyou)){
                    this.baseDamage += 6;
                }
                else if((orb instanceof Shanghai) || (orb instanceof Penglai) || (orb instanceof Helan)){
                    this.baseDamage += 9;
                }
            }
        }
        super.applyPowers();
        super.calculateCardDamage(mo);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        boolean hasNingyou = false;
        for(AbstractOrb orb : p.orbs){
            if(!(orb instanceof EmptyOrbSlot)){
                hasNingyou = true;
                break;
            }
        }
        if(hasNingyou) {
            final ChooseAction choice = new ChooseAction(this, m, NingyouYunhei.EXTENDED_DESCRIPTION[0], true, this.magicNumber);
            for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                final ArrayList<Integer> orbnum = new ArrayList<>();
                orbnum.clear();
                orbnum.add(i);
                if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                    choice.add(new NormalDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), 3));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof YariNingyou) {
                    choice.add(new SpearDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), 2));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof TateNingyou) {
                    choice.add(new ShieldDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), 2));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof YumiNingyou) {
                    choice.add(new ArcherDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), 2));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof Shanghai) {
                    choice.add(new ShangHaiDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), 2));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof Penglai) {
                    choice.add(new PengLaiDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), 2));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof Helan) {
                    choice.add(new HeLanDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), 2));
                    });
                }
            }
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            AbstractDungeon.actionManager.addToBottom(choice);
        }
        else {
            AbstractOrb orb = new NingyouOrb();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        }

//        final ChooseAction choice2 = new ChooseAction(this, m, NingyouFukuhei.EXTENDED_DESCRIPTION[1],false, 1);
//        choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2],NingyouFukuhei.EXTENDED_DESCRIPTION[3], () -> {
//            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.discardPile));
//        });
//        choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2],NingyouFukuhei.EXTENDED_DESCRIPTION[4], () -> {
//
//        });
//        AbstractDungeon.actionManager.addToBottom(choice2);
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouOkisou());
        opposite.add(new NingyouChiyari());
        return opposite;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
        tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
        return tips;
    }

    public AbstractCard makeCopy() {
        return new NingyouYunhei();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouYunhei");
        NAME = NingyouYunhei.cardStrings.NAME;
        DESCRIPTION = NingyouYunhei.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NingyouYunhei.cardStrings.EXTENDED_DESCRIPTION;
    }
}
