package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class FateSonata extends AbstractRemiriaCards {
    public static final String ID = "FateSonata";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private int step;

    public FateSonata() {
        this(false, 0);
    }

    public FateSonata(int step) {
        this(false, step);
    }

    public FateSonata(boolean isPlus) {
        this(isPlus, 0);
    }

    public FateSonata(boolean isPlus, int step) {
        super("FateSonata", FateSonata.NAME,  0, FateSonata.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, isPlus);
        this.baseDamage = 4;
        this.baseBlock = 4;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
        this.attackType = AttackType.LIGHT;
        this.step = step;
        this.updateDescrip();
    }

    private void updateDescrip(){
        switch (this.step){
            case 1:
                this.name = EXTENDED_DESCRIPTION[2];
                this.rawDescription = EXTENDED_DESCRIPTION[3];
                this.initializeTitle();
                this.initializeDescription();
                break;
            case 2:
                this.name = EXTENDED_DESCRIPTION[4];
                this.rawDescription = EXTENDED_DESCRIPTION[5];
                this.initializeTitle();
                this.initializeDescription();
                break;
            case 3:
                this.name = EXTENDED_DESCRIPTION[6];
                this.rawDescription = EXTENDED_DESCRIPTION[7];
                this.initializeTitle();
                this.initializeDescription();
                this.attackType = null;
                this.target = CardTarget.NONE;
                this.addTips();
                break;
            default:
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        switch (this.step){
            case 0:
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                if(this.isPlus) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(this.makeCopy(1), 1, false, false));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.makeCopy(1), 1));
                }
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
                if(this.isPlus) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(this.makeCopy(2), 1, false, false));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.makeCopy(2), 1));
                }
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
                if(this.isPlus) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(this.makeCopy(3), 1, false, false));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.makeCopy(3), 1));
                }
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.drawPile, true, this));
                AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, true, this));
                AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.discardPile, true, this));
                if(this.isPlus) {
                    if(p.hasPower(BloodBruisePower.POWER_ID)) {
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, BloodBruisePower.POWER_ID));
                    }
                    for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                        if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                            if(target.hasPower(BloodBruisePower.POWER_ID)) {
                                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, p, BloodBruisePower.POWER_ID));
                            }
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 3), 3));
                }
                break;
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FateSonata(true);
            }
        }
        return new FateSonata();
    }

    public AbstractCard makeCopy(int num) {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FateSonata(true, num);
            }
        }
        return new FateSonata(num);
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        ((FateSonata)card).step = this.step;
        ((FateSonata)card).updateDescrip();
        return card;
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(4);
            this.upgradeBlock(4);
            this.rawDescription = EXTENDED_DESCRIPTION[8 + this.step];
            this.initializeDescription();
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        if(this.step == 3){
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[12]));
        }
        else {
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FateSonata");
        NAME = FateSonata.cardStrings.NAME;
        DESCRIPTION = FateSonata.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FateSonata.cardStrings.EXTENDED_DESCRIPTION;
    }
}
