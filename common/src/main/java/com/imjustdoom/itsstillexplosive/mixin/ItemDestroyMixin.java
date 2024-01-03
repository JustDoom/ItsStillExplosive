package com.imjustdoom.itsstillexplosive.mixin;

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
    public void onDestroyed(ItemEntity itemEntity, CallbackInfo ci) { // TODO: Account for tnt in shulker and maybe boom power scales by stack size

        if ((itemEntity.getItem().getItem() == Items.TNT || itemEntity.getItem().getItem() == Items.TNT_MINECART) && itemEntity.isOnFire()) {
            itemEntity.discard();
            if (!itemEntity.level().isClientSide) {
                itemEntity.level().explode(null, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 1f, Level.ExplosionInteraction.TNT);
            }
        }
    }
}
