/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstract class for Objects of the game
 *
 * @author Paul
 */
public abstract class GameObject {

    /**
     * position X
     */
    protected int x;
    /**
     * position Y
     */
    protected int y;
    /**
     * Enum ID
     */
    protected ID id;
    /**
     * Vitesse selon X
     */
    protected int velocityX;
    /**
     * Vitesse selon Y
     */
    protected int velocityY;

    /**
     * Constructeur
     *
     * @param x position X
     * @param y position Y
     * @param id Enum ID
     */
    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    // Public or protected ?
    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getId() {
        return id;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

}
