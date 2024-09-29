package com.example.apirest.Repository;

import com.example.apirest.Entity.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {
    List<Persona> findByNombreContainigOrApellidoContaining(String nombre, String apellido);

    Page<Persona> findByNombreContainigOrApellidoContaining(String nombre, String apellido, Pageable pageable);

    //boolean existsByDni (int dni);
    @Query(value="SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    List<Persona> search(@Param("filtro")String filtro);

    @Query(value="SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    Page<Persona> search(@Param("filtro")String filtro, Pageable pageable);

    @Query(
            value= "SELECT * FROM Persona WHERE Persona.nombre LIKE %:filtro% OR Persona.apellido LIKE %:filtro%",
            nativeQuery = true
    )
    List<Persona> searchNativo(@Param("filtro")String filtro);

    @Query(
            value= "SELECT * FROM Persona WHERE Persona.nombre LIKE %:filtro% OR Persona.apellido LIKE %:filtro%",
            countQuery ="SELECT count(*) FROM Persona",
            nativeQuery = true
    )
    Page<Persona> searchNativo(@Param("filtro")String filtro, Pageable pageable);
}
