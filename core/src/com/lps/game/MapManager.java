package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

import java.util.Iterator;

public class MapManager {
    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TiledMapTileLayer floorLayer;
    private Iterator waterIterator;
    private float elapsedSinceAnimation;

    public MapManager(String path) {
        map = new TmxMapLoader().load(path);
        floorLayer = (TiledMapTileLayer)map.getLayers().get("Floor");
        renderer = new IsometricTiledMapRenderer(map);
        elapsedSinceAnimation = 0;
        waterIterator = map.getTileSets().getTileSet("water").iterator();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Config.tileWidth * Config.noHorizontalTile - 10, 107, 0);
    }

    public void draw() {

        camera.update();

        renderer.setView(camera);
        renderer.render();

        elapsedSinceAnimation += Gdx.graphics.getDeltaTime();
        if(elapsedSinceAnimation > 1/30f){
            updateWaterAnimations();
            elapsedSinceAnimation = 0;
        }
    }

    public int getFloorType(int x, int y) {

        TiledMapTileLayer.Cell cell = floorLayer.getCell(x, y + Config.noVerticalTile);

        return cell.getTile().getId();
    }

    private void updateWaterAnimations(){

        if (!waterIterator.hasNext()) {
            waterIterator = map.getTileSets().getTileSet("water").iterator();
        }
        TiledMapTile newTile = (TiledMapTile)waterIterator.next();

        for(int x = 0; x < floorLayer.getWidth();x++){
            for(int y = 0; y < floorLayer.getHeight();y++){
                TiledMapTileLayer.Cell cell = floorLayer.getCell(x,y);
                Object prop = cell.getTile().getProperties().get("isWater");

                if (prop != null){
                    cell.setTile(newTile);
                }
            }
        }
    }
}
