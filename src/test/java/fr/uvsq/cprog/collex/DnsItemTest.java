package fr.uvsq.cprog.collex;
import org.junit.Test;
import static org.junit.Assert.*;


public class DnsItemTest {


@Test
public void testToString() {
DnsItem it = new DnsItem(new AdresseIP("1.2.3.4"), new NomMachine("m.example.com"));
assertEquals("1.2.3.4 m.example.com", it.toString());
}
}