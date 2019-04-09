package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class RedtheNightlessCastle extends AbstractRemiriaCards {
    public static final String ID = "RedtheNightlessCastle";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public RedtheNightlessCastle() {
        this(false);
    }

    public RedtheNightlessCastle(boolean isPlus) {
        super("RedtheNightlessCastle", RedtheNightlessCastle.NAME,  2, RedtheNightlessCastle.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, isPlus);
        this.baseDamage = 2;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.addTips();
        this.attackType = null;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; i ++){
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        if(this.isPlus){
            for(int i = 0; i < this.magicNumber; i ++){
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new RedtheNightlessCastle(true);
            }
        }
        return new RedtheNightlessCastle();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.name = EXTENDED_DESCRIPTION[2];
        this.initializeTitle();
    }

    @Override
    public void normalCard() {
        super.normalCard();
        this.name = NAME;
        this.initializeTitle();
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RedtheNightlessCastle");
        NAME = RedtheNightlessCastle.cardStrings.NAME;
        DESCRIPTION = RedtheNightlessCastle.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = RedtheNightlessCastle.cardStrings.EXTENDED_DESCRIPTION;
    }
}
