package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class PlayerTalkAction extends AbstractGameAction
{
    private String msg;
    private boolean used;
    private float bubbleDuration;

    public PlayerTalkAction(final AbstractCreature source, final String text, final float duration, final float bubbleDuration) {
        this.used = false;
        this.setValues(source, source);
        if (Settings.FAST_MODE) {
            this.duration = Settings.ACTION_DUR_MED;
        }
        else {
            this.duration = duration;
        }
        this.msg = text;
        this.actionType = AbstractGameAction.ActionType.TEXT;
        this.bubbleDuration = bubbleDuration;
    }

    public PlayerTalkAction(final AbstractCreature source, final String text) {
        this(source, text, 2.0f, 2.0f);
    }

    public void update() {
        if (!this.used) {
            AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, this.bubbleDuration, this.msg, true));
            this.used = true;
        }
        this.tickDuration();
    }
}
