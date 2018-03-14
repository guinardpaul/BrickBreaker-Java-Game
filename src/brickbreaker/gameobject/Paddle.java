/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker.gameobject;

import brickbreaker.Game;
import brickbreaker.event.Util;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Paul
 */
public class Paddle extends GameObject {

    public static final int width = 50;
    public static final int height = 10;

    public Paddle(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += velocityX;

        x = Util.clamp(x, 0, Game.WIDTH - (width + 6));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRoundRect(x, y, width, height, 10, 10);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
