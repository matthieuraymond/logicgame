package com.bob.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MapManager {
    private final TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private final TiledMapTileLayer floorLayer;
    private Iterator waterIterator;
    private float elapsedSinceAnimation;

    public MapManager(String path) {
        map = new TmxMapLoader().load(path);

        floorLayer = (TiledMapTileLayer)map.getLayers().get("Floor");
        waterIterator = map.getTileSets().getTileSet("water").iterator();

        elapsedSinceAnimation = 0;
    }

    public void initRender() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Config.tileWidth * Config.noHorizontalTile - 50, 67, 0);

        renderer = new IsometricTiledMapRenderer(map);
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

                if (type != null){
                    if (!type.equals("water")) sb.append(type + "(" + x + "," + y + ")." );
                }
            }
        }

        return sb.toString();
    }

    public List<WorldCoordinates> getQuestionCoordinates() {
        List<WorldCoordinates> res = new LinkedList<>();

        for(int x = 0; x < floorLayer.getWidth();x++){
            for(int y = 0; y < floorLayer.getHeight();y++){

                TiledMapTileLayer.Cell cell = floorLayer.getCell(x,y);
                Object type = cell.getTile().getProperties().get("type");

                if (type != null){
                    if (type.equals("question")) {
                        res.add(new WorldCoordinates(x, y));
                    }
                }
            }
        }

        return res;
    }

    public void setGold(int i, int j) {

        TiledMapTileSet tileSet = map.getTileSets().getTileSet("floor");

        TiledMapTile goldTile = tileSet.getTile(6);
        TiledMapTile questionTile = tileSet.getTile(7);

        for (TiledMapTile t : tileSet) {
            if (t.getProperties().get("type").equals("gold")) {
                goldTile = t;
            }

            if (t.getProperties().get("type").equals("question")) {
                questionTile = t;
            }
        }

        for(int x = 0; x < floorLayer.getWidth();x++){
            for(int y = 0; y < floorLayer.getHeight();y++){

                TiledMapTileLayer.Cell cell = floorLayer.getCell(x,y);
                Object type = cell.getTile().getProperties().get("type");

                if (type != null){
                    if (type.equals("gold")) {
                        cell.setTile(questionTile);
                    }
                }
            }
        }

        floorLayer.getCell(i, j).setTile(goldTile);
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
