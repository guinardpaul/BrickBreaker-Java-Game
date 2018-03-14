/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import brickbreaker.event.Handler;
import brickbreaker.event.KeyInput;
import brickbreaker.gameobject.Ball;
import brickbreaker.gameobject.Brick;
import brickbreaker.gameobject.ID;
import brickbreaker.gameobject.Paddle;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * Class to handle the Game
 *
 * @author Paul
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 700;
    public static final int PADDLE_X = WIDTH / 2 - Paddle.width;
    public static final int PADDLE_Y = HEIGHT - 80;
    public static final int BALL_X = PADDLE_X + Paddle.width / 2;
    public static final int BALL_Y = PADDLE_Y - Ball.height;
    private final int brickWidth = Brick.width;
    private final int brickHeight = Brick.height;
    private Thread thread;
    private boolean running = false;
    private Handler handler;

    /**
     * Constructeur de la classe Game
     */
    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Brick Breaker", this);

        handler.addObject(new Paddle(PADDLE_X, PADDLE_Y, ID.Paddle));
        handler.addObject(new Ball(BALL_X, BALL_Y, ID.Ball, handler));
        initBricks();
        //  handler.addObject(new Brick(WIDTH / 2 - 10, HEIGHT / 2 - 100, ID.Brick));
    }

    private int[][] initPositionBrick() {
        int positionBricks[][] = new int[90][2];
        int arrayNumber = 0;
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 15; i++) {
                positionBricks[arrayNumber] = new int[]{15 + brickWidth * i, 150 + brickHeight * j};
                arrayNumber++;
            }
        }
        return positionBricks;
    }

    private void initBricks() {
        int[][] positionBricks = initPositionBrick();

        for (int[] p : positionBricks) {
            handler.objects.add(new Brick(p[0], p[1], ID.Brick));
        }
    }

    /**
     * Start game
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Stop game
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Game Loop
     */
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS :" + frames);
                frames = 0;
            }
        }
        stop();
    }

    /**
     * tick method
     */
    private void tick() {
        handler.tick();
    }

    /**
     * render method
     */
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();

    }

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Game();
    }

}
