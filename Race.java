import java.time.*;

abstract class Race implements Comparable<Race>, Cloneable {
    Athlete athlete;
    Modality modality;
    private String raceTime;

    Race(Athlete objAthlete, Modality objModality, String strRaceTime){
        super();
        this.athlete = objAthlete;
        this.modality = objModality;
        this.raceTime = strRaceTime;
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
        return (this.hashCode() - another.hashCode());
    }

    @Override
    public boolean equals(Object another) {
        if ((another instanceof Race) && (((Race)another).athlete.name.equals(this.athlete.name)) && (((Race)another).modality.name.equals(this.modality.name))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Race {" +
            "\nID=" + this.hashCode() +
            "\nModality=" + this.modality.name +
            "\nAthlete {" +
            "\nName=" + this.athlete.name +
            "\nCountry=" + this.athlete.country + 
            "\n} \n";
    }
}