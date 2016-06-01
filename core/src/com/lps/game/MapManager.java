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

        setMap(path);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Config.tileWidth * Config.noHorizontalTile - 50, 67, 0);
    }

    private void setMap(String path) {
        map = new TmxMapLoader().load(path);
        floorLayer = (TiledMapTileLayer)map.getLayers().get("Floor");
        renderer = new IsometricTiledMapRenderer(map);
        elapsedSinceAnimation = 0;
        waterIterator = map.getTileSets().getTileSet("water").iterator();
    }

    public void draw(float deltaTime) {

        camera.update();

        renderer.setView(camera);
        renderer.render();

        elapsedSinceAnimation += deltaTime;

        if(elapsedSinceAnimation > 1/24f){
            updateWaterAnimations();
            elapsedSinceAnimation = 0;
        }
    }

    public String getType(int x, int y) {

        TiledMapTileLayer.Cell cell = floorLayer.getCell(x, y);//  Config.noVerticalTile + y);
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

                //int newY = -(Config.noVerticalTile - y);

                if (type != null){
                    if (!type.equals("water")) sb.append(type + "(" + x + "," + y + ")." );
                }
            }
        }

        return sb.toString();
    }

    public boolean checkIfWet(WorldCoordinates coord) {
        String type = this.getType(Math.round(coord.getWorldX()), Math.round(coord.getWorldY()));

        return type.equals("water");
    }

    public boolean chekIfWon(WorldCoordinates coord) {
        String type = this.getType(Math.round(coord.getWorldX()), Math.round(coord.getWorldY()));

        return type.equals("gold");
    }
}
