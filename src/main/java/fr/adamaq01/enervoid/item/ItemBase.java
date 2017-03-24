package fr.adamaq01.enervoid.item;

import fr.adamaq01.enervoid.Enervoid;
import net.minecraft.item.Item;

/**
 * Created by Adamaq01 on 23/03/2017.
 */
public class ItemBase extends Item {

    @Override
    public Item setUnlocalizedName(String name) {
        if(!name.startsWith(Enervoid.MOD_ID + ".")) {
            name = Enervoid.MOD_ID + "." + name;
        }
        return super.setUnlocalizedName(name);
    }
}