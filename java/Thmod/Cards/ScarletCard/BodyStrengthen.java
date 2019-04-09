package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BodyStrengthenPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BodyStrengthen extends AbstractRemiriaCards {
    public static final String ID = "BodyStrengthen";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BodyStrengthen() {
        this(false);
    }

    public BodyStrengthen(boolean isPlus) {
        super("BodyStrengthen", BodyStrengthen.NAME,  0, BodyStrengthen.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BodyStrengthenPower(p, this.magicNumber), this.magicNumber));
        if(this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BodyStrengthen(true);
            }
        }
        return new BodyStrengthen();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BodyStrengthen");
        NAME = BodyStrengthen.cardStrings.NAME;
        DESCRIPTION = BodyStrengthen.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BodyStrengthen.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BodyStrengthen.cardStrings.EXTENDED_DESCRIPTION;
    }
}
