package fr.uvsq.cprog.collex;

import java.util.Arrays;
public final class AdresseIP implements Comparable<AdresseIP> {
private final int[] octets = new int[4];
public AdresseIP(String ip) {
if (ip == null) {
throw new IllegalArgumentException("Adresse IP nulle");
}
String[] parts = ip.trim().split("\\.");
if (parts.length != 4) {
throw new
IllegalArgumentException("Format d'adresse IP invalide: " + ip);
}
for (int i = 0; i < 4; i++) {
try {
int v = Integer.parseInt(parts[i]);
if (v < 0 || v > 255) {
throw new IllegalArgumentException("Octet hors plage: "
+ parts[i]);
}
octets[i] = v;
} catch (NumberFormatException e) {
throw new IllegalArgumentException("Octet invalide: " +
parts[i], e);
}
}
}
@Override
public String toString() {
return String.format("%d.%d.%d.%d", octets[0], octets[1], octets[2],
octets[3]);
}
@Override
public boolean equals(Object obj) {
if (this == obj) {
return true;
}
if (!(obj instanceof AdresseIP)) {
return false;
}
AdresseIP other = (AdresseIP) obj;
return Arrays.equals(this.octets, other.octets);
}
@Override
public int hashCode() {
return Arrays.hashCode(octets);
}
@Override
public int compareTo(AdresseIP o) {
for (int i = 0; i < 4; i++) {
int cmp = Integer.compare(this.octets[i], o.octets[i]);
if (cmp != 0) {
return cmp;
}
}
return 0;
}
}

