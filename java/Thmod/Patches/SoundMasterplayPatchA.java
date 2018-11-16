package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.Settings;

import java.util.HashMap;

@SpirePatch(cls="com.megacrit.cardcrawl.audio.SoundMaster", method="playA", paramtypes={"java.lang.String", "float"})
public class SoundMasterplayPatchA
{
    public static HashMap<String, Sfx> map = new HashMap();

    public static long Postfix(long res, SoundMaster _inst, String key, float pitchAdjust)
    {
        if (map.containsKey(key))
            return (map.get(key)).play(Settings.SOUND_VOLUME * Settings.MASTER_VOLUME, 1F + pitchAdjust, 0F);


        return 0L;
    }

    private static Sfx load(String filename)
    {
        return new Sfx("audio/sound/" + filename, false);
    }

    static
    {
        map.put("select00", load("select00.ogg"));
        map.put("world", load("world.ogg"));
        map.put("world_ot", load("world_ot.ogg"));
        map.put("power0", load("power0.ogg"));
        map.put("nep00", load("nep00.ogg"));
    }
}
