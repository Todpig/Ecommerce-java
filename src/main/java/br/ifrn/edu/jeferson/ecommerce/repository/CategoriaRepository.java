package br.ifrn.edu.jeferson.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>,
        JpaSpecificationExecutor<Categoria> {

    boolean existsByNome(String nome);

    Optional<Categoria> findByNome(String nome);

}
