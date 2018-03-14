/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker.gameobject;

import brickbreaker.Game;
import brickbreaker.event.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Paul
 */
public class Ball extends GameObject {

    public static final int width = 20;
    public static final int height = 20;
    public static int initialVelX = 1;
    public static int initialVelY = -5;
    private Handler handler;

    public Ball(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        if (x <= 0 || x >= Game.WIDTH - 20) {
            velocityX *= -1;
        }
        if (y <= 0) {
            velocityY *= -1;
        }
        if (y >= Game.HEIGHT - 20) {
            // Game Over
            System.exit(1);
        }

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempGameObject = handler.objects.get(i);

            if (tempGameObject.getId() == ID.Paddle) {
                if (getBounds().intersects(tempGameObject.getBounds())) {
                    double paddleCenter = tempGameObject.getBounds().getCenterX();
                    if (x <= paddleCenter) {
                        if (x <= paddleCenter - 20) {
                            velocityX = -4;
                            velocityY = -2;
                        } else if (x <= paddleCenter - 10) {
                            velocityX = -3;
                            velocityY = -3;
                        }
                    } else if (x >= paddleCenter) {
                        if (x >= paddleCenter + 10) {
                            velocityX = 3;
                            velocityY = -3;
                        } else if (x >= paddleCenter + 20) {
                            velocityX = 4;
                            velocityY = -2;
                        }
                    }
                }
            } else if (tempGameObject.getId() == ID.Brick) {
                if (getBounds().intersects(tempGameObject.getBounds())) {
                    velocityY *= -1;
                    handler.objects.remove(i);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D gd = (Graphics2D) g;
        gd.setColor(Color.red);
        gd.fillRoundRect(x, y, width, width, 100, 100);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
