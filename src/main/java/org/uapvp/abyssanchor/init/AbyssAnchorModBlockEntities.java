
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package org.uapvp.abyssanchor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import org.uapvp.abyssanchor.block.entity.AbyssAnchorStage1BlockEntity;
import org.uapvp.abyssanchor.block.entity.AbyssAnchorBlockEntity;
import org.uapvp.abyssanchor.AbyssAnchorMod;

public class AbyssAnchorModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AbyssAnchorMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> ABYSS_ANCHOR_STAGE_1 = register("abyss_anchor_stage_1", AbyssAnchorModBlocks.ABYSS_ANCHOR_STAGE_1, AbyssAnchorStage1BlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ABYSS_ANCHOR = register("abyss_anchor", AbyssAnchorModBlocks.ABYSS_ANCHOR, AbyssAnchorBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
