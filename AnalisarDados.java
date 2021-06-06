
public class AnalisarDados{
    public static void main(String[] args) {
        AnalisarDadosSystem SO = new AnalisarDadosSystem();
        
        SO.appBooting(args);

        System.out.println("(a) pais com mais medalhas de ouro: " +
        SO.getFirstCountryRankingMedals(RaceGold.class).toString());

        System.out.println("(b) pais com mais medalhas: " +
        SO.getFirstCountryRankingMedals().toString());

        System.out.println("(c) atleta com mais medalhas de ouro: " +
        SO.getFirstAthleteRankingMedals(RaceGold.class).toString());

        System.out.println("(d) atleta com mais medalhas: " +
        SO.getFirstAthleteRankingMedals().toString());

        System.out.println("(e) atleta que gastou mais tempo competindo: " +
        SO.getAthleteSpentMoreTime().toString());

        SO.appShutdown();
    }    
}