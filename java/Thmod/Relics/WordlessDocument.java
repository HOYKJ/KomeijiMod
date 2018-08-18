package Thmod.Relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WordlessDocument extends AbstractThRelic {
    public static final String ID = "WordlessDocument";

    public WordlessDocument()
    {
        super("WordlessDocument",  RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new WordlessDocument();
    }
}
