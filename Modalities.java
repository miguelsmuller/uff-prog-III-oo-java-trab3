import java.util.*;

class Modalities {
    private Set<Modality> lstModalities = new TreeSet<Modality>();

    void addModality(Modality objModality){
        lstModalities.add(objModality);
    }

    Set<Modality> getModalities() { return lstModalities; }

    float calculaAlgumInfo(){ return 0; }
}