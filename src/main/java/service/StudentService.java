package service;

import dto.StudentDTO;
import entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
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

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        //not in hibernate session
        StudentEntity mappedEntity = new ModelMapper().map(studentDTO, StudentEntity.class);
        Transaction transaction = null;
        Session session = null;

        try {
            //
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            StudentEntity toUpdate= repo.search(studentDTO.getId(), session);
            if(studentDTO.getName()!=null && !studentDTO.getName().isEmpty()){
                toUpdate.setName(studentDTO.getName());
            }
            if (studentDTO.getAddress()!=null && !studentDTO.getAddress().isEmpty()){
                toUpdate.setAddress(studentDTO.getAddress());
            }
            if (studentDTO.getContact()!=null && !studentDTO.getContact().isEmpty()){
                toUpdate.setContact(studentDTO.getContact());
            }
            transaction.commit();
            new ModelMapper().map(toUpdate, studentDTO);
            return studentDTO;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return null;
    }
}
