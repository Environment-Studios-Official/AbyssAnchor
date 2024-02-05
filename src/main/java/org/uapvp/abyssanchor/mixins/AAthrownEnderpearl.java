package org.uapvp.abyssanchor.mixins;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.uapvp.abyssanchor.AbyssAnchorMod;
import org.uapvp.abyssanchor.procedures.AbyssAnchorenergyProcedure;

@Mixin(EnderpearlItem.class)
public class AAthrownEnderpearl extends Item {

    public AAthrownEnderpearl(Properties p_41383_) {
        super(p_41383_);
    }

    /**
     * @author :)
     * @reason :)
     */
    @Overwrite
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack $$3 = player.getItemInHand(hand);
        if (!AbyssAnchorMod.ThrowingEnderperls)
        {
            $$3 = player.getItemInHand(hand);
            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this, 20);
            if (!level.isClientSide) {
                ThrownEnderpearl $$4 = new ThrownEnderpearl(level, player);
                $$4.setItem($$3);
                $$4.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity($$4);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                $$3.shrink(1);
            }

            return InteractionResultHolder.sidedSuccess($$3, level.isClientSide());
        }
        AbyssAnchorMod.ThrowingEnderperls = false;
        return InteractionResultHolder.sidedSuccess($$3, level.isClientSide());
    }
}