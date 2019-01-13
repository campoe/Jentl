package physics;

import math.Vector2f;

import java.awt.*;

public class Body<T> {

    private T owner;
    private World world;

    private boolean collidable;
    private boolean gravitational;

    private int x, y;

    private int pivotX, pivotY;

    private int width, height;

    private double vx, vy;

    private double adx, ady;

    private int minTerrainCollisionY, minTerrainCollisionRangeY;

    private int leftBound, rightBound, topBound, bottomBound;
    private int middleXBound, middleYBound;

    private double horizontalVx;
    private double jumpVx;
    private double jumpVy;

    private boolean movable;

    private boolean moveLeft, moveRight, moveUp, moveDown, moveJump;

    private boolean onStair, stairDownLeft, stairDownRight, stairUpLeft, stairUpRight;
    private Vector2f stairPosition;

    public Body(T owner, int x, int y, int width, int height, int pivotX, int pivotY, int minTerrainCollisionRangeY) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pivotX = pivotX;
        this.pivotY = pivotY;
        this.minTerrainCollisionRangeY = minTerrainCollisionRangeY;
        this.horizontalVx = 1.5;
        this.jumpVx = 1.75;
        this.jumpVy = -5.0;
        this.stairPosition = new Vector2f();
        this.movable = true;
        this.collidable = true;
        this.gravitational = true;
    }

    public void reset() {
        this.minTerrainCollisionY = 0;
        this.onStair = false;
        this.vx = this.vy = this.adx = this.ady = 0;
    }

    public void update() {
        updateMovement();
        updateGravity();
        updateHorizontalPosition();
        if (onStair) {
            updateStairVerticalPosition();
        } else {
            updateVerticalPosition();
        }
    }

    public void resetMovement() {
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
        this.moveJump = false;
    }

    private void updateMovement() {
        if (movable) {
            boolean collidingWithTerrain = collidingWithTerrainFloor();
            if (moveLeft) {
                vx = collidingWithTerrain || onStair ? -horizontalVx : -jumpVx;
            } else if (moveRight) {
                vx = collidingWithTerrain || onStair ? horizontalVx : jumpVx;
            } else {
                vx = 0;
            }
            if (!onStair && moveJump && collidingWithTerrain) {
                vy = jumpVy;
                moveJump = false;
            }
        }
    }

    private void updateGravity() {
        if (gravitational) {
            vy += World.GRAVITY;
        }
    }

    private void updateHorizontalPosition() {
        adx += vx;
        int sign = adx < 0 ? -1 : 1;
        while (adx * sign >= 1) {
            if ((collidingWithTerrainRight() && adx > 0) ||
                    (collidingWithTerrainLeft() && adx < 0)) {
                adx = 0;
                vx = 0;
                break;
            } else {
                x += sign;
                adx -= sign;
                if (!onStair && checkBeginStairState()) {
                    stairPosition.set(x, y);
                    minTerrainCollisionY = Integer.MAX_VALUE;
                    onStair = true;
                    adx = 0;
                    vx = 0;
                    break;
                }
            }
        }
    }

    private void updateVerticalPosition() {
        ady += vy;
        int sign = ady < 0 ? -1 : 1;
        while (ady * sign >= 1) {
            boolean collidingWithFloor = collidingWithTerrainFloor();
            if (collidingWithFloor) {
                minTerrainCollisionY = y - pivotY - minTerrainCollisionRangeY;
            }
            if ((collidingWithFloor && ady > 0) ||
                    (collidingWithTerrainCeil() && ady < 0)) {
                ady = 0;
                vy = 0;
                break;
            } else {
                y += sign;
                ady -= sign;
            }
        }
    }

    private void updateStairVerticalPosition() {
        vy = 0;
        float targetY = y;
        if (stairDownLeft) {
            targetY = stairPosition.y + (stairPosition.x - x) + 1;
        } else if (stairDownRight) {
            targetY = stairPosition.y + (x - stairPosition.x) + 1;
        } else if (stairUpLeft) {
            targetY = stairPosition.y + (x - stairPosition.x) - 1;
        } else if (stairUpRight) {
            targetY = stairPosition.y + (stairPosition.x - x) - 1;
        }
        int diff = (int) (targetY - y);
        int sign = diff < 0 ? -1 : 1;
        diff = Math.abs(diff);
        while (diff != 0) {
            y += sign;
            diff--;
            if (checkEndStairState()) {
                minTerrainCollisionY = 0;
                onStair = false;
                break;
            }
        }
    }

    public void updateBounds() {
        leftBound = x - pivotX;
        rightBound = leftBound + width - 1;
        topBound = y - pivotY;
        bottomBound = topBound + height - 1;
        middleXBound = leftBound + width / 2;
        middleYBound = topBound + height / 2;
    }

    private boolean collidingWithTerrainFloor() {
        updateBounds();
        return world.terrain.isRigidByPosition(leftBound, bottomBound + 1, minTerrainCollisionY) || world.terrain.isRigidByPosition(rightBound, bottomBound + 1, minTerrainCollisionY);
    }

    private boolean collidingWithTerrainCeil() {
        updateBounds();
        return world.terrain.isRigidByPosition(leftBound, topBound - 1, minTerrainCollisionY) || world.terrain.isRigidByPosition(rightBound, topBound - 1, minTerrainCollisionY);
    }

    private boolean collidingWithTerrainRight() {
        updateBounds();
        return world.terrain.isRigidByPosition(rightBound + 1, topBound, minTerrainCollisionY) || world.terrain.isRigidByPosition(rightBound + 1, middleYBound, minTerrainCollisionY) || world.terrain.isRigidByPosition(rightBound + 1, bottomBound, minTerrainCollisionY);
    }

    private boolean collidingWithTerrainLeft() {
        updateBounds();
        return world.terrain.isRigidByPosition(leftBound - 1, topBound, minTerrainCollisionY) || world.terrain.isRigidByPosition(leftBound - 1, middleYBound, minTerrainCollisionY) || world.terrain.isRigidByPosition(leftBound - 1, bottomBound, minTerrainCollisionY);
    }

    private boolean collidingWithTerrain() {
        updateBounds();
        return world.terrain.isRigidByPosition(rightBound, topBound, 0) ||
                world.terrain.isRigidByPosition(leftBound, topBound, 0) ||
                world.terrain.isRigidByPosition(rightBound, middleYBound, 0) ||
                world.terrain.isRigidByPosition(leftBound, middleYBound, 0) ||
                world.terrain.isRigidByPosition(rightBound, bottomBound, 0) ||
                world.terrain.isRigidByPosition(leftBound, bottomBound, 0);
    }

    private boolean checkBeginStairState() {
        stairDownLeft = false;
        stairUpRight = false;
        stairDownRight = false;
        stairUpLeft = false;
        updateBounds();
        int tileId = world.terrain.getIdByPosition(middleXBound, bottomBound + 1);
        if (stairDownLeft = tileId == 7 && moveDown && moveLeft && middleXBound % Terrain.TILE_SIZE == 2) {
            return true;
        } else if (stairUpRight = tileId == 5 && moveUp && moveRight && middleXBound % Terrain.TILE_SIZE == 5) {
            return true;
        } else if (stairDownRight = tileId == 4 && moveDown && moveRight && middleXBound % Terrain.TILE_SIZE == 5) {
            return true;
        } else if (stairUpLeft = tileId == 2 && moveUp && moveLeft && middleXBound % Terrain.TILE_SIZE == 2) {
            return true;
        }
        return false;
    }

    private boolean checkEndStairState() {
        updateBounds();
        return (bottomBound + 1) % 8 == 0 && world.terrain.isRigidByPosition(middleXBound, bottomBound + 1, 0);
    }

    public boolean collides(Body b) {
        updateBounds();
        b.updateBounds();
        if (Math.max(leftBound + width, b.leftBound + b.width) - Math.min(leftBound, b.leftBound) > width + b.width) {
            return false;
        } else if (Math.max(topBound + height, b.topBound + b.height) - Math.min(topBound, b.topBound) > height + b.height) {
            return false;
        }
        return true;
    }

    public void drawDebug(Graphics2D g) {
        int rx = x - pivotX;
        int ry = y - pivotY;
        g.setColor(Color.BLUE);
        g.drawRect(rx, ry, width - 1, height - 1);
        g.setColor(Color.MAGENTA);
        g.drawLine(x, y, x, y);
        g.setColor(Color.GRAY);
        g.drawLine(0, minTerrainCollisionY, 800, minTerrainCollisionY);
    }

}
