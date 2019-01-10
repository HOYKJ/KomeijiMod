package Thmod.Power;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.DevConsole;

public class DeepSleep extends AbstractPower {
    public static final String POWER_ID = "DeepSleep";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DeepSleep");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    private int turns = 2;

    public DeepSleep(AbstractCreature owner)
    {
        this.name = NAME;
        this.ID = "DeepSleep";
        this.owner = owner;
        this.amount = -1;
        updateDescription();

        this.type = AbstractPower.PowerType.BUFF;
        this.img = ImageMaster.loadImage("images/power/32/DeepSleep.png");

        this.moveByte = 1;
        this.moveIntent = AbstractMonster.Intent.UNKNOWN;

        if (owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster)owner;

            this.moveByte = Byte.valueOf(m.nextMove).byteValue();
            this.moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());

            byte a = 127;
            m.setMove(a, AbstractMonster.Intent.SLEEP);
            m.createIntent();
            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, a, AbstractMonster.Intent.SLEEP));
        }
    }

    @Override
    public int onLoseHp(int damageAmount) {
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));

        if (this.owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster) this.owner;

            m.setMove(this.moveByte, this.moveIntent);
            m.createIntent();
            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
            m.updatePowers();
        }

        return super.onLoseHp(damageAmount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(info.output > this.owner.currentBlock){
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));

            if (this.owner instanceof AbstractMonster) {
                AbstractMonster m = (AbstractMonster) this.owner;

                m.setMove(this.moveByte, this.moveIntent);
                m.createIntent();
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
                m.updatePowers();
            }
        }

        return super.onAttacked(info, damageAmount);
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

    public void atEndOfRound()
    {
        if(turns > 0){
            turns -= 1;
            AbstractDungeon.actionManager.addToBottom(new TalkAction(this.owner, "zzz...", 0.5F, 2.0F));
            if (owner instanceof AbstractMonster) {
                AbstractMonster m = (AbstractMonster) owner;
                byte a = 127;
                m.setMove(a, AbstractMonster.Intent.SLEEP);
                m.createIntent();
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, a, AbstractMonster.Intent.SLEEP));
            }
        }
        else {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));

            if (this.owner instanceof AbstractMonster) {
                AbstractMonster m = (AbstractMonster) this.owner;

                m.setMove(this.moveByte, this.moveIntent);
                m.createIntent();
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
                m.updatePowers();
            }
        }
    }
}
