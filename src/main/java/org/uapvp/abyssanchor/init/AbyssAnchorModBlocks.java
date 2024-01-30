
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package org.uapvp.abyssanchor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import org.uapvp.abyssanchor.block.AbyssAnchorStage1Block;
import org.uapvp.abyssanchor.block.AbyssAnchorBlock;
import org.uapvp.abyssanchor.AbyssAnchorMod;

public class AbyssAnchorModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, AbyssAnchorMod.MODID);
	public static final RegistryObject<Block> ABYSS_ANCHOR_STAGE_1 = REGISTRY.register("abyss_anchor_stage_1", () -> new AbyssAnchorStage1Block());
	public static final RegistryObject<Block> ABYSS_ANCHOR = REGISTRY.register("abyss_anchor", () -> new AbyssAnchorBlock());
}
