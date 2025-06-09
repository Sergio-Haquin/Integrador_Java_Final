package Entities.DAOs;

import Entities.Sucursal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SucursalDAO {

    private final List<Sucursal> listaSucursales = new ArrayList<>();
    private long idActual = 1;

    public Sucursal getById(Long id) {
        return listaSucursales.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Sucursal> getAll() {
        return new ArrayList<>(listaSucursales);
    }

    public void save(Sucursal sucursal) {
        sucursal.setId(idActual++);
        listaSucursales.add(sucursal);
        System.out.println("Sucursal insertada: " + sucursal);
    }

    public void update(Sucursal sucursal) {
        Optional<Sucursal> existente = listaSucursales.stream()
                .filter(s -> s.getId().equals(sucursal.getId()))
                .findFirst();

        if (existente.isPresent()) {
            Sucursal original = existente.get();
            original.setNombre(sucursal.getNombre());
            original.setHorarioApertura(sucursal.getHorarioApertura());
            original.setHorarioCierre(sucursal.getHorarioCierre());
            original.setDomicilio(sucursal.getDomicilio());
            System.out.println("Sucursal actualizada: " + original);
        } else {
            System.out.println("No se encontró la sucursal con ID: " + sucursal.getId());
        }
    }

    public void delete(Long id) {
        boolean eliminado = listaSucursales.removeIf(s -> s.getId().equals(id));
        if (eliminado) {
            System.out.println("Sucursal eliminada con ID: " + id);
        } else {
            System.out.println(" No se encontró la sucursal con ID: " + id);
        }
    }
}