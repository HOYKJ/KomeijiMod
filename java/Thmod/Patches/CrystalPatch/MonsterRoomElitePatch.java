package Thmod.Patches.CrystalPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import Thmod.Characters.KomeijiSatori;
import Thmod.Relics.CrystalOfMemory;

public class MonsterRoomElitePatch {

    @SpirePatch(
            clz = MonsterRoomElite.class,
            method = "dropReward"
    )
    public static class dropReward {
        //@SpireInsertPatch(rloc = 0)
        public static void Postfix(MonsterRoomElite _inst) {
            if (AbstractDungeon.player instanceof KomeijiSatori) {
                if (!AbstractDungeon.player.hasRelic(CrystalOfMemory.ID)) {
                    RewardItem rel = new RewardItem(new CrystalOfMemory());
                    rel.type = RewardItemEnum.CRYSTAL;
                    _inst.rewards.add(rel);
                    _inst.rewards.get(_inst.rewards.size() - 2).relicLink = rel;
                    rel.relicLink = _inst.rewards.get(_inst.rewards.size() - 2);
                }
            }
        }
    }
}
