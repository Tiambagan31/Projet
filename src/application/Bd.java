package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bd {

    static Connection connexion;
         //  information de la base de données
    public static void dd() {
        String url = "jdbc:mysql://ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com/db0072788"; // URL de connexion
        String utilisateur = "user0072788"; //  nom d'utilisateur
        String motDePasse = "Yf3IgyBsOPa34WR"; //  mot de passe

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            if (connexion != null) {
                System.out.println("Connexion à la base de données réussie !");
            } else {
                System.out.println("Échec de la connexion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getResultat(String matricule) {
        String resultat = "";
        String query = "SELECT resultat FROM etudiants WHERE matricule = ?";

        try (PreparedStatement stmt = connexion.prepareStatement(query)) {
            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultat = rs.getString("resultat");
            } else {
                resultat = "Matricule non trouvé.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultat = "Erreur lors de la requête.";
        }
        return resultat;
    }

    // Nouvelle méthode pour obtenir les détails
    public static String getDetails(String matricule) {
        String details = "";
        String query = "SELECT nom,prenom, matricule,moyenne,Ecole,resultat FROM etudiants WHERE matricule = ?";

        try (PreparedStatement stmt = connexion.prepareStatement(query)) {
            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                details = "Nom: " + rs.getString("nom") +
                		  "\nPrénom: " + rs.getString("prenom") +
                          "\nMatricule: " + rs.getString("matricule") +
                          "\nEcole:  " + rs.getString("Ecole") +
                         
                          "\nMoyenne:  " + rs.getString("moyenne") + 
                           "\nDécision:  " + rs.getString("resultat");
                          
            } else {
                details = "Matricule non trouvé.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            details = "Erreur lors de la requête.";
        }
        return details;
    }
}