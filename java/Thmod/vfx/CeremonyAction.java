package Thmod.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import Thmod.Relics.SpellCardsRule;
import basemod.DevConsole;

public class CeremonyAction extends AbstractGameEffect {
    private String code = "";

    public void update() {
        for(int i = 0;i < 81;i++){
            if(SpellCardsRule.torchLight.get(i))
                this.code += "1";
            else
                this.code += "0";
        }
//        DevConsole.logger.info("ceremonied?" + this.code);
        SpellCardsRule.ceremonied = this.code.equals("001000100001101100001111100011000110101000101011000110001111100001101100001000100");
        DevConsole.logger.info("true?" + SpellCardsRule.ceremonied);
        this.isDone = true;
    }

    public void render(SpriteBatch sb)
    {

    }

    public void dispose(){}
}
