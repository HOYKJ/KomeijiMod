package Thmod.Power;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Actions.common.LatterAction;
import Thmod.vfx.JyouchiEffect;

public class JyouchiPower extends AbstractPower {
    public static final String POWER_ID = "JyouchiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("JyouchiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int magicNumber;

    public JyouchiPower(AbstractCreature owner,int magicNumber) {
        this.name = NAME;
        this.ID = "JyouchiPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/JyouchiPower.png");
        this.type = PowerType.BUFF;
        this.magicNumber = magicNumber;
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if ((info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != null) && (info.owner != this.owner))
        {
            flash();
            AbstractDungeon.actionManager.addToTop(new LatterAction(() ->{
                AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner,new DamageInfo(this.owner, 8, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, this.owner, new WeakPower(info.owner, this.magicNumber,false), this.magicNumber));
            AbstractDungeon.effectList.add(new JyouchiEffect(info.owner.hb.cX, info.owner.hb.cY, Color.YELLOW.cpy()));
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner,this.owner,this));
            }, 0.5F));

            return damageAmount;
        }
        else
            return damageAmount;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
