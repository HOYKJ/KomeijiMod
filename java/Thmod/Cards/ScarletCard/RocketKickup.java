package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.common.DrawSpeCardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.uncommonCards.SealingFear;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class RocketKickup extends AbstractRemiriaCards {
    public static final String ID = "RocketKickup";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public RocketKickup() {
        this(false);
    }

    public RocketKickup(boolean isPlus) {
        super("RocketKickup", RocketKickup.NAME,  1, RocketKickup.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, isPlus);
        this.baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(m.hasPower(BloodBruisePower.POWER_ID)){
            if(this.isPlus){
                if(p.drawPile.group.size() < 1){
                    AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
                }
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                    final ChooseAction choice = new ChooseAction(null, null, SealingFear.EXTENDED_DESCRIPTION[2], true, 1);
                    for (AbstractCard card : p.drawPile.group) {
                        if(card != this) {
                            choice.add(card, () -> {
                                AbstractDungeon.actionManager.addToBottom(new DrawSpeCardAction(card));
                            });
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(choice);
                }, 0.1f));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
            }
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, BloodBruisePower.POWER_ID, 1));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new RocketKickup(true);
            }
        }
        return new RocketKickup();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RocketKickup");
        NAME = RocketKickup.cardStrings.NAME;
        DESCRIPTION = RocketKickup.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = RocketKickup.cardStrings.EXTENDED_DESCRIPTION;
    }
}
