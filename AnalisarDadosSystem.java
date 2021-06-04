import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class AnalisarDadosSystem {
    private String titleFile = "";
    private ArrayList<String> matrixFile = new ArrayList<String>();
    private BufferedReader currentFile = null;

    public String getTitleFile(){ return titleFile; }

    public ArrayList<String> getMatrix(String strFileSource) {
        try {
            currentFile = new BufferedReader(new FileReader(strFileSource));   
            
            titleFile = currentFile.readLine();
            
            for(String readedLine; (readedLine = currentFile.readLine()) != null; ) {
                String[] splitedLine = readedLine.split(";");
                if (splitedLine.length != 10 )
                    throw new Exception("Formato de dados incorreto!");

                matrixFile.add(readedLine);
            }

            currentFile.close();

        } catch (FileNotFoundException e ) {
            System.out.println("Arquivo não encontrado!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Arquivo não pode ser lido!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro genérico");
            e.printStackTrace();
        }
        
        return matrixFile;
    }

    public LinkedHashMap<String, Integer> sortDictByValue(LinkedHashMap<String, Integer> unsortMap, final boolean order){
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

        for (Entry<String, Integer> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}