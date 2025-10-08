package fr.uvsq.cprog.collex;
import org.junit.Test;
import static org.junit.Assert.*;


public class AdresseIPTest {


@Test
public void testValid() {
AdresseIP ip = new AdresseIP("192.168.0.1");
assertEquals("192.168.0.1", ip.toString());
}


@Test(expected = IllegalArgumentException.class)
public void testInvalidFormat() {
AdresseIP ip = new AdresseIP("192.168.1");
}


@Test(expected = IllegalArgumentException.class)
public void testInvalidOctet() {
new AdresseIP("256.0.0.1");
}


@Test
public void testEqualsAndHash() {
AdresseIP a = new AdresseIP("1.2.3.4");
AdresseIP b = new AdresseIP("1.2.3.4");
assertEquals(a, b);
assertEquals(a.hashCode(), b.hashCode());
}
}