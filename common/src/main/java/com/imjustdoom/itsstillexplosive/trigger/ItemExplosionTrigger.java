package com.imjustdoom.itsstillexplosive.trigger;

import com.google.gson.JsonObject;
import com.imjustdoom.itsstillexplosive.ItsStillExplosive;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ItemExplosionTrigger extends SimpleCriterionTrigger<ItemExplosionTrigger.TriggerInstance> {

    static final ResourceLocation ID = new ResourceLocation(ItsStillExplosive.MOD_ID + ":item_explosion");

    public ResourceLocation getId() {
        return ID;
    }

    public ItemExplosionTrigger() {}

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        ContextAwarePredicate player = EntityPredicate.fromJson(jsonObject, "player", deserializationContext);
        return new TriggerInstance(contextAwarePredicate, player);
    }

    public void trigger(ServerPlayer serverPlayer) {
        this.trigger(serverPlayer, TriggerInstance::requirementsMet);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(ContextAwarePredicate arg, ContextAwarePredicate player) {
            super(ItemExplosionTrigger.ID, arg);
        }

        boolean requirementsMet() {
            return true;
        }
    }
}
