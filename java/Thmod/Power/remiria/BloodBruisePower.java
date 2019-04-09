package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.ScarletCard.uncommonCards.PainBurst;
import Thmod.Characters.RemiriaScarlet;

public class BloodBruisePower extends AbstractPower {
    public static final String POWER_ID = "BloodBruisePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BloodBruisePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BloodBruisePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "BloodBruisePower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/BloodBruisePower.png");
        this.type = PowerType.DEBUFF;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if(!this.owner.hasPower(RedMagicPower.POWER_ID)) {
            float multiple = 0.5f * (float) Math.pow(1.5, this.amount / 12);

            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(this.owner, (int) (this.amount * multiple), DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.NONE));
            if (this.owner instanceof RemiriaScarlet) {
                ((RemiriaScarlet) this.owner).bloodCounter += (int) ((float) this.amount / 2);
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card instanceof PainBurst) {
                        ((PainBurst) card).reduceCost();
                    }
                }
                for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                    if (card instanceof PainBurst) {
                        ((PainBurst) card).reduceCost();
                    }
                }
                for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
                    if (card instanceof PainBurst) {
                        ((PainBurst) card).reduceCost();
                    }
                }
            }
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if(this.owner instanceof RemiriaScarlet){
            if(target.hasPower(this.ID)) {
                float num = Math.min(this.amount * 0.015f, 0.3f);
                AbstractDungeon.player.heal((int) (num * damageAmount));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
