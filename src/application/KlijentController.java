package application;

import classes.Klijent;
import classes.Korisnik;
import classes.Obavjestenje;
import classes.Rezervacija;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class KlijentController implements Initializable {

    public Klijent klijent;
    Parent root;
    Scene scene;
    Stage stage;

    @FXML
    Label ime, prezime, username, obavjestenje1;

    public void setKorisnik(Klijent k){
        klijent = k;
        ime.setText(klijent.getIme());
        prezime.setText(klijent.getPrezime());
        username.setText(klijent.getKorisnickoIme());

        List<Obavjestenje> temp = List.copyOf(Obavjestenje.all);

        for(Obavjestenje o: temp){
            if (o.getKlijent().equals(klijent)){
                AlertBox.display(o.toString(), ":(");
                Obavjestenje.all.remove(o);
                try {
                    database.Write.deleteObavjestenje(o);
                } catch (SQLException e) {
                    System.out.println("Greska u bazi.");
                }
            }
        }

        obavjestenje1.setVisible(false);

        for(Rezervacija r: Rezervacija.all){
            if(r.getKlijent().equals(klijent) && r.getOtkazana().equals("ne") &&
                    r.getAranzman().getDatumPolaska().isBefore(LocalDate.now().plusDays(17)) &&
                    r.getPlaceno() != r.getUkupnaCijena()){
                obavjestenje1.setVisible(true);
                break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void promjenaLozinke(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/promjena-lozinke.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PromjenaLozinkeController PLC = loader.getController();
        PLC.setKorisnik(klijent);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void odjava(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/aplikacija.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void novaRezervacija(ActionEvent event){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/klijent-rezervacija.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        KlijentRezervacijaController krc = loader.getController();
        krc.setKlijent(klijent);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void pregledajRezervacije(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/klijent-pregled.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        KlijentPregledController kpc = loader.getController();
        kpc.setKlijent(klijent);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
