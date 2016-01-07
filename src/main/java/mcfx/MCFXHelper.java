package mcfx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3i;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.ItemModelMesherForge;
import net.minecraftforge.client.model.IColoredBakedQuad;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;
import truetyper.FontHelper;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;

public final class MCFXHelper{
    private static final ResourceLocation texGlint = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private static final ItemModelMesher mesher = new ItemModelMesherForge(FMLClientHandler.instance().getClient().getBlockRendererDispatcher().getBlockModelShapes().getModelManager());

    public static void drawString(String str, int x, int y, Color c){
        float[] color = {
            c.getRed(),
            c.getGreen(),
            c.getBlue(),
            c.getAlpha()
        };
        FontHelper.drawString(str, x, y, MCFXDecoratorEngine.get().font(), 1.0F, 1.0F, color);
    }

    public static IBakedModel getModel(ItemStack stack){
        return mesher.getItemModel(stack);
    }

    public static void renderBakedModel(IBakedModel model, int color, ItemStack stack){
        Minecraft mc = FMLClientHandler.instance().getClient();
        mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);

        if(model.isBuiltInRenderer() && stack != null){
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableRescaleNormal();
            TileEntityItemStackRenderer.instance.renderByItem(stack);
        } else{
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            renderModel(model, color, stack);

            if(stack != null && stack.getItem() != null && stack.hasEffect()){
                GlStateManager.depthMask(false);
                GlStateManager.depthFunc(GL11.GL_EQUAL);
                GlStateManager.disableLighting();
                GlStateManager.blendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                mc.getTextureManager().bindTexture(texGlint);
                GlStateManager.matrixMode(GL11.GL_TEXTURE);
                GlStateManager.pushMatrix();
                GlStateManager.scale(8.0F, 8.0F, 8.0F);
                float f = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
                GlStateManager.translate(f, 0.0F, 0.0F);
                GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
                renderModel(model, -8372020, null);
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.scale(8.0F, 8.0F, 8.0F);
                float f1 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
                GlStateManager.translate(-f1, 0.0F, 0.0F);
                GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
                renderModel(model, -8372020, null);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(GL11.GL_MODELVIEW);
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.enableLighting();
                GlStateManager.depthFunc(GL11.GL_LEQUAL);
                GlStateManager.depthMask(true);
                mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            }
        }

        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
    }

    private static void renderModel(IBakedModel model, int color, ItemStack stack){
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer rend = tess.getWorldRenderer();
        rend.startDrawingQuads();
        rend.setVertexFormat(DefaultVertexFormats.ITEM);
        for (EnumFacing face : EnumFacing.values()) {
            renderQuads(rend, model.getFaceQuads(face), color, stack);
        }
        renderQuads(rend, model.getGeneralQuads(), color, stack);
        tess.draw();
    }

    private static void renderQuads(WorldRenderer rend, List quads, int color, ItemStack stack){
        BakedQuad quad;
        int j;

        for(Iterator it = quads.iterator(); it.hasNext(); renderQuad(rend, quad, j)){
            quad = (BakedQuad) it.next();
            j = color;

            if(color == -1){
                if(quad.hasTintIndex() && stack != null && stack.getItem() != null){
                    j = stack.getItem().getColorFromItemStack(stack, quad.getTintIndex());

                    if(EntityRenderer.anaglyphEnable){
                        j = TextureUtil.anaglyphColor(j);
                    }

                    j |= -16777216;
                }
            }
        }
    }

    private static void renderQuad(WorldRenderer rend, BakedQuad quad, int color){
        rend.addVertexData(quad.getVertexData());
        if(quad instanceof IColoredBakedQuad){
            ForgeHooksClient.putQuadColor(rend, quad, color);
        } else{
            rend.putColor4(color);
        }

        Vec3i vec = quad.getFace().getDirectionVec();
        rend.putNormal((float) vec.getX(), (float) vec.getY(), (float) vec.getZ());
    }

    public static ScaledResolution getScaledResolutionFromMinecraft(Minecraft mc){
        return new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
    }

    public static int getMinecraftWidth(){
        return FMLClientHandler.instance().getClient().displayWidth;
    }

    public static int getMinecraftHeight(){
        return FMLClientHandler.instance().getClient().displayHeight;
    }

    public static Dimension getPreferredMaxSize(){
        return new Dimension(getMinecraftWidth() / 4, getMinecraftHeight() / 4);
    }

    public static RenderItem getRenderItem(){
        return FMLClientHandler.instance().getClient().getRenderItem();
    }

    public static float fontWidth(String str){
        return MCFXDecoratorEngine.get().font().getWidth(str);
    }

    public static float fontHeight(){
        return MCFXDecoratorEngine.get().font().getHeight();
    }

    public static Color complement(Color c){
        return new Color(255 - c.getRed(),
                         255 - c.getGreen(),
                         255 - c.getBlue(),
                         c.getAlpha());
    }
}