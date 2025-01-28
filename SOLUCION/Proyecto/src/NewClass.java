
import java.util.Scanner;

/**
 *
 * @author hansa
 */
public class NewClass {

    public static void main(String[] args) {
        double[] sueldos = IngresarSueldos();
        double vivienda = 0, educacion = 0, alimentacion = 0, vestimenta = 0, salud = 0, turismo = 0;
        ingresarFactura("Vivienda");
        ingresarFactura("Educacion");
        ingresarFactura("Alimentacion");
        ingresarFactura("Vestimenta");
        ingresarFactura("Salud");
        ingresarFactura("Turismo");

        double ingresoAnual = calcularIngresoAnual(sueldos);
        double deducciones = vivienda + educacion + alimentacion + vestimenta + salud + turismo;
        double impuesto = calcularImpuesto(ingresoAnual, deducciones);

        System.out.println("\nResumen de la Declaracion de Impuestos");
        System.out.println("Ingreso Anual: " + ingresoAnual);
        System.out.println("Deducciones Totales: " + deducciones);
        System.out.println("Base Imponible: " + (ingresoAnual - deducciones));
        System.out.println("Impuesto a Pagar: " + impuesto);
    }

    public static double[] IngresarSueldos() {
        double[] sueldos = new double[12];
        double sueldoMensual = solicitarDatoNumerico("Ingrese el sueldo mensual: ");
        for (int i = 0; i < 12; i++) {
            sueldos[i] = sueldoMensual;
        }
        return sueldos;
    }

    public static double ingresarFactura(String categoria) {
        return solicitarDatoNumerico("Ingrese el monto de facturas para " + categoria + ": ");
    }

    public static double calcularIngresoAnual(double[] sueldos) {
        double total = 0;
        for (int i = 0; i < sueldos.length; i++) {
            total += sueldos[i];
        }
        return total;
    }

    public static double calcularImpuesto(double ingresoAnual, double deducciones) {
        double[] rangosA = {0, 2000, 5000, 1000};
        double[] porcentaje = {0, 0.05, 0.1, 0.15, 0.2};
        double ingresoImponible = ingresoAnual - deducciones;
        double impuesto = 0;
        for (int i = 0; i < rangosA.length - 1; i++) {
            if (ingresoImponible > rangosA[i + 1]) {
                double rangoIngreso = rangosA[i + 1] - rangosA[i];
                impuesto += rangoIngreso * porcentaje[i + 1];
            } else if (ingresoImponible > rangosA[i]) {
                impuesto += (ingresoImponible - rangosA[i]) * porcentaje[i + 1];
                break;
            }
        }
        return impuesto;
    }

    public static double solicitarDatoNumerico(String mensaje) {
        Scanner tcl = new Scanner(System.in);
        while (true) {
            System.out.print(mensaje); 
            if (tcl.hasNextDouble()) { 
                return tcl.nextDouble(); 
            } else {
                System.out.println("Ingrese un numero valido.");
                tcl.next(); 
            }
        }
    }
}
