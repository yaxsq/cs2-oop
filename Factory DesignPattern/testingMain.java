import CityFactory.CityFactory;
import CityFactory.City;

import java.io.FileNotFoundException;

public class testingMain {

    public static void main(String[] args) throws FileNotFoundException {

        CityFactory cf = new CityFactory();

        City city = cf.getCity("Punjab");
        System.out.println(city.getCityString());

    }


}
