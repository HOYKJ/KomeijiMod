package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.DeriveCards.ArcherDoll;
import Thmod.Cards.DeriveCards.HeLanDoll;
import Thmod.Cards.DeriveCards.NormalDoll;
import Thmod.Cards.DeriveCards.PengLaiDoll;
import Thmod.Cards.DeriveCards.ShangHaiDoll;
import Thmod.Cards.DeriveCards.ShieldDoll;
import Thmod.Cards.DeriveCards.SpearDoll;
import Thmod.Cards.NingyouShinki;
import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;
import basemod.helpers.TooltipInfo;

public class ArtfulChanter extends AbstractKomeijiCards {
    public static final String ID = "ArtfulChanter";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public ArtfulChanter() {
        super("ArtfulChanter", ArtfulChanter.NAME,  0, ArtfulChanter.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, CardSet_k.ALICE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean hasNingyou = false;
        for(AbstractOrb orb : p.orbs){
            if(!(orb instanceof EmptyOrbSlot)){
                hasNingyou = true;
                break;
            }
        }
        if(hasNingyou) {
            final ChooseAction choice = new ChooseAction(this, m, ArtfulChanter.EXTENDED_DESCRIPTION[0], false, 1);
            for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                final ArrayList<Integer> orbnum = new ArrayList<>();
                orbnum.clear();
                orbnum.add(i);
                if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                    choice.add(new NormalDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), true));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof YariNingyou) {
                    choice.add(new SpearDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), false));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof TateNingyou) {
                    choice.add(new ShieldDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), false));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof YumiNingyou) {
                    choice.add(new ArcherDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), false));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof Shanghai) {
                    choice.add(new ShangHaiDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), false));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof Penglai) {
                    choice.add(new PengLaiDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), false));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
                    });
                }
                if (AbstractDungeon.player.orbs.get(i) instanceof Helan) {
                    choice.add(new HeLanDoll(), () -> {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0), false));
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
                    });
                }
            }
            AbstractDungeon.actionManager.addToBottom(choice);
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
        return tips;
    }

    public AbstractCard makeCopy() {
        return new ArtfulChanter();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ArtfulChanter");
        NAME = ArtfulChanter.cardStrings.NAME;
        DESCRIPTION = ArtfulChanter.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = ArtfulChanter.cardStrings.EXTENDED_DESCRIPTION;
    }
}
