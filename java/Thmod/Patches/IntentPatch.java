package Thmod.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import Thmod.Relics.CrystalOfMemory;
import Thmod.ThMod;
import basemod.DevConsole;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.AbstractMonster", method="renderTip")
public class IntentPatch {
    @SpireInsertPatch(rloc=12, localvars={"tips"})
    public static void Insert(AbstractMonster _inst,SpriteBatch sb,ArrayList<PowerTip> tips)
    {
        if(AbstractDungeon.player.hasRelic("KoishisEye")){
            boolean noChange = false;
            if(AbstractDungeon.player.hasRelic(CrystalOfMemory.ID)){
                if (((CrystalOfMemory) AbstractDungeon.player.getRelic(CrystalOfMemory.ID)).number[0] == 2){
                    noChange = true;
                }
            }
            if(!noChange) {
                tips.clear();
            }
        }
    }
}
