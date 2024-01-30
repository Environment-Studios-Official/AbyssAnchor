
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package org.uapvp.abyssanchor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import org.uapvp.abyssanchor.AbyssAnchorMod;

public class AbyssAnchorModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, AbyssAnchorMod.MODID);
	public static final RegistryObject<Item> ABYSS_ANCHOR_STAGE_1 = block(AbyssAnchorModBlocks.ABYSS_ANCHOR_STAGE_1);
	public static final RegistryObject<Item> ABYSS_ANCHOR = block(AbyssAnchorModBlocks.ABYSS_ANCHOR);

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
