package Thmod.Patches.BasemodFix;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;

import Thmod.Screens.CustomRelicViewScreen;

@SpirePatch(cls = "com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen", method = "<ctor>", paramtypes = { "boolean" })
public class RelicLibraryPatch
{
    @SpireInsertPatch(rloc = 1)
    public static void Insert(final MainMenuScreen _inst, final boolean b) {
        _inst.relicScreen = new CustomRelicViewScreen();
    }
}
