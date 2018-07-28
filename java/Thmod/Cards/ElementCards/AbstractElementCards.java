package Thmod.Cards.ElementCards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.common.MoveOrbAction;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import Thmod.Orbs.ElementOrb.EarthOrb;
import Thmod.Orbs.ElementOrb.FireOrb;
import Thmod.Orbs.ElementOrb.KenjiaOrb.AbstractKenjiaOrb;
import Thmod.Orbs.ElementOrb.LunaOrb;
import Thmod.Orbs.ElementOrb.MetalOrb;
import Thmod.Orbs.ElementOrb.SunOrb;
import Thmod.Orbs.ElementOrb.WaterOrb;
import Thmod.Orbs.ElementOrb.WoodOrb;
import Thmod.Patches.AbstractCardEnum;
import Thmod.ThMod;
import basemod.abstracts.CustomCard;

public abstract class AbstractElementCards extends CustomCard {
    public ElementType elementType;
    private ElementType mixElementType;
    private boolean allElements;

    public AbstractElementCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final ElementType elementType){
        super(id, name, ThMod.komeijiCardImage(id), cost, description, type, AbstractCardEnum.古明地觉, rarity, target);
        this.elementType = elementType;
        this.mixElementType = null;
        this.allElements = false;
    }

    public AbstractElementCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final ElementType elementType,boolean isSP){
        super(id, name, ThMod.komeijiCardImage(id), cost, description, type, AbstractCardEnum.Sp符卡, rarity, target);
        this.elementType = elementType;
        this.mixElementType = null;
        this.allElements = false;
    }

    public AbstractElementCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final ElementType elementType, final ElementType mixElementType){
        super(id, name, ThMod.komeijiCardImage(id), cost, description, type, AbstractCardEnum.Sp符卡, rarity, target);
        this.elementType = elementType;
        this.mixElementType = null;
        this.mixElementType = mixElementType;
        this.allElements = false;
    }

    public AbstractElementCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final boolean allElements){
        super(id, name, ThMod.komeijiCardImage(id), cost, description, type, AbstractCardEnum.Sp符卡, rarity, target);
        this.elementType = null;
        this.mixElementType = null;
        this.allElements = allElements;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!(this.freeToPlayOnce)) {
            if (this.mixElementType == null) {
                if (this.elementType != null) {
                    switch (this.elementType) {
                        case Earth:
                            AbstractOrb orb = new EarthOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            break;
                        case Fire:
                            orb = new FireOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            break;
                        case Luna:
                            orb = new LunaOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            break;
                        case Metal:
                            orb = new MetalOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            break;
                        case Sun:
                            orb = new SunOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            break;
                        case Water:
                            orb = new WaterOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            break;
                        case Wood:
                            orb = new WoodOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            break;
                    }
                } else if (this.allElements) {
                    boolean fired = false;
                    boolean wooded = false;
                    boolean metaled = false;
                    boolean earthed = false;
                    boolean watered = false;
                    for (int i = 0; i < p.orbs.size(); i++) {
                        if (p.orbs.get(i) instanceof AbstractElementOrb) {
                            AbstractElementOrb elementOrb = (AbstractElementOrb) p.orbs.get(i);
                            switch (elementOrb.elementType) {
                                case Fire:
                                    if (!(fired)) {
                                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, true));
                                        fired = true;
                                    }
                                    break;
                                case Wood:
                                    if (!(wooded)) {
                                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, true));
                                        wooded = true;
                                    }
                                    break;
                                case Metal:
                                    if (!(metaled)) {
                                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, true));
                                        metaled = true;
                                    }
                                    break;
                                case Earth:
                                    if (!(earthed)) {
                                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, true));
                                        earthed = true;
                                    }
                                    break;
                                case Water:
                                    if (!(watered)) {
                                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, true));
                                        watered = true;
                                    }
                                    break;
                            }
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(new MoveOrbAction());
                }
            } else {
                for (int i = 0; i < p.orbs.size(); i++) {
                    if (p.orbs.get(i) instanceof AbstractElementOrb) {
                        AbstractElementOrb elementOrb = (AbstractElementOrb) p.orbs.get(i);
                        if (elementOrb.elementType.equals(this.elementType)) {
                            AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, true));
                            break;
                        }
                    }
                }
                for (int i = 0; i < p.orbs.size(); i++) {
                    if (p.orbs.get(i) instanceof AbstractElementOrb) {
                        AbstractElementOrb elementOrb = (AbstractElementOrb) p.orbs.get(i);
                        if (elementOrb.elementType.equals(this.mixElementType)) {
                            AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, true));
                            break;
                        }
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new MoveOrbAction());
            }
        }
        if((!(this.purgeOnUse)) && (!(this.allElements)) && p.hasPower("KenjiaPower")){
            boolean double1 = false;
            boolean double2 = false;
            for(int i = 0;i < p.orbs.size();i++) {
                if (p.orbs.get(i) instanceof AbstractKenjiaOrb) {
                    AbstractKenjiaOrb elementOrb = (AbstractKenjiaOrb) p.orbs.get(i);
                    if (this.elementType != null) {
                        if (elementOrb.elementType.equals(this.elementType))
                            double1 = true;
                    }
                    if(this.mixElementType != null){
                        if (elementOrb.elementType.equals(this.mixElementType))
                            double2 = true;
                    }
                    else
                        double2 = true;
                }
            }
            if((double1) && (double2)){
                flash();
                for(int i = 0;i < p.orbs.size();i++) {
                    if (p.orbs.get(i) instanceof AbstractKenjiaOrb) {
                        AbstractKenjiaOrb elementOrb = (AbstractKenjiaOrb) p.orbs.get(i);
                        if (this.elementType != null) {
                            if (elementOrb.elementType.equals(this.elementType))
                                AbstractDungeon.actionManager.addToTop(new ChangeOrbAction(i,true));
                        }
                        if(this.mixElementType != null){
                            if (elementOrb.elementType.equals(this.mixElementType))
                                AbstractDungeon.actionManager.addToTop(new ChangeOrbAction(i,true));
                        }
                    }
                }
                AbstractCard tmp = this.makeStatEquivalentCopy();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = this.current_x;
                tmp.current_y = this.current_y;
                tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
                tmp.target_y = (Settings.HEIGHT / 2.0F);
                tmp.freeToPlayOnce = true;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, this.energyOnUse));
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        boolean element1 = false;
        boolean element2 = false;
        if(this.mixElementType != null) {
            for (int i = 0; i < p.orbs.size(); i++) {
                if (p.orbs.get(i) instanceof AbstractElementOrb) {
                    AbstractElementOrb elementOrb = (AbstractElementOrb) p.orbs.get(i);
                    if (elementOrb.elementType.equals(this.elementType))
                        element1 = true;
                    if (elementOrb.elementType.equals(this.mixElementType))
                        element2 = true;
                    if((element1) && (element2))
                        return true;
                }
            }
            this.cantUseMessage = "我没有施法所需的元素球!";
            return false;
        }
        else if(this.allElements){
            boolean hasElement1 = false;
            boolean hasElement2 = false;
            boolean hasElement3 = false;
            boolean hasElement4 = false;
            boolean hasElement5 = false;
            for (int i = 0; i < p.orbs.size(); i++) {
                if (p.orbs.get(i) instanceof AbstractElementOrb) {
                    AbstractElementOrb elementOrb = (AbstractElementOrb) p.orbs.get(i);
                    if (elementOrb.elementType.equals(ElementType.Earth))
                        hasElement1 = true;
                    if (elementOrb.elementType.equals(ElementType.Fire))
                        hasElement2 = true;
                    if (elementOrb.elementType.equals(ElementType.Metal))
                        hasElement3 = true;
                    if (elementOrb.elementType.equals(ElementType.Water))
                        hasElement4 = true;
                    if (elementOrb.elementType.equals(ElementType.Wood))
                        hasElement5 = true;
                    if((hasElement1) && (hasElement2) && (hasElement3) && (hasElement4) && (hasElement5))
                        return true;
                }
            }
            return false;
        }
        return true;
    }

    public void applyPowers() {
        super.applyPowers();
        if(AbstractDungeon.player.hasPower("ForestBlazePower"))
            this.setCostForTurn(-9);
    }

    public static enum ElementType
    {
        Fire,Water,Metal,Wood,Earth,Sun,Luna;
    }
}
