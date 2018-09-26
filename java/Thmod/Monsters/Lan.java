package Thmod.Monsters;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Actions.common.AllGainBlockAction;

public class Lan extends AbstractMonster {
    public static final String ID = "Lan";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Lan");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private AbstractPlayer p = AbstractDungeon.player;

    public Lan(float x, float y) {
        super(NAME, "Lan", 250, 40.0F, 0.0F, 280.0F, 325.0F, "images/monsters/Lan/Main.png", x, y);
    }

    protected void getMove(int num) {
        if (lastMove((byte)1)){
            setMove(MOVES[1],(byte)2, Intent.DEFEND_DEBUFF);
        }
        else if(lastMove((byte)2)){
            setMove(MOVES[2],(byte)3, Intent.DEFEND_DEBUFF);
        }
        else if(lastMove((byte)3)){
            setMove(MOVES[3],(byte)4, Intent.DEFEND_BUFF);
        }
        else{
            boolean hasCheng = false;
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    if(target.id.equals(Cheng.ID))
                        hasCheng = true;
                }
            }
            if(!(hasCheng)){
                setMove(MOVES[0],(byte)1, Intent.UNKNOWN);
            }
            else{
                setMove(MOVES[1],(byte)2, Intent.DEFEND_DEBUFF);
            }
        }
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new Cheng(-450.0F, -10.0F), false));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new PlatedArmorPower(this,25),25));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new AllGainBlockAction(this, 10));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, this, new WeakPower(p, 2, true), 2));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new AllGainBlockAction(this, 10));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, this, new VulnerablePower(p, 2, true), 2));
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new AllGainBlockAction(this, 10));
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new StrengthPower(m, 2), 2));
                    }
                }
                break;
            default:
                AbstractDungeon.actionManager.addToBottom(new AllGainBlockAction(this, 10));
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new StrengthPower(m, 3), 3));
                    }
                }
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void die()
    {
        this.deathTimer += 1.5F;
        super.die();
    }
}
