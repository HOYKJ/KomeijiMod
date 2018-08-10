package Thmod.Cards.RareCards;

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
import Thmod.Orbs.Helan;

public class HelanNingyou extends AbstractKomeijiCards {
    public static final String ID = "HelanNingyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 2;

    public HelanNingyou() {
        super("HelanNingyou", HelanNingyou.NAME,  2, HelanNingyou.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.isEthereal = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int EmptyNum = 0;
        for(int i = 0;i < p.orbs.size();i++){
            if(p.orbs.get(i) instanceof EmptyOrbSlot)
                EmptyNum += 1;
        }
        if(EmptyNum > 0) {
            AbstractOrb orb = new Helan();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        }
    }

    public AbstractCard makeCopy() {
        return new HelanNingyou();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HelanNingyou");
        NAME = HelanNingyou.cardStrings.NAME;
        DESCRIPTION = HelanNingyou.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = HelanNingyou.cardStrings.UPGRADE_DESCRIPTION;
    }
}
