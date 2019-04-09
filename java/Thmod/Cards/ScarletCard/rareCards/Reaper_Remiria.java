package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.Remiria.ReaperRemiAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Reaper_Remiria extends AbstractRemiriaFate {
    public static final String ID = "Reaper_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Reaper_Remiria() {
        this(false);
    }

    public Reaper_Remiria(boolean isPlus) {
        super("Reaper_Remiria", Reaper_Remiria.NAME,  -2, Reaper_Remiria.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.misc = 2;
        this.baseDamage = 0;
        this.isMultiDamage = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.calculateCardDamage(m);
        AbstractDungeon.actionManager.addToBottom(new ReaperRemiAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SMASH, this.uuid));
        if(!this.isPlus){
            if(p.hasPower(BloodBruisePower.POWER_ID)) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(p, new DamageInfo(p, p.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber,
                        this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
        super.use(p, m);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.use(AbstractDungeon.player, null);
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for(int i = 0; i < temp; ++i) {
            if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped()) {
                AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if(monster.hasPower(BloodBruisePower.POWER_ID)){
                    AbstractPower blood = monster.getPower(BloodBruisePower.POWER_ID);
                    this.multiDamage[i] += blood.amount * this.magicNumber;
                }
            }
        }
        this.damage = this.multiDamage[0];
        this.isDamageModified = true;
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Reaper_Remiria(true);
            }
        }
        return new Reaper_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Reaper_Remiria");
        NAME = Reaper_Remiria.cardStrings.NAME;
        DESCRIPTION = Reaper_Remiria.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Reaper_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
