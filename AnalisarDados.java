import java.util.*;

public class AnalisarDados{
    static final String FILE_PATH = "others/data.txt";
    public static void main(String[] args) {
        AnalisarDadosSystem SO = new AnalisarDadosSystem();
        
        ArrayList<String> matrixData = SO.getMatrix(FILE_PATH);

        Country appCountry = new Country();
        Racing appRacing = new Racing();
        Athletes appAthletes = new Athletes();
        //Modalities appModalities = new Modalities();

        for (String line: matrixData){
            String[] lineCell = line.split(";");

            Modality modality = new Modality(lineCell[0]);
            
            Athlete athleteGold = new Athlete(lineCell[1], lineCell[2]);
            Athlete athleteSilver = new Athlete(lineCell[4], lineCell[5]);
            Athlete athleteBronze = new Athlete(lineCell[7], lineCell[8]);

            Race raceGold = new RaceGold(athleteGold, modality, lineCell[3]);
            Race raceSilver = new RaceSilver(athleteSilver, modality, lineCell[6]);
            Race raceBronze = new RaceBronze(athleteBronze, modality, lineCell[9]);

            appCountry.addCountry(lineCell[2]);

            appRacing.addRacing(raceGold);
            appRacing.addRacing(raceSilver);
            appRacing.addRacing(raceBronze);

            // athleteGold.addRacing(raceGold);
            // athleteSilver.addRacing(raceSilver);
            // athleteBronze.addRacing(raceBronze);            

            appAthletes.addAthlete(athleteGold);
            appAthletes.addAthlete(athleteSilver);
            appAthletes.addAthlete(athleteBronze);

            //modality.setChampionRaces(raceGold, raceSilver, raceBronze);
            //appModalities.addModality(modality);
        }

        System.out.println("(a) país com mais medalhas de ouro: " +
        appCountry.getFirstCountryRankingMedals(appRacing, RaceGold.class));

        System.out.println("(b) país com mais medalhas: " +
        appCountry.getFirstCountryRankingMedals(appRacing));

        System.out.println("(c) atleta com mais medalhas de ouro: " +
        appCountry.getFirstCountryRankingMedals(appRacing, RaceGold.class));

        System.out.println("(d) atleta com mais medalhas: " +
        appCountry.getFirstCountryRankingMedals(appRacing));

        System.out.println("(e) atleta que gastou mais tempo competindo: " +
        appCountry.getFirstCountryRankingMedals(appRacing));
    }    
}