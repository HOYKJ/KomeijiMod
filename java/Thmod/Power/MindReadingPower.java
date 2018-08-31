package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Relics.BookofPenglai;
import Thmod.Relics.FamiliarSpoon;
import Thmod.ThMod;

public class MindReadingPower extends AbstractPower {
    public static final String POWER_ID = "MindReadingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MindReadingPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean seen ;
    private AbstractMonster targetMonster;
    private AbstractPlayer p = AbstractDungeon.player;
    private int atknum;
    private int othnum;

    public MindReadingPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "MindReadingPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/MindReadingPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.player.hasRelic("Strange Spoon")) {
            AbstractDungeon.player.loseRelic("Strange Spoon");
            AbstractRelic relic = new FamiliarSpoon();
            UnlockTracker.markRelicAsSeen(relic.relicId);
            relic.obtain();
            relic.isObtained = true;
            relic.isAnimating = false;
            relic.isDone = false;
        }
        if (ThMod.blessingOfDetermination == 2){
            boolean giveBook = true;
            for (AbstractRelic r : p.relics){
                if(r instanceof BookofPenglai) {
                    giveBook = false;
                    break;
                }
            }
            if(giveBook) {
                AbstractRelic relic = new BookofPenglai();
                relic.obtain();
                relic.isObtained = true;
                relic.isAnimating = false;
                relic.isDone = false;
            }
        }
    }

    public void atStartOfTurnPostDraw() {
        this.seen = false;
        this.atknum = 0;
        this.othnum = 0;
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        AbstractMonster[] monsters = new AbstractMonster[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
        AbstractDungeon.getCurrRoom().monsters.monsters.toArray(monsters);
        if (!(this.seen)){
            for (int i = 0; i < temp; ++i) {
                if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped()) {
                    this.targetMonster = (AbstractDungeon.getCurrRoom().monsters.monsters.get(i));
                    if (this.targetMonster != null && (this.targetMonster.intent == AbstractMonster.Intent.ATTACK || this.targetMonster.intent == AbstractMonster.Intent.ATTACK_BUFF || this.targetMonster.intent == AbstractMonster.Intent.ATTACK_DEBUFF || this.targetMonster.intent == AbstractMonster.Intent.ATTACK_DEFEND))
                        this.atknum += 1;
                    else
                        this.othnum += 1;
                }
            }
            if (atknum > 0)
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, this.atknum), this.atknum));
            if (othnum > 0)
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, this.othnum), this.othnum));
            this.seen = true;
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        int Artnum = 0;
        if(isPlayer) {
            if(p.hasPower("Artifact")){
                Artnum = p.getPower("Artifact").amount;
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p,p,"Artifact"));
            }
            if (atknum > 0)
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, -this.atknum), -this.atknum));
            if (othnum > 0)
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, -this.othnum), -this.othnum));
            if(Artnum > 0){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, Artnum), Artnum));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
