package Game;/*
 * This is for creating individual isGameActive tiles for Minesweeper.
 *
 * Author: Mantsory
 * Version updated: 2.2.1
 */

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
        return true;
    }

    public String flag() {
        if (this.isOpen) return "Can't flag this space because: it is already open.";

        if (this.isFlagged) {
            this.isFlagged = false;
            return "Unflagged the space. You can now open it.";
        }
        else {
            this.isFlagged = true;
            return "Flagged the space. You can no longer open it.";
        }
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