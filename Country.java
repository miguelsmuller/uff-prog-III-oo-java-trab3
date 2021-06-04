import java.util.*;

class Country {
    private Set<String> lstCountries = new TreeSet<String>();

    AnalisarDadosSystem SO = new AnalisarDadosSystem();

    void addCountry(String nameContry){
        this.lstCountries.add(nameContry);
    }

    String getFirstCountryRankingMedals(Racing lstRacing){
        return this.getFirstCountryRankingMedals(lstRacing, null);
    }

    String getFirstCountryRankingMedals(Racing lstRacing, Class<?> raceType){
        LinkedHashMap<String, Integer> dictCountries = new LinkedHashMap<String, Integer>();

        for (Race race: lstRacing.getRacing()){
            if (raceType != null) {
                if (raceType.getName() == race.getClass().getName()){
                    String countryName = race.athlete.country;
                    if (dictCountries.containsKey(countryName)) {
                        dictCountries.put(countryName, dictCountries.get(countryName) + 1);
                    }else{
                        dictCountries.put(countryName, 1);
                    }
                }
            }else{
                String countryName = race.athlete.country;
                if (dictCountries.containsKey(countryName)) {
                    dictCountries.put(countryName, dictCountries.get(countryName) + 1);
                }else{
                    dictCountries.put(countryName, 1);
                }
            }
        }

        LinkedHashMap<String, Integer> dictCountries1 = SO.sortDictByValue(dictCountries, false);

        Set<Map.Entry<String, Integer>> mapSet = dictCountries1.entrySet();
        Map.Entry<String, Integer> element = (new ArrayList<Map.Entry<String, Integer>>(mapSet)).get(0);

        return element.getKey() + " with " + element.getValue() + " medals.";
    }
}