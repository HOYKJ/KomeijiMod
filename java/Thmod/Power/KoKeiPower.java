package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KoKeiPower extends AbstractPower {
    public static final String POWER_ID = "KoKeiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KoKeiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean turn;

    public KoKeiPower(AbstractCreature owner,boolean turn) {
        this.name = NAME;
        this.ID = "KoKeiPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/KoKeiPower.png");
        this.type = PowerType.BUFF;
        this.turn = turn;
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if(!(this.turn)) {
            if (card.type == AbstractCard.CardType.ATTACK)
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "KoKeiPower"));
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL)
            return (damage * 2F);

        return damage;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
