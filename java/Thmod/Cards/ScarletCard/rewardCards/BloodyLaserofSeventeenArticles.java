package Thmod.Cards.ScarletCard.rewardCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.Remiria.SeventeenArticlesAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodyLaserofSeventeenArticles extends AbstractRemiriaCards {
    public static final String ID = "BloodyLaserofSeventeenArticles";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodyLaserofSeventeenArticles() {
        this(false);
    }

    public BloodyLaserofSeventeenArticles(boolean isPlus) {
        super("BloodyLaserofSeventeenArticles", BloodyLaserofSeventeenArticles.NAME,  3, BloodyLaserofSeventeenArticles.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.NONE, isPlus);
        this.baseDamage = 1;
        this.baseMagicNumber = 17;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.LIGHT;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SeventeenArticlesAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.baseDamage, this.damageTypeForTurn),
                this.magicNumber, AbstractGameAction.AttackEffect.BLUNT_LIGHT, this.isPlus));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodyLaserofSeventeenArticles(true);
            }
        }
        return new BloodyLaserofSeventeenArticles();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodyLaserofSeventeenArticles");
        NAME = BloodyLaserofSeventeenArticles.cardStrings.NAME;
        DESCRIPTION = BloodyLaserofSeventeenArticles.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodyLaserofSeventeenArticles.cardStrings.EXTENDED_DESCRIPTION;
    }
}
