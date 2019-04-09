package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.Iterator;
import java.util.UUID;

public class ModifyMagicNumber extends AbstractGameAction {
    UUID uuid;

    public ModifyMagicNumber(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        Iterator var1 = GetAllInBattleInstances.get(this.uuid).iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            c.baseMagicNumber += this.amount;
            if (c.baseMagicNumber < 0) {
                c.baseMagicNumber = 0;
            }
            c.magicNumber = c.baseMagicNumber;
        }

        this.isDone = true;
    }
}
