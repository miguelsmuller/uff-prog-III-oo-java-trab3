class Modality implements Comparable<Modality>{
    String name;

    Modality(String strName){
        name = strName;   
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
            "\nID=" + this.hashCode() +
            "\nName=" + this.name +
            "\n} \n";
    }
}