package com.werwolv.tileEntities;

public class TileEntityTest extends TileEntity {

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public void onTileClicked(int button) {
        super.onTileClicked(button);

        System.out.println("Clicked TileEntity with " + button + " mouse button!");
    }
}
