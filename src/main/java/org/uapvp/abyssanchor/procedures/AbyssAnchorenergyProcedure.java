package org.uapvp.abyssanchor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import org.uapvp.abyssanchor.network.AbyssAnchorModVariables;
import org.uapvp.abyssanchor.init.AbyssAnchorModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AbyssAnchorenergyProcedure {
	public static boolean ThrowingEnderperls = false;

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
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == AbyssAnchorModBlocks.ABYSS_ANCHOR.get() && !entity.isShiftKeyDown()
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.ENDER_PEARL) {
			ThrowingEnderperls = true;

			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
					} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
								&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(Items.ENDER_PEARL);
					_setstack.setCount((int) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getCount() - 1));
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
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
			if ((world instanceof Level _lvl ? _lvl.dimension() : Level.OVERWORLD) == Level.END) {
				{
					boolean _setval = true;
					entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Dimension = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = (entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorX;
					entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.EndAnchorX = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = (entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorY;
					entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.EndAnchorY = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = (entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).AnchorZ;
					entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.EndAnchorZ = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				{
					boolean _setval = false;
					entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Dimension = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (!world.isClientSide()) {
				if (!world.isClientSide()) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putString("Player", (entity.getDisplayName().getString()));
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = AbyssAnchorModBlocks.ABYSS_ANCHOR_STAGE_1.get().defaultBlockState();
					BlockEntity _be = world.getBlockEntity(_bp);
					CompoundTag _bnbt = null;
					if (_be != null) {
						_bnbt = _be.saveWithFullMetadata();
						_be.setRemoved();
					}
					world.setBlock(_bp, _bs, 3);
					if (_bnbt != null) {
						_be = world.getBlockEntity(_bp);
						if (_be != null) {
							try {
								_be.load(_bnbt);
							} catch (Exception ignored) {
							}
						}
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.respawn_anchor.charge")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.respawn_anchor.charge")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
		}
		else ThrowingEnderperls = false;
	}
}
