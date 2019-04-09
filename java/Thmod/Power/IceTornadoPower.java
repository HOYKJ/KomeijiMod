package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

public class IceTornadoPower extends AbstractPower {
    public static final String POWER_ID = "IceTornadoPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("IceTornadoPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damage;
    private AbstractPlayer p = AbstractDungeon.player;

    public IceTornadoPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = "IceTornadoPower";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/IceTornadoPower.png");
        this.type = PowerType.BUFF;
        this.damage = Amount;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (damage > this.damage) {
            damage -= this.damage;
        }
        else {
            damage = 0;
        }
        return damage;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if (this.amount == 1) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "IceTornadoPower"));
            }
            else {
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        flash();
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, this.damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                    }
                }
                this.damage += 2;
                this.amount -= 1;
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
