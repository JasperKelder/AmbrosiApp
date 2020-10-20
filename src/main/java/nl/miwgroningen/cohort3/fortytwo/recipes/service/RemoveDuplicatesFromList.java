package nl.miwgroningen.cohort3.fortytwo.recipes.service;

import java.util.ArrayList;

public class RemoveDuplicatesFromList {

    public ArrayList<Integer> removeDuplicates(ArrayList<Integer> list)
    {

        // Create a new ArrayList
        ArrayList<Integer> newList = new ArrayList<>();

        // Traverse through the first list
        for (Integer element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }
}