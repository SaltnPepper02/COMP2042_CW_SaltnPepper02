package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.demo.Account.getAccounts;
import static com.example.demo.Main.pstage;

/**
 * This class is to control the leaderboard screen
 *
 * @author Richard Gan Soon Ching
 */
public class LBController implements Initializable {// used to set Leaderboard

    @FXML
    private TableView<Account> table;
    @FXML
    private TableColumn<Account, String> PColumn;

    @FXML
    private TableColumn<Account, Long> SColumn;

    /**
     * Go back to menu
     * @param event on event
     * @throws IOException if there is no menu to go back to
     */
    public void whenBackPushed(ActionEvent event) throws IOException{
        getAccounts().clear();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/demo/menu.fxml")));
        pstage.setTitle("Welcome");
        Scene scene = new Scene(root);
        pstage.setScene(scene);
        pstage.show();
    }

    ObservableList<Account> list = FXCollections.observableArrayList(getAccounts());

    /**
     * set table view for leaderboard
     * @param url pass url
     * @param resourceBundle pass resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("userName"));
        SColumn.setCellValueFactory(new PropertyValueFactory<Account, Long>("score"));

        table.setItems(list);
    }
}
