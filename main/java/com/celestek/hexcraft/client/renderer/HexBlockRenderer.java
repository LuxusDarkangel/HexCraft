package com.celestek.hexcraft.client.renderer;

import coloredlightscore.src.api.CLApi;
import com.celestek.hexcraft.HexCraft;
import com.celestek.hexcraft.block.BlockGlowingHexoriumGlass;
import com.celestek.hexcraft.block.BlockHexoriumLamp;
import com.celestek.hexcraft.block.BlockHexoriumLampInv;
import com.celestek.hexcraft.block.BlockOfHexoriumCrystal;
import com.celestek.hexcraft.client.HexClientProxy;
import com.celestek.hexcraft.util.HexColors;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * @author Thorinair   <celestek@openmailbox.org>
 * @version 0.5.1
 * @since 2015-04-14
 */

public class HexBlockRenderer implements ISimpleBlockRenderingHandler {

    // Brightness when lamp is OFF.
    private static float darkLamp = 0.15F;

    // Variables
    private int renderID;
    private int renderBlockID;
    private int brightness;
    private float r = 1F;
    private float g = 1F;
    private float b = 1F;

    /**
     * Constructor for custom block rendering.
     * @param renderID Minecraft's internal ID of a certain block.
     * @param brightness Intensity of the inner block layer glow.
     * @param r Red component of the inner block layer color.
     * @param g Green component of the inner block layer color.
     * @param b Blue component of the inner block layer color.
     */
    public HexBlockRenderer(int renderID, int brightness, float r, float g, float b)
    {
        // Save the current HexCraft block ID.
        this.renderBlockID = HexCraft.idCounter;

        // Load the constructor parameters.
        this.renderID = renderID;

        if (Loader.isModLoaded("coloredlightscore"))
            this.brightness = HexColors.brightnessCL;
        else
            this.brightness = brightness;

        this.r = r;
        this.g = g;
        this.b = b;

        /** Increment block counter in HexCraft class. */
        HexCraft.idCounter++;
    }

    /**
     * Render the inventory block icon.
     */
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        // Create the Tessellator and prepare OpenGL.
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        // Turn Mipmap OFF.
        int minFilter = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        int magFilter = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        // Start drawing inner layer of the block.
        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        if (block instanceof BlockHexoriumLamp)
            tessellator.setColorOpaque_F(r * darkLamp, g * darkLamp, b * darkLamp);
        else if (block instanceof BlockHexoriumLampInv)
            tessellator.setColorOpaque_F(r, g, b);
        else
            tessellator.setColorOpaque_F(r, g, b);
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(6, 1));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        if (block instanceof BlockHexoriumLamp)
            tessellator.setColorOpaque_F(r * darkLamp, g * darkLamp, b * darkLamp);
        else if (block instanceof BlockHexoriumLampInv)
            tessellator.setColorOpaque_F(r, g, b);
        else
            tessellator.setColorOpaque_F(r, g, b);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        // If special faces should be drawn, use those textures instead.
        if(block instanceof BlockOfHexoriumCrystal)
            renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(7, 1));
        else
            renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(6, 1));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        if (block instanceof BlockHexoriumLamp)
            tessellator.setColorOpaque_F(r * darkLamp, g * darkLamp, b * darkLamp);
        else if (block instanceof BlockHexoriumLampInv)
            tessellator.setColorOpaque_F(r, g, b);
        else
            tessellator.setColorOpaque_F(r, g, b);
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        if(block instanceof BlockOfHexoriumCrystal)
            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(8, 1));
        else
            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(6, 1));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        if (block instanceof BlockHexoriumLamp)
            tessellator.setColorOpaque_F(r * darkLamp, g * darkLamp, b * darkLamp);
        else if (block instanceof BlockHexoriumLampInv)
            tessellator.setColorOpaque_F(r, g, b);
        else
            tessellator.setColorOpaque_F(r, g, b);
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        if(block instanceof BlockOfHexoriumCrystal)
            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(9, 1));
        else
            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(6, 1));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        if (block instanceof BlockHexoriumLamp)
            tessellator.setColorOpaque_F(r * darkLamp, g * darkLamp, b * darkLamp);
        else if (block instanceof BlockHexoriumLampInv)
            tessellator.setColorOpaque_F(r, g, b);
        else
            tessellator.setColorOpaque_F(r, g, b);
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        if(block instanceof BlockOfHexoriumCrystal)
            renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(10, 1));
        else
            renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(6, 1));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setBrightness(brightness);
        if (block instanceof BlockHexoriumLamp)
            tessellator.setColorOpaque_F(r * darkLamp, g * darkLamp, b * darkLamp);
        else if (block instanceof BlockHexoriumLampInv)
            tessellator.setColorOpaque_F(r, g, b);
        else
            tessellator.setColorOpaque_F(r, g, b);
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        if(block instanceof BlockOfHexoriumCrystal)
            renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(11, 1));
        else
            renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(6, 1));
        tessellator.draw();

        // Start drawing outer layer of the block.
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 2));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, 2));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, 2));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, 2));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, 2));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, 2));
        tessellator.draw();

        // Turn Mipmap ON.
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, minFilter);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, magFilter);

        // Finish drawing.
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    /**
     * Renders the block in world.
     */
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        int meta = world.getBlockMetadata(x, y, z);

        // Prepare the Tessellator.
        Tessellator tessellator = Tessellator.instance;

        // Check if this is the first (opaque) render pass, if it is...
        if(HexClientProxy.renderPass[renderBlockID] == 0) {
            // Prepare the inner block texture.
            IIcon c = block.getIcon(6, 0);
            float u = c.getMinU();
            float v = c.getMinV();
            float U = c.getMaxU();
            float V = c.getMaxV();

            // More tessellator preparation.
            tessellator.addTranslation(x, y, z);

            // Set up brightness and color.
            tessellator.setBrightness(brightness);

            if (block instanceof BlockHexoriumLamp || block instanceof BlockHexoriumLampInv) {
                if (meta == 0)
                    tessellator.setColorOpaque_F(r * darkLamp, g * darkLamp, b * darkLamp);
                else if (meta == 1)
                    tessellator.setColorOpaque_F(r, g, b);
            }
            else
                tessellator.setColorOpaque_F(r, g, b);

            // DOWN
            // Check if the block face should be visible. If yes draw it.
            if(block.shouldSideBeRendered(world, x, y - 1, z, 0)) {
                // If special faces should be drawn, use those textures instead.
                if (block instanceof BlockOfHexoriumCrystal) {
                    c = block.getIcon(6, meta);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                else if (block instanceof BlockGlowingHexoriumGlass) {
                    c = block.getIcon(world, x, y, z, 6);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                tessellator.addVertexWithUV(0, 0, 1, u, V);
                tessellator.addVertexWithUV(0, 0, 0, u, v);
                tessellator.addVertexWithUV(1, 0, 0, U, v);
                tessellator.addVertexWithUV(1, 0, 1, U, V);
            }

            // UP
            if(block.shouldSideBeRendered(world, x, y + 1, z, 1)) {
                if (block instanceof BlockOfHexoriumCrystal) {
                    c = block.getIcon(7, meta);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                else if (block instanceof BlockGlowingHexoriumGlass) {
                    c = block.getIcon(world, x, y, z, 7);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                tessellator.addVertexWithUV(0, 1, 0, u, v);
                tessellator.addVertexWithUV(0, 1, 1, u, V);
                tessellator.addVertexWithUV(1, 1, 1, U, V);
                tessellator.addVertexWithUV(1, 1, 0, U, v);
            }

            // NORTH
            if(block.shouldSideBeRendered(world, x, y, z - 1, 2)) {
                if (block instanceof BlockOfHexoriumCrystal) {
                    c = block.getIcon(8, meta);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                else if (block instanceof BlockGlowingHexoriumGlass) {
                    c = block.getIcon(world, x, y, z, 8);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                tessellator.addVertexWithUV(1, 0, 0, u, V);
                tessellator.addVertexWithUV(0, 0, 0, U, V);
                tessellator.addVertexWithUV(0, 1, 0, U, v);
                tessellator.addVertexWithUV(1, 1, 0, u, v);
            }

            // SOUTH
            if(block.shouldSideBeRendered(world, x, y, z + 1, 3)) {
                if (block instanceof BlockOfHexoriumCrystal) {
                    c = block.getIcon(9, meta);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                else if (block instanceof BlockGlowingHexoriumGlass) {
                    c = block.getIcon(world, x, y, z, 9);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                tessellator.addVertexWithUV(0, 1, 1, u, v);
                tessellator.addVertexWithUV(0, 0, 1, u, V);
                tessellator.addVertexWithUV(1, 0, 1, U, V);
                tessellator.addVertexWithUV(1, 1, 1, U, v);
            }

            // WEST
            if(block.shouldSideBeRendered(world, x - 1, y, z, 4)) {
                if (block instanceof BlockOfHexoriumCrystal) {
                    c = block.getIcon(10, meta);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                else if (block instanceof BlockGlowingHexoriumGlass) {
                    c = block.getIcon(world, x, y, z, 10);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                tessellator.addVertexWithUV(0, 0, 0, u, V);
                tessellator.addVertexWithUV(0, 0, 1, U, V);
                tessellator.addVertexWithUV(0, 1, 1, U, v);
                tessellator.addVertexWithUV(0, 1, 0, u, v);
            }

            // EAST
            if(block.shouldSideBeRendered(world, x + 1, y, z, 5)) {
                if (block instanceof BlockOfHexoriumCrystal) {
                    c = block.getIcon(11, meta);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                else if (block instanceof BlockGlowingHexoriumGlass) {
                    c = block.getIcon(world, x, y, z, 11);
                    u = c.getMinU();
                    v = c.getMinV();
                    U = c.getMaxU();
                    V = c.getMaxV();
                }
                tessellator.addVertexWithUV(1, 0, 1, u, V);
                tessellator.addVertexWithUV(1, 0, 0, U, V);
                tessellator.addVertexWithUV(1, 1, 0, U, v);
                tessellator.addVertexWithUV(1, 1, 1, u, v);
            }
            tessellator.addTranslation(-x, -y, -z);
        }
        // If this is the second (transparent) render pass...
        else {
            // If Tessellator doesn't do anything, it will crash, so make a dummy quad.
            tessellator.addVertex(0, 0, 0);
            tessellator.addVertex(0, 0, 0);
            tessellator.addVertex(0, 0, 0);
            tessellator.addVertex(0, 0, 0);

            // Draw the outer layer of the block.
            renderer.renderStandardBlock(block, x, y, z);
        }

        return true;
    }

    /**
     * Retrieves Minecraft's internal ID of a certain block.
     */
    @Override
    public int getRenderId()
    {
        return renderID;
    }

    /**
     * Makes the block render 3D in invenotry.
     */
    @Override
    public boolean shouldRender3DInInventory(int i)
    {
        return true;
    }
}
