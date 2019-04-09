package Thmod.Cards.RareCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Relics.KoishisEye;

public class SubterraneanRose extends AbstractKomeijiCards {
    public static final String ID = "SubterraneanRose";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public SubterraneanRose() {
        super("SubterraneanRose", SubterraneanRose.NAME,  3, SubterraneanRose.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, CardSet_k.KOISHI);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.PURPLE.cpy(), ShockWaveEffect.ShockWaveType.CHAOTIC), 0.5F));

        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new WeakPower(target, this.magicNumber,false), this.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new VulnerablePower(target, this.magicNumber,false), this.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new FrailPower(target, this.magicNumber,false), this.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new StrengthPower(target, -this.magicNumber), -this.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new DexterityPower(target, -this.magicNumber), -this.magicNumber));
            }
        }
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasRelic(KoishisEye.ID)) {
                return new SubterraneanRose();
            }
            return new TerribleSouvenir();
        }
        return new SubterraneanRose();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SubterraneanRose");
        NAME = SubterraneanRose.cardStrings.NAME;
        DESCRIPTION = SubterraneanRose.cardStrings.DESCRIPTION;
    }
}
