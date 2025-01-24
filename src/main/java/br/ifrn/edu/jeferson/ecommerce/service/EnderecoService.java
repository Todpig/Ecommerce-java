package br.ifrn.edu.jeferson.ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
    private static final Logger logger = LoggerFactory.getLogger(EnderecoService.class);

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoMapper mapper;

    public EnderecoResponseDTO salvar(Long clientId, EnderecoRequestDTO enderecoDto) {
        logger.info("Salvando novo endereço para o cliente com ID {}", clientId);
        // Verifica se o cliente existe
        Cliente cliente = clienteRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        if (enderecoRepository.existsByClienteId(clientId)) {
            throw new ResourceNotFoundException("Cliente já possui um endereço cadastrado");
        }

        // Mapeia o endereço e associa ao cliente
        Endereco endereco = mapper.toEntity(enderecoDto);
        endereco.setCliente(cliente);

        enderecoRepository.save(endereco);
        return mapper.toResponseDTO(endereco);
    }

    public EnderecoResponseDTO listar(Long clientId) {
        logger.info("Listando endereço do cliente com ID {}", clientId);
        // Verifica se o cliente existe
        if (!clienteRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        Endereco endereco = enderecoRepository.findByClienteId(clientId);
        System.out.println("Endereco" + endereco);
        return mapper.toResponseDTO(endereco);
    }

    public void deletar(Long clientId, Long id) {
        logger.info("Deletando endereço com ID {} do cliente com ID {}", id, clientId);
        // Verifica se o cliente existe
        if (!clienteRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        // Verifica se o endereço existe e pertence ao cliente
        Endereco endereco = enderecoRepository.findByIdAndClienteId(id, clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado ou não pertence ao cliente"));

        enderecoRepository.delete(endereco);
    }

    public EnderecoResponseDTO atualizar(Long clientId, Long id, EnderecoRequestDTO enderecoDto) {
        logger.info("Atualizando endereço com ID {} do cliente com ID {}", id, clientId);
        // Verifica se o cliente existe
        if (!clienteRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        // Verifica se o endereço existe e pertence ao cliente
        Endereco endereco = enderecoRepository.findByIdAndClienteId(id, clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado ou não pertence ao cliente"));

        // Atualiza os dados do endereço
        mapper.updateEntityFromDTO(enderecoDto, endereco);
        Endereco enderecoAtualizado = enderecoRepository.save(endereco);

        return mapper.toResponseDTO(enderecoAtualizado);
    }
}
