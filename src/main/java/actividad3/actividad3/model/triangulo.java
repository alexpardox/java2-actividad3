package actividad3.actividad3.model;

public class triangulo {
    private double base;
    private double altura;

    public triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcularArea() {
        return (base * altura) / 2;
    }

    public double calcularPerimetro() {
        return (altura * 2) + base;
    }
}
