package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.TimeLockPower;

public class LunaDial extends AbstractKomeijiCards {
    public static final String ID = "LunaDial";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 2;

    public LunaDial() {
        super("LunaDial", LunaDial.NAME,  2, LunaDial.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY, CardSet_k.SAKUYA);
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        m.useShakeAnimation(0.1F);
        if(this.upgraded){
            if(m.hasPower("Artifact"))
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(m,p,"Artifact"));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TimeLockPower(m)));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(m != null) {
            if (m.hasPower(TimeLockPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, EXTENDED_DESCRIPTION[0]));
                return false;
            }
        }
        return super.canUse(p, m);
    }

    public AbstractCard makeCopy() {
        return new LunaDial();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LunaDial");
        NAME = LunaDial.cardStrings.NAME;
        DESCRIPTION = LunaDial.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = LunaDial.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = LunaDial.cardStrings.EXTENDED_DESCRIPTION;
    }
}
