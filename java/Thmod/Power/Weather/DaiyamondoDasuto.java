package Thmod.Power.Weather;

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

import Thmod.Power.HibernatePower;

public class DaiyamondoDasuto extends AbstractPower {
    public static final String POWER_ID = "KaiSei";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DaiyamondoDasuto");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DaiyamondoDasuto(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "DaiyamondoDasuto";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/DaiyamondoDasuto.png");
        this.type = PowerType.BUFF;
    }

    public void atEndOfRound() {
        for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if(AbstractMonster.Intent.valueOf(target.intent.name()) == AbstractMonster.Intent.SLEEP) {
                    AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(this.owner, 999, DamageInfo.DamageType.HP_LOSS)));
                    flash();
                }
            }
        }
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DaiyamondoDasuto"));
        else
            this.amount -= 1;

        if(AbstractDungeon.player.hasPower(HibernatePower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this.owner, 1, DamageInfo.DamageType.HP_LOSS)));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
