package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MonApplication extends Application {

    @Override
    public void start(Stage stageline) {
        // Connexion à la base de données
        Bd.dd();

        // Création d'un en-tête
        Label headerLabel = new Label("Consultation des résultats");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #4A4A4A; -fx-padding: 10;");

        // Création des éléments de l'interface utilisateur
        TextField matriculeField = new TextField();
        matriculeField.setPromptText("Entrez votre matricule");
        matriculeField.setStyle("-fx-padding: 10px; -fx-border-color: #007BFF; -fx-border-radius: 5px; -fx-background-color: #F0F8FF;");

        Button consultButton = new Button("Consulter le résultat");
        consultButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px;");
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: #333;");

        // Action à exécuter lorsque le bouton est cliqué
        consultButton.setOnAction(e -> {
            String matricule = matriculeField.getText();
            String resultat = Bd.getResultat(matricule); // Appel de la méthode getResultat
            resultLabel.setText(resultat);
        });

        // Bouton pour afficher les détails
        Button detailsButton = new Button("Détails");
        detailsButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px;");
        detailsButton.setOnAction(e -> {
            String matricule = matriculeField.getText();
            String details = Bd.getDetails(matricule); // Appel de la méthode getDetails

            // Affichage des détails dans une alerte
            Alert detailsAlert = new Alert(Alert.AlertType.INFORMATION);
            detailsAlert.setTitle("Détails de l'étudiant");
            detailsAlert.setHeaderText("Informations sur l'étudiant");
            detailsAlert.setContentText(details);
            detailsAlert.showAndWait();
        });

        // Bouton pour fermer l'application
        Button closeButton = new Button("Fermer");
        closeButton.setStyle("-fx-background-color: #FF3B3B; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px;");
        closeButton.setOnAction(e -> stageline.close());

        // Création d'un layout vertical pour organiser les éléments
        VBox layout = new VBox(15, headerLabel, matriculeField, consultButton, resultLabel, detailsButton, closeButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #E6E6FA;");

        // Création de la scène et ajout au stage
        Scene scene = new Scene(layout, 300, 300);
        stageline.setScene(scene);
        stageline.setTitle("Consultation des résultats d'examen");
        stageline.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
