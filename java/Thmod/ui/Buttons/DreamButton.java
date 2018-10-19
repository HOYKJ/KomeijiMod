package Thmod.ui.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import Thmod.vfx.campfire.DreamEventEffect;
import basemod.DevConsole;

public class DreamButton {
    private static final float OPTION_SPACING_Y = -82.0F * Settings.scale;
    private static final float TEXT_ADJUST_X = -50.0F * Settings.scale;
    private static final float TEXT_ADJUST_Y = 10.0F * Settings.scale;
    public String msg;
    private Color textColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private Color boxColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private float x;
    private float y = 150.0F * Settings.scale;
    public Hitbox hb;
    private static final float ANIM_TIME = 0.5F;
    private float animTimer = 0.5F;
    private float alpha = 0.0F;
    private static final Color TEXT_ACTIVE_COLOR = Color.WHITE.cpy();
    private static final Color TEXT_INACTIVE_COLOR = new Color(0.8F, 0.8F, 0.8F, 1.0F);
    private static final Color TEXT_DISABLED_COLOR = Color.FIREBRICK.cpy();
    private Color boxInactiveColor = new Color(0.2F, 0.25F, 0.25F, 0.0F);
    public boolean pressed = false;
    public boolean isDisabled;
    public int slot = 0;
    private AbstractCard cardToPreview;
    private static final int W = 890;
    private static final int H = 77;
    private Texture img;

    public DreamButton(int slot, String msg, boolean isDisabled, AbstractCard previewCard)
    {
        this.x = (620.0F * Settings.scale);
        this.slot = slot;
        this.isDisabled = isDisabled;
        this.cardToPreview = previewCard;
        if (isDisabled) {
            this.msg = stripColor(msg);
        } else {
            this.msg = msg;
        }
        this.hb = new Hitbox(210.0F * Settings.scale, 90.0F * Settings.scale);
        if(slot == 0){
            this.img = ImageMaster.loadImage("images/ui/leftButton.png");
            this.x = (620.0F * Settings.scale);
        }
        else {
            this.img = ImageMaster.loadImage("images/ui/rightButton.png");
            this.x = (1220.0F * Settings.scale);
        }
    }

    public DreamButton(int slot, String msg)
    {
        this(slot, msg, false, null);
    }

    public DreamButton(int slot, String msg, boolean isDisabled)
    {
        this(slot, msg, isDisabled, null);
    }

    public DreamButton(int slot, String msg, AbstractCard previewCard)
    {
        this(slot, msg, false, previewCard);
    }

    private String stripColor(String input)
    {
        input = input.replace("#r", "");
        input = input.replace("#g", "");
        input = input.replace("#b", "");
        input = input.replace("#y", "");
        return input;
    }

    public void calculateY(int size)
    {
        if(DreamEventEffect.optionList.size() == 1){
            this.x = 920.0F;
        }
        this.hb.move(this.x, this.y);
    }

    public void update(int size)
    {
        calculateY(size);
        hoverAndClickLogic();
        updateAnimation();
    }

    private void updateAnimation()
    {
        if (this.animTimer != 0.0F)
        {
            this.animTimer -= Gdx.graphics.getDeltaTime();
            if (this.animTimer < 0.0F) {
                this.animTimer = 0.0F;
            }
            this.alpha = Interpolation.exp5In.apply(0.0F, 1.0F, 1.0F - this.animTimer / 0.5F);
//            DevConsole.logger.info(alpha);
        }
        this.textColor.a = this.alpha;
        this.boxColor.a = this.alpha;
    }

    private void hoverAndClickLogic()
    {
        if(this.animTimer == 0.0F) {
            this.hb.update();
            if ((this.hb.hovered) && (InputHelper.justClickedLeft) && (!this.isDisabled) && (this.animTimer < 0.1F)) {
                InputHelper.justClickedLeft = false;
                this.hb.clickStarted = true;
            }
            if ((this.hb.hovered) && (CInputActionSet.select.isJustPressed()) && (!this.isDisabled)) {
                this.hb.clicked = true;
            }
            if (this.hb.clicked) {
                this.hb.clicked = false;
                this.pressed = true;
            }
            if (!this.isDisabled) {
                if (this.hb.hovered) {
                    this.textColor = TEXT_ACTIVE_COLOR;
                    this.boxColor = Color.WHITE.cpy();
                } else {
                    this.textColor = TEXT_INACTIVE_COLOR;
                    this.boxColor = new Color(0.4F, 0.4F, 0.4F, 1.0F);
                }
            } else {
                this.textColor = TEXT_DISABLED_COLOR;
                this.boxColor = this.boxInactiveColor;
            }
            if (this.hb.hovered) {
                if (!this.isDisabled) {
                    this.textColor = TEXT_ACTIVE_COLOR;
                } else {
                    this.textColor = Color.GRAY.cpy();
                }
            } else if (!this.isDisabled) {
                this.textColor = TEXT_ACTIVE_COLOR;
            } else {
                this.textColor = Color.GRAY.cpy();
            }
        }
    }

    public void render(SpriteBatch sb)
    {
//        DevConsole.logger.info("Render ok");
        float scale = Settings.scale;
        if (this.hb.clickStarted) {
            scale *= 0.99F;
        }
        if (this.isDisabled)
        {
//            DevConsole.logger.info("Not able");
        }
        else
        {
            sb.setColor(this.boxColor);
            sb.draw(this.img, this.x - 100.0F, this.y - 43.5F, 100.0F, 43.5F, 200.0F, 87.0F, scale, scale, 0.0F, 0, 0, 200, 87, false, false);

            sb.setBlendFunction(770, 1);
            sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
            sb.draw(this.img, this.x - 100.0F, this.y - 43.5F, 100.0F, 43.5F, 200.0F, 87.0F, scale, scale, 0.0F, 0, 0, 200, 87, false, false);

            sb.setBlendFunction(770, 771);
        }
        if (FontHelper.getSmartWidth(FontHelper.largeDialogOptionFont, this.msg, Settings.WIDTH, 0.0F) > 800.0F * Settings.scale) {
            FontHelper.renderSmartText(sb, FontHelper.smallDialogOptionFont, this.msg, this.x + TEXT_ADJUST_X, this.y + TEXT_ADJUST_Y, Settings.WIDTH, 0.0F, this.textColor);
        } else {
            FontHelper.renderSmartText(sb, FontHelper.largeDialogOptionFont, this.msg, this.x + TEXT_ADJUST_X, this.y + TEXT_ADJUST_Y, Settings.WIDTH, 0.0F, this.textColor);
        }
        this.hb.render(sb);
    }
}
