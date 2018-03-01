import java.io.*;
import java.util.concurrent.ExecutionException;

public class aplicacio {

    private final String ruta_rcBugPackage = "./src/rcBugPackage.txt";
    private final String ruta_packageMaintainer = "./src/rcBugPackage.txt";
    private final String fitxerFinal ="emailMainteiner.txt";


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

        //1)Es cerca el nom de paquet associat del llistat rcBugPackage.txt
        String [] paquets = cercaPaquets(BugId);

        //2)Es comprova que el bug existeix. Si no, escriu missatge error
        if(paquets[0].equals("<unknow>")){

            System.out.println("404 - Bug no trobat");

        }else{

            //3)Es passa l'objecte BugId al email
            e.setBug(BugId);

            //4)Es cerquen els paquets associats al BugId
            e.setPaquets(retornaPaquets(paquets[1]));

            //5)S'acaba de construir l'objecte amb els emails i noms dels
            // paquets passat
            pushEmailsAndNoms(e.getPaquets());

        }


    }


    public pushEmailsAndNoms(String [] paquets) {

        File file = new File (this.ruta_packageMaintainer);

        try{

            BufferedReader br = new  BufferedReader(new FileReader(file));

            boolean trobat = false;



            while (br.ready() || !trobat) {
                String linia = br.readLine();
                linia = linia.replaceAll("\\s+","");
                String [] dades =linia.split(";");

                for (String p: paquets){
                    if(p.equals(dades[0])){
                        //agafaElsNomsDelaLinia();
                        //agafaElsEmailsDelaLinia();
                    }
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





    public String [] retornaPaquets(String linia){

        linia = linia.replaceAll("\\s+","");
        String[] paquets = linia.split(",");

        return paquets;
    }



    /**Cerca en rcBugPackage si hi ha una incidència amb el número passat com argument
     * d'entrada. En el cas de no haver cap línia refenciada retorna un sol element
     * anomenat <unknow>
     * @param numBug el número de bug
     * @return un array amb els noms dels paquets refenciats
     */
    public String [] cercaPaquets(String numBug){

        String [] nomPaquets = {"<unknow>"};

        File file = new File (this.ruta_rcBugPackage);

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
