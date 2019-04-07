package com.myprojecy.platformamvc.controllers;

import com.myprojecy.platformamvc.models.*;
import com.myprojecy.platformamvc.models.data.*;
import com.myprojecy.platformamvc.models.operatii.Procese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;

@Controller
@RequestMapping(value="platforma")
public class ControllerBazaSportiva {


    private Client clientLogat;

    private String data;

    private String ora;

    private Sport sport;

    private double pret;

    public int flag = 0;

    private String strRezervare;
    @Autowired
    private BazaSportivaDao bazaSportivaDao;

    @Autowired
    private TerenDao terenDao;

    private ArrayList<String> denumiri = new ArrayList<>();

    @Autowired
    private ClientDao clientDao;

    @RequestMapping(value="")
    public String firstPage(){
        return "platforma/index2";
    }

    @RequestMapping(value="logIn")
    public String logIn(){
        return "platforma/logIn";
    }

    @RequestMapping(value="home")
    public String home(){
        return "platforma/index";
    }

    @RequestMapping(value="error")
    public String error(){
        return "platforma/error";
    }

    @RequestMapping(value="about")
    public String about(){
        return "platforma/about";
    }

    @RequestMapping(value="signUp")
    public String signUp(){
        return "platforma/signUp";
    }

    @RequestMapping(value="payment")
    public String payment(){
        return "platforma/payment";
    }

    @RequestMapping(value="error", method = RequestMethod.POST)
    public String goPayment(){
        return "redirect:/platforma/payment";
    }


    //@RequestMapping(value="home", method = RequestMethod.POST)////////////////
   // public String home1(){
    //    return "platforma/index";
   // }


    @RequestMapping(value="booking")
    public String booking(Model model) {

        for(String d : denumiri){
            System.out.println(d);
        }
        model.addAttribute("denumiri", denumiri);
        return "platforma/booking";
    }

    @RequestMapping(value="booking", method = RequestMethod.POST)
    public String goToPayment(@RequestParam String denumire) {

        strRezervare = denumire;
        return "redirect:/platforma/payment";
    }

    @RequestMapping(value="services", method = RequestMethod.GET)
    public String afisareOrase(Model model){

        ArrayList<BazaSportiva> bazeSportive = (ArrayList<BazaSportiva>) bazaSportivaDao.findAll();
        ArrayList<String> orase = new ArrayList<String>();
        for(BazaSportiva bazaSportiva : bazeSportive) {
            String oras = bazaSportiva.getOras();
            if (!orase.contains(oras)) {
                orase.add(oras);
            }
        }
        Procese procese = new Procese();
        ArrayList<String> date = procese.dateProgramare();
        ArrayList<String> ore = procese.oreProgramare();
        model.addAttribute("orase", (Iterable) orase);
        model.addAttribute("sporturi", Sport.values());
        model.addAttribute("date", date);
        model.addAttribute("ore", ore);
        return "platforma/services";
    }



    @RequestMapping(value="services", method = RequestMethod.POST)
    public String bazeSportiveGasite(Model model, @RequestParam("oras") String oras, @RequestParam Sport sport,
                                        @RequestParam String data, @RequestParam String ora){

        ArrayList<BazaSportiva> bazeSportive = (ArrayList<BazaSportiva>) bazaSportivaDao.findAll();
        ArrayList<Teren> terenuri = (ArrayList<Teren>) terenDao.findAll();
        ArrayList<Rezervare> rezervari = (ArrayList<Rezervare>) rezervareDao.findAll();
        Procese procese = new Procese();
        System.out.println(oras);
        bazeSportive = procese.bazeSportivePeOras(oras, bazeSportive);
        denumiri.clear();
        denumiri = procese.terenuriBazeSportive(terenuri, bazeSportive, sport, rezervari, data, ora);
        this.ora = ora;
        this.data = data;
        this.sport = sport;
        return "redirect:/platforma/booking";
    }


    @RequestMapping(value = "logIn", method = RequestMethod.POST)
    public String registerUser(@RequestParam @Valid String numeUtilizator,@RequestParam @Valid String parola){
        ArrayList<Client> clienti = (ArrayList<Client>) clientDao.findAll();
        for(Client c:clienti){
            if(numeUtilizator.equals(c.getNumeUtilizator()) && parola.equals(c.getParola())) {
                clientLogat = c;
                flag = 0;
                return "redirect:/platforma/home";
            }
        }

        flag = 1;
        return "redirect:/platforma/logIn";
    }


    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public String addUser(@RequestParam @Valid String numeUtilizator, @RequestParam @Valid String parola,@RequestParam @Valid String email,
                          @RequestParam @Valid String nume, @RequestParam @Valid String prenume, @RequestParam @Valid String numarTelefon){


        Client client = new Client(nume, prenume, email, numarTelefon, numeUtilizator, parola);

        ArrayList<Client> clienti = (ArrayList<Client>) clientDao.findAll();
        for(Client c:clienti) {
            if (numeUtilizator.equals(c.getNumeUtilizator())) {
                flag = 1;
                return "redirect:/platforma/signUp";
            }
        }

        clientDao.save(client);
        flag = 0;
        return "redirect:/platforma/logIn";
    }

    @Autowired
    private CardDao cardDao;

    @Autowired
    private RezervareDao rezervareDao;


    @RequestMapping(value="payment", method = RequestMethod.POST)
    public String makePayment(@RequestParam String numarCard, @RequestParam String codSecuritate,
                              @RequestParam String nume, @RequestParam String prenume){

        Rezervare rezervare;

        int cod = 199990;
        try {
            cod = Integer.parseInt(codSecuritate);
        }catch (Exception e){
            flag = 1;
        }
        System.out.println("numar card");
        Card myCard = new Card(numarCard, nume, prenume, cod);

        ArrayList<Card> carduri = (ArrayList<Card>) cardDao.findAll();



        String[] partiTeren = strRezervare.split(" Teren ");
        String[] partiPret = partiTeren[1].split(" pret:");
        String[] partiLei = partiPret[1].split( " lei");

        pret = Double.parseDouble(partiLei[0]);

        int terenNo = Integer.parseInt(partiPret[0]);


        for(Card card:carduri){
            if(card.getNume().equals(myCard.getNume()) && card.getPrenume().equals(myCard.getPrenume()) &&  card.getCodSecuritate() == myCard.getCodSecuritate() && card.getNumarCard().equals(myCard.getNumarCard())){
                if(card.getSuma() >= pret){
                    Card c = cardDao.findOne(numarCard);
                    cardDao.delete(c);
                    if(clientLogat.getNumarRezervari()>=10) {
                        c.setSuma((float) (card.getSuma() - pret + 10));
                    }
                    else
                    {
                        c.setSuma((float) (card.getSuma() - pret));
                    }
                    Client cl = clientDao.findOne(clientLogat.getNumeUtilizator());
                    cl.setNumarRezervari(cl.getNumarRezervari() + 1);
                    clientDao.save(cl);
                    clientLogat.setNumarRezervari(cl.getNumarRezervari());

                    cardDao.save(c);
                    rezervare = new Rezervare(clientLogat.getNumeUtilizator(), data, ora, terenNo, partiTeren[0], sport);
                    rezervareDao.save(rezervare);


                    return "redirect:/platforma/home";
                }
            }

        }

        return "redirect:/platforma/error";
    }

    @RequestMapping(value="displayBookings", method = RequestMethod.GET)
    public String myBookings(Model model){

        Procese procese = new Procese();
        ArrayList<Rezervare> rezervari = procese.rezervareClient(clientLogat, (ArrayList<Rezervare>) rezervareDao.findAll());

        for(Rezervare r: rezervari) {
            System.out.println(r.getIdRezervare() + "---------->>>>>>>>>>>>>>>>>>");
        }
        model.addAttribute("rezervari", rezervari);

        return "platforma/displayBookings";
    }




    private static int counter = 0;
    private void writeFeedBack(String feedBack){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("FeedBack/FeedBack"+ counter+++ ".txt"), "utf-8"));

            writer.write(feedBack);

        } catch (IOException ex) {

        } finally {
            try {writer.close();}catch (Exception ex){ }
        }
    }


    @RequestMapping(value = "home", method = RequestMethod.POST)
    public String addFeedBack(@RequestParam String feedBack){
        writeFeedBack(feedBack);
        return "redirect:/platforma/home";
    }

}
