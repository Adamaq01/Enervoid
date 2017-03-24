package fr.adamaq01.enervoid;

import fr.adamaq01.enervoid.item.TestItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Adamaq01 on 23/03/2017.
 */
public class EnervoidItems {

    public static Item TEST_ITEM;

    public static void init() {
        TEST_ITEM = new TestItem().setUnlocalizedName("testItem").setRegistryName(Enervoid.MOD_ID, "testItem").setCreativeTab(Enervoid.CREATIVE_TAB);
        GameRegistry.register(TEST_ITEM);
    }
}
