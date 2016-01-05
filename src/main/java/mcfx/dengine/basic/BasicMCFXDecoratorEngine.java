package mcfx.dengine.basic;

import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.Versioned;
import mcfx.dengine.basic.decorator.BasicMCFXButtonDecorator;
import mcfx.dengine.basic.decorator.BasicMCFXToggleButtonDecorator;
import mcfx.ui.component.MButton;
import truetyper.TrueTypeFont;

import java.awt.Color;
import java.awt.Font;

@Named("Basic")
@Versioned("0.0.0.0")
public final class BasicMCFXDecoratorEngine
extends MCFXDecoratorEngine{
    private BasicMCFXDecoratorEngine(){}

    @Override
    public void init() {
        this.addProperty("mcfx.font", new TrueTypeFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12), true))
            .addProperty(MButton.BG_COLOR, Color.BLACK)
            .addProperty(MButton.FG_COLOR, Color.WHITE);

        this.addDecorator(BasicMCFXButtonDecorator.class);
        this.addDecorator(BasicMCFXToggleButtonDecorator.class);
    }
}