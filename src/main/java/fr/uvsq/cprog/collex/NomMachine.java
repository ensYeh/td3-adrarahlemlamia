package fr.uvsq.cprog.collex;


public final class NomMachine {
private final String full;
private final String machine;
private final String domaine;


public NomMachine(String full) {
if (full == null) {
throw new IllegalArgumentException("Nom null");
}
String t = full.trim();
int idx = t.indexOf('.');
if (idx <= 0 || idx == t.length() - 1) {
throw new IllegalArgumentException("Nom de machine doit être 'machine.domaine' : " + full);
}
this.machine = t.substring(0, idx);
this.domaine = t.substring(idx + 1);
this.full = t;
}


public String getMachine() {
return machine;
}


public String getDomaine() {
return domaine;
}


@Override
public String toString() {
return full;
}


@Override
public boolean equals(Object obj) {
if (this == obj) return true;
if (!(obj instanceof NomMachine)) return false;
NomMachine other = (NomMachine) obj;
return this.full.equals(other.full);
}


@Override
public int hashCode() {
return full.hashCode();
}
}