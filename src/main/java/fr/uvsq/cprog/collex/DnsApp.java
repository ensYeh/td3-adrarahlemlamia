package fr.uvsq.cprog.collex;

public final class DnsApp {
    public static void main(String[] args) {
        try {
            
            Dns dns = new Dns();

            DnsTUI tui = new DnsTUI(dns,System.in);

            System.out.println("DNS Simulator - tapez 'quit' pour sortir");

            while(true) {
                Commande cmd = tui.nextCommande();
                cmd.execute();

                if (cmd instanceof QuitCommande) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
