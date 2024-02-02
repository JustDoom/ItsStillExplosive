package com.imjustdoom.itsstillexplosive;

import com.imjustdoom.itsstillexplosive.mixin.CriterionRegistryAccessor;
import com.imjustdoom.itsstillexplosive.trigger.ItemExplosionTrigger;

public class ItsStillExplosive {
    public static final String MOD_ID = "itsstillexplosive";

    public static ItemExplosionTrigger BOOM = CriterionRegistryAccessor.registerCriterion(new ItemExplosionTrigger());

    public static void init() {}
    // TODO: advancement for killing player with thrown tnt and one for a powerful explosion
}
