package src.main.java.org.example.DAO;

import src.main.java.org.example.modelo.Tareas;

import java.util.List;

public class tareasDAO implements IOperationsCRUD<Tareas> {
    @Override
    public List<Tareas> getAll() {
        return List.of();
    }

    @Override
    public Tareas findById(Long id) {
        return null;
    }

    @Override
    public int add(Tareas object) {
        return 0;
    }

    @Override
    public int update(Tareas object) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
