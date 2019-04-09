package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Judgement extends AbstractPower {
    public static final String POWER_ID = "Judgement";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Judgement");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean turnToJudge;
    private String desForTurn;
    private AbstractPlayer p = AbstractDungeon.player;

    public Judgement(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = "Judgement";
        this.owner = owner;
        this.amount = amount;
        this.img = ImageMaster.loadImage("images/power/32/Judgement.png");
        this.type = PowerType.BUFF;
        this.turnToJudge = false;
        this.desForTurn = DESCRIPTIONS[3];
        updateDescription();
    }

    public void atStartOfTurn() {
        this.turnToJudge = !this.turnToJudge;
        if(this.turnToJudge)
            this.desForTurn = DESCRIPTIONS[2];
        else
            this.desForTurn = DESCRIPTIONS[3];
        updateDescription();
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(this.turnToJudge){
            if(card.type == AbstractCard.CardType.ATTACK){
                this.amount += 1;
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, 2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
        else{
            if(card.type == AbstractCard.CardType.SKILL){
                this.amount += 1;
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, 2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.desForTurn + DESCRIPTIONS[1];
    }
}
