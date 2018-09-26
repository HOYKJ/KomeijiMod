package Thmod.Orbs.ElementOrb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.ArrayList;
import java.util.Scanner;

import Thmod.Cards.ElementCards.AbstractElementCards;
import basemod.DevConsole;

public abstract class AbstractElementOrb extends AbstractOrb {
    public AbstractElementCards.ElementType elementType;
    private ArrayList<PowerTip> tips = new ArrayList();
    private boolean hasTips = false;

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 10.0f;
    }

    @Override
    public void applyFocus() {
        super.applyFocus();

    }

    @Override
    public void update() {
//        super.update();
        this.hb.update();

        if(!(this.hasTips)){
            this.tips.add(new PowerTip(this.name, this.description));
            Scanner desc = new Scanner(this.description);
            while (desc.hasNext())
            {
                String s = desc.next();
                if (s.charAt(0) == '#') {
                    s = s.substring(2);
                }
                s = s.replace(',', ' ');
                s = s.replace('.', ' ');
                s = s.trim();
                s = s.toLowerCase();

                boolean alreadyExists = false;
                if (GameDictionary.keywords.containsKey(s))
                {
                    s =  GameDictionary.parentWord.get(s);
                    for (PowerTip t : this.tips) {
                        if (t.header.toLowerCase().equals(s))
                        {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        this.tips.add(new PowerTip(TipHelper.capitalize(s), GameDictionary.keywords.get(s)));
                    }
                }
            }
            desc.close();
            this.hasTips = true;
        }
        if (this.hb.hovered) {
//            TipHelper.renderGenericTip(this.tX + 96.0F * Settings.scale, this.tY + 64.0F * Settings.scale, this.name, this.description);
            TipHelper.queuePowerTips(this.tX + 96.0F * Settings.scale, this.tY + 64.0F * Settings.scale * Settings.scale, this.tips);
        }

        this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7F);
    }

    public void playChannelSFX()
    {
    }

    public void onEvoke() {
    }
}
