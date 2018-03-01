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
            //escriuFileERROR("Bug no trobat");
        }else{

            for (String paquet: paquets) {
               // cercaDadesMainer(paquet);
            }
        }

        //2)Si el bug existeix es cerquen els administradors dels paquets el l'altre
        //fitxer.

    }


    /**Cerca en rcBugPackage si hi ha una incidència amb el número passat com argument
     * d'entrada. En el cas de no haver cap línia refenciada retorna un sol element
     * anomenat"<unkow>"
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




    public static int countLines(String filename) throws IOException {
        LineNumberReader reader  = new LineNumberReader(new FileReader(filename));
        int cnt = 0;
        String lineRead = "";
        while ((lineRead = reader.readLine()) != null) {}

        cnt = reader.getLineNumber();
        reader.close();
        return cnt;
    }









/*
    public void cerca(String fromUser, String MaintainerEmail, String packageName, String bug){



        From: owner@bugs.debian.org
        To: maintainerEmail [, maintainerEmail2, ...]
        Dear maintainerName, [ maintainerName2, ...]
        You have a new bug:
        packageName [, packageName2, ...] - RC bug number #bugId
        Please, fix it as soon as possible.
                Cheers.

    }
*/
}
