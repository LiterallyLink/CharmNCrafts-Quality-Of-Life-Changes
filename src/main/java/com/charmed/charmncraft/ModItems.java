package com.charmed.charmncraft;

import com.charmed.charmncraft.item.OrbVialItem;
import com.charmed.charmncraft.item.XPVialItem;
import com.charmed.charmncraft.item.IronGolemTotemItem;
import com.charmed.charmncraft.item.LoyalTotemItem;
import com.charmed.charmncraft.item.BerserkerTotemItem;
import com.charmed.charmncraft.item.WitheredTotemItem;
import com.charmed.charmncraft.item.VoidTotemItem;
import com.charmed.charmncraft.item.EvokerTotemItem;
import com.charmed.charmncraft.item.AngelicTotemItem;
import com.charmed.charmncraft.item.PhantomTotemItem;
import com.charmed.charmncraft.item.EchoTotemItem;
import com.charmed.charmncraft.item.ExplosiveTotemItem;
import com.charmed.charmncraft.item.GhastlyTotemItem;
import com.charmed.charmncraft.item.RottingTotemItem;
import com.charmed.charmncraft.item.SkeletalTotemItem;
import com.charmed.charmncraft.item.StingingTotemItem;
import com.charmed.charmncraft.item.TentacledTotemItem;
import com.charmed.charmncraft.item.TeleportingTotemItem;
import com.charmed.charmncraft.item.TotemOfNeverdyingItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    // XP Vial Capacity Constants
    public static final int VIAL_SMALL_CAPACITY = 1395;    // 10 levels
    public static final int VIAL_MEDIUM_CAPACITY = 6973;   // 30 levels
    public static final int VIAL_LARGE_CAPACITY = 13946;   // 50 levels

    // Nuggets
    public static final Item EMERALD_NUGGET = new Item(new FabricItemSettings());
    public static final Item DIAMOND_NUGGET = new Item(new FabricItemSettings());
    public static final Item NETHERITE_NUGGET = new Item(new FabricItemSettings());

    // Vials
    public static final Item ORB_VIAL = new OrbVialItem(new FabricItemSettings().maxCount(1));
    public static final Item XP_VIAL_SMALL = new XPVialItem(new FabricItemSettings().maxCount(1), VIAL_SMALL_CAPACITY);
    public static final Item XP_VIAL_MEDIUM = new XPVialItem(new FabricItemSettings().maxCount(1), VIAL_MEDIUM_CAPACITY);
    public static final Item XP_VIAL_LARGE = new XPVialItem(new FabricItemSettings().maxCount(1), VIAL_LARGE_CAPACITY);

    // Totems
    public static final Item IRON_GOLEM_TOTEM = new IronGolemTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item LOYAL_TOTEM = new LoyalTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item BERSERKER_TOTEM = new BerserkerTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item WITHERED_TOTEM = new WitheredTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item VOID_TOTEM = new VoidTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item EVOKER_TOTEM = new EvokerTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item ANGELIC_TOTEM = new AngelicTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item PHANTOM_TOTEM = new PhantomTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item ECHO_TOTEM = new EchoTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item EXPLOSIVE_TOTEM = new ExplosiveTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item SKELETAL_TOTEM = new SkeletalTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item TELEPORTING_TOTEM = new TeleportingTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item GHASTLY_TOTEM = new GhastlyTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item STINGING_TOTEM = new StingingTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item TENTACLED_TOTEM = new TentacledTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item ROTTING_TOTEM = new RottingTotemItem(new FabricItemSettings().maxCount(1));
    public static final Item TOTEM_OF_NEVERDYING = new TotemOfNeverdyingItem(new FabricItemSettings().maxCount(1));

    // Registry map for efficient batch registration
    private static final Map<String, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<String, Item> TOTEM_ITEMS = new LinkedHashMap<>();

    static {
        ITEMS.put("emerald_nugget", EMERALD_NUGGET);
        ITEMS.put("diamond_nugget", DIAMOND_NUGGET);
        ITEMS.put("netherite_nugget", NETHERITE_NUGGET);

        ITEMS.put("orb_vial", ORB_VIAL);
        ITEMS.put("xp_vial_small", XP_VIAL_SMALL);
        ITEMS.put("xp_vial_medium", XP_VIAL_MEDIUM);
        ITEMS.put("xp_vial_large", XP_VIAL_LARGE);

        TOTEM_ITEMS.put("iron_golem_totem", IRON_GOLEM_TOTEM);
        TOTEM_ITEMS.put("loyal_totem", LOYAL_TOTEM);
        TOTEM_ITEMS.put("berserker_totem", BERSERKER_TOTEM);
        TOTEM_ITEMS.put("withered_totem", WITHERED_TOTEM);
        TOTEM_ITEMS.put("void_totem", VOID_TOTEM);
        TOTEM_ITEMS.put("evoker_totem", EVOKER_TOTEM);
        TOTEM_ITEMS.put("angelic_totem", ANGELIC_TOTEM);
        TOTEM_ITEMS.put("phantom_totem", PHANTOM_TOTEM);
        TOTEM_ITEMS.put("echo_totem", ECHO_TOTEM);
        TOTEM_ITEMS.put("explosive_totem", EXPLOSIVE_TOTEM);
        TOTEM_ITEMS.put("skeletal_totem", SKELETAL_TOTEM);
        TOTEM_ITEMS.put("teleporting_totem", TELEPORTING_TOTEM);
        TOTEM_ITEMS.put("ghastly_totem", GHASTLY_TOTEM);
        TOTEM_ITEMS.put("stinging_totem", STINGING_TOTEM);
        TOTEM_ITEMS.put("tentacled_totem", TENTACLED_TOTEM);
        TOTEM_ITEMS.put("rotting_totem", ROTTING_TOTEM);
        TOTEM_ITEMS.put("totem_of_neverdying", TOTEM_OF_NEVERDYING);
    }

    public static void initialize() {
        CharmNCraft.LOGGER.info("Registering items.");

        ITEMS.forEach((id, item) ->
            Registry.register(Registries.ITEM, new Identifier(CharmNCraft.MOD_ID, id), item)
        );
        TOTEM_ITEMS.forEach((id, item) ->
            Registry.register(Registries.ITEM, new Identifier(CharmNCraft.MOD_ID, id), item)
        );

        registerItemGroupEntries();
    }

    private static void registerItemGroupEntries() {
        // Tools group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ORB_VIAL);
            entries.add(XP_VIAL_SMALL);
            entries.add(XP_VIAL_MEDIUM);
            entries.add(XP_VIAL_LARGE);
        });

        // Materials group - add nuggets
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(EMERALD_NUGGET);
            entries.add(DIAMOND_NUGGET);
            entries.add(NETHERITE_NUGGET);
        });

        // Combat group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            TOTEM_ITEMS.values().forEach(entries::add);
        });
    }
}
