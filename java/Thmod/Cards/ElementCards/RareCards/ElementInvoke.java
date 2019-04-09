package Thmod.Cards.ElementCards.RareCards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import Thmod.Actions.unique.ResonateAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import Thmod.Orbs.ElementOrb.EarthOrb;
import Thmod.Orbs.ElementOrb.FireOrb;
import Thmod.Orbs.ElementOrb.LunaOrb;
import Thmod.Orbs.ElementOrb.MetalOrb;
import Thmod.Orbs.ElementOrb.SunOrb;
import Thmod.Orbs.ElementOrb.WaterOrb;
import Thmod.Orbs.ElementOrb.WoodOrb;

public class ElementInvoke extends AbstractKomeijiCards {
    public static final String ID = "ElementInvoke";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 1;

    public ElementInvoke() {
        super("ElementInvoke", ElementInvoke.NAME,  0, ElementInvoke.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, CardSet_k.OTHER);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int tem = this.magicNumber;
        for (AbstractOrb orb : p.orbs) {
            if (orb instanceof AbstractElementOrb) {
                if(tem > 0){
                    AbstractDungeon.actionManager.addToBottom(new ResonateAction((AbstractElementOrb) orb, true));
                }
                else {
                    break;
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        return new ElementInvoke();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ElementInvoke");
        NAME = ElementInvoke.cardStrings.NAME;
        DESCRIPTION = ElementInvoke.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = ElementInvoke.cardStrings.UPGRADE_DESCRIPTION;
    }
}
