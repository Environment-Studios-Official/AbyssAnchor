package org.uapvp.abyssanchor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;


import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import org.uapvp.abyssanchor.network.AbyssAnchorModVariables;
import org.uapvp.abyssanchor.init.AbyssAnchorModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class YPosCheckProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((new Object() {
			public String getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getString(tag);
				return "";
			}
		}.getValue(world,
				BlockPos.containing((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorX,
						(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorY,
						(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorZ),
				"Player")).equals(entity.getDisplayName().getString())) {
			if ((entity.level().dimension()) == Level.END && entity.getY() <= -20 && (entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).Dimension == true) {
				entity.getPersistentData().putBoolean("IsFalling", true);
			} else {
				entity.getPersistentData().putBoolean("IsFalling", false);
			}
			if (entity.getPersistentData().getBoolean("IsFalling") == true
					&& (world.getBlockState(BlockPos.containing((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorX,
							(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorY,
							(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorZ))).getBlock() == AbyssAnchorModBlocks.ABYSS_ANCHOR_STAGE_1.get()) {
				if (!(entity instanceof ServerPlayer _plr11 && _plr11.level() instanceof ServerLevel
						&& _plr11.getAdvancements().getOrStartProgress(_plr11.server.getAdvancements().getAdvancement(new ResourceLocation("abyss_anchor:abyss_anchoruse"))).isDone())) {
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("abyss_anchor:abyss_anchoruse"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
				entity.fallDistance = 2;
				{
					Entity _ent = entity;
					_ent.teleportTo(((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorX + 0.5),
							((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorY + 1),
							((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorZ + 0.5));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorX + 0.5),
								((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorY + 1),
								((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorZ + 0.5), _ent.getYRot(), _ent.getXRot());
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				world.setBlock(BlockPos.containing((entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorX,
						(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorY,
						(entity.getCapability(AbyssAnchorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new AbyssAnchorModVariables.PlayerVariables())).EndAnchorZ), AbyssAnchorModBlocks.ABYSS_ANCHOR.get().defaultBlockState(), 3);
			}
		}
	}
}
