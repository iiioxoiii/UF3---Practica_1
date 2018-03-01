public class email {



    private final String remitent = "owner@bugs.debian.org";

    private String bug = "<unknow>";
    private String[] emailDestinataris = {"<unknow>"};
    private String[] nomDestinataris = {"<unknow>"};
    private String[] paquets = {"<unknow>"};



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


    public String pinta(String [] c){
        String cadena = "";
        for (String d : c) {
            cadena = cadena.concat(" ").concat(d);
        }
        return cadena;
    }

    public String pintaNomsDestinataris(){
        String eDest = "";
        for (String d : this.emailDestinataris) {
            eDest = eDest.concat(" ").concat(d);
        }
        return eDest;
    }


    public String pintaPaquets(){
        String paquets = "";
        for (String d : this.paquets) {
            paquets = paquets.concat(" ").concat(d);
        }
        return paquets;
    }





    public boolean mailValidat() {

        boolean preparat = false;

        if (this.remitent.equals("<unknow>") ||
                this.destinataris[0].equals("<unknow>") ||
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
