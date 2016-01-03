package mcfx.example;

import mcfx.example.gui.MCFXExampleUI;
import mcfx.example.item.ItemExample;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "mcfx", name = "MCFXExample", version = "0.0.0.0")
public final class MCFXExample
implements IGuiHandler{
    @Mod.Instance("mcfx")
    public static MCFXExample instance;

    public static final Item itemTest = new ItemExample()
            .setCreativeTab(CreativeTabs.tabMisc)
            .setUnlocalizedName("test");

    static{
        try{
            Class.forName("mcfx.MCFXDecoratorEngine");
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e){
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, this);

        GameRegistry.registerItem(itemTest, "itemTest");
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return ID == 0 ? new MCFXExampleUI() : null;
    }
}