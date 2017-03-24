package fr.adamaq01.enervoid.block.multiblock.enervoid;

import fr.adamaq01.enervoid.Enervoid;
import fr.adamaq01.enervoid.block.multiblock.enervoid.TileEnerVoid;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Adamaq01 on 23/03/2017.
 */
public class EnerVoidBlock extends BlockContainer {

    public EnerVoidBlock() {
        super(Material.IRON);
        setUnlocalizedName("enervoid");
        setRegistryName("enervoid:" + "enervoid");
        setCreativeTab(Enervoid.CREATIVE_TAB);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos blockPos, BlockPos block) {
        TileEntity tile = world.getTileEntity(blockPos);
        if (tile != null && tile instanceof TileEnerVoid) {
            TileEnerVoid multiBlock = (TileEnerVoid) tile;
            if (multiBlock.hasMaster()) {
                if (multiBlock.isMaster()) {
                    if (!multiBlock.checkMultiBlockForm())
                        multiBlock.resetStructure();
                } else {
                    if (!multiBlock.checkForMaster()) {
                        multiBlock.reset();
                    }
                }
            }
        }
        super.onNeighborChange(world, blockPos, block);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEnerVoid();
    }
}
