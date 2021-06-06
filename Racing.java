import java.util.*;

class Racing {
    private Set<Race> lstRacing = new TreeSet<Race>();

    void addRacing(Race objRace){
        lstRacing.add(objRace);
    }

    Set<Race> getRacing() { return lstRacing; }
}