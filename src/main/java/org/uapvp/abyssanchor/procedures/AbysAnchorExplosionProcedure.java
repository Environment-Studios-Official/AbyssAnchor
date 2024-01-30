package org.uapvp.abyssanchor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;

import org.uapvp.abyssanchor.network.AbyssAnchorModVariables;
import org.uapvp.abyssanchor.init.AbyssAnchorModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AbysAnchorExplosionProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == AbyssAnchorModBlocks.ABYSS_ANCHOR_STAGE_1.get()) {
				if (entity.isShiftKeyDown()) {
					if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.ENDER_PEARL)) {
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
						{
							double _setval = x;
							entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.EndAnchorX = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = x;
							entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.AnchorX = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = y;
							entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.EndAnchorY = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = y;
							entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.AnchorY = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = z;
							entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.EndAnchorZ = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = z;
							entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.AnchorZ = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorX,
									(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorY,
									(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorZ);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putString("Player", (entity.getDisplayName().getString()));
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					}
				} else {
					{
						double _setval = x;
						entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.AnchorX = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = y;
						entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.AnchorY = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						double _setval = z;
						entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.AnchorZ = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					world.setBlock(BlockPos.containing((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorX,
							(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorY,
							(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorZ), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof Level _level && !_level.isClientSide())
						_level.explode(null, ((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorX + 0.5),
								((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorY + 0.5),
								((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorZ + 0.5), 5, true, Level.ExplosionInteraction.BLOCK);
				}
			}
		}
	}
}
