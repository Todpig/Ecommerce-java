package br.ifrn.edu.jeferson.ecommerce.specification;

import org.springframework.data.jpa.domain.Specification;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;

public class CategoriaSpecification {

    public static Specification<Categoria> comIdIgual(Long id) {
        return (root, query, builder) -> id == null ? null : builder.equal(root.get("id"), id);
    }

    public static Specification<Categoria> comNomeContendo(String nome) {
        return (root, query, builder)
                -> nome == null ? null : builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Categoria> comDescricaoContendo(String descricao) {
        return (root, query, builder)
                -> descricao == null ? null : builder.like(builder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
    }

}
