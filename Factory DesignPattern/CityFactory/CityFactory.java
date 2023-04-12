package CityFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CityFactory {

    private ArrayList<City> sindh;
    private ArrayList<City> punjab;
    private ArrayList<City> balochistan;
    private ArrayList<City> kpk;

    public CityFactory() throws FileNotFoundException {
        setUpLists();
    }

    public City getCity(String adminName) {
        City city;

        switch (adminName) {
            case "Sindh":
                city = sindh.get(sindh.size() - 1);
                sindh.remove(sindh.size() - 1);
                return city;
            case "Punjab":
                city = punjab.get(punjab.size() - 1);
                punjab.remove(punjab.size() - 1);
                return city;
            case "Balochistan":
                city = balochistan.get(balochistan.size() - 1);
                balochistan.remove(balochistan.size() - 1);
                return city;
            case "Khyber Pakhtunkhwa":
                city = kpk.get(kpk.size() - 1);
                kpk.remove(kpk.size() - 1);
                return city;
//            case "Islamabad":
//                city = punjab.get(punjab.size() - 1);
//                punjab.remove(punjab.size()-1);
//                return city;
        }
        return null;
    }

    public void printLists() {
        for (int i = 0; i < kpk.size(); i++) {
            System.out.println(kpk.get(i).getCityString() + "   " + i);
        }
    }

    private void setUpLists() throws FileNotFoundException {
        File file = new File("src/pk.csv");
        Scanner in = new Scanner(file);

        sindh = new ArrayList<>();
        punjab = new ArrayList<>();
        balochistan = new ArrayList<>();
        kpk = new ArrayList<>();

        String line = in.nextLine();
        String[] lineElements;

        while (in.hasNextLine()) {
            lineElements = line.split(",");

            switch (lineElements[5]) {
                case "Sindh":
                    sindh.add(new City(lineElements));
                    break;
                case "Punjab":
                    punjab.add(new City(lineElements));
                    break;
                case "Balochistān":
                    balochistan.add(new City(lineElements));
                    break;
                case "Khyber Pakhtunkhwa":
                    kpk.add(new City(lineElements));
                    break;
                case "Islāmābād":
                    lineElements[5] = "Punjab";
                    punjab.add(new City(lineElements));
                    break;
            }

            line = in.nextLine();
        }

    }

}
