class Modality implements Comparable<Modality>{
    String name;
    Race raceGold;
    Race raceSilver;
    Race raceBronze;

    Modality(String strName){
        name = strName;   
    }

    void setChampionRaces(Race objRaceGold, Race objRaceSilver, Race objRaceBronze){
        raceGold = objRaceGold;
        raceSilver = objRaceSilver;
        raceBronze = objRaceBronze;
    }

    @Override
    public int compareTo(Modality another) {
        return (this.hashCode() - another.hashCode());
    }

    @Override
    public boolean equals(Object another) {
        if ((another instanceof Modality) && (((Modality)another).name.equals(this.name))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Modality {" +
            "\nName=" + this.name +
            "\n} \n";
    }
}