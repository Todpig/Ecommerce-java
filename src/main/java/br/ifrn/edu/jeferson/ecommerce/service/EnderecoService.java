package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public List<EnderecoResponseDTO> findAll() {
        return enderecoMapper.toDTOList(enderecoRepository.findAll());
    }

    public EnderecoResponseDTO findById(Long id) {
        return enderecoMapper.toResponseDTO(enderecoRepository.findById(id).get());
    }

    public EnderecoResponseDTO save(EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoMapper.toEntity(enderecoRequestDTO);
        return enderecoMapper.toResponseDTO(enderecoRepository.save(endereco));
    }

    public EnderecoResponseDTO update(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoRepository.findById(id).get();
        return enderecoMapper.toResponseDTO(enderecoRepository.save(endereco));
    }

    public void delete(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new RuntimeException("Endereco não encontrado");
        }
        enderecoRepository.deleteById(id);
    }
}
