package Thmod.Power;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.common.DashAction;
import Thmod.Monsters.Satori;
import Thmod.Power.satoriEnemy.PointPowerSpe;
import Thmod.vfx.animation.DashEffect;

public class DashPower extends AbstractPower {
    public static final String POWER_ID = "DashPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DashPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public DashPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = "DashPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/DashPower.png");
        this.type = PowerType.BUFF;
    }

    public void updateDescription()
    {
        if (this.amount <= 1)
            this.description = DESCRIPTIONS[0];
        else
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

//    public float atDamageReceive(float damage, DamageInfo.DamageType type)
//    {
//        if(this.owner != p) {
//            if (damage > 0.0F) {
//                damage = 0.0F;
//            }
//        }
//        return damage;
//    }

    public int onAttacked(com.megacrit.cardcrawl.cards.DamageInfo info, int damageAmount) {
        if(this.owner != p) {
            if (damageAmount > 0) {
                damageAmount = 0;
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this, 1));
                if(this.owner instanceof Satori){
                    if (!(this.owner.hasPower("PointPower"))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PointPowerSpe(this.owner, 1), 1));
                    } else if (this.owner.getPower("PointPower").amount < 5) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PointPowerSpe(this.owner, 1), 1));
                    }
                }
                AbstractDungeon.effectList.add(new DashEffect(this.owner, 1, !this.owner.flipHorizontal));
            }
            CardCrawlGame.sound.play("graze");
        }
        return damageAmount;
    }

    public int onLoseHp(int damageAmount) {
        if(damageAmount > 0) {
            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            if (this.owner == p) {
                if (!(p.hasPower("PointPower"))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                } else if (p.getPower("PointPower").amount < 5) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                }
                CardCrawlGame.sound.play("graze");
            }
            AbstractDungeon.effectList.add(new DashEffect(this.owner, 1, this.owner.flipHorizontal));
            return 0;
        }
        return damageAmount;
    }
}
