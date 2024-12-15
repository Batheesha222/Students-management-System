package service;

import dto.StudentDTO;
import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.StudentRepo;
import util.FactoryConfiguration;

public class StudentService {

    private StudentRepo repo;
    public StudentService(){
        //dependency injection
        this.repo = new StudentRepo();
    }

    public StudentDTO saveStudent(StudentDTO student){
        //1.convert dto to entity(we can't sent dto to repo)

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setName(student.getName());
        studentEntity.setAddress(student.getAddress());
        studentEntity.setContact(student.getContact());
        Transaction transaction = null;
        Session session = null;
        try {
            //2.create a session
           session = FactoryConfiguration.getInstance().getSession();
            //when we are trying to change database we need to start a transaction
            //3.handle the transaction
             transaction = session.beginTransaction();
            StudentEntity save = repo.save(studentEntity, session);
            System.out.println("saved id " + save.getId());
            student.setId(save.getId());
            transaction.commit();
            return student;
        }catch (Exception e){
            //4.exception handling
            if (transaction!=null)transaction.rollback();
            e.printStackTrace();
        }finally {

            if(session!=null)session.close();
        }
        return null;
    }
}
