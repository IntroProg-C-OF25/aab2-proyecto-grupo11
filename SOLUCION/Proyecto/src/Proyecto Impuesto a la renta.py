def main():
    meses = 12
    num_categorias = 6
    categorias = ["Vivienda", "Educacion", "Alimentacion", "Vestimenta", "Salud", "Turismo"]

    tabla_impuestos = [
        [0, 400, 0, 0.0],
        [400, 1000, 0, 0.02],
        [1000, 5000, 12, 0.05],
        [5000, 11722, 100, 0.08],
        [11722, 14935, 0, 0.05],
        [14935, 18666, 161, 0.10],
        [18666, 22418, 534, 0.12],
        [22418, 32783, 984, 0.15],
        [32783, 43147, 2539, 0.20],
        [43147, 53512, 4612, 0.25],
        [53512, 63876, 7203, 0.30],
        [63876, 103644, 10312, 0.35]
    ]

    sueldos = registro_de_sueldos(meses)
    facturas = registro_de_facturas(num_categorias, categorias)
    total_ingreso = sumar_arreglo(sueldos)
    total_deducciones = sumar_arreglo(facturas)
    ingreso_neto = total_ingreso - total_deducciones
    impuesto = calcular_impuesto(ingreso_neto, tabla_impuestos)
    generar_declaracion(total_ingreso, total_deducciones, ingreso_neto, impuesto)


def registro_de_sueldos(meses):
    sueldos = []
    print("Ingrese sus sueldos mensuales:")
    for i in range(meses):
        while True:
            try:
                sueldo = float(input(f"Sueldo mes {i + 1}: "))
                if sueldo < 0:
                    print("Error: El sueldo no puede ser negativo.")
                else:
                    sueldos.append(sueldo)
                    break
            except ValueError:
                print("Error: Ingrese un número válido.")
    return sueldos


def registro_de_facturas(num_categorias, categorias):
    facturas = []
    print("\nIngrese el total anual de facturas por cada categoria:")
    for i in range(num_categorias):
        while True:
            try:
                factura = float(input(f"{categorias[i]}: "))
                if factura < 0:
                    print("Error: El monto no puede ser negativo.")
                else:
                    facturas.append(factura)
                    break
            except ValueError:
                print("Error: Ingrese un número válido.")
    return facturas


def sumar_arreglo(arreglo):
    return sum(arreglo)


def calcular_impuesto(ingreso_neto, tabla):
    impuesto = 0

    if ingreso_neto <= tabla[0][1]:
        return impuesto

    for fila in tabla:
        lim_inf = fila[0]
        lim_sup = fila[1]
        base = fila[2]
        tasa = fila[3]

        if ingreso_neto > lim_inf and ingreso_neto <= lim_sup:
            impuesto = base + (ingreso_neto - lim_inf) * tasa
            break

    return impuesto


def generar_declaracion(total_ingreso, total_deducciones, ingreso_neto, impuesto):
    print("\n******** Declaracion de Impuesto a la Renta ********")
    print(f"Total de ingresos anuales: {total_ingreso:.2f}")
    print(f"Total de deducciones (facturas): {total_deducciones:.2f}")
    print(f"Ingreso neto (ingresos - deducciones): {ingreso_neto:.2f}")
    print(f"Impuesto a pagar: {impuesto:.2f}")
    
    if impuesto == 0:
        print("Usted no presenta obligaciones tributarias.")
    
    print("*********************************************************")


if __name__ == "__main__":
    main()
