package org.uapvp.abyssanchor.mixins;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.uapvp.abyssanchor.AbyssAnchorMod;
import org.uapvp.abyssanchor.init.AbyssAnchorModBlocks;

@Mixin(EnderpearlItem.class)
public class AAthrownEnderpearl {

    /**
     * @author :)
     * @reason :)
     */
    @Overwrite
    public InteractionResultHolder<ItemStack> use(Level p_41190_, Player player, InteractionHand p_41192_) {
        ItemStack $$3 = player.getItemInHand(p_41192_);
        if (raycastBlock(player) == AbyssAnchorModBlocks.ABYSS_ANCHOR.get().defaultBlockState())
            return InteractionResultHolder.sidedSuccess($$3, p_41190_.isClientSide());
        else
        {
            $$3 = player.getItemInHand(p_41192_);
            p_41190_.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (p_41190_.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(Items.ENDER_PEARL, 20);
            if (!p_41190_.isClientSide) {
                ThrownEnderpearl $$4 = new ThrownEnderpearl(p_41190_, player);
                $$4.setItem($$3);
                $$4.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                p_41190_.addFreshEntity($$4);
            }

            player.awardStat(Stats.ITEM_USED.get(Items.ENDER_PEARL));
            if (!player.getAbilities().instabuild) {
                $$3.shrink(1);
            }

            return InteractionResultHolder.sidedSuccess($$3, p_41190_.isClientSide());
        }
    }

    private BlockState raycastBlock(Player player) {
        // Perform a raycast to get the block the player is looking at
        HitResult hitResult = player.pick(5.0D, 0.0F, false);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            var code = player.getCommandSenderWorld().getBlockState(((BlockHitResult) hitResult).getBlockPos()).toString();
            AbyssAnchorMod.LOGGER.info(code);
            return player.getCommandSenderWorld().getBlockState(((BlockHitResult) hitResult).getBlockPos());
        }
        return null;
    }
}