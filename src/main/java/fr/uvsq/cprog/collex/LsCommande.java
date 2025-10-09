package fr.uvsq.cprog.collex;
import java.util.List;
public class LsCommande implements Commande {
private final Dns dns;
private final DnsTUI tui;
private final String domaine;
private final boolean sortByIp;
public LsCommande(Dns dns, DnsTUI tui, String domaine, boolean sortByIp)
{
this.dns = dns;
this.tui = tui;
this.domaine = domaine;
this.sortByIp = sortByIp;
}
@Override
public void execute() {
List<DnsItem> items = sortByIp ? dns.getItemsSortedByIp(domaine) :
dns.getItems(domaine);
if (items.isEmpty()) {
tui.affiche("(aucune machine pour le domaine)");
return;
}
for (DnsItem it : items) {
tui.affiche(it.getIp().toString() + " " +
it.getNom().toString());
}
}
}