package src.main.java.org.example.DAO;

import src.main.java.org.example.modelo.Usuario;

import java.util.List;

public class usuarioDAO implements IOperationsCRUD<Usuario> {
    @Override
    public List<Usuario> getAll() {
        return List.of();
    }

    @Override
    public Usuario findById(Long id) {
        return null;
    }

    @Override
    public int add(Usuario object) {
        return 0;
    }

    @Override
    public int update(Usuario object) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
