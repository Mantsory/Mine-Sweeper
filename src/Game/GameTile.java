/*
 * This is for creating individual isGameActive tiles for Minesweeper.
 *
 * Author: Mantsory
 * Version updated: 2.3
 */
package Game;

public class GameTile {

    private char content;
    private boolean isOpen;
    private boolean isFlagged;

    public GameTile(char content) {
        this.content = content;
        this.isOpen = false;
        this.isFlagged = false;
    }

    public boolean open() {
        if (this.isFlagged||this.isOpen) return false;
        this.isOpen = true;
        GameBoard.openedTiles++;
        return true;
    }

    public void flag() {
        isFlagged = !isFlagged;
    }

    public char getContent() {
        return this.content;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public boolean isFlagged() {
        return this.isFlagged;
    }

    public void setContent(char content) {
        this.content = content;
    }
}