package com.imjustdoom.itsstillexplosive.mixin;

import com.imjustdoom.itsstillexplosive.ItsStillExplosive;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {Item.class, BlockItem.class})
public abstract class ItemDestroyMixin {

    @Inject(method = "onDestroyed", at = @At("HEAD"))
    public void onDestroyed(ItemEntity itemEntity, CallbackInfo ci) { // TODO: Account for tnt in shulker

        if ((itemEntity.getItem().getItem() == Items.TNT
                || itemEntity.getItem().getItem() == Items.TNT_MINECART
                || itemEntity.getItem().getItem() == Items.GUNPOWDER) // TODO: config for supported items and its multiplyer
                && itemEntity.isOnFire()) {
            itemEntity.discard();
            if (!itemEntity.level().isClientSide) {
                float base = itemEntity.getItem().getItem() == Items.GUNPOWDER ? 0.2f : 0.75f;
                itemEntity.level().explode(itemEntity.getOwner(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 1f + base * (float) Math.log(itemEntity.getItem().getCount()), Level.ExplosionInteraction.TNT);

                if (itemEntity.getOwner() != null && itemEntity.getOwner() instanceof ServerPlayer) {
                    ItsStillExplosive.BOOM.trigger((ServerPlayer) itemEntity.getOwner());
                }
            }
        }
    }
}
