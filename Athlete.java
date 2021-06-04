import java.util.Objects;

class Athlete implements Comparable<Athlete>{
    private int ID;
    String name;
    String country;
    Racing personalRaces;

    Athlete(String strName, String strCountry){
        this.name = strName;
        this.country = strCountry;
        this.ID = hashCode();
    }

    void addRacing(Race objRace){
        personalRaces.addRacing(objRace);
    }

    @Override
    public int compareTo(Athlete another) {
        return (this.ID - another.ID);
    }

    @Override
    public boolean equals(Object another) {
        if ((another instanceof Athlete) && (((Athlete)another).name.equals(this.name))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return "Athlete {" +
            "\nID=" + this.ID +
            "\nName=" + this.name +
            "\nCountry=" + this.country + 
            "\n} \n";
    }
}