package Thmod.Patches.EventPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.events.city.TheLibrary;

@SpirePatch(
        clz=TheLibrary.class,
        method= SpirePatch.CLASS
)
public class TheLibraryField {
    public static SpireField<Boolean> isAddOption = new SpireField<>(() -> true);
}
