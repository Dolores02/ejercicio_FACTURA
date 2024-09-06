package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceApp");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = Cliente.builder().apellido("Videla").nombre("Dolores").dni(44439349).build();
            Domicilio domicilio = Domicilio.builder().nombrecalle("Calle A").numero(100).build();

            cliente.setDomicilio(domicilio);
            domicilio.setCliente(cliente);

            Factura factura1 = Factura.builder().fecha("10/09/2024").numero(10).total(100).build();
            Categoria perecederos = Categoria.builder().denominacion("Perecederos").build();
            Categoria lacteos = Categoria.builder().denominacion("Lacteos").build();
            Categoria limpieza = Categoria.builder().denominacion("Limpieza").build();
            Articulo art1 = Articulo.builder().cantidad(400).denominacion("Yogurt Ser Frutilla").precio(350).build();
            Articulo art2 = Articulo.builder().cantidad(300).denominacion("Detergente Magistral").precio(500).build();

            factura1.setCliente(cliente);
            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);

            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura detalle1 = DetalleFactura.builder().articulo(art1).cantidad(2).subtotal(700).build();
            DetalleFactura detalle2 = DetalleFactura.builder().articulo(art2).cantidad(4).subtotal(2000).build();

            art1.getDetallesfacturas().add(detalle1);
            factura1.getDetalles().add(detalle1);
            detalle1.setFactura(factura1);

            art2.getDetallesfacturas().add(detalle2);
            factura1.getDetalles().add(detalle2);
            detalle2.setFactura(factura1);


            factura1.setTotal(2700);


            em.persist(factura1);
            em.persist(cliente);

            em.flush();

            em.getTransaction().commit();


        } catch (Exception e) {
            em.getTransaction().rollback();  //por si hay un error

        }
        em.close();
        emf.close();

    }
}