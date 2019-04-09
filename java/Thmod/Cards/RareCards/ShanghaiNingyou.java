package Thmod.Cards.RareCards;

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

import Thmod.Actions.common.AddNewOrbAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.NingyouFukuhei;
import Thmod.Orbs.Shanghai;

public class ShanghaiNingyou extends AbstractKomeijiCards {
    public static final String ID = "ShanghaiNingyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 2;

    public ShanghaiNingyou() {
        super("ShanghaiNingyou", ShanghaiNingyou.NAME,  2, ShanghaiNingyou.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, CardSet_k.ALICE);
        this.isEthereal = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int EmptyNum = 0;
        for(int i = 0;i < p.orbs.size();i++){
            if(p.orbs.get(i) instanceof EmptyOrbSlot)
                EmptyNum += 1;
        }
        if(EmptyNum > 0) {
            AbstractOrb orb = new Shanghai();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new AddNewOrbAction(this, NingyouFukuhei.EXTENDED_DESCRIPTION[0],4));
        }

        if(this.upgraded) {
            final ChooseAction choice2 = new ChooseAction(this, m, NingyouFukuhei.EXTENDED_DESCRIPTION[1], false, 1);
            choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2], NingyouFukuhei.EXTENDED_DESCRIPTION[3], () -> {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.discardPile));
            });
            choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2], NingyouFukuhei.EXTENDED_DESCRIPTION[4], () -> {

            });
            AbstractDungeon.actionManager.addToBottom(choice2);
        }
    }

    public AbstractCard makeCopy() {
        return new ShanghaiNingyou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isEthereal = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ShanghaiNingyou");
        NAME = ShanghaiNingyou.cardStrings.NAME;
        DESCRIPTION = ShanghaiNingyou.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = ShanghaiNingyou.cardStrings.UPGRADE_DESCRIPTION;
    }
}
