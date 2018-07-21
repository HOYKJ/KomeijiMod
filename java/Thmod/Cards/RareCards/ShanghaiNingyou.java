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
import Thmod.Orbs.Shanghai;

public class ShanghaiNingyou extends AbstractKomeijiCards {
    public static final String ID = "ShanghaiNingyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public ShanghaiNingyou() {
        super("ShanghaiNingyou", ShanghaiNingyou.NAME,  2, ShanghaiNingyou.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
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
    }

    public AbstractCard makeCopy() {
        return new ShanghaiNingyou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isEthereal = false;
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ShanghaiNingyou");
        NAME = ShanghaiNingyou.cardStrings.NAME;
        DESCRIPTION = ShanghaiNingyou.cardStrings.DESCRIPTION;
    }
}
