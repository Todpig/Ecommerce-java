package br.ifrn.edu.jeferson.ecommerce.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.CategoriaMapper;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.specification.CategoriaSpecification;

@Service
public class CategoriaService {

    private static final Logger logger = LogManager.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaMapper mapper;
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private ProdutoMapper produtoMapper;

    private void verificaSeTemProduto(Long id) {
        logger.debug("Verificando se a categoria com ID {} tem produtos associados.", id);
        if (produtoRepository.existsByCategorias_Id(id)) {
            logger.warn("A categoria com ID {} possui produtos associados e não pode ser excluída.", id);
            throw new BusinessException("Não é possível deletar uma categoria que possui produtos associados");
        }
    }

    public CategoriaResponseDTO salvar(CategoriaRequestDTO categoriaDto) {
        logger.info("Iniciando o processo de salvar categoria: {}", categoriaDto);

        var categoria = mapper.toEntity(categoriaDto);

        if (categoriaRepository.existsByNome(categoria.getNome())) {
            logger.warn("Tentativa de salvar categoria com nome já existente: {}", categoria.getNome());
            throw new BusinessException("Já existe uma categoria com esse nome");
        }

        categoriaRepository.save(categoria);
        logger.info("Categoria salva com sucesso: {}", categoria);

        return mapper.toResponseDTO(categoria);
    }

    public Page<CategoriaResponseDTO> lista(Pageable pageable, String nome, String descricao) {
        logger.info("Listando categorias com os filtros - Nome: {}, Descrição: {}", nome, descricao);

        Specification<Categoria> spec = Specification.where(CategoriaSpecification.comNomeContendo(nome))
                .and(CategoriaSpecification.comDescricaoContendo(descricao));
        Page<Categoria> categorias = categoriaRepository.findAll(spec, pageable);

        logger.info("Categorias listadas com sucesso. Total de resultados: {}", categorias.getTotalElements());
        return categoriaMapper.toDTOList(categorias);
    }

    public void deletar(Long id) {
        logger.info("Iniciando a exclusão da categoria com ID: {}", id);

        if (!categoriaRepository.existsById(id)) {
            logger.error("Categoria com ID {} não encontrada para exclusão.", id);
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        verificaSeTemProduto(id);
        categoriaRepository.deleteById(id);

        logger.info("Categoria com ID {} excluída com sucesso.", id);
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaDto) {
        logger.info("Atualizando categoria com ID: {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        if (!categoria.getNome().equals(categoriaDto.getNome()) && categoriaRepository.existsByNome(categoriaDto.getNome())) {
            logger.warn("Tentativa de atualizar categoria com nome já existente: {}", categoriaDto.getNome());
            throw new BusinessException("Já existe uma categoria com esse nome");
        }

        categoriaMapper.updateEntityFromDTO(categoriaDto, categoria);
        var categoriaAlterada = categoriaRepository.save(categoria);

        logger.info("Categoria com ID {} atualizada com sucesso: {}", id, categoriaAlterada);
        return categoriaMapper.toResponseDTO(categoriaAlterada);
    }

    public CategoriaResponseDTO buscarPorId(Long id) {
        logger.info("Buscando categoria por ID: {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        logger.info("Categoria encontrada: {}", categoria);
        return categoriaMapper.toResponseDTO(categoria);
    }

    public ProdutoResponseDTO adicionarProdutoACategoria(Long idCategoria, Long idProduto) {
        logger.info("Adicionando produto com ID {} à categoria com ID {}", idProduto, idCategoria);

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        categoria.getProdutos().add(produto);
        produto.getCategorias().add(categoria);

        produtoRepository.save(produto);
        categoriaRepository.save(categoria);

        logger.info("Produto com ID {} adicionado à categoria com ID {}", idProduto, idCategoria);
        return produtoMapper.toResponseDTO(produto);
    }

    public ProdutoResponseDTO removerProdutoDaCategoria(Long idCategoria, Long idProduto) {
        logger.info("Removendo produto com ID {} da categoria com ID {}", idProduto, idCategoria);

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        categoria.getProdutos().remove(produto);
        produto.getCategorias().remove(categoria);

        produtoRepository.save(produto);
        categoriaRepository.save(categoria);

        logger.info("Produto com ID {} removido da categoria com ID {}", idProduto, idCategoria);
        return produtoMapper.toResponseDTO(produto);
    }
}
