import java.util.*;

class Athletes {
    private Set<Athlete> lstAthletes = new TreeSet<Athlete>();

    AnalisarDadosSystem SO = new AnalisarDadosSystem();

    void addAthlete(Athlete objAthlete){
        lstAthletes.add(objAthlete);
    }

    Set<Athlete> getAthletes() { return lstAthletes; }
}