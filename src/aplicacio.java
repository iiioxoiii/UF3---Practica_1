import java.io.*;
import java.util.ArrayList;

public class aplicacio {




    public static void main(String[] args) {
        aplicacio inici = new aplicacio();
        try {
            inici.go(args[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void go(String BugId){

        //1. S'instancia l'objecte que escriurà el correu
        email e = new email();

        //2. Es passa el num BugId al email
        e.setBug(BugId);


        //3. S'instancia l'objecte on desar els paquets trobats
        controller ll = new controller();


        //4. Es crea una llista amb tots els paquets, noms i users
        ll.omplirLlista();

        //4. Es cerquen els noms de paquet associats al num bug
        // i es posen en un array
        String [] nomsPaquetsTrobats = ll.cercaPaquets(BugId);

        //5. Es mira si hi ha coincidència amb algí paquet.
        //Si no hi ha cap paquet amb el nom es pinta <unknow>

        if(nomsPaquetsTrobats[0].equals("<unknow>")) {
            System.out.println("404 - Bug no trobat");

            e.escriuMail(e.composaEmailError());




        } else {

            for (paquet p : ll.getLlista()) {

                System.out.println(p.toString());
                for (int i = 0; i < nomsPaquetsTrobats.length; i++) {
                    if(p.getNomPaquet().equals(nomsPaquetsTrobats[i]));
                    e.addPaquetALlista(p);
                }
            }

            e.pintaNoms();
            e.pintaEmails();
            e.pintaNomsPaquets();
            e.escriuMail(e.composaEmail());

        }

    }




    //Entra un nom de paquet i cerca en el fitxer i extreu un array amb nom i email

    public String [] cercaDadesPaquet(String nom){

        String [] nomYemail = {"<unknow>"};

        File file = new File (new Config().packageMaintainer);

        try{

            BufferedReader br = new  BufferedReader(new FileReader(file));

            while (br.readLine() != null) {
                String linia = br.readLine();
                String nomPaquetTemp = nomPaquet(linia);



                if(nomPaquetTemp.equals(nom)){
                    System.out.println("JOOOO");
                }
            }

            br.close();

            return nomYemail;

        }catch (IOException e){
            System.out.println("Error en accés al fitxer");
            e.printStackTrace();
        }


        return nomYemail;
    }




    public String nomPaquet(String linia){
        String [] nomsPaquets = linia.split(";");

        return nomsPaquets[0];
    }

    public String nomUsuari(String linia){
        String [] nomsPaquets = linia.split(";");

        return nomsPaquets[1];
    }

    public String nomEmail(String linia){
        String [] nomsPaquets = linia.split(";");

        return nomsPaquets[2];
    }



    public String [] retornaPaquets(String linia){

        linia = linia.replaceAll("\\s+","");
        String[] nomsPaquets = linia.split(",");

        return nomsPaquets;
    }

}
