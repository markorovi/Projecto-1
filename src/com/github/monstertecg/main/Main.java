package com.github.monstertecg.main;
import com.github.monstertecg.plantillasDeListas.ListaStack;

/**
 * Ejecuta el código completo
 *
 * @author Luis Delgado
 * @version 1.0
 * @since 0.2
 */

public class Main {

    public static void main(String[] args) {

        ListaStack<String> stack;
        ListaStack<Integer> stack2;
        stack = new ListaStack<String>("asd");
        stack2 = new ListaStack<Integer>(123);

        stack.AgregarElemento("nada");
        stack2.AgregarElemento(234);

        System.out.println(stack.Largo());
        System.out.println(stack2.Largo());

        System.out.println(stack.ObtenerValor());
        System.out.println(stack2.ObtenerValor());

        stack.EliminarUltimoElemento();
        stack2.EliminarUltimoElemento();

        System.out.println(stack.ObtenerValor());
        System.out.println(stack2.ObtenerValor());

        System.out.println(stack.Largo());
        System.out.println(stack2.Largo());

    }

}
