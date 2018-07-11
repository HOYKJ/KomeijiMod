package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.SouSeki;


public class MakuraSekiPower extends AbstractPower {
    public static final String POWER_ID = "MakuraSekiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MakuraSekiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static AbstractPlayer p = AbstractDungeon.player;
    private boolean attacked;
    private boolean uped;
    private AbstractDeriveCards c;

    public MakuraSekiPower(AbstractCreature owner, boolean uped) {
        this.name = NAME;
        this.ID = "MakuraSekiPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/MakuraSekiPower.png");
        this.type = AbstractPower.PowerType.BUFF;
        this.attacked = false;
        this.uped = uped;
    }

    public int onAttacked(DamageInfo info, int damageAmount){
        if ((info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != null) && (info.owner != this.owner))
            this.attacked = true;
        return damageAmount;
    }

    public void atStartOfTurnPostDraw() {
        if(this.attacked){
            c = new SouSeki();
            if(this.uped)
                c.upgrade();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
            this.attacked = false;
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,this.ID));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
