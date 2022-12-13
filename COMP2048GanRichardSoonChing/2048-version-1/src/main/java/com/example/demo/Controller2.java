package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.Account.accounts;
import static com.example.demo.Main.pstage;

/**
 * @author Richard Gan Soon Ching
 */
public class Controller2 implements Initializable {// used to set Leaderboard

    @FXML
    private TableView<Account> table;
    @FXML
    private TableColumn<Account, String> PColumn;

    @FXML
    private TableColumn<Account, Long> SColumn;

    /**
     * back to menu
     * @param event
     * @throws IOException
     */
    public void whenBackPushed(ActionEvent event) throws IOException{
        accounts.clear();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        pstage.setTitle("Welcome");
        Scene scene = new Scene(root);
        pstage.setScene(scene);
        pstage.show();
    }

    ObservableList<Account> list = FXCollections.observableArrayList(accounts);

    /**
     * set table view for leaderboard
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("userName"));
        SColumn.setCellValueFactory(new PropertyValueFactory<Account, Long>("score"));

        table.setItems(list);
    }
}
