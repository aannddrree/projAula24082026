import jdk.jfr.TransitionTo;
import org.example.FreteService;
import org.example.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class FreteServiceTest {
    private final FreteService fs = new FreteService();
    private static final double DELTA = 0.0001;

    @Test (expected = IllegalArgumentException.class)
    public void validarDeveRejeitarDistanciaNegativa(){
        fs.calcularFrete(
                new Pedido(100.0,-1, 2, false, false)
        );
    }

    @Test (expected = IllegalArgumentException.class)
    public void validarPesoNegativo(){
        fs.calcularFrete(
                new Pedido(50.0, 10.0, -5, true, true)
        );
    }

    @Test
    public void validarBasePorDistanciaPercorrida(){
        Pedido p =
                new Pedido(50.0,
                        10.0,
                        3.0,
                        false,
                        false);

        Assert.assertEquals(12.0, fs.calcularFrete(p), DELTA);
    }

    @Test
    public void validarExcessoPesoAcimaLimite(){
        Pedido p = new Pedido(12.0, 10.0, 5.5, false, false);

        Assert.assertEquals(13.0, fs.calcularFrete(p), DELTA);
    }

    @Test
    public void validarFreteExpresso(){
        Pedido p = new Pedido(90.0, 5.0,
                2.0, false, true);

        // 1.2 * 5 => 6 / * 1.5 => 9.0 => 10
        Assert.assertEquals(10.0, fs.calcularFrete(p), DELTA);
    }

    @Test
    public void freteGratis(){
        Pedido gratis = new Pedido(220.0, 20.0, 2.0, false, false);
        Assert.assertEquals(0.0, fs.calcularFrete(gratis), DELTA);
    }
}
