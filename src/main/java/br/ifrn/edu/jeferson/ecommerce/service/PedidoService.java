package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    public List<PedidoResponseDTO> findAll() {
        return pedidoMapper.toDTOList(pedidoRepository.findAll());
    }

    public PedidoResponseDTO findById(Long id) {
        return pedidoMapper.toResponseDTO(pedidoRepository.findById(id).get());
    }

    public PedidoResponseDTO save(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoRequestDTO);
        return pedidoMapper.toResponseDTO(pedidoRepository.save(pedido));
    }

    public PedidoResponseDTO update(Long id, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoRepository.findById(id).get();
        return pedidoMapper.toResponseDTO(pedidoRepository.save(pedido));
    }

    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
