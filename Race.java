import java.time.*;
import java.util.Objects;

abstract class Race implements Comparable<Race>, Cloneable {
    private int ID;
    Athlete athlete;
    Modality modality;
    private String raceTime;

    Race(){}
    Race(Athlete objAthlete, Modality objModality, String strRaceTime){
        super();
        this.athlete = objAthlete;
        this.modality = objModality;
        this.raceTime = strRaceTime;
        this.ID = hashCode();
    }

    Duration getDuration(){
        String[] arrDuration = raceTime.split(":");
        Duration rtnDuration;
        Double partOfSeconds, partOfMinutes;
        
        partOfSeconds = Double.parseDouble(arrDuration[0]);
        rtnDuration = Duration.ofMillis((long)(partOfSeconds*1000));

        if (arrDuration.length != 1 ){ 
            partOfMinutes = Double.parseDouble(arrDuration[0]);
            partOfSeconds = Double.parseDouble(arrDuration[1]);
            
            rtnDuration = Duration.ofMillis((long)(partOfMinutes*6000) + (long)(partOfSeconds*1000));
        }

        return rtnDuration;
    }

    @Override
    public int compareTo(Race another) {
        return (this.ID - another.ID);
    }

    @Override
    public boolean equals(Object another) {
        if ((another instanceof Race) && (((Race)another).athlete.name.equals(this.athlete.name)) && (((Race)another).modality.name.equals(this.modality.name))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.athlete.name + this.modality.name);
    }

    @Override
    public String toString() {
        return "Race {" +
            "\nID=" + this.ID +
            "\nModality=" + this.modality.name +
            "\nAthlete {" +
            "\nID=" + this.ID + 
            "\nName=" + this.athlete.name +
            "\nCountry=" + this.athlete.country + 
            "\n} \n";
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}