package application;

import javafx.scene.image.Image;

public class Player {
    private String name;
    private Image image;

    public Player(String name, String imagePath) {
        this.name = name;
        this.image = new Image(imagePath);
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    // Additional methods if necessary
}
