package Thmod.Power;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.DeriveCards.Boundaries;
import Thmod.Monsters.Yukari;

public class BoundariesPower extends AbstractPower {
    public static final String POWER_ID = "BoundariesPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BoundariesPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public BoundariesPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "BoundariesPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/BoundariesPower.png");
        this.type = PowerType.BUFF;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type)
    {
        if (damage > 0.0F) {
            damage = 0.0F;
        }
        return damage;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (damage > 0.0F) {
            damage = 0.0F;
        }
        return damage;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        return 0;
    }

    @Override
    public int onLoseHp(int damageAmount) {
        return 0;
    }

    public void onRemove() {
        for(AbstractCard c : p.hand.group){
            if(c instanceof Boundaries){
                AbstractDungeon.player.hand.removeCard(c);
            }
        }
        for(AbstractCard c : p.discardPile.group){
            if(c instanceof Boundaries){
                AbstractDungeon.player.discardPile.removeCard(c);
            }
        }
        for(AbstractCard c : p.drawPile.group){
            if(c instanceof Boundaries){
                AbstractDungeon.player.drawPile.removeCard(c);
            }
        }
        AbstractMonster monster = (AbstractMonster) this.owner;
        ((Yukari) monster).changeImg();
        ((Yukari) monster).changeIntend();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
