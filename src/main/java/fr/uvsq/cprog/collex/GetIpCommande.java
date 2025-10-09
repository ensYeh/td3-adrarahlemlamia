package fr.uvsq.cprog.collex;

public class GetIpCommande implements Commande {
private final Dns dns;
private final DnsTUI tui;
private final NomMachine nom;

public GetIpCommande(Dns dns, DnsTUI tui, NomMachine nom) {
this.dns = dns;
this.tui = tui;
this.nom = nom;
}
@Override
public void execute() {
dns.getItem(nom).ifPresentOrElse(
item -> tui.affiche(item.getIp().toString()),
() -> tui.affiche("ERREUR : Nom introuvable !")
);
}
}
