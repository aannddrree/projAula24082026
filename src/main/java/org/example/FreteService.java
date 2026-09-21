package org.example;

public class FreteService {

    private static final double BASE_POR_KM = 1.20;
    private static final double EXCESSO_PESO_LIMITE_KG = 5.0;
    private static final double EXCESSO_PESO_TAXA_POR_KG = 2.0;
    private static final double TAXA_FRAGIL = 15.0;
    private static final double MULTIPLICADOR_EXPRESSO = 1.5;
    private static final double MINIMO = 10.0;
    private static final double MAXIMO = 300.0;

    public double calcularFrete(Pedido pedido){

        // Valor total do Frete do Pedido
        double total = 0.0;

        //Regra 1: Validar dados de entrada
        validar(pedido);

        //Regra 2: Calcular o valor com base na distancia percorrida
        total += pedido.getDistanciaKm() * BASE_POR_KM;

        //Regras 3: Calcular o valor com base no excesso de peso
        if (pedido.getPesoKg() > EXCESSO_PESO_LIMITE_KG){
            double excesso = pedido.getPesoKg() - EXCESSO_PESO_LIMITE_KG;
            total += excesso * EXCESSO_PESO_TAXA_POR_KG;
        }

        //Regra 4: Verificar se o produto é fragil
        if (pedido.isFragil()){
            total += TAXA_FRAGIL;
        }

        //Regra 5: Verifico se o tipo de frete é expresso
        if (pedido.isExpresso()){
            total *= MULTIPLICADOR_EXPRESSO;
        }

        //Regra 6: Piso/teto -> valor minimo e valor maximo do frete
        total = Math.max(MINIMO, Math.min(MAXIMO, total));

        //Regra 7: Frete Gratis valor > 200 e
        // distancia for menor ou igual a 20km e nao for frete gratis.

        if (!pedido.isExpresso() &&
                pedido.getValorItens() >= 200.0 &&
                pedido.getDistanciaKm() <= 20.0){
            return 0.0;
        }
        return total;
    }

    private void validar(Pedido pedido) {
        // Regras referente a distancia e ao peso do produto.
        if (pedido.getDistanciaKm() < 0) throw
                new IllegalArgumentException("Distancia não pode ser negativa");
        if (pedido.getPesoKg() < 0) throw
                new IllegalArgumentException("O peso não pode ser negativo");
        if (pedido.getValorItens() < 0) throw
                new IllegalArgumentException("O valor dos itens não pode ser negativos");
    }

    public static void main(String[] args) {
        Pedido pedido1 =
                new Pedido( 10,
                            -1,
                            10,
                            true,
                            true);
        //new FreteService().calcularFrete(pedido1);
        Pedido padrao = new Pedido(150.0,30.0,
                5.5, false, false);
        Pedido fragil = new Pedido(80.0, 10.0,
                8.0,true, false);
        Pedido expresso = new Pedido(90.0, 9.0,
                2.0, false, true);
        Pedido gratis = new Pedido(250.0, 15.0,
                3.0, false, false);

        FreteService fs = new FreteService();
        System.out.println("Frete Padrão: " + fs.calcularFrete(padrao));
        System.out.println("Frete Frágil: " + fs.calcularFrete(fragil));
        System.out.println("Frete Expresso: " + fs.calcularFrete(expresso));
        System.out.println("Frete Grátis: " + fs.calcularFrete(gratis));

    }
}
