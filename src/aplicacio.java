import java.io.*;
import java.util.concurrent.ExecutionException;

public class aplicacio {




    public static void main(String[] args) {
        aplicacio inici = new aplicacio();
        try {
            inici.go(args[0]);

        }catch (Exception e){
            System.out.println("Error en pas de paràmetre");
        }
    }


    public void go(String BugId){

        email e = new email();
        llistaPaquets ll = new llistaPaquets();


        //1)Es quen els noms de paquet associats al num bug
        String [] paquetsTrobats = cercaPaquets(BugId);
        if(paquetsTrobats[0].equals("<unknow>")){
            System.out.println("404 - Bug no trobat");
        }

        //2)Es passa l'objecte BugId al email
            e.setBug(BugId);


        //3)Es crea llista de tots els paquets
            ll.omplirLlista();

        //3)Es cerca la info dels paquets referenciat al num del bub
        for (int i = 1; i < paquetsTrobats.length; i++) {
            ll.cercaPaquet(paquetsTrobats[i]);
        }



    }






    public String [] retornaPaquets(String linia){

        linia = linia.replaceAll("\\s+","");
        String[] nomsPaquets = linia.split(",");

        return nomsPaquets;
    }



    /**Cerca en rcBugPackage si hi ha una incidència amb el número passat com argument
     * d'entrada. En el cas de no haver cap línia refenciada retorna un sol element
     * anomenat <unknow>
     * @param numBug el número de bug
     * @return un array amb els noms dels paquets refenciats
     */
    public String [] cercaPaquets(String numBug){

        String [] nomPaquets = {"<unknow>"};

        File file = new File (new Config().rcBugPackage);

        try{

            BufferedReader br = new  BufferedReader(new FileReader(file));

            boolean trobat = false;

            while (br.ready() || !trobat) {
                String linia = br.readLine();
                String numTemp = numeroBug(linia);

                if(numBug.equals(numTemp)){

                    nomPaquets= nomPaquets(linia);

                    trobat = true;
                }
            }

            br.close();

            return nomPaquets;

        }catch (IOException e){
            System.out.println("Error en accés al fitxer");
            e.printStackTrace();
        }

        return nomPaquets;
    }






    /**retorna el nom del paquet
     * @param linia sencera
     * @return nom del paquet
     */
    public static String [] nomPaquets(String linia){
        String [] paraules;
        paraules = linia.split(";");

        paraules[1]=paraules[1].replaceAll("\\s+","");
        String [] nomPaquets =  paraules[1].split(",");

        return nomPaquets;
    }





    public static String numeroBug(String linia){
        String [] paraules;
        paraules = linia.split(";");
        paraules[0]=paraules[0].replaceAll("\\s+","");

        return paraules[0];
    }


}
