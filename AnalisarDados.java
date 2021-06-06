
import java.io.*;
import java.util.*;
import java.util.Map.*;

public class AnalisarDados{
    static final String FILE_PATH = "others/data.txt";
    public static void main(String[] args) {
        AnalisarDadosSystem SO = new AnalisarDadosSystem();
        
        ArrayList<String> matrixData = SO.getMatrix(FILE_PATH);

        Racing appRacing = new Racing();
        for (String line: matrixData){
            String[] lineCell = line.split(";");

            Modality modality = new Modality(lineCell[0]);
            
            Athlete athleteGold = new Athlete(lineCell[1], lineCell[2]);
            Athlete athleteSilver = new Athlete(lineCell[4], lineCell[5]);
            Athlete athleteBronze = new Athlete(lineCell[7], lineCell[8]);

            Race raceGold = new RaceGold(athleteGold, modality, lineCell[3]);
            Race raceSilver = new RaceSilver(athleteSilver, modality, lineCell[6]);
            Race raceBronze = new RaceBronze(athleteBronze, modality, lineCell[9]);

            appRacing.addRacing(raceGold);
            appRacing.addRacing(raceSilver);
            appRacing.addRacing(raceBronze);
        }

        System.out.println("(a) pais com mais medalhas de ouro: " +
        SO.getFirstCountryRankingMedals(appRacing, RaceGold.class));

        System.out.println("(b) pais com mais medalhas: " +
        SO.getFirstCountryRankingMedals(appRacing));

        System.out.println("(c) atleta com mais medalhas de ouro: " +
        SO.getFirstAthleteRankingMedals(appRacing, RaceGold.class));

        System.out.println("(d) atleta com mais medalhas: " +
        SO.getFirstAthleteRankingMedals(appRacing));

        System.out.println("(e) atleta que gastou mais tempo competindo: " +
        SO.getAthleteSpentMoreTime(appRacing));
    }    
}

class AnalisarDadosSystem {
    private String titleFile = "";
    private ArrayList<String> matrixFile = new ArrayList<String>();
    private BufferedReader currentFile = null;

    LinkedHashMap<String, Double> dictSortByValue(LinkedHashMap<String, Double> unsortMap, boolean order){
        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Double>>(){
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();

        for (Entry<String, Double> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    LinkedHashMap<String, Double> dictWithEqualValue(LinkedHashMap<String, Double> dict, Integer index ){
        LinkedHashMap<String, Double> equalRecordas = new LinkedHashMap<String, Double>();

        Set<Map.Entry<String, Double>> mapSet = dict.entrySet();
        ArrayList<Map.Entry<String, Double>> element = (new ArrayList<Map.Entry<String, Double>>(mapSet));

        String valueOfIndex = (new ArrayList<Map.Entry<String, Double>>(mapSet)).get(index).getValue().toString();
        
        for (Map.Entry<String, Double> entry : element) {
            if (entry.getValue().toString().equals(valueOfIndex) ) {
                equalRecordas.put(entry.getKey(), entry.getValue());         
            }
        }

        return equalRecordas;
    }

    public ArrayList<String> getMatrix(String strFileSource) {
        try {
            currentFile = new BufferedReader(new InputStreamReader(new FileInputStream(strFileSource), "UTF-8"));   
            
            titleFile = currentFile.readLine();
            
            for(String readedLine; (readedLine = currentFile.readLine()) != null; ) {
                String[] splitedLine = readedLine.split(";");
                if (splitedLine.length != 10 )
                    throw new Exception("Formato de dados incorreto!");

                matrixFile.add(readedLine);
            }

            currentFile.close();

        } catch (FileNotFoundException e ) {
            System.out.println("Arquivo não encontrado!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Arquivo não pode ser lido!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro genérico");
            e.printStackTrace();
        }
        
        return matrixFile;
    }

    public String getTitleFile(){
        return titleFile;
    }

    public String getFirstCountryRankingMedals(Racing lstRacing, Class<?> raceType){
        LinkedHashMap<String, Double> dictCountries = new LinkedHashMap<String, Double>();

        for (Race race: lstRacing.getRacing()){
            if (raceType != null) {
                if (raceType.getName() == race.getClass().getName()){
                    String countryName = race.athlete.country;
                    if (dictCountries.containsKey(countryName)) {
                        dictCountries.put(countryName, dictCountries.get(countryName) + 1);
                    }else{
                        dictCountries.put(countryName, (double)1);
                    }
                }
            }else{
                String countryName = race.athlete.country;
                if (dictCountries.containsKey(countryName)) {
                    dictCountries.put(countryName, dictCountries.get(countryName) + 1);
                }else{
                    dictCountries.put(countryName, (double)1);
                }
            }
        }

        LinkedHashMap<String, Double> dictCountries1 = this.dictSortByValue(dictCountries, false);
        LinkedHashMap<String, Double> dictWithEqualValues = this.dictWithEqualValue(dictCountries1, 0);

        return dictWithEqualValues.toString();
    }

    public String getFirstAthleteRankingMedals(Racing lstRacing, Class<?> raceType){
        LinkedHashMap<String, Double> dictAthletes = new LinkedHashMap<String, Double>();

        for (Race race: lstRacing.getRacing()){
            String athlete = race.athlete.name;

            if (raceType != null) {
                if ((raceType.getName() == race.getClass().getName())) {
                    if (dictAthletes.containsKey(athlete)) {
                        dictAthletes.put(athlete, dictAthletes.get(athlete) + 1);
                    } else {
                        dictAthletes.put(athlete, (double)1);
                    }
                }
            } else {
                if (dictAthletes.containsKey(athlete)) {
                    dictAthletes.put(athlete, dictAthletes.get(athlete) + 1);
                } else {
                    dictAthletes.put(athlete, (double)1);
                }
            }
        }

        LinkedHashMap<String, Double> dictCountriesOrdened = this.dictSortByValue(dictAthletes, false);
        LinkedHashMap<String, Double> dictWithEqualValues = this.dictWithEqualValue(dictCountriesOrdened, 0);

        return dictWithEqualValues.toString();
    }

    public String getAthleteSpentMoreTime(Racing lstRacing){
        LinkedHashMap<String, Double> dictAthletes = new LinkedHashMap<String, Double>();

        for (Race race: lstRacing.getRacing()){
            String athlete = race.athlete.name;
            Double duration = (double)race.getDuration().toMillis();

            if (dictAthletes.containsKey(athlete)) {
                dictAthletes.put(athlete, duration + dictAthletes.get(athlete) );
            }else{
                dictAthletes.put(athlete, duration);
            }
        }

        LinkedHashMap<String, Double> dictOrdened = this.dictSortByValue(dictAthletes, false);
        LinkedHashMap<String, Double> dictWithEqualValues = this.dictWithEqualValue(dictOrdened, 0);

        return dictWithEqualValues.toString();
    }

    public String getFirstCountryRankingMedals(Racing lstRacing){
        return this.getFirstCountryRankingMedals(lstRacing, null);
    }

    public String getFirstAthleteRankingMedals(Racing lstRacing){
        return this.getFirstAthleteRankingMedals(lstRacing, null);
    }
} 