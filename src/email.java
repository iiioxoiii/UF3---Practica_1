import java.util.ArrayList;
import java.util.List;

public class email {



    private final String remitent = "owner@bugs.debian.org";

    private String bug;
    private List<String> emailDestinataris;
    private List<String> nomDestinataris;
    private List<String> paquets;




    public email(){
        this.bug = "unknow";
        this.emailDestinataris = new ArrayList<>();
        this.nomDestinataris = new ArrayList<>();
        this.paquets = new ArrayList<>();
    }





    public String composaEmail() {



        String textmail = "From: " + this.remitent +"\n"+
                "To: " + pinta(emailDestinataris) + "\n" +
                "Dear "+ pinta(nomDestinataris) + "\n" +
                "You have a new bug:" + "\n" +
                pinta(paquets) + " - RC bug number " + this.bug + "\n" +
                "Please, fix it as soon as possible." + "\n" +
                "Cheers." + "\n";

        return textmail;
    }


    public String pintaArray(List<String> llista){
        for (String ll: llista) {
            ll
        }
        return cadena;
    }






    public boolean mailValidat() {

        boolean preparat = false;

        if (this.remitent.equals("<unknow>") ||
                this.emailDestinataris[0].equals("<unknow>") ||
                this.nomDestinataris[0].equals("<unknow>") ||
                this.paquets[0].equals("<unknow>") ||
                this.bug.equals("<unknow>"))
        {
            return false;
        }else {

            preparat = true;

        }

        return preparat;

    }



    public String getRemitent() {
        return remitent;
    }

    public void setRemitent(String remitent) {
        this.remitent = remitent;
    }

    public String[] getDestinataris() {
        return destinataris;
    }

    public void setDestinataris(String[] destinataris) {
        this.destinataris = destinataris;
    }

    public String[] getPaquets() {
        return paquets;
    }

    public void setPaquets(String[] paquets) {
        this.paquets = paquets;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }


}
