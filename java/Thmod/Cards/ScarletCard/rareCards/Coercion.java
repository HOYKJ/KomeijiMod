package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.CoercionPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Coercion extends AbstractRemiriaFate {
    public static final String ID = "Coercion";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Coercion() {
        this(false);
    }

    public Coercion(boolean isPlus) {
        super("Coercion", Coercion.NAME,  -2, Coercion.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.isInnate = true;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(AbstractDungeon.player instanceof RemiriaScarlet){
            ((RemiriaScarlet) AbstractDungeon.player).changeState("SHOP");
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CoercionPower(AbstractDungeon.player, this.isPlus)));
        if(this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        }
        super.use(p, m);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.use(AbstractDungeon.player, null);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Coercion(true);
            }
        }
        return new Coercion();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Coercion");
        NAME = Coercion.cardStrings.NAME;
        DESCRIPTION = Coercion.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Coercion.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = Coercion.cardStrings.EXTENDED_DESCRIPTION;
    }
}
