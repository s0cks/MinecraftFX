package mcfx.dengine.basic;

import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.Versioned;
import mcfx.dengine.basic.decorator.BasicMCFXButtonDecorator;
import mcfx.dengine.basic.decorator.BasicMCFXImageDecorator;
import mcfx.dengine.basic.decorator.BasicMCFXToggleButtonDecorator;
import truetyper.TrueTypeFont;

import java.awt.Font;

@Named("Basic")
@Versioned("0.0.0.0")
public final class BasicMCFXDecoratorEngine
extends MCFXDecoratorEngine{
    private BasicMCFXDecoratorEngine(){}

    @Override
    public void init() {
        this.addProperty("mcfx.font", new TrueTypeFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12), true));

        this.addDecorator(BasicMCFXButtonDecorator.class)
            .addDecorator(BasicMCFXToggleButtonDecorator.class)
            .addDecorator(BasicMCFXImageDecorator.class);
    }
}