package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.CruelSlaughterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class CruelSlaughter extends AbstractRemiriaCards {
    public static final String ID = "CruelSlaughter";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public CruelSlaughter() {
        this(false);
    }

    public CruelSlaughter(boolean isPlus) {
        super("CruelSlaughter", CruelSlaughter.NAME,  2, CruelSlaughter.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, isPlus);
        this.baseDamage = 15;
        this.isMultiDamage = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new CruelSlaughterAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new CruelSlaughter(true);
            }
        }
        return new CruelSlaughter();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
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

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CruelSlaughter");
        NAME = CruelSlaughter.cardStrings.NAME;
        DESCRIPTION = CruelSlaughter.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CruelSlaughter.cardStrings.EXTENDED_DESCRIPTION;
    }
}
