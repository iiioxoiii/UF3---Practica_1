import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class email {

    private final String remitent = "owner@bugs.debian.org";

    private String bug;
    private ArrayList<paquet> paquets;




    public email(){
        this.bug = "unknow";
        this.paquets = new ArrayList<>();
    }


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



    public String emailValidat() {

        String textmail = "From: " + this.remitent +"\n"+
                "To: " + pintaEmails() + "\n" +
                "Dear "+ pintaNoms() + "\n" +
                "You have a new bug:" + "\n" +
                pintaNomsPaquets() + " - RC bug number " + this.bug + "\n" +
                "Please, fix it as soon as possible." + "\n" +
                "Cheers." + "\n";

        return textmail;
    }


    public String emailError(){
        String textmail = "No hi ha dades associades al bug: "+ "\n"+
                " - RC bug number " + this.bug + "\n" +
                "Something not work." + "\n" +
                "Cheers." + "\n";

        return textmail;
    }



    public String pintaNoms(){

        String cadena="";

        for (paquet ll: this.paquets) {
            cadena = ll.getNameUser().concat(" ");
        }

        return cadena;
    }


    public String pintaEmails(){

        String cadena="";

        for (paquet ll: this.paquets) {
            cadena = ll.getEmailUser().concat(" ");
        }

        return cadena;
    }


    public String pintaNomsPaquets(){

        String cadena="";

        for (paquet ll: this.paquets) {
            cadena = ll.getNomPaquet().concat(" ");
        }

        return cadena;
    }



    public void setBug(String bug) {

        this.bug = bug;
    }



    public void addPaquetALlista( paquet p){

        this.paquets.add(p);
    }

}
