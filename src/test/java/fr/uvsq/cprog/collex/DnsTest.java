package fr.uvsq.cprog.collex;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class DnsTest {
private Path tmp;

@Test
public void testLoadAndQuery() throws Exception {
tmp = Files.createTempFile("dns-test",".txt");
Files.write(tmp, java.util.Arrays.asList("www.toto.fr 1.2.3.4",
"m.toto.fr 1.2.3.5"));
Dns d = new Dns(tmp);
assertTrue(d.getItem(new AdresseIP("1.2.3.4")).isPresent());
assertTrue(d.getItem(new NomMachine("m.toto.fr")).isPresent());
List<DnsItem> items = d.getItems("toto.fr");
assertEquals(2, items.size());
}
@Test
public void testAddItem() throws Exception {
tmp = Files.createTempFile("dns-test-add",".txt");
Dns d = new Dns(tmp);
d.addItem(new AdresseIP("10.0.0.1"), new NomMachine("a.domain"));
assertTrue(d.getItem(new AdresseIP("10.0.0.1")).isPresent());
try {
d.addItem(new AdresseIP("10.0.0.1"), new NomMachine("b.domain"));
fail("Attendu : exception pour IP existante");
} catch (DnsException ex) {
// ok
}
}
}
