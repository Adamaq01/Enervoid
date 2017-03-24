package fr.adamaq01.enervoid.proxies;

import fr.adamaq01.enervoid.EnervoidItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Adamaq01 on 23/03/2017.
 */
public class ClientProxy implements CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        registerItemRenderer(EnervoidItems.TEST_ITEM);
    }

    public void registerItemRenderer(Item item) {
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, itemModelResourceLocation);
    }

}
