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

        //S'instancia l'objecte que escriurà el correu
        email e = new email();

        //Es passa el num BugId al email
        e.setBug(BugId);

        //S'instancia l'objecte on desar els paquets trobats
        controller ll = new controller();

        //Es crea un arrayList  amb objectes paquet
        ll.omplirLlista();

        //Es cerquen els noms de paquet associats al num bug
        // i es posen en un array
        String [] nomsPaquetsTrobats = ll.cercaPaquets(BugId);

        //Es mira si hi ha coincidència amb algún paquet.
        //Si no hi ha cap paquet amb el nom es pinta <unknow>
        if(nomsPaquetsTrobats[0].equals("<unknow>")) {
            System.out.println("404 - Bug no trobat");

            //I escriu un correu aamb l'error
            e.escriuMail(e.emailError());

            //Si no, amb la llista els noms dels paquets trobats es fa una
            //iteració sobre la llista global de nom,user,email.
            //En cas de ser trobar-se d'afegeix al arrayList del mail

        } else {

            for (paquet p : ll.getLlista()) {

                for (int i = 0; i < nomsPaquetsTrobats.length; i++) {

                    if(p.getNomPaquet().equals(nomsPaquetsTrobats[i])) {

                        e.addPaquetALlista(p);

                    } else{
                        p.setEmailUser("<unknow>");
                        p.setNameUser("<unknow>");
                        p.setNomPaquet(nomsPaquetsTrobats[i]);
                        e.addPaquetALlista(p);
                    }
                }

            }

            //Finalment es construeix el mail
            e.pintaNoms();
            e.pintaEmails();
            e.pintaNomsPaquets();
            e.escriuMail(e.emailValidat());
        }

    }

}
