package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.CardRewardScreen", method="placeCards")
public class CardRewardScreenPatch {
    public static void Prefix(CardRewardScreen Obj){
        if (Obj.rewardGroup.size() == 5){
            Obj.rewardGroup.get(0).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH * 1.6f - (40.0f * Settings.scale) * 1.6f;
            Obj.rewardGroup.get(1).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH * 0.8f - (40.0f * Settings.scale) * 0.8f;
            Obj.rewardGroup.get(2).target_x = Settings.WIDTH / 2.0f ;
            Obj.rewardGroup.get(3).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH * 0.8f + (40.0f * Settings.scale) * 0.8f;
            Obj.rewardGroup.get(4).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH * 1.6f + (40.0f * Settings.scale) * 1.6f;
            Obj.rewardGroup.get(0).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(1).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(2).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(3).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(4).target_y = Settings.HEIGHT * 0.45f;
        }
        else if(Obj.rewardGroup.size() == 6){
            Obj.rewardGroup.get(0).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH * 2.5f;
            Obj.rewardGroup.get(1).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH * 1.5f;
            Obj.rewardGroup.get(2).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH * 0.5f;
            Obj.rewardGroup.get(3).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH * 0.5f;
            Obj.rewardGroup.get(4).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH * 1.5f;
            Obj.rewardGroup.get(5).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH * 2.5f;
            Obj.rewardGroup.get(0).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(1).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(2).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(3).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(4).target_y = Settings.HEIGHT * 0.45f;
            Obj.rewardGroup.get(5).target_y = Settings.HEIGHT * 0.45f;
        }
    }
}
