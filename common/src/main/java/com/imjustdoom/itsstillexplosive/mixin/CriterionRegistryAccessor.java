package com.imjustdoom.itsstillexplosive.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CriteriaTriggers.class)
public interface CriterionRegistryAccessor {

    @Invoker("register")
    static <T extends CriterionTrigger<?>> T registerCriterion(T criterion) {
        return null;
    }
}