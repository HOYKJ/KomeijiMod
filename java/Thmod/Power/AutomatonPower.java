package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Orbs.Helan;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;

public class AutomatonPower extends AbstractPower {
    public static final String POWER_ID = "AutomatonPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("AutomatonPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public AutomatonPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "AutomatonPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/AutomatonPower.png");
        this.type = PowerType.BUFF;
    }

    public void atEndOfTurn(boolean isPlayer) {
        int YariNum = 0;
        int TateNum = 0;
        int YumiNum = 0;
        int HealNum = 0;
        if(isPlayer){
            for(int i1 = 0;i1 < this.amount;i1++) {
                if (p.energy.energy > 0) {
                    for (int i = 0; i < p.orbs.size(); i++) {
                        if ((p.orbs.get(i) instanceof YariNingyou) || (p.orbs.get(i) instanceof Shanghai) || (p.orbs.get(i) instanceof Penglai))
                            YariNum += 1;
                        if ((p.orbs.get(i) instanceof TateNingyou) || (p.orbs.get(i) instanceof Helan))
                            TateNum += 1;
                        if (p.orbs.get(i) instanceof YumiNingyou)
                            YumiNum += 1;
                    }
                    p.loseEnergy(1);
                    for (int i = 0; i < YariNum; i++) {
                        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                        if(m != null)
                            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                    }
                    for (int i = 0; i < TateNum; i++)
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 2, true));
                    for (int i = 0; i < YumiNum; i++) {
                        if (HealNum == 1) {
                            p.heal(1);
                            HealNum = 0;
                        } else
                            HealNum += 1;
                    }
                }
                else
                    break;
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
