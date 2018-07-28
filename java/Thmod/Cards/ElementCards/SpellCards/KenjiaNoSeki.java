package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.MoveOrbAction;
import Thmod.Orbs.ElementOrb.KenjiaOrb.AbstractKenjiaOrb;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaEarth;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaFire;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaMetal;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaWater;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaWood;
import Thmod.Power.KenjiaPower;

public class KenjiaNoSeki extends AbstractElementSpellCards {
    public static final String ID = "KenjiaNoSeki";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public KenjiaNoSeki() {
        super("KenjiaNoSeki", KenjiaNoSeki.NAME,  3, KenjiaNoSeki.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF,true);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new MoveOrbAction());
        AbstractKenjiaOrb orbToGet = new KenjiaFire();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaWood();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaMetal();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaEarth();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaWater();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new KenjiaPower(p)));
    }

    public AbstractCard makeCopy() {
        return new KenjiaNoSeki();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("KenjiaNoSeki");
        NAME = KenjiaNoSeki.cardStrings.NAME;
        DESCRIPTION = KenjiaNoSeki.cardStrings.DESCRIPTION;
    }
}
