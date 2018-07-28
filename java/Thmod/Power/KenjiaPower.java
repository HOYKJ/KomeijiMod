package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.common.MoveOrbAction;
import Thmod.Orbs.ElementOrb.KenjiaOrb.AbstractKenjiaOrb;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaEarth;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaFire;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaMetal;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaWater;
import Thmod.Orbs.ElementOrb.KenjiaOrb.KenjiaWood;

public class KenjiaPower extends AbstractPower {
    public static final String POWER_ID = "KenjiaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KenjiaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public KenjiaPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KenjiaPower";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/KenjiaPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurnPostDraw() {
        for(int i = 0;i < p.orbs.size();i++){
            if((p.orbs.get(i) instanceof KenjiaFire) || (p.orbs.get(i) instanceof KenjiaWood) || (p.orbs.get(i) instanceof KenjiaMetal) || (p.orbs.get(i) instanceof KenjiaEarth) || (p.orbs.get(i) instanceof KenjiaWater)){
                AbstractDungeon.actionManager.addToTop(new MoveOrbAction());
                AbstractDungeon.actionManager.addToTop(new ChangeOrbAction(i,true));
            }
        }
        AbstractKenjiaOrb orbToGet = new KenjiaFire();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaWood();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaMetal();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaEarth();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
        orbToGet = new KenjiaWater();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbToGet));
    }

    public void atEndOfRound() {
        if(this.amount <= 1) {
            for(int i = 0;i < p.orbs.size();i++){
                if((p.orbs.get(i) instanceof KenjiaFire) || (p.orbs.get(i) instanceof KenjiaWood) || (p.orbs.get(i) instanceof KenjiaMetal) || (p.orbs.get(i) instanceof KenjiaEarth) || (p.orbs.get(i) instanceof KenjiaWater)){
                    AbstractDungeon.actionManager.addToTop(new MoveOrbAction());
                    AbstractDungeon.actionManager.addToTop(new ChangeOrbAction(i,true));
                }
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
