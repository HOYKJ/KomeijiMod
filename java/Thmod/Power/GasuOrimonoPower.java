package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import java.util.Iterator;

public class GasuOrimonoPower extends AbstractPower {
    public static final String POWER_ID = "GasuOrimonoPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("GasuOrimonoPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public GasuOrimonoPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "GasuOrimonoPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/GasuOrimonoPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurnPostDraw()
    {
        if (!(AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
            flash();
            for (Iterator localIterator = AbstractDungeon.getMonsters().monsters.iterator(); localIterator.hasNext(); ) { AbstractMonster m = (AbstractMonster)localIterator.next();
                if ((!(m.isDead)) && (!(m.isDying)))
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this.owner, new PoisonPower(m, this.owner, this.amount), this.amount));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
