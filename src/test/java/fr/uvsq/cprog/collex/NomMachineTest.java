package fr.uvsq.cprog.collex;



import org.junit.Test;
import static org.junit.Assert.*;


public class NomMachineTest {


@Test
public void testParse() {
NomMachine n = new NomMachine("www.uvsq.fr");
assertEquals("www", n.getMachine());
assertEquals("uvsq.fr", n.getDomaine());
}


@Test(expected = IllegalArgumentException.class)
public void testInvalid() {
new NomMachine("invalidname");
}
}