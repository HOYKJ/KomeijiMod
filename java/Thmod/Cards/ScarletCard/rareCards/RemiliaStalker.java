package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.RemiliaStalkerPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class RemiliaStalker extends AbstractRemiriaCards {
    public static final String ID = "RemiliaStalker";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public RemiliaStalker() {
        this(false);
    }

    public RemiliaStalker(boolean isPlus) {
        super("RemiliaStalker", RemiliaStalker.NAME,  1, RemiliaStalker.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RemiliaStalkerPower(p)));
        if(this.isPlus) {
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                if(p.hasPower(RemiliaStalkerPower.POWER_ID)){
                    ((RemiliaStalkerPower)p.getPower(RemiliaStalkerPower.POWER_ID)).active();
                }
            }, 0.1f));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new RemiliaStalker(true);
            }
        }
        return new RemiliaStalker();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isInnate = true;
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaStalker");
        NAME = RemiliaStalker.cardStrings.NAME;
        DESCRIPTION = RemiliaStalker.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = RemiliaStalker.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = RemiliaStalker.cardStrings.EXTENDED_DESCRIPTION;
    }
}
