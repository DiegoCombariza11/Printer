package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.Archive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class checkSheets {
    public int totalSheet(ArrayList<Archive> documents){
        int total = 0;
        for (Archive document : documents) {
            total = total + document.getPages();
        }
        return total;
    }

    public boolean checkSheets(int requestedSheets, int aviableSheets){
        if(requestedSheets>=aviableSheets){
            return false;
        }else{
            return true;
        }
    }

    public int stablishAmountOfSheets(int requestedSheets, int aviableSheets){
        return aviableSheets-requestedSheets;
    }

    public int addSheets(int newSheets, int aviableSheets){
        return newSheets+aviableSheets;
    }

    public ArrayList<Archive> sortAchieves(ArrayList<Archive> Archives){
        Archives.sort((Archive1, Archive2) -> Integer.compare(Archive1.getPages(), Archive2.getPages()));
        ArrayList<Archive> sortedAchives = new ArrayList<>(Archives);
        return sortedAchives;
    }
}
