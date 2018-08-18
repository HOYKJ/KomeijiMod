package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AtonePower extends AbstractPower {
    public static final String POWER_ID = "AtonePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("AtonePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean isAttack;
    private String desForTurn;

    public AtonePower(AbstractCreature owner,boolean isAttack) {
        this.name = NAME;
        this.ID = "AtonePower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/AtonePower.png");
        this.type = PowerType.BUFF;
        this.isAttack = isAttack;
        if(this.isAttack)
            this.desForTurn = "攻击";
        else
            this.desForTurn = "技能";
        updateDescription();
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(this.isAttack){
            if(card.type == AbstractCard.CardType.ATTACK){
                if(p.getPower("Judgement").amount > 0)
                    p.getPower("Judgement").amount -= 1;
            }
        }
        else {
            if(card.type == AbstractCard.CardType.SKILL){
                if(p.getPower("Judgement").amount > 0)
                    p.getPower("Judgement").amount -= 1;
            }
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer)
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.desForTurn + DESCRIPTIONS[1];
    }
}
