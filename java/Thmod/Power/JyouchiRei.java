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
import com.megacrit.cardcrawl.powers.AbstractPower;

public class JyouchiRei extends AbstractPower {
    public static final String POWER_ID = "JyouchiRei";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("JyouchiRei");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public JyouchiRei(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "JyouchiRei";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/JyouchiRei.png");
        this.type = PowerType.DEBUFF;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(p, (this.amount * 10), DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
        flash();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "JyouchiRei"));
        return damageAmount;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
