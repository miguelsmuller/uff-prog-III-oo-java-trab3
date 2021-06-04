import java.util.*;

class Athletes {
    private Set<Athlete> lstAthletes = new TreeSet<Athlete>();

    AnalisarDadosSystem SO = new AnalisarDadosSystem();

    void addAthlete(Athlete objAthlete){
        lstAthletes.add(objAthlete);
    }

    Set<Athlete> getAthletes() { return lstAthletes; }

    String getFirstAthleteRankingMedals(){
        return this.getFirstAthleteRankingMedals(null);
    }

    String getFirstAthleteRankingMedals(Class<?> raceType){
        LinkedHashMap<String, Integer> dictCountries = new LinkedHashMap<String, Integer>();

        // for (Athlete athlete: lstAthletes){
        //     if (raceType != null) {
        //         if (raceType.getName() == athlete.getClass().getName()){
        //             String countryName = athlete.athlete.country;
        //             if (dictCountries.containsKey(countryName)) {
        //                 dictCountries.put(countryName, dictCountries.get(countryName) + 1);
        //             }else{
        //                 dictCountries.put(countryName, 1);
        //             }
        //         }
        //     }else{
        //         String countryName = athlete.athlete.country;
        //         if (dictCountries.containsKey(countryName)) {
        //             dictCountries.put(countryName, dictCountries.get(countryName) + 1);
        //         }else{
        //             dictCountries.put(countryName, 1);
        //         }
        //     }
        // }

        LinkedHashMap<String, Integer> dictCountries1 = SO.sortDictByValue(dictCountries, false);

        Set<Map.Entry<String, Integer>> mapSet = dictCountries1.entrySet();
        Map.Entry<String, Integer> element = (new ArrayList<Map.Entry<String, Integer>>(mapSet)).get(0);

        return element.getKey() + " with " + element.getValue() + " medals.";
    }
}