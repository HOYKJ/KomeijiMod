package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class ScarletDestiny extends AbstractRemiriaCards {
    public static final String ID = "ScarletDestiny";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public ScarletDestiny() {
        this(false);
    }

    public ScarletDestiny(boolean isPlus) {
        super("ScarletDestiny", ScarletDestiny.NAME,  2, ScarletDestiny.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, true, this));
        }, 0f));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new ScarletDestiny(true);
            }
        }
        return new ScarletDestiny();
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.retain = true;
    }

    @Override
    public void normalCard() {
        super.normalCard();
        this.retain = false;
    }

    @Override
    public void update() {
        super.update();
        if((this.isPlus) && (!this.retain)){
            this.retain = true;
        }
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ScarletDestiny");
        NAME = ScarletDestiny.cardStrings.NAME;
        DESCRIPTION = ScarletDestiny.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = ScarletDestiny.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = ScarletDestiny.cardStrings.EXTENDED_DESCRIPTION;
    }
}
