/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker.event;

import brickbreaker.gameobject.GameObject;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Handler to render game objects
 *
 * @author Paul
 */
public class Handler {

    /**
     * List des GameObject du jeu
     */
    public LinkedList<GameObject> objects = new LinkedList<>();

    /**
     * Tick methode sur tous les ObjectGame de objects list
     */
    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempGameObject = objects.get(i);

            tempGameObject.tick();
        }
    }

    /**
     * Render methode sur tous les ObjectGame de objects list
     *
     * @param g Graphics
     */
    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempGameObject = objects.get(i);

            tempGameObject.render(g);
        }
    }

    /**
     * Add GameObject to the game
     *
     * @param obj Game Object
     */
    public void addObject(GameObject obj) {
        this.objects.add(obj);
    }

    /**
     * Remove GameObject to the game
     *
     * @param obj Game Object
     */
    public void removeObject(GameObject obj) {
        this.objects.remove(obj);
    }

}
