package fr.uvsq.cprog.collex;
import java.io.IOException;
public class AddCommande implements Commande {
private final Dns dns;
private final DnsTUI tui;
private final AdresseIP ip;
private final NomMachine nom;
public AddCommande(Dns dns, DnsTUI tui, AdresseIP ip, NomMachine nom) {
this.dns = dns;
this.tui = tui;
this.ip = ip;
this.nom = nom;
}
@Override
public void execute() {
try {
dns.addItem(ip, nom);
tui.affiche("OK");
} catch (DnsException e) {
tui.affiche(e.getMessage());
} catch (IOException e) {
tui.affiche("ERREUR E/S : " + e.getMessage());
}
}
}
