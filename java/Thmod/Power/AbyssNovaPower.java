package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbyssNovaPower extends AbstractPower {
    public static final String POWER_ID = "AbyssNovaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("AbyssNovaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private static int novaIdOffset;

    public AbyssNovaPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "AbyssNovaPower" + novaIdOffset;
        novaIdOffset += 1;
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/AbyssNovaPower.png");
        this.type = PowerType.BUFF;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if(this.amount > 1)
                this.amount -= 1;
            else if (this.amount == 1){
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        flash();
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, 60, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), 3));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,this.ID));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
