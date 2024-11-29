@RestController
@RequestMapping("/usuario")
public class UsuarioControle {
    @Autowired
    private RepositorioUsuario repositorio;

    @GetMapping("/todos")
    public List<Usuario> obterTodos() {
        return repositorio.findAll();
    }

    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return repositorio.save(usuario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario atualizacao) {
        Optional<Usuario> usuario = repositorio.findById(id);
        if (usuario.isPresent()) {
            Usuario entidade = usuario.get();
            entidade.setNome(atualizacao.getNome());
            repositorio.save(entidade);
            return ResponseEntity.ok(entidade);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
