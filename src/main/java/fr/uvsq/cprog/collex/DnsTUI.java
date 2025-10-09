package fr.uvsq.cprog.collex;
import java.io.InputStream;
import java.util.Scanner;
public class DnsTUI {
private final Dns dns;
private final Scanner scanner;
public DnsTUI(Dns dns, InputStream in) {
this.dns = dns;
this.scanner = new Scanner(in);
}
// lit une ligne de l'entrée standard
public Commande nextCommande() {
if (!scanner.hasNextLine()) {
return new QuitCommande(this);
}
String line = scanner.nextLine().trim();
return parse(line);
}
// méthode utile pour les tests : parse une ligne donnée
public Commande parse(String line) {
if (line == null || line.trim().isEmpty()) {
return new Commande() { public void execute() { /* rien */ } };
}
String s = line.trim();
if (s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("exit")) {
    return new QuitCommande(this);
}
if (s.startsWith("add ")) {
String[] parts = s.split("\\s+");
if (parts.length != 3) {
return new Commande() { public void execute() {
affiche("ERREUR : usage -> add <ip> <nom>"); } };
}
try {
AdresseIP ip = new AdresseIP(parts[1]);
NomMachine nom = new NomMachine(parts[2]);
return new AddCommande(dns, this, ip, nom);
} catch (IllegalArgumentException e) {
return new Commande() { public void execute() {
affiche("ERREUR : " + e.getMessage()); } };
}
}
if (s.startsWith("ls")) {
String[] parts = s.split("\\s+");
boolean sortByIp = false;
String domaine = null;
if (parts.length == 2) {
domaine = parts[1];
} else if (parts.length == 3 && "-a".equals(parts[1])) {
sortByIp = true;
domaine = parts[2];
} else {
return new Commande() { public void execute() {
affiche("ERREUR : usage -> ls [-a] <domaine>"); } };
}
return new LsCommande(dns, this, domaine, sortByIp);
}
// ip simple
if (s.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
try {
AdresseIP ip = new AdresseIP(s);
return new GetNomCommande(dns, this, ip);
} catch (IllegalArgumentException e) {
return new Commande() { public void execute() {
affiche("ERREUR : ip invalide"); } };
}
}
// nom machine
try {
NomMachine nom = new NomMachine(s);
return new GetIpCommande(dns, this, nom);
} catch (IllegalArgumentException e) {
return new Commande() { public void execute() {
    affiche("ERREUR : commande inconnue"); } };
}
}

public void affiche(String texte) {
System.out.println(texte);
}
}
