import CityFactory.City;

import java.awt.*;
import java.awt.image.ImageObserver;

public class CityButton extends Button {

    City city;

    public CityButton(City city) {
        this.x = city.getLocation().x;
        this.y = city.getLocation().y;
        this.width = 50;
        this.height = 50;
        this.text = city.getName();
        this.city = city;
        setColor();
        textColor = Color.black;
        setDefaultListener();
    }

    @Override
    void paint(Graphics g, ImageObserver ob) {
        g.setColor(color);
        g.drawOval(x, y, width, height);

        drawText(city.getName() + " " + x + " " + y, textColor, g, 12);
    }

    public Point getLocation() {
        return city.getLocation();
    }

    private void setColor() {
        switch (city.getAdminName()) {
            case "Sindh":
                color = Color.yellow;
                break;
            case "Punjab":
                color = Color.green;
                break;
            case "Balochistān":
                color = Color.orange;
                break;
            case "Khyber Pakhtunkhwa":
                color = Color.blue;
                break;
            case "Islāmābād":
                color = Color.green;
                break;
        }
    }

    public void setDefaultListener() {
        this.mouseListener = new mouseListener() {
            @Override
            public void onClick(int x, int y) {
                IsClicked(x, y);
            }
        };
    }

}
