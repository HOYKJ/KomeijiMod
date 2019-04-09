package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.DeriveCards.ArcherDoll;
import Thmod.Cards.DeriveCards.ShieldDoll;
import Thmod.Cards.DeriveCards.SpearDoll;
import Thmod.Orbs.NingyouOrb;

public class LittleLegion extends AbstractKomeijiCards {
    public static final String ID = "LittleLegion";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 3;

    public LittleLegion() {
        super("LittleLegion", LittleLegion.NAME,  2, LittleLegion.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, CardSet_k.ALICE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean hasEmpty = false;
        for (AbstractOrb orb : p.orbs){
            if(orb instanceof EmptyOrbSlot){
                hasEmpty = true;
                break;
            }
        }
        if(hasEmpty){
            for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                if (AbstractDungeon.player.orbs.get(i) instanceof EmptyOrbSlot) {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 0));
                }
            }
        }
        else {
            final ChooseAction choice = new ChooseAction(this, m, LittleLegion.EXTENDED_DESCRIPTION[0], false, 1);
            choice.add(new SpearDoll(), () -> {
                for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                    if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 1));
                    }
                }
            });
            choice.add(new ShieldDoll(), () -> {
                for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                    if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 2));
                    }
                }
            });
            choice.add(new ArcherDoll(), () -> {
                for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                    if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 3));
                    }
                }
            });
            AbstractDungeon.actionManager.addToBottom(choice);
        }
    }

    public AbstractCard makeCopy() {
        return new LittleLegion();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LittleLegion");
        NAME = LittleLegion.cardStrings.NAME;
        DESCRIPTION = LittleLegion.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = LittleLegion.cardStrings.EXTENDED_DESCRIPTION;
    }
}
