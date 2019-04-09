package Thmod.Patches.MusicPatch;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.MainMusic;

public class MainMusicPatch
{
    @SpirePatch(cls="com.megacrit.cardcrawl.audio.MainMusic", method="getSong")
    public static class getSongPatch
    {
        public static Music Postfix(Music res, MainMusic _inst, String key)
        {
            if (key.equals("MENU")) {
                return MainMusic.newMusic("audio/music/目录.mp3");
            }
            return res;
        }
    }
}
