package cl.inacap.covid.utils;

public class RutUtils {
    static String compruebaNumeros[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}; //Este Array se utiliza para comprobar los numeros del rut.

    public boolean verificaRutChileno(String rut) {
        int contador = 0;
        boolean validado = false;

        contador = 0;

        if (rut.length() == 9) { //Este código solo se ejecuta si el largo del rut es igual a 9.

            for (int i = 0; i < rut.length(); i++) { //Aca se recorren todos los caracteres del rut.

                if (i == 0) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(0)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 1) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(1)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 2) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(2)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 3) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(3)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 4) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(4)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 5) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(5)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 6) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(6)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 7 && Character.toString(rut.charAt(7)).equalsIgnoreCase("-")) {
                    contador++;
                }
                if (i == 8) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(8)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 8 && Character.toString(rut.charAt(8)).equalsIgnoreCase("k")) {
                    contador++;
                }
            }
        }

        if (rut.length() == 10) { //Este código solo se ejecuta si el largo del rut es igual a 10.

            for (int i = 0; i < rut.length(); i++) { //Aca se recorren todos los caracteres del rut.

                if (i == 0) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(0)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 1) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(1)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 2) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(2)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 3) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(3)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 4) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(4)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 5) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(5)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 6) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(6)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 7) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if(Character.toString(rut.charAt(7)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 8 && Character.toString(rut.charAt(8)).equalsIgnoreCase("-")) {
                    contador++;
                }
                if (i == 9) {
                    for (int j = 0; j < compruebaNumeros.length; j++) {
                        if (Character.toString(rut.charAt(9)).equalsIgnoreCase(compruebaNumeros[j])) {
                            contador++;
                        }
                    }
                }
                if (i == 9 && Character.toString(rut.charAt(9)).equalsIgnoreCase("k")) {
                    contador++;
                }
            }
        }

        if (contador == 9 && rut.length() == 9) {
            validado = true;
        }
        else if (contador == 10 && rut.length() == 10) {
            validado = true;
        }
        return validado;
    }
}
