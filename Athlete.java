class Athlete implements Comparable<Athlete>{
    String name;
    String country;

    Athlete(String strName, String strCountry){
        this.name = strName;
        this.country = strCountry;
    }

    @Override
    public int compareTo(Athlete another) {
        return (this.hashCode() - another.hashCode());
    }

    @Override
    public boolean equals(Object another) {
        if ((another instanceof Athlete) && (((Athlete)another).name.equals(this.name))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Athlete {" +
            "\nID=" + this.hashCode() +
            "\nName=" + this.name +
            "\nCountry=" + this.country + 
            "\n} \n";
    }
}