package sk.tuke.gamestudio;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Rating2;
import sk.tuke.gamestudio.entity.Student;
import sk.tuke.gamestudio.entity.StudyGroup;
import sk.tuke.gamestudio.service.RatingService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

//@Transactional
//public class PlaygroundJPA {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void play(){
//        System.out.println("Opening PlaygroundJPA");
//
////        entityManager.persist(new StudyGroup("basic"));
////        entityManager.persist(new StudyGroup("advanced"));
////        entityManager.persist(new StudyGroup("expert"));
//
//        List<StudyGroup> studyGroups=entityManager.createQuery("select g from StudyGroup g").getResultList();
//
//        int noOfGroups=studyGroups.size();
//        for(int gropNumber=0;gropNumber<noOfGroups;gropNumber++){
//            System.out.println(gropNumber+" "+studyGroups.get(gropNumber));
//        }
//       Student student=new Student("Wen","Heui",studyGroups.get(2));
//
//     //   Student student2=new Student("Jon","Nowec",studyGroups.get(0));
//
//      System.out.println(student);
//      entityManager.persist(student);
//  //     entityManager.persist(student);
//
//        System.out.println("Closing PlaygroundJPA");
//    }
//}

//
//public class PlaygroundJPA {
//    @Autowired
//    private Rating2 ratingService;
//    public void play(){
//        System.out.println("Opening PlaygroundJPA");
//
//        Rating rating=new Rating("mines","Stevo",4,new Date());
//        ratingService.setRating(rating);
//
//        rating.setUsername("palo");
//        rating.setRating(1);
//        ratingService.setRating(rating);
//
//        System.out.println(ratingService.getRating("mines","Stevo"));
//
//        System.out.println("Closing PlaygroundJPA");
//    }
//}
@Transactional
public class PlaygroundJPA {


    public void play(){
        System.out.println("Opening PlaygroundJPA");

//

        System.out.println("Closing PlaygroundJPA");
    }
}
