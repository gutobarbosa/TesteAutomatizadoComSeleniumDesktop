package utils;

public class Funcoes {
    public static int removeTextoItemsDevolveInt(String texto) {
        texto = texto.replace(" items", "");
        return Integer.parseInt(texto);
    }
}
