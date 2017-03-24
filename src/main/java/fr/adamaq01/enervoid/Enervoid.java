package fr.adamaq01.enervoid;

import fr.adamaq01.enervoid.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Enervoid.MOD_ID,
        name = Enervoid.MOD_NAME,
        version = Enervoid.VERSION
)
public class Enervoid {

    public static final String MOD_ID = "enervoid";
    public static final String MOD_NAME = "Enervoid";
    public static final String VERSION = "1.0";
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_NAME) {
        @Override public Item getTabIconItem() {
            return EnervoidItems.TEST_ITEM;
        }
    };
    @SidedProxy(clientSide = "fr.adamaq01.enervoid.proxies.ClientProxy", serverSide = "fr.adamaq01.enervoid.proxies.ServerProxy")
    public static CommonProxy PROXY;
    @Mod.Instance(MOD_ID)
    public static Enervoid INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(MOD_NAME + " is loading!");
        EnervoidItems.init();
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
