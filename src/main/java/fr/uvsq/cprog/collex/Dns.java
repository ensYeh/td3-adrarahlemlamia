package fr.uvsq.cprog.collex;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
public class Dns {
private final Map<AdresseIP, DnsItem> byIp = new HashMap<>();
private final Map<NomMachine, DnsItem> byName = new HashMap<>();
private final Path dbPath;
// Charge le chemin depuis dns.properties (fichier à la racine du projet)
public Dns() throws IOException {
Properties props = new Properties();
try (InputStream in =
Files.newInputStream(Paths.get("dns.properties"))) {
props.load(in);
}
String f = props.getProperty("dns.file");
if (f == null) {
throw new IllegalArgumentException("Propriété dns.file introuvable dans dns.properties");
}
this.dbPath = Paths.get(f);
load();
}
public Dns(Path dbPath) throws IOException {
this.dbPath = dbPath;
load();
}
private void load() throws IOException {
byIp.clear();
byName.clear();
if (!Files.exists(dbPath)) {
// créer le fichier et ses dossiers parents si nécessaire
if (dbPath.getParent() != null) {
Files.createDirectories(dbPath.getParent());
}
Files.write(dbPath, Collections.emptyList(),
StandardCharsets.UTF_8);
return;
}
List<String> lines = Files.readAllLines(dbPath,
StandardCharsets.UTF_8);
for (String l : lines) {
String t = l.trim();
if (t.isEmpty() || t.startsWith("#")) continue;
String[] parts = t.split("\\s+");
if (parts.length != 2) continue; // ignorer les lignes malformées
NomMachine nom = new NomMachine(parts[0]);
AdresseIP ip = new AdresseIP(parts[1]);
DnsItem it = new DnsItem(ip, nom);
byIp.put(ip, it);
byName.put(nom, it);
}
}
public Optional<DnsItem> getItem(AdresseIP ip) {
return Optional.ofNullable(byIp.get(ip));
}
public Optional<DnsItem> getItem(NomMachine nom) {
return Optional.ofNullable(byName.get(nom));
}
public List<DnsItem> getItems(String domaine) {
return byName.values().stream()
.filter(i ->
i.getNom().getDomaine().equalsIgnoreCase(domaine))
.sorted(Comparator.comparing(i -> i.getNom().getMachine()))
.collect(Collectors.toList());
}
public List<DnsItem> getItemsSortedByIp(String domaine) {
return byName.values().stream()
.filter(i ->
i.getNom().getDomaine().equalsIgnoreCase(domaine))
.sorted(Comparator.comparing(DnsItem::getIp))
.collect(Collectors.toList());
}
public synchronized void addItem(AdresseIP ip, NomMachine nom) throws
DnsException, IOException {
if (byIp.containsKey(ip)) {
throw new DnsException("ERREUR : L'adresse IP existe déjà !");
}
if (byName.containsKey(nom)) {
throw new DnsException("ERREUR : Le nom de machine existe déjà !");
}
DnsItem it = new DnsItem(ip, nom);
byIp.put(ip, it);
byName.put(nom, it);
String line = nom.toString() + " " + ip.toString() +
System.lineSeparator();
Files.write(dbPath, line.getBytes(StandardCharsets.UTF_8),
StandardOpenOption.CREATE, StandardOpenOption.APPEND);
}
}
