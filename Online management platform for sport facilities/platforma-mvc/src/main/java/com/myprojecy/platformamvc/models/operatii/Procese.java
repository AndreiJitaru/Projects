package com.myprojecy.platformamvc.models.operatii;

import com.myprojecy.platformamvc.models.*;

import java.util.ArrayList;

public class Procese {

    public ArrayList<BazaSportiva> bazeSportivePeOras(String oras, ArrayList<BazaSportiva> bazeSportive){

        ArrayList<BazaSportiva> bazaSportivaArrayList = new ArrayList<BazaSportiva>();
        for(BazaSportiva bs: bazeSportive){
            if(bs.getOras().equals(oras)){
                bazaSportivaArrayList.add(bs);
            }
        }
        return bazaSportivaArrayList;
    }

    public ArrayList<String> terenuriBazeSportive(ArrayList<Teren> terenuri, ArrayList<BazaSportiva> bazeSportive, Sport sport,ArrayList<Rezervare> rezervari, String data, String ora){

        ArrayList<String> terenArrayList = new ArrayList<>();
        for(Teren teren : terenuri){
            for(BazaSportiva bs: bazeSportive){
                if((teren.getBazaSportiva().equals(bs)) && (teren.getSport().getName().equals(sport.getName()))){
                    if(isNotEmpty(bs, teren, rezervari, data, ora)){
                        terenArrayList.add(bs.getNume()+ " Teren " +teren.getNumar() + " pret:" + teren.getTarif() + " lei");
                    }
                }
            }
        }

        return terenArrayList;
    }


    private boolean isNotEmpty(BazaSportiva bs, Teren teren, ArrayList<Rezervare> rezervari, String data, String ora){
        for(Rezervare r: rezervari){
            if(r.getData().equals(data) && r.getOra().equals(ora) && r.getNumeBazaSportiva().equals(bs.getNume()) && r.getNumarTeren() == teren.getNumar()){
                return false;
            }
        }
        return true;
    }

    public int gasireIdBazaSportiva(String bazaSportiva, ArrayList<BazaSportiva> bazeSportive){

        for (BazaSportiva bs:bazeSportive){
            if(bs.getNume().equals(bazaSportiva)){
                return bs.getIdBazaSportiva();
            }
        }
        return -1;
    }

    public ArrayList<String> dateProgramare(){
        ArrayList<String> date = new ArrayList<String>();
        date.add("18.01.2019");
        date.add("19.01.2019");
        date.add("20.01.2019");
        date.add("21.01.2019");
        date.add("22.01.2019");
        date.add("23.01.2019");
        date.add("24.01.2019");
        date.add("25.01.2019");
        date.add("26.01.2019");
        date.add("27.01.2019");
        date.add("28.01.2019");
        date.add("29.01.2019");
        return date;
    }

    public ArrayList<String> oreProgramare(){
        ArrayList<String> ore = new ArrayList<String>();
        ore.add("10:00");
        ore.add("11:00");
        ore.add("12:00");
        ore.add("13:00");
        ore.add("14:00");
        ore.add("15:00");
        ore.add("16:00");
        ore.add("17:00");
        ore.add("18:00");
        ore.add("19:00");
        ore.add("20:00");
        ore.add("21:00");
        return ore;
    }

    public ArrayList<Rezervare> rezervareClient(Client c, ArrayList<Rezervare> rezervari){

        ArrayList<Rezervare> rezervariActualizate = new ArrayList<>();
        for(Rezervare r : rezervari){
            if(r.getNumeUtilizator().equals(c.getNumeUtilizator())){
                rezervariActualizate.add(r);
            }
        }

        return rezervariActualizate;
    }


}
