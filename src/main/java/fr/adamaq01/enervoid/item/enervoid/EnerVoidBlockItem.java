package fr.adamaq01.enervoid.item.enervoid;

import fr.adamaq01.enervoid.Enervoid;
import fr.adamaq01.enervoid.block.multiblock.enervoid.EnerVoidBlock;
import net.minecraft.item.ItemBlock;

/**
 * Created by Adamaq01 on 23/03/2017.
 */
public class EnerVoidBlockItem extends ItemBlock {

    public EnerVoidBlockItem() {
        super(new EnerVoidBlock());
        setUnlocalizedName("enervoid");
        setRegistryName("enervoid:" + "enervoid");
        setCreativeTab(Enervoid.CREATIVE_TAB);
    }
}
