package Thmod.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import Thmod.vfx.TheWorld;

public class WocchiPower extends AbstractPower {
    public static final String POWER_ID = "WocchiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("WocchiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean sekai;
    //private boolean done;

    public WocchiPower(AbstractCreature owner,boolean sekai) {
        this.name = NAME;
        this.ID = "WocchiPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.img = ImageMaster.loadImage("images/power/32/WocchiPower.png");
        this.sekai = sekai;
        //this.done = false;
    }

    public void atStartOfTurnPostDraw() {
        flash();
        if ((AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) || (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss))
            CardCrawlGame.music.silenceTempBgmInstantly();
        else
            CardCrawlGame.music.silenceBGM();
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new TheWorld(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY,true), 2F));
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new TimeLockPower(target)));
            }
        }
        if (!(this.sekai))
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EntanglePower(AbstractDungeon.player)));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,"WocchiPower"));
        //this.done = true;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
