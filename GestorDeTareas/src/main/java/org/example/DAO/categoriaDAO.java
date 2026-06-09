package src.main.java.org.example.DAO;

import src.main.java.org.example.modelo.Categoria;

import java.util.List;

public class categoriaDAO implements IOperationsCRUD<Categoria>{
    @Override
    public List<Categoria> getAll() {
        return List.of();
    }

    @Override
    public Categoria findById(Long id) {
        return null;
    }

    @Override
    public int add(Categoria object) {
        return 0;
    }

    @Override
    public int update(Categoria object) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
