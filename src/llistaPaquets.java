import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class llistaPaquets {

    private ArrayList <paquet> llista = new ArrayList<paquet>();


    public void omplirLlista(){

        Config c = new Config();

        File f = new File(c.packageMaintainer);

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            while (br.readLine() != null){

                String linia = br.readLine();
                String [] token = linia.split(";");

                paquet p = new paquet();
                p.setNomPaquet(token[0]);
                p.setNameUser(token[1]);
                p.setEmailUser(token[2]);

                llista.add(p);
            }

            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public paquet cercaPaquet(String nom){

        Iterator<paquet> itrPaquets = llista.iterator();

        paquet paquetSortida = new paquet();

        boolean trobat = false;

        ////¿?¿?
        while (itrPaquets.hasNext() && !trobat){
            paquet p = itrPaquets.next();

            if(nom.equals(p.getNomPaquet())) {
                trobat = true;

                return p;
            }
        }

        if(!trobat){
            paquetSortida.setNomPaquet(nom);
            paquetSortida.setEmailUser("unknow");
            paquetSortida.setNameUser("unknow");
        }

        return paquetSortida;
    }

}
