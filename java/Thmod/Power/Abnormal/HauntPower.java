package Thmod.Power.Abnormal;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class HauntPower extends AbstractPower {
    public static final String POWER_ID = "HauntPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HauntPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HauntPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "HauntPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Abnormal/HauntPower.png");
        this.type = PowerType.DEBUFF;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(target == AbstractDungeon.player) {
            int roll = MathUtils.random(2);
            switch (roll){
                case 0:
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new RedAbnormity(this.owner, 2), 2));
                    break;
                case 1:
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new GreenAbnormity(this.owner, 2), 2));
                    break;
                case 2:
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new BlueAbnormity(this.owner, 2), 2));
                    break;
                default:
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new RedAbnormity(this.owner, 2), 2));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
