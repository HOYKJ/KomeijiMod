package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;

import java.util.HashMap;

@SpirePatch(cls="com.megacrit.cardcrawl.audio.SoundMaster", method="play", paramtypes={"java.lang.String", "boolean"})
public class SoundMasterplayPatch
{
    public static HashMap<String, Sfx> map = new HashMap();

    public static long Postfix(long res, SoundMaster _inst, String key, boolean useBgmVolume)
    {
        if (map.containsKey(key)) {
            if (useBgmVolume)
                return (map.get(key)).play(Settings.MUSIC_VOLUME * Settings.MASTER_VOLUME);

            return (map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME);
        }
        return res;
    }

    private static Sfx load(String filename)
    {
        return new Sfx("audio/sound/" + filename, false);
    }

    static
    {
        map.put("playerScore", load("playerScore.ogg"));
        map.put("select00", load("select00.ogg"));
        map.put("world", load("world.ogg"));
        map.put("world_ot", load("world_ot.ogg"));
        map.put("Fire_Remnant1", load("Fire_Remnant1.ogg"));
        map.put("Fire_Remnant2", load("Fire_Remnant2.ogg"));
        map.put("graze", load("graze.ogg"));
        map.put("power0", load("power0.ogg"));
        map.put("nep00", load("nep00.ogg"));
    }
}
