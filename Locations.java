package Server;

import java.util.Random;

public class Locations {

    public String[] locations = {
       "Nicol_Hall_201", "Nicol_Hall_202", "Nicol_Hall_203", "Nicol_Hall_204",
            "Nicol_Hall_301", "Nicol_Hall_301", "Nicol_Hall_301", "Nicol_Hall_301",
            "AKSOB_1001", "AKSOB_1002", "AKSOB_1003", "AKSOB_1004", "AKSOB_1005", "AKSOB_1006",
            "AKSOB_1101", "AKSOB_1102", "AKSOB_1103", "AKSOB_1104", "AKSOB_1105", "AKSOB_1106",
            "Safadi_Fine_Arts_201", "Safadi_Fine_Arts_202", "Safadi_Fine_Arts_203", "Safadi_Fine_Arts_204",
            "Safadi_Fine_Arts_301", "Safadi_Fine_Arts_302", "Safadi_Fine_Arts_303", "Safadi_Fine_Arts_304",
    } ;

    public String getRandomLocation(){
        int index = generateIndex(locations.length);
        return locations[index];
    }

    private int generateIndex(int max){
        Random random = new Random();
        return random.nextInt(max);
    }

}
