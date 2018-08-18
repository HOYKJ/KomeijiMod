package Thmod.Patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.TempMusic;

import java.util.HashMap;

@SpirePatch(cls="com.megacrit.cardcrawl.audio.TempMusic", method="getSong")
public class TempMusicPatch
{
    private static HashMap<String, String> map = new HashMap();

    public static Music Postfix(Music res, TempMusic _inst, String key)
    {
        if (map.containsKey(key)) {
            return Gdx.audio.newMusic(Gdx.files.internal((String)map.get(key)));
        }
        return res;
    }

    private static String load(String filename)
    {
        return "audio/music/" + filename;
    }

    static
    {
        map.put("TH_BGM_AWAKEN1", load("MysteriousMountain1.mp3"));
        map.put("TH_BGM_AWAKEN2", load("MysteriousMountain2.mp3"));
        map.put("TH_BGM_TIMEEATER", load("LunaDial.mp3"));
        map.put("TH_BGM_DECADONU", load("狂気.mp3"));
        map.put("TH_BGM_REMIRIA",load("七重奏.mp3"));
        map.put("TH_BGM_SHIKIERIKI",load("审判.mp3"));
    }
}
