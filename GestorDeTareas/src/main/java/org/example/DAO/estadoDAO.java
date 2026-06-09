package src.main.java.org.example.DAO;

import src.main.java.org.example.modelo.Estado;

import java.util.List;

public class estadoDAO implements IOperationsCRUD<Estado> {
    @Override
    public List<Estado> getAll() {
        return List.of();
    }

    @Override
    public Estado findById(Long id) {
        return null;
    }

    @Override
    public int add(Estado object) {
        return 0;
    }

    @Override
    public int update(Estado object) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
