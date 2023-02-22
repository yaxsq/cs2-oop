import java.io.File;

public class Car {

    private String name;
    private String price;
    private String newPriceRoadworthy;
    private String roadTax3Month;
    private String bodyType;
    private String transmission;
    private String seatCount;
    private String segment;
    private String intro;
    private String end;

    public Car(String name, String price, String newPriceRoadworthy, String roadTax3Month, String bodyType, String transmission, String seatCount, String segment, String intro, String end) {
        this.name = name;
        this.price = price;
        this.newPriceRoadworthy = newPriceRoadworthy;
        this.roadTax3Month = roadTax3Month;
        this.bodyType = bodyType;
        this.transmission = transmission;
        this.seatCount = seatCount;
        this.segment = segment;
        this.intro = intro;
        this.end = end;
    }

    public Car(String details) {
        String[] detailsArr = details.split("'");

        this.name = detailsArr[0];
        this.price = detailsArr[1];
        this.newPriceRoadworthy = detailsArr[2];
        this.roadTax3Month = detailsArr[3];
        this.bodyType = detailsArr[4];
        this.transmission = detailsArr[5];
        this.seatCount = detailsArr[6];
        this.segment = detailsArr[7];
        this.intro = detailsArr[8];
        if (detailsArr.length == 10)
            this.end = detailsArr[9];
    }

    public void getCarFromFile(File file) {

    }

    public String getName () {
        return name;
    }

    public String getEnd () {
        return end;
    }

}
