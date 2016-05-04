package com.lps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

import java.util.Iterator;

public class MapManager {
    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TiledMapTileLayer floorLayer;


    public MapManager(String path) {
        map = new TmxMapLoader().load(path);
        floorLayer = (TiledMapTileLayer)map.getLayers().get("Floor");
        renderer = new IsometricTiledMapRenderer(map);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Utils.tileWidth * Utils.noHorizontalTile - 10, 107, 0);
    }

    public void draw() {
        camera.update();

        renderer.setView(camera);
        renderer.render();
    }

    public int getFloorType(int x, int y) {

        TiledMapTileLayer.Cell cell = floorLayer.getCell(x, y + Utils.noVerticalTile);

        return cell.getTile().getId();
    }
}
