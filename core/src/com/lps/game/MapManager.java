package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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
        camera.position.set(Config.tileWidth * Config.noHorizontalTile - 50, 67, 0);
    }

    public void draw(float deltaTime) {

        camera.update();

        renderer.setView(camera);
        renderer.render();

        elapsedSinceAnimation += deltaTime;
        if(elapsedSinceAnimation > 1/30f){
            updateWaterAnimations();
            elapsedSinceAnimation = 0;
        }
    }

    public String getType(int x, int y) {

        TiledMapTileLayer.Cell cell = floorLayer.getCell(x, Config.noVerticalTile + y);
        Object type = cell.getTile().getProperties().get("type");

        if (type != null) {
            return (String)type;
        } else {
            return "";
        }
    }

    private void updateWaterAnimations(){

        if (!waterIterator.hasNext()) {
            waterIterator = map.getTileSets().getTileSet("water").iterator();
        }
        TiledMapTile newTile = (TiledMapTile)waterIterator.next();

        for(int x = 0; x < floorLayer.getWidth();x++){
            for(int y = 0; y < floorLayer.getHeight();y++){

                TiledMapTileLayer.Cell cell = floorLayer.getCell(x,y);
                Object type = cell.getTile().getProperties().get("type");

                if (type != null){
                    if (type.equals("water")) cell.setTile(newTile);
                }
            }
        }
    }

    public String getLPSDescription() {

        StringBuilder sb = new StringBuilder();

        for(int x = 0; x < floorLayer.getWidth();x++) {
            for (int y = 0; y < floorLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = floorLayer.getCell(x,y);
                Object type = cell.getTile().getProperties().get("type");

                int newY = -(Config.noVerticalTile - y);

                if (type != null){
                    if (!type.equals("water")) sb.append(type + "(" + ((x < 0) ? "m" : "") + Math.abs(x) + "," + ((newY < 0) ? "m" : "") + Math.abs(newY) + ")." );
                }
            }
        }

        return sb.toString();
    }
}
