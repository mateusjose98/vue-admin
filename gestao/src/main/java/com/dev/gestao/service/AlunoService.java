package com.dev.gestao.service;

import com.dev.gestao.domain.aluno.Aluno;
import com.dev.gestao.domain.aluno.AlunoDTO;
import com.dev.gestao.domain.aluno.Responsavel;
import com.dev.gestao.domain.enums.TipoEmailEvento;
import com.dev.gestao.domain.usuario.Usuario;
import com.dev.gestao.repos.AlunoRepository;
import com.dev.gestao.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.gestao.util.exceptions.UniqueViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.dev.gestao.domain.enums.TipoEmailEvento.NOVO_USUARIO;
import static com.dev.gestao.domain.enums.TipoEmailEvento.NOVO_USUARIO_ASSUNTO;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final StorageService storageService;
    private final UsuarioService usuarioService;
    private final NotificacaoService notificacaoService;
    private final ResponsavelService responsavelService;

    public List<AlunoDTO> findAll() {
        final List<Aluno> alunoes = alunoRepository.findAll(Sort.by("id").descending());
        return alunoes.stream()
                .map(aluno -> mapToDTO(aluno, new AlunoDTO()))
                .toList();
    }

    public AlunoDTO get(final Integer id) {
        return alunoRepository.findById(id)
                .map(aluno -> mapToDTO(aluno, new AlunoDTO()))
                .orElseThrow(NotFoundException::new);
    }
    @Transactional
    public Integer create(final AlunoDTO alunoDTO) {
        final Aluno aluno = new Aluno();
        if(cpfExists(alunoDTO.getCpf())) {
           throw new UniqueViolationException(String.format("O cpf %s já existe!", alunoDTO.getCpf()));
        }
        if(alunoDTO.isCriarAcesso()) {
            Usuario usuario = usuarioService.criaraPartirDasCredenciais(alunoDTO.getCpf(), alunoDTO.getCpf());
            aluno.setUsuario(usuario);
            notificacaoService.enviar(NOVO_USUARIO_ASSUNTO,String.format(NOVO_USUARIO, alunoDTO.getNome()), alunoDTO.getEmail(), null) ;
        }

        mapToEntity(alunoDTO, aluno);

        Aluno salvo = alunoRepository.save(aluno);
        responsavelService.criar(alunoDTO.getCpfResponsavel(), alunoDTO.getNome(), salvo);
        Integer id = salvo.getId();
        salvo.gerarMatricula(id);
        return id;
    }
    @Transactional
    public void update(final Integer id, final AlunoDTO alunoDTO) {
        final Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(alunoDTO, aluno);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void delete(final Integer id) {
        alunoRepository.deleteById(id);
    }

    private AlunoDTO mapToDTO(final Aluno aluno, final AlunoDTO alunoDTO) {
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setEmail(aluno.getEmail());
        alunoDTO.setUf(aluno.getUf());
        alunoDTO.setFoto(aluno.getFoto());
        alunoDTO.setDataNascimento(aluno.getDataNascimento());
        alunoDTO.setTelefone(aluno.getTelefone());
        alunoDTO.setMatricula(aluno.getMatricula());
        return alunoDTO;
    }

    private Aluno mapToEntity(final AlunoDTO alunoDTO, final Aluno aluno) {
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setUf(alunoDTO.getUf());
        aluno.setFoto(alunoDTO.getFoto());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());

        return aluno;
    }

    public boolean cpfExists(final String cpf) {
        return alunoRepository.existsByCpfIgnoreCase(cpf);
    }

    @Transactional
    public String upsertFoto(Integer id, MultipartFile file) {
        Aluno aluno = alunoRepository.getReferenceById(id);
        String link = storageService.uploadFile(file);
        aluno.setFoto(link);
        alunoRepository.save(aluno);
        return aluno.getFoto();
    }

    @Transactional
    public void createInBatch(List<AlunoDTO> lista) {

        List<Aluno> toInsert = lista.stream().map(dto ->  {
            final Aluno aluno = new Aluno();
            return mapToEntity(dto, aluno);
        }).collect(Collectors.toList());
        var salvos = alunoRepository.saveAll(toInsert);

        salvos.forEach(aluno -> {
            aluno.setMatricula(String.valueOf(LocalDate.now().getYear()) +  aluno.getId() );
        });

        alunoRepository.saveAll(salvos);

    }
}
