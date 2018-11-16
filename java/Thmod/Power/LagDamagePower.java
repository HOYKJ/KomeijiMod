package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.Actions.common.RoundDiggerAction;

public class LagDamagePower extends AbstractPower {
    public static final String POWER_ID = "LagDamagePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("LagDamagePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean isRed;
    private ArrayList<AbstractCreature> target = new ArrayList<>();

    public LagDamagePower(AbstractCreature owner, int amount,boolean isRed) {
        this.name = NAME;
        this.ID = "LagDamagePower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/LagDamagePower.png");
        this.type = PowerType.BUFF;
        this.isRed = isRed;
    }

    public void atStartOfTurn() {
        for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
            AbstractMonster target =AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                this.target.add(target);
            }
        }
        flash();
        AbstractDungeon.effectList.add(new RoundDiggerAction(amount,this.isRed,target));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,this));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
