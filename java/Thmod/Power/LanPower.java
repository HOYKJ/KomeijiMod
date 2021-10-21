package Thmod.Power;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class LanPower extends AbstractPower {
    public static final String POWER_ID = "LanPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("LanPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private Texture adventurerImg;

    public LanPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "LanPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/LanPower.png");
        this.type = PowerType.BUFF;
        this.adventurerImg = ImageMaster.loadImage("images/monsters/Lan/Main.png");
    }

    public void atEndOfTurn(boolean isPlayer) {
        int newAmount = this.amount % 3;
        switch (newAmount) {
            case 3:
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 10));
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 3, false), 3));
                }
                this.amount -= 1;
                this.description = DESCRIPTIONS[1];

                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 10));
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 3, false), 3));
                }
                this.amount -= 1;
                this.description = DESCRIPTIONS[2];

                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 10));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
                if(this.amount == 1) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, this));
                }
                else{
                    this.amount -= 1;
                }
                break;
            default:
                if(this.amount == 0) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, this));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 10));
                    for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 3, false), 3));
                    }
                    this.amount -= 1;
                    this.description = DESCRIPTIONS[1];
                }
        }
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        sb.setColor(c);
        sb.draw(this.adventurerImg, (850 * Settings.scale) - 256.0F, (590 * Settings.scale) - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, true, false);

    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}

