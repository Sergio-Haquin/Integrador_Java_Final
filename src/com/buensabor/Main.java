package com.buensabor;

import Entities.DAOs.SucursalDAO;
import Entities.*;
import Entities.Enums.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- INICIANDO INSTANCIACION Y RELACION DE ENTIDADES PARA 'BUEN SABOR' ---");

        Pais paisArgentina = Pais.builder()
                .id(1L)
                .nombre("Argentina")
                .build();

        Provincia provinciaMendoza = Provincia.builder()
                .id(1L)
                .nombre("Mendoza")
                .pais(paisArgentina)
                .build();

        Localidad localidadCiudadMendoza = Localidad.builder()
                .id(1L)
                .nombre("Ciudad de Mendoza")
                .provincia(provinciaMendoza)
                .build();

        Domicilio domicilioSucursalCentral = Domicilio.builder()
                .id(1L)
                .calle("Av. Sarmiento")
                .numero(789)
                .cp(5500)
                .localidad(localidadCiudadMendoza)
                .build();

        Sucursal sucursalCentro = new Sucursal();
        sucursalCentro.setId(1L);
        sucursalCentro.setNombre("Sucursal Centro");
        sucursalCentro.setHorarioApertura(LocalTime.of(9, 0));
        sucursalCentro.setHorarioCierre(LocalTime.of(18, 0));
        sucursalCentro.setDomicilio(domicilioSucursalCentral);


        SucursalDAO sucursalDAO = new SucursalDAO();
        sucursalDAO.save(sucursalCentro);

        Domicilio domicilioCliente1 = Domicilio.builder()
                .id(2L)
                .calle("Calle Falsa")
                .numero(123)
                .cp(5501)
                .localidad(localidadCiudadMendoza)
                .build();

        Domicilio domicilioCliente2 = Domicilio.builder()
                .id(3L)
                .calle("Av. Siempre Viva")
                .numero(742)
                .cp(5502)
                .localidad(localidadCiudadMendoza)
                .build();

        UnidadMedida unidadMedidaGramos = UnidadMedida.builder()
                .id(1L)
                .denominacion("gramos")
                .build();

        UnidadMedida unidadMedidaUnidades = UnidadMedida.builder()
                .id(2L)
                .denominacion("unidades")
                .build();

        UnidadMedida unidadMedidaLitros = UnidadMedida.builder()
                .id(3L)
                .denominacion("litros")
                .build();

        Imagen imagenHamburguesa = Imagen.builder()
                .id(1L)
                .denominacion("https://ejemplo.com/hamburguesa.jpg")
                .build();
        Imagen imagenCocaCola = Imagen.builder()
                .id(2L)
                .denominacion("https://ejemplo.com/coca_cola.jpg")
                .build();
        Imagen imagenPapasFritas = Imagen.builder()
                .id(3L)
                .denominacion("https://ejemplo.com/papas_fritas.jpg")
                .build();
        Imagen imagenTomate = Imagen.builder()
                .id(4L)
                .denominacion("https://ejemplo.com/tomate.jpg")
                .build();
        Imagen imagenPan = Imagen.builder()
                .id(5L)
                .denominacion("https://ejemplo.com/pan.jpg")
                .build();


        ArticuloInsumo insumoTomate = ArticuloInsumo.builder()
                .id(1L)
                .denominacion("Tomate")
                .precioCompra(50.0)
                .stockActual(100)
                .stockMinimo(10)
                .unidadMedida(unidadMedidaGramos)
                .esParaElaborar(true)
                .imagen(imagenTomate)
                .build();

        ArticuloInsumo insumoPanBrioche = ArticuloInsumo.builder()
                .id(2L)
                .denominacion("Pan Brioche")
                .precioCompra(70.0)
                .stockActual(50)
                .stockMinimo(5)
                .unidadMedida(unidadMedidaUnidades)
                .esParaElaborar(true)
                .imagen(imagenPan)
                .build();

        ArticuloInsumo insumoPapas = ArticuloInsumo.builder()
                .id(3L)
                .denominacion("Papas Fritas Congeladas")
                .precioCompra(150.0)
                .stockActual(2000)
                .stockMinimo(200)
                .unidadMedida(unidadMedidaGramos)
                .esParaElaborar(true)
                .imagen(imagenPapasFritas)
                .build();

        ArticuloManufacturado hamburguesaSimple = ArticuloManufacturado.builder()
                .id(1L)
                .denominacion("Hamburguesa Simple")
                .descripcion("Clasica hamburguesa con lechuga, tomate y queso")
                .precioVenta(1200.0)
                .tiempoEstimadoMinutos(15)
                .imagen(imagenHamburguesa)
                .build();

        ArticuloManufacturado cocaCola = ArticuloManufacturado.builder()
                .id(2L)
                .denominacion("Coca Cola 500ml")
                .descripcion("Bebida gaseosa refrescante")
                .precioVenta(400.0)
                .tiempoEstimadoMinutos(1)
                .imagen(imagenCocaCola)
                .build();

        ArticuloManufacturado papasFritas = ArticuloManufacturado.builder()
                .id(3L)
                .denominacion("Papas Fritas")
                .descripcion("Porcion de papas fritas")
                .precioVenta(500.0)
                .tiempoEstimadoMinutos(8)
                .imagen(imagenPapasFritas)
                .build();

        ArticuloManufacturadoDetalle detHamburguesaTomate = ArticuloManufacturadoDetalle.builder()
                .id(1L)
                .cantidad(50)
                .unidadMedida(unidadMedidaGramos)  // Asigna gramos para el tomate
                .articuloInsumo(insumoTomate)
                .articuloManufacturado(hamburguesaSimple)
                .build();

        ArticuloManufacturadoDetalle detHamburguesaPan = ArticuloManufacturadoDetalle.builder()
                .id(2L)
                .cantidad(1)
                .unidadMedida(unidadMedidaUnidades)  // Asigna unidades para el pan
                .articuloInsumo(insumoPanBrioche)
                .articuloManufacturado(hamburguesaSimple)
                .build();

        hamburguesaSimple.addDetalle(detHamburguesaTomate);
        hamburguesaSimple.addDetalle(detHamburguesaPan);

        Empresa empresaPrincipal = Empresa.builder()
                .id(1L)
                .nombre("El Buen Sabor")
                .razonSocial("El Buen Sabor S.A.")
                .cuil(30-71234567-8)
                .build();


        Usuario usuarioCliente1 = Usuario.builder()
                .id(1L)
                .auth0Id("auth0|abc123client1")
                .username("cliente1@email.com")
                .rol(Rol.CLIENTE)
                .build();

        Cliente cliente1 = Cliente.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Perez")
                .telefono("2615551234")
                .email("juan.perez@example.com")
                .usuario(usuarioCliente1)
                .domicilio(domicilioCliente1)
                .build();


        usuarioCliente1.setCliente(cliente1);

        Promocion happyHourPizzas = Promocion.builder()
                .id(1L)
                .denominacion("Happy Hour de Pizzas")
                .fechaDesde(LocalDate.of(2025, 5, 1))
                .fechaHasta(LocalDate.of(2025, 5, 31))
                .horaDesde(LocalTime.of(18, 0))
                .horaHasta(LocalTime.of(20, 0))
                .descripcionDescuento("50% de descuento en pizzas grandes")
                .tipoPromocion(TipoPromocion.HAPPY_HOUR)
                .build();

        happyHourPizzas.addArticuloManufacturado(hamburguesaSimple);
        happyHourPizzas.addArticuloInsumo(insumoTomate);
        happyHourPizzas.addSucursal(sucursalCentro);

        DetallePedido detalle1 = DetallePedido.builder()
                .id(1L)
                .cantidad(2)
                .subTotal(hamburguesaSimple.getPrecioVenta())
                .articulo(hamburguesaSimple)
                .build();


        DetallePedido detalle2 = DetallePedido.builder()
                .id(2L)
                .cantidad(3)
                .subTotal(cocaCola.getPrecioVenta())
                .articulo(cocaCola)
                .build();


        Pedido pedido1 = Pedido.builder()
                .id(1L)
                .horaEstimadaFinalizacion(LocalTime.now().plusMinutes(30))
                .estado(Estado.PENDIENTE)
                .tipoEnvio(TipoEnvio.DELIVERY)
                .formaPago(FormaPago.MERCADO_PAGO)
                .fechaPedido(LocalDate.now())
                .cliente(cliente1)
                .sucursal(sucursalCentro)
                .build();

        pedido1.addDetallePedido(detalle1);
        pedido1.addDetallePedido(detalle2);
        pedido1.setTotal(pedido1.calculateTotal());

        Factura factura1 = Factura.builder()
                .id(1L)
                .fechaFacturacion(LocalDate.now())
                .mpPaymentId(987654321)
                .mpMerchantOrderId(12345)
                .mpPreferenceId("MP-PREF-XYZ")
                .mpPaymentType("tarjeta_credito")
                .formaPago(FormaPago.MERCADO_PAGO)
                .totalVenta(pedido1.getTotal())
                .build();

        pedido1.setFactura(factura1);
        factura1.setPedido(pedido1);

        System.out.println("\n--- DATOS DE PRUEBA ---");

        System.out.println("\n--- Pais y Ubicacion ---");
        System.out.println("Pais: " + paisArgentina.getNombre());
        System.out.println("Provincia: " + provinciaMendoza.getNombre());
        System.out.println("Localidad: " + localidadCiudadMendoza.getNombre());
        System.out.println("Domicilio de Sucursal: " + domicilioSucursalCentral.getCalle() + " " + domicilioSucursalCentral.getNumero() + ", " + domicilioSucursalCentral.getLocalidad().getNombre());

        System.out.println("\n--- Articulos y Detalles ---");
        System.out.println("Insumo Tomate: " + insumoTomate.getDenominacion() + " (Stock: " + insumoTomate.getStockActual() + " " + insumoTomate.getUnidadMedida().getDenominacion() + ")");
        System.out.println("Insumo Pan Brioche: " + insumoPanBrioche.getDenominacion() + " (Stock: " + insumoPanBrioche.getStockActual() + " " + insumoPanBrioche.getUnidadMedida().getDenominacion() + ")");
        System.out.println("Insumo Papas: " + insumoPapas.getDenominacion() + " (Stock: " + insumoPapas.getStockActual() + " " + insumoPapas.getUnidadMedida().getDenominacion() + ")");
        System.out.println("Articulo Manufacturado: " + hamburguesaSimple.getDenominacion() + " (Precio Venta: $" + hamburguesaSimple.getPrecioVenta() + ")");
        System.out.println("Detalles de Hamburguesa:");
        for (ArticuloManufacturadoDetalle detalle : hamburguesaSimple.getDetalles()) {
            System.out.println("  - " + detalle.getCantidad() + " " + detalle.getUnidadMedida().getDenominacion() + " de " + detalle.getArticuloInsumo().getDenominacion());
        }
        System.out.println("Bebida: " + cocaCola.getDenominacion() + " (Precio Venta: $" + cocaCola.getPrecioVenta() + ")");

        System.out.println("\n--- Empresa y Sucursal ---");
        System.out.println("Empresa: " + empresaPrincipal.getNombre() + " (Razon Social: " + empresaPrincipal.getRazonSocial() + ")");
        System.out.println("Sucursal: " + sucursalCentro.getNombre() + " (Horario: " + sucursalCentro.getHorarioApertura() + " - " + sucursalCentro.getHorarioCierre() + ")");
        System.out.println("Sucursales de la Empresa (" + empresaPrincipal.getNombre() + "):");
        for (Sucursal sucursal : empresaPrincipal.getSucursales()) {
            System.out.println("  - " + sucursal.getNombre());
        }

        System.out.println("\n--- Cliente y Usuario ---");
        System.out.println("Cliente: " + cliente1.getNombre() + " " + cliente1.getApellido() + " (Email: " + cliente1.getEmail() + ", Tel�fono: " + cliente1.getTelefono() + ")");
        System.out.println("Usuario del Cliente: " + cliente1.getUsuario().getUsername() + " (Rol: " + cliente1.getUsuario().getRol() + ")");
        System.out.println("Domicilio del Cliente: " + cliente1.getDomicilio().getCalle() + " " + cliente1.getDomicilio().getNumero() + ", " + cliente1.getDomicilio().getLocalidad().getNombre());


        System.out.println("\n--- Promociones ---");
        System.out.println("Promocion: " + happyHourPizzas.getDenominacion() + " (" + happyHourPizzas.getDescripcionDescuento() + ")");
        System.out.println("Fecha de Promocion: " + happyHourPizzas.getFechaDesde() + " al " + happyHourPizzas.getFechaHasta());
        System.out.println("Horario de Promocion: " + happyHourPizzas.getHoraDesde() + " a " + happyHourPizzas.getHoraHasta());
        System.out.println("Art�culos Manufacturados en promocion:");
        for (ArticuloManufacturado articulo : happyHourPizzas.getArticulosManufacturados()) {
            System.out.println("  - " + articulo.getDenominacion());
        }
        System.out.println("Art�culos Insumos en promocion:");
        for (ArticuloInsumo insumo : happyHourPizzas.getArticulosInsumos()) {
            System.out.println("  - " + insumo.getDenominacion());
        }
        System.out.println("Sucursales con la promocion:");
        for (Sucursal sucursal : happyHourPizzas.getSucursales()) {
            System.out.println("  - " + sucursal.getNombre());
        }

        System.out.println("\n--- Pedido, Detalles y Factura ---");
        System.out.println("Pedido ID: " + pedido1.getId());
        System.out.println("Estado del Pedido: " + pedido1.getEstado());
        System.out.println("Tipo de Envio: " + pedido1.getTipoEnvio());
        System.out.println("Forma de Pago: " + pedido1.getFormaPago());
        System.out.println("Cliente del Pedido: " + pedido1.getCliente().getNombre() + " " + pedido1.getCliente().getApellido());
        System.out.println("Sucursal del Pedido: " + pedido1.getSucursal().getNombre());
        System.out.println("Detalles del Pedido (ID: " + pedido1.getId() + "):");
        for (DetallePedido dp : pedido1.getDetallePedidos()) {
            System.out.println("  - " + dp.getCantidad() + "x " + dp.getArticulo().getDenominacion() + " (Precio Unitario: $" + dp.getSubTotal() + ", SubTotal: $" + dp.getSubTotal() + ")");
        }
        System.out.println("Total calculado del Pedido: $" + pedido1.getTotal());
        System.out.println("Factura asociada al Pedido (ID: " + factura1.getId() + "):");
        System.out.println("  - Fecha de Facturacion: " + factura1.getFechaFacturacion());
        System.out.println("  - Total de Venta: $" + factura1.getTotalVenta());
        System.out.println("  - Forma de Pago Factura: " + factura1.getFormaPago());
        System.out.println("  - ID de Pago MP: " + factura1.getMpPaymentId());
        System.out.println("  - Tipo de Pago MP: " + factura1.getMpPaymentType());


        System.out.println("\n--- FIN DE LA INSTANCIACION Y RELACION ---");
    }
}