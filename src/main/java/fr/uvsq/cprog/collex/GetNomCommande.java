package fr.uvsq.cprog.collex;
public class GetNomCommande implements Commande {
private final Dns dns;
private final DnsTUI tui;
private final AdresseIP ip;
public GetNomCommande(Dns dns, DnsTUI tui, AdresseIP ip) {
this.dns = dns;
this.tui = tui;
this.ip = ip;
13
}
@Override
public void execute() {
dns.getItem(ip).ifPresentOrElse(
item -> tui.affiche(item.getNom().toString()),
() -> tui.affiche("ERREUR : Adresse introuvable !")
);
}
}
