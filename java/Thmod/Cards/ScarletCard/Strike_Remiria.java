package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.BaseModCardTags;
import basemod.helpers.TooltipInfo;

public class Strike_Remiria extends AbstractRemiriaCards {
    public static final String ID = "Strike_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Strike_Remiria() {
        this(false);
    }

    public Strike_Remiria(boolean isPlus) {
        super("Strike_Remiria", Strike_Remiria.NAME,  1, Strike_Remiria.DESCRIPTION, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, isPlus);
        this.baseDamage = 6;
        tags.add(CardTags.STARTER_STRIKE);
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Strike_Remiria(true);
            }
        }
        return new Strike_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Strike_Remiria");
        NAME = Strike_Remiria.cardStrings.NAME;
        DESCRIPTION = Strike_Remiria.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Strike_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
