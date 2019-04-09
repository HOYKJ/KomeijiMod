package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

import Thmod.Characters.RemiriaScarlet;

public class FateMeisterPower extends AbstractPower {
    public static final String POWER_ID = "FateMeisterPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FateMeisterPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private ArrayList<String> list = new ArrayList<>();

    public FateMeisterPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "FateMeisterPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/FateMeisterPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);
        if(this.owner instanceof RemiriaScarlet){
            if(!((RemiriaScarlet) this.owner).masterGroupCopy.contains(card)){
                if(!list.contains(card.cardID)) {
                    list.add(card.cardID);
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
                }
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        this.list.clear();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
