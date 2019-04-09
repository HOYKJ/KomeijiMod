package Thmod.Cards.ScarletCard.DeriveCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.DemonsDinnerFork_Remiria;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class DemonsFork_Remiria extends AbstractRemiriaDeriveCards {
    public static final String ID = "DemonsFork_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 0;

    public DemonsFork_Remiria() {
        this(false);
    }

    public DemonsFork_Remiria(boolean isPlus) {
        super("DemonsFork_Remiria", DemonsFork_Remiria.NAME,  0, DemonsFork_Remiria.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, isPlus);
        this.baseDamage = 2;
        this.isMultiDamage = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if(this.isPlus){
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target, 1), 1));
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new DemonsFork_Remiria(true);
            }
        }
        return new DemonsFork_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DemonsFork_Remiria");
        NAME = DemonsFork_Remiria.cardStrings.NAME;
        DESCRIPTION = DemonsFork_Remiria.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DemonsFork_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
