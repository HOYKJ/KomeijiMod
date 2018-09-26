package Thmod.Monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class Cheng extends AbstractMonster {
    public static final String ID = "Cheng";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Cheng");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private AbstractPlayer p = AbstractDungeon.player;
    private int attackTimes;

    public Cheng(float x, float y) {
        super(NAME, "Cheng", 50, 10.0F, 0.0F, 200.0F, 275.0F, "images/monsters/Cheng/Main.png", x, y);
        this.attackTimes = 2;
    }

    protected void getMove(int num) {
        boolean hasLan = false;
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if(target.id.equals(Lan.ID))
                    hasLan = true;
            }
        }
        if(hasLan) {
            if (this.attackTimes < 4)
                setMove(MOVES[0], (byte) 1, Intent.ATTACK, 5, this.attackTimes, true);
            else
                setMove(MOVES[1], (byte) 1, Intent.ATTACK, 5, this.attackTimes, true);
        }
        else {
            setMove((byte) 2,Intent.ESCAPE);
        }
    }

    public void takeTurn() {
        switch (this.nextMove){
            case 1:
                for(int i = 0;i < this.attackTimes;i++) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, 5, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
                this.attackTimes += 1;
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new EscapeAction(this));
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void die()
    {
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if(target.id.equals(Lan.ID)){
                    for(AbstractPower power:target.powers){
                        if(power instanceof PlatedArmorPower)
                            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(target,this,power));
                    }
                }
            }
        }
        this.deathTimer += 1.5F;
        super.die();
    }
}
