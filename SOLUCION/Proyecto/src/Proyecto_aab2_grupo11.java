/**
 *
 * @author hansa
 */
import java.util.Scanner;

public class Proyecto_aab2_grupo11 {

    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        int meses = 12;
        int numCategorias = 6;
        String[] categorias = {"Vivienda", "Educacion", "Alimentacion", "Vestimenta", "Salud", "Turismo"};

        double[][] tablaImpuestos = {
            {0, 400, 0, 0.0},
            {400, 1000, 0, 0.02}, 
            {1000, 5000, 12, 0.05}, 
            {5000, 11722, 100, 0.08}, 
            {11722, 14935, 0, 0.05},
            {14935, 18666, 161, 0.10},
            {18666, 22418, 534, 0.12},
            {22418, 32783, 984, 0.15},
            {32783, 43147, 2539, 0.20},
            {43147, 53512, 4612, 0.25},
            {53512, 63876, 7203, 0.30},
            {63876, 103644, 10312, 0.35}
};

        double[] sueldos = registroDeSueldos(tcl, meses);
        double[] facturas = registroDeFacturas(tcl, numCategorias, categorias);
        double totalIngreso = sumarArreglo(sueldos);
        double totalDeducciones = sumarArreglo(facturas);
        double ingresoNeto = (totalIngreso - totalDeducciones);
        double impuesto = calcularImpuesto(ingresoNeto, tablaImpuestos);
        generarDeclaracion(totalIngreso, totalDeducciones, ingresoNeto, impuesto);
    }

    public static double[] registroDeSueldos(Scanner tcl, int meses) {
        double[] sueldos = new double[meses];
        System.out.println("Ingrese sus sueldos mensuales:");
        for (int i = 0; i < meses; i++) {
            do {
                System.out.print("Sueldo mes " + (i + 1) + ": ");
                sueldos[i] = tcl.nextDouble();
            } while (sueldos[i] < 0);
        }
        return sueldos;
    }

    public static double[] registroDeFacturas(Scanner tcl, int numCategorias, String[] categorias) {
        double[] facturas = new double[numCategorias];
        System.out.println("\nIngrese el total anual de facturas por cada categora:");
        for (int i = 0; i < numCategorias; i++) {
            do {
                System.out.print(categorias[i] + ": ");
                facturas[i] = tcl.nextDouble();
                if (facturas[i] < 0) {
                    System.out.println("Error: El monto no puede ser negativo.");
                }
            } while (facturas[i] < 0);
        }
        return facturas;
    }

    public static double sumarArreglo(double[] arreglo) {
        double suma = 0;
        for (int i = 0; i < arreglo.length; i++) {
            suma += arreglo[i];
        }
        return suma;
    }

    public static double calcularImpuesto(double ingresoNeto, double[][] tabla) {
        double impuesto = 0;

        if (ingresoNeto <= tabla[0][1]) {
            return impuesto;
        }

        for (int i = 0; i < tabla.length; i++) {
            double limInf = tabla[i][0];
            double limSup = tabla[i][1];
            double base = tabla[i][2];
            double tasa = tabla[i][3];

            if (ingresoNeto > limInf && ingresoNeto <= limSup) {
                impuesto = base + (ingresoNeto - limInf) * tasa;
                break;
            }
        }

        return impuesto;
    }

    public static void generarDeclaracion(double totalIngreso, double totalDeducciones, double ingresoNeto, double impuesto) {
        System.out.println("\n******** Declaracion de Impuesto a la Renta 2023 ********");
        System.out.printf("Total de ingresos anuales: %.2f%n", totalIngreso);
        System.out.printf("Total de deducciones (facturas): %.2f%n", totalDeducciones);
        System.out.printf("Ingreso neto (ingresos - deducciones): %.2f%n", ingresoNeto);
        System.out.printf("Impuesto a pagar: %.2f%n", impuesto);
        if (impuesto == 0) {
            System.out.println("Usted no presenta obligaciones tributarias.");
        }
        System.out.println("*********************************************************");
    }

}
/***
 * Run 
 * Ingrese sus sueldos mensuales:
Sueldo mes 1: 700
Sueldo mes 2: 700
Sueldo mes 3: 700
Sueldo mes 4: 700
Sueldo mes 5: 700
Sueldo mes 6: 700
Sueldo mes 7: 700
Sueldo mes 8: 700
Sueldo mes 9: 700
Sueldo mes 10: 700
Sueldo mes 11: 700
Sueldo mes 12: 700

Ingrese el total anual de facturas por cada categora:
Vivienda: 20
Educacion: 20
Alimentacion: 20
Vestimenta: 20
Salud: 20
Turismo: 20

******** Declaracion de Impuesto a la Renta 2023 ********
Total de ingresos anuales: 8400,00
Total de deducciones (facturas): 120,00
Ingreso neto (ingresos - deducciones): 8280,00
Impuesto a pagar: 362,40
*********************************************************
 */
