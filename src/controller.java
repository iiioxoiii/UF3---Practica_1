import java.io.*;

import java.util.ArrayList;


public class controller {




    public ArrayList <paquet> llista = new ArrayList<paquet>();



    public void escriuMail(String mail){
        config c = new config();

        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(c.fitxerFinal)));

            bwr.write(mail);
            bwr.close();

        }catch (IOException e){
            e.printStackTrace();

        }
    }


    public static String numeroBug(String linia){
        String [] paraules;
        paraules = linia.split(";");
        paraules[0]=paraules[0].replaceAll("\\s+","");

        return paraules[0];
    }


    /**retorna el nom del paquet
     * @param linia sencera
     * @return un array de Strings amb els noms dels paquets
     */
    public static String [] nomPaquets(String linia){
        String [] paraules;
        paraules = linia.split(";");

        paraules[1]=paraules[1].replaceAll("\\s+","");
        String [] nomPaquets =  paraules[1].split(",");

        return nomPaquets;
    }


    public static String nomUser(String linia){

        String [] paraules;
        paraules = linia.split(";");

        return paraules[1];

    }

    public static String emailUser(String linia){
        String [] paraules;
        paraules = linia.split(";");

        return paraules[2];
    }


    public String [] cercaPaquets(String numBug){

        String [] nomPaquets = {"<unknow>"};

        File file = new File (new config().rcBugPackage);

        try{

            BufferedReader br = new  BufferedReader(new FileReader(file));

            while (br.readLine() != null) {
                String linia = br.readLine();
                String numTemp = numeroBug(linia);

                if(numBug.equals(numTemp)){

                    nomPaquets= nomPaquets(linia);

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






    public void omplirLlista() {

        config c = new config();

        File f = new File(c.packageMaintainer);

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            while (br.readLine() != null) {

                String linia = br.readLine();
                linia = linia.replaceAll("\\s+", "");


                String[] token = linia.split(";");

                paquet p = new paquet();

                if(token.length < 3){
                    p.setNomPaquet("<unknow>");
                    p.setNameUser("<unknow>");
                    p.setEmailUser("<unknow>");
                }else{
                    p.setNomPaquet(token[0]);
                    p.setNameUser(token[1]);
                    p.setEmailUser(token[2]);
                }
                llista.add(p);
            }

            br.close();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



        public ArrayList<paquet> getLlista() {

        return llista;
    }



}
