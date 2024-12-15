package repo;

import dto.StudentDTO;
import entity.StudentEntity;
import org.hibernate.Session;

import java.io.Serializable;

public class StudentRepo {

    public StudentEntity save(StudentEntity entity, Session session){
        Serializable save = session.save(entity);
        entity.setId((Integer) save);
        return entity;
    }
}
