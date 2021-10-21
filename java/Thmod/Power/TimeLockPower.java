package Thmod.Power;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import Thmod.Actions.common.LatterAction;
import Thmod.ThMod;

public class TimeLockPower extends AbstractPower {
    public static final String POWER_ID = "TimeLockPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TimeLockPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    private float STC;

    public TimeLockPower(AbstractCreature owner)
    {
        this.name = NAME;
        this.ID = "TimeLockPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();

        this.type = AbstractPower.PowerType.BUFF;
        this.img = ImageMaster.loadImage("images/power/32/TimeLockPower.png");

        this.moveByte = 1;
        this.moveIntent = AbstractMonster.Intent.UNKNOWN;

        if (owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster)owner;

            this.moveByte = Byte.valueOf(m.nextMove).byteValue();
            this.moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());

            byte a = 127;
            m.setMove(a, AbstractMonster.Intent.STUN);
            m.createIntent();
            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, a, AbstractMonster.Intent.STUN));
        }
        if(owner.state != null){
            STC = owner.state.getTimeScale();
            owner.state.setTimeScale(0F);
        }
        this.owner.tint.color = Color.LIGHT_GRAY.cpy();
        this.priority = 100;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

    public void atEndOfRound()
    {
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));

        if (this.owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster)this.owner;

            m.setMove(this.moveByte, this.moveIntent);
            m.createIntent();
            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
            m.updatePowers();
        }
        CardCrawlGame.music.unsilenceBGM();

        if(owner.state != null){
            owner.state.setTimeScale(STC);
        }
        this.owner.tint.changeColor(Color.WHITE.cpy());
    }

//    @Override
//    public void update(int slot) {
//        super.update(slot);
//        if (this.owner instanceof AbstractMonster) {
//            AbstractMonster m = (AbstractMonster)owner;
//
//            if(m.intent != AbstractMonster.Intent.STUN) {
//                ThMod.logger.info("Intent : " + m.intent.name());
//                byte a = 127;
//                m.setMove(a, AbstractMonster.Intent.STUN);
//                m.createIntent();
//                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, a, AbstractMonster.Intent.STUN));
//                ThMod.logger.info("Intent : " + m.intent.name());
//            }
//        }
//    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            if (this.owner instanceof AbstractMonster) {
                AbstractMonster m = (AbstractMonster)owner;

                ThMod.logger.info("Intent : " + m.intent.name());
                byte a = 127;
                m.setMove(a, AbstractMonster.Intent.STUN);
                m.createIntent();
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, a, AbstractMonster.Intent.STUN));
                ThMod.logger.info("Intent : " + m.intent.name());
            }
        }, 0.1f));
        return damageAmount;
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        this.owner.tint.color = Color.GRAY.cpy();
    }
}
