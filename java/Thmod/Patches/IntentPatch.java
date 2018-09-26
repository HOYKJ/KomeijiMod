package Thmod.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.ThMod;
import basemod.DevConsole;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.AbstractMonster", method="renderTip")
public class IntentPatch {
    @SpireInsertPatch(rloc=4, localvars={"tips"})
    public static void Insert(AbstractMonster _inst,SpriteBatch sb,ArrayList<PowerTip> tips)
    {
        if(AbstractDungeon.player.hasRelic("KoishisEye")){
            if(!(AbstractDungeon.player.hasRelic("Runic Dome"))){
                tips.clear();
//                for(int i = 0;i < tips.size();i++){
//                    DevConsole.logger.info(i + tips.get(i).header);
//                }
            }
        }
    }
}
