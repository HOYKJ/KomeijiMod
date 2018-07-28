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

import Thmod.Cards.AbstractKomeijiCards;
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
    private static final int COST = 2;

    public ElementInvoke() {
        super("ElementInvoke", ElementInvoke.NAME,  2, ElementInvoke.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.isEthereal = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = 0;i < p.maxOrbs;i++){
            if(p.orbs.get(i) instanceof EmptyOrbSlot){
                int roll = MathUtils.random(11);
                switch (roll){
                    case 0: case 7:
                        AbstractOrb orb = new EarthOrb();
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                        break;
                    case 1: case 8:
                        orb = new FireOrb();
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                        break;
                    case 2:
                        orb = new LunaOrb();
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                        break;
                    case 3: case 9:
                        orb = new MetalOrb();
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                        break;
                    case 4:
                        orb = new SunOrb();
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                        break;
                    case 5: case 10:
                        orb = new WaterOrb();
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                        break;
                    case 6: case 11:
                        orb = new WoodOrb();
                        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
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
            this.upgradeDamage(3);
            this.isEthereal = false;
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
