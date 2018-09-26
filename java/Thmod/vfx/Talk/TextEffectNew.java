package Thmod.vfx.Talk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.ui.SpeechWord;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TextEffectNew extends AbstractGameEffect {
    private static GlyphLayout gl;
    private BitmapFont font;
    private DialogWord.AppearEffect a_effect;
    private static final float DEFAULT_WIDTH;
    private static final float LINE_SPACING;
    private static final float CHAR_SPACING;
    private static final float WORD_TIME = 0.03F;
    private float wordTimer = 0.0F;
    private boolean textDone = false;
    private float x;
    private float y;
    private ArrayList<SpeechWord> words = new ArrayList();
    private int curLine = 0;
    private Scanner s;
    private float curLineWidth = 0.0F;
    private static final float FADE_TIME = 0.3F;
    private boolean LclickStart;
    private boolean Lclick;
    private boolean RclickStart;
    private boolean Rclick;

    public TextEffectNew(float x, float y, String msg, DialogWord.AppearEffect a_effect) {
        if (gl == null) {
            gl = new GlyphLayout();
        }

        this.x = x;
        this.y = y;
        this.font = FontHelper.speech_font;
        this.a_effect = a_effect;
        this.s = new Scanner(msg);
        this.duration = 0.3F;

        this.Lclick = false;
        this.LclickStart = false;
        this.Rclick = false;
        this.RclickStart = false;
    }

    public void update() {
        this.wordTimer -= Gdx.graphics.getDeltaTime();
        if (this.wordTimer < 0.0F && !this.textDone) {
            if (Settings.lineBreakViaCharacter) {
                this.addWordCN();
            } else {
                this.addWord();
            }
        }

        Iterator var1 = this.words.iterator();

        SpeechWord w;
        while(var1.hasNext()) {
            w = (SpeechWord)var1.next();
            w.update();
        }

        if(InputHelper.justClickedLeft){
            this.LclickStart = true;
        }
        if((this.LclickStart) && (InputHelper.justReleasedClickLeft)){
            this.Lclick = true;
        }
        if(InputHelper.justClickedRight){
            this.RclickStart = true;
        }
        if((this.RclickStart) && (InputHelper.justReleasedClickRight)){
            this.Rclick = true;
        }

        if((this.Lclick) || (this.Rclick)) {
            this.duration -= Gdx.graphics.getDeltaTime();
        }

        if ((this.Lclick) && (this.duration == 0)) {
            this.words.clear();
            this.isDone = true;
            this.Lclick = false;
        }
        if ((this.Rclick) && (this.duration == 0)) {
            this.words.clear();
            this.isDone = true;
            this.Rclick = false;
        }

        if((this.Lclick) || (this.Rclick)) {
            var1 = this.words.iterator();

            while(var1.hasNext()) {
                w = (SpeechWord)var1.next();
                w.fadeOut();
            }
        }

    }

    private void addWord() {
        this.wordTimer = 0.03F;
        if (this.s.hasNext()) {
            String word = this.s.next();
            if (word.equals("NL")) {
                ++this.curLine;
                Iterator var7 = this.words.iterator();

                while(var7.hasNext()) {
                    SpeechWord w = (SpeechWord)var7.next();
                    w.shiftY(LINE_SPACING);
                }

                this.curLineWidth = 0.0F;
                return;
            }

            DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
            if (color != DialogWord.WordColor.DEFAULT) {
                word = word.substring(2, word.length());
            }

            DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
            if (effect != DialogWord.WordEffect.NONE) {
                word = word.substring(1, word.length() - 1);
            }

            gl.setText(this.font, word);
            float temp = 0.0F;
            Iterator var5;
            SpeechWord w;
            if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
                ++this.curLine;
                var5 = this.words.iterator();

                while(var5.hasNext()) {
                    w = (SpeechWord)var5.next();
                    w.shiftY(LINE_SPACING);
                }

                this.curLineWidth = gl.width + CHAR_SPACING;
                temp = -this.curLineWidth / 2.0F;
            } else {
                this.curLineWidth += gl.width;
                temp = -this.curLineWidth / 2.0F;
                var5 = this.words.iterator();

                while(var5.hasNext()) {
                    w = (SpeechWord)var5.next();
                    if (w.line == this.curLine) {
                        w.setX(this.x + temp);
                        gl.setText(this.font, w.word);
                        temp += gl.width + CHAR_SPACING;
                    }
                }

                this.curLineWidth += CHAR_SPACING;
                gl.setText(this.font, word + " ");
            }

            this.words.add(new SpeechWord(this.font, word, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * (float)this.curLine, this.curLine));
        } else {
            this.textDone = true;
            this.s.close();
        }

    }

    private void addWordCN() {
        this.wordTimer = 0.03F;
        if (this.s.hasNext()) {
            String word = this.s.next();
            if (word.equals("NL")) {
                return;
            }

            DialogWord.WordColor color = SpeechWord.identifyWordColor(word);
            if (color != DialogWord.WordColor.DEFAULT) {
                word = word.substring(2, word.length());
            }

            DialogWord.WordEffect effect = SpeechWord.identifyWordEffect(word);
            if (effect != DialogWord.WordEffect.NONE) {
                word = word.substring(1, word.length() - 1);
            }

            for(int i = 0; i < word.length(); ++i) {
                String tmp = Character.toString(word.charAt(i));
                gl.setText(this.font, tmp);
                float temp = 0.0F;
                Iterator var7;
                SpeechWord w;
                if (this.curLineWidth + gl.width > DEFAULT_WIDTH) {
                    ++this.curLine;
                    var7 = this.words.iterator();

                    while(var7.hasNext()) {
                        w = (SpeechWord)var7.next();
                        w.shiftY(LINE_SPACING);
                    }

                    this.curLineWidth = gl.width;
                    temp = -this.curLineWidth / 2.0F;
                } else {
                    this.curLineWidth += gl.width;
                    temp = -this.curLineWidth / 2.0F;
                    var7 = this.words.iterator();

                    while(var7.hasNext()) {
                        w = (SpeechWord)var7.next();
                        if (w.line == this.curLine) {
                            w.setX(this.x + temp);
                            gl.setText(this.font, w.word);
                            temp += gl.width;
                        }
                    }

                    gl.setText(this.font, tmp + " ");
                }

                this.words.add(new SpeechWord(this.font, tmp, this.a_effect, effect, color, this.x + temp, this.y - LINE_SPACING * (float)this.curLine, this.curLine));
            }
        } else {
            this.textDone = true;
            this.s.close();
        }

    }

    public void render(SpriteBatch sb) {
        Iterator var2 = this.words.iterator();

        while(var2.hasNext()) {
            SpeechWord w = (SpeechWord)var2.next();
            w.render(sb);
        }

    }

    static {
        DEFAULT_WIDTH = 280.0F * Settings.scale;
        LINE_SPACING = 15.0F * Settings.scale;
        CHAR_SPACING = 8.0F * Settings.scale;
    }
}
