package fr.uvsq.cprog.collex;
public class QuitCommande implements Commande {
private final DnsTUI tui;
public QuitCommande(DnsTUI tui) {
this.tui = tui;
}
@Override
public void execute() {
tui.affiche("Au revoir !");
}
}
