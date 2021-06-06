
import java.io.*;
import java.util.*;
import java.util.Map.*;

class AnalisarDadosSystem {
    private String titleOfCurrentFile = "";
    private ArrayList<String> arryOfCurrentFile = new ArrayList<String>();
    
    /*
     * Class constructor
     */
    Racing lstRacing = new Racing();


    /**
     * Method that starts the application's execution
     * 
     * @param args String[] - Parameters coming from the main method 
     */
    public void appBooting(String[] args){
        this.readFile(args);

        for (String line: arryOfCurrentFile){
            String[] lineCell = line.split(";");

            Modality modality = new Modality(lineCell[0]);
            
            Athlete athleteGold = new Athlete(lineCell[1], lineCell[2]);
            Athlete athleteSilver = new Athlete(lineCell[4], lineCell[5]);
            Athlete athleteBronze = new Athlete(lineCell[7], lineCell[8]);

            Race raceGold = new RaceGold(athleteGold, modality, lineCell[3]);
            Race raceSilver = new RaceSilver(athleteSilver, modality, lineCell[6]);
            Race raceBronze = new RaceBronze(athleteBronze, modality, lineCell[9]);

            this.lstRacing.addRacing(raceGold);
            this.lstRacing.addRacing(raceSilver);
            this.lstRacing.addRacing(raceBronze);
        }
    }


    /**
     * Method that displays the final comments of program execution
     */
    public void appShutdown(){
        System.out.println("\n");
        System.out.println("OBS: As respostas são apresentadas como conjunto em virtude do arquivo de origem dos dados poder, em certas situações, apresentar empate nas resposas.");
    }


    /**
     * Method that reads the data file and stores the information 
     * 
     * @param args String[] - Parameters coming from the main method 
     */
    private void readFile(String[] args) {
        final String DEFAULT_FILE = "data.txt";
        BufferedReader bufferedCurrentFile = null;
        
        try {
            if (args.length != 0) {
                System.out.println("Fazendo leitura do arquivo '" + args[0] + "'.");
                System.out.println("");
                bufferedCurrentFile = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
            } else {
                System.out.println("Nenhum arquivo definido como parâmetro do comando.");
                System.out.println("Fazendo leitura do arquivo padrão '" + DEFAULT_FILE + "'.");
                System.out.println("");
                bufferedCurrentFile = new BufferedReader(new InputStreamReader(new FileInputStream(DEFAULT_FILE), "UTF-8"));
            }               
            
            this.titleOfCurrentFile = bufferedCurrentFile.readLine();
            
            for(String readedLine; (readedLine = bufferedCurrentFile.readLine()) != null; ) {
                String[] splitedLine = readedLine.split(";");
                if (splitedLine.length != 10 )
                    throw new Exception("Formato de dados no arquivo de leitura incorreto!");

                this.arryOfCurrentFile.add(readedLine);
            }

            bufferedCurrentFile.close();

        } catch (FileNotFoundException e ) {
            System.out.println("<Arquivo não encontrado! Encerrando Execução!>");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("<Arquivo não pode ser lido! Encerrando Execução!>");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("<Erro genérico>");
            System.exit(1);
        }
    }
    

    /**
     * Method that returns the title of the data file being analyzed
     * 
     * @return String - Title of the data file being analyzed
     */
    public String getTitleFile(){
        return this.titleOfCurrentFile;
    }


    /**
     * Method that returns the group of countries that was most often
     * in the specified type
     *
     * @param Class - Class that defines define the type of position
     * 
     * @return LinkedHashMap - Set in format <String, Double> containing
     * country name and number of occurrences
     */
    public LinkedHashMap<String, Double> getFirstCountryRankingMedals(Class<?> raceType){
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

        LinkedHashMap<String, Double> dictCountriesOrdened = this.dictSortByValue(dictCountries, false);
        LinkedHashMap<String, Double> dictWithEqualValues = this.dictWithEqualValue(dictCountriesOrdened, 0);

        return dictWithEqualValues;
    }
    public LinkedHashMap<String, Double> getFirstCountryRankingMedals(){
        return this.getFirstCountryRankingMedals(null);
    }


    /**
     * Method that returns group of athletes that was most often in the specified type
     * 
     * @param Class - Class that defines define the type of position
     * 
     * @return LinkedHashMap - Set in format <String, Double> containing
     * athlete name and number of occurrences
     */
    public LinkedHashMap<String, Double> getFirstAthleteRankingMedals(Class<?> raceType){
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

        return dictWithEqualValues;
    }
    public LinkedHashMap<String, Double> getFirstAthleteRankingMedals(){
        return this.getFirstAthleteRankingMedals(null);
    }


    /**
     * Method that returns group of athletes who spent more time in competition
     * 
     * @return LinkedHashMap - Set in format <String, Double> containing 
     * athlete name and time in milliseconds spent 
     */
     public LinkedHashMap<String, Double> getAthleteSpentMoreTime(){
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

        return dictWithEqualValues;
    }


    /**
     * Method that sorts a LinkedHashMap/Dictionary in format <String, Double> by value 
     * 
     * @param dictionary - LinkedHashMap/Dictionary of elements to be analyzed
     * @param order - True sort ascending; False sort descending 
     * 
     * @return LinkedHashMap - LinkedHashMap/Dictionary converted to String 
     */
    private LinkedHashMap<String, Double> dictSortByValue(LinkedHashMap<String, Double> dictionary, boolean order){
        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(dictionary.entrySet());

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


    /**
     * Method that returns a LinkedHashMap/Dictionary in format <String, Double> with a 
     * value equal to the value of the element with the index entered in the parameter
     * 
     * @param dictionary - LinkedHashMap/Dictionary of elements to be analyzed
     * @param index - Element index taken as reference
     * 
     * @return LinkedHashMap - LinkedHashMap/Dictionary converted to String 
     */
    private LinkedHashMap<String, Double> dictWithEqualValue(LinkedHashMap<String, Double> dictionary, Integer index){
        LinkedHashMap<String, Double> equalRecords = new LinkedHashMap<String, Double>();

        Set<Map.Entry<String, Double>> mapSet = dictionary.entrySet();
        ArrayList<Map.Entry<String, Double>> element = (new ArrayList<Map.Entry<String, Double>>(mapSet));

        String valueOfIndex = (new ArrayList<Map.Entry<String, Double>>(mapSet)).get(index).getValue().toString();
        
        for (Map.Entry<String, Double> entry : element) {
            if (entry.getValue().toString().equals(valueOfIndex) ) {
                equalRecords.put(entry.getKey(), entry.getValue());         
            }
        }

        return equalRecords;
    }
} 