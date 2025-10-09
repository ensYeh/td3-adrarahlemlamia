package fr.uvsq.cprog.collex;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
public class DnsTUITest {
@Test
public void testParseGetIp() throws Exception {
Dns dns = new Dns(java.nio.file.Files.createTempFile("dnstest",".txt"));
DnsTUI tui = new DnsTUI(dns, new ByteArrayInputStream(new byte[0]));
Commande c = tui.parse("www.example.com");
assertNotNull(c);
}
}
