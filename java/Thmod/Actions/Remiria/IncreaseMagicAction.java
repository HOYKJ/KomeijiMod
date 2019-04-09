package Thmod.Actions.Remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.UUID;

import Thmod.Cards.ScarletCard.uncommonCards.YoungDemonLord;

public class IncreaseMagicAction extends AbstractGameAction
{
    private int miscIncrease;
    private UUID uuid;

    public IncreaseMagicAction(UUID targetUUID, int miscIncrease)
    {
        this.miscIncrease = miscIncrease;

        this.uuid = targetUUID;
    }

    public void update()
    {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(this.uuid))
            {
                if(c instanceof YoungDemonLord) {
                    ((YoungDemonLord) c).mis2[0] += this.miscIncrease;
                    c.baseMagicNumber = ((YoungDemonLord) c).mis2[0];
                    c.applyPowers();
                    c.magicNumber = c.baseMagicNumber;
                    c.isMagicNumberModified = false;
                }
            }
        }
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid))
        {
            if(c instanceof YoungDemonLord) {
                ((YoungDemonLord) c).mis2[0] += this.miscIncrease;
                c.baseMagicNumber = ((YoungDemonLord) c).mis2[0];
                c.applyPowers();
                c.magicNumber = c.baseMagicNumber;
            }
        }
        this.isDone = true;
    }
}
