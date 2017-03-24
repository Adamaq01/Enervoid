package fr.adamaq01.enervoid.block.multiblock.enervoid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Adamaq01 on 23/03/2017.
 */
public class TileEnerVoid extends TileEntity implements ITickable {

    private boolean hasMaster, isMaster;
    private BlockPos masterPos;

    @Override
    public void update() {
        if (!worldObj.isRemote) {
            if (hasMaster()) {
                if (isMaster()) {
                    // Put stuff you want the multiblock to do here!
                    System.out.println("JE SUIS ACTIF OMG");
                    System.out.println("MA POS: " + pos.toString());
                }
            } else {
                // Constantly check if structure is formed until it is.
                if (checkMultiBlockForm())
                    setupStructure();
            }
        }
    }

    /** Check that structure is properly formed */
    public boolean checkMultiBlockForm() {
        int i = 0;
        // Scan a 3x3x3 area, starting with the bottom left corner
        for (int x = pos.getX() - 1; x < pos.getX() + 2; x++)
            for (int y = pos.getY(); y < pos.getY() + 3; y++)
                for (int z = pos.getZ() - 1; z < pos.getZ() + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(new BlockPos(x, y, z));
                    // Make sure tile isn't null, is an instance of the same Tile, and isn't already a part of a multiblock
                    if (tile != null && (tile instanceof TileEnerVoid)) {
                        if (this.isMaster()) {
                            if (((TileEnerVoid)tile).hasMaster())
                                i++;
                        } else if (!((TileEnerVoid)tile).hasMaster())
                            i++;
                    }
                }
        // check if there are 26 blocks present ((3*3*3) - 1) and check that center block is empty
        return i > 25 && worldObj.isAirBlock(new BlockPos(pos).add(0, 1, 0));
    }

    /** Setup all the blocks in the structure*/
    public void setupStructure() {
        for (int x = pos.getX() - 1; x < pos.getX() + 2; x++)
            for (int y = pos.getY(); y < pos.getY() + 3; y++)
                for (int z = pos.getZ() - 1; z < pos.getZ() + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(new BlockPos(x, y, z));
                    // Check if block is bottom center block
                    boolean master = (x == pos.getX() && y == pos.getY() && z == pos.getZ());
                    if (tile != null && (tile instanceof TileEnerVoid)) {
                        ((TileEnerVoid) tile).setMasterCoords(pos);
                        ((TileEnerVoid) tile).setHasMaster(true);
                        ((TileEnerVoid) tile).setIsMaster(master);
                    }
                }
    }

    /** Reset method to be run when the master is gone or tells them to */
    public void reset() {
        masterPos = new BlockPos(0, 0, 0);
        hasMaster = false;
        isMaster = false;
    }

    /** Check that the master exists */
    public boolean checkForMaster() {
        TileEntity tile = worldObj.getTileEntity(masterPos);
        return (tile != null && (tile instanceof TileEnerVoid));
    }

    /** Reset all the parts of the structure */
    public void resetStructure() {
        for (int x = pos.getX() - 1; x < pos.getX() + 2; x++)
            for (int y = pos.getY(); y < pos.getY() + 3; y++)
                for (int z = pos.getZ() - 1; z < pos.getZ() + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(new BlockPos(x, y, z));
                    if (tile != null && (tile instanceof TileEnerVoid))
                        ((TileEnerVoid) tile).reset();
                }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("masterX", masterPos.getX());
        data.setInteger("masterY", masterPos.getY());
        data.setInteger("masterZ", masterPos.getZ());
        data.setBoolean("hasMaster", hasMaster);
        data.setBoolean("isMaster", isMaster);
        if (hasMaster() && isMaster()) {
            // Any other values should ONLY BE SAVED TO THE MASTER
        }
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        masterPos = new BlockPos(data.getInteger("masterX"), data.getInteger("masterY"), data.getInteger("masterZ"));
        hasMaster = data.getBoolean("hasMaster");
        isMaster = data.getBoolean("isMaster");
        if (hasMaster() && isMaster()) {
            // Any other values should ONLY BE READ BY THE MASTER
        }
    }

    public boolean hasMaster() {
        return hasMaster;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public BlockPos getMasterPos() {
        return masterPos;
    }

    public void setHasMaster(boolean bool) {
        hasMaster = bool;
    }

    public void setIsMaster(boolean bool) {
        isMaster = bool;
    }

    public void setMasterCoords(BlockPos blockPos) {
        masterPos = blockPos;
    }
}
