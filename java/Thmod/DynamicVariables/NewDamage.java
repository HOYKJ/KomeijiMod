package Thmod.DynamicVariables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;

public class NewDamage extends DynamicVariable {
    @Override
    public String key() {
        return "[D]";
        // What you put in your localization file between ! to show your value. Eg, !myKey!.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return false;
        // Set to true if the value is modified from the base value.
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {

        // Do something such that isModified will return the value v.
        // This method is only necessary if you want smith upgrade previews to function correctly.
    }

    @Override
    public int value(AbstractCard card) {
        return card.magicNumber + 2;
        // What the dynamic variable will be set to on your card. Usually uses some kind of int you store on your card.
    }

    @Override
    public int baseValue(AbstractCard card) {
        return card.magicNumber + 2;
        // Should generally just be the above.
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return true;
        // Set to true if this value is changed on upgrade
    }
}
