package sk.tuke.gamestudio.entity;

import sk.tuke.gamestudio.service.RatingService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class Rating2 implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) {
        Rating rating2write=null;
        final String STATEMENT_GET_RATING = "select rt from Rating rt where rt.game=:myGame and rt.username=:myUsername";

        try{
            rating2write=(Rating) entityManager.createQuery(STATEMENT_GET_RATING)
                    .setParameter("myGame",rating.getGame())
                    .setParameter("myUsername",rating.getUsername())
                    .getSingleResult();
            rating2write.setRating(rating.getRating());
            rating2write.setRated_at(rating.getRated_at());
        }catch (NoResultException e){
          //  rating2write=new Rating(rating.getGame(),rating.getUsername(),rating.getRating(),rating.getRated_at());
            entityManager.persist(rating);
        }

    }

    @Override
    public int getAverageRating(String game) {
        final String STATEMENT_GET_AVG_RATING = "select AVG(rt.rating) from Rating rt where rt.game=:myGame";

        try {


            var rating = entityManager.createQuery(STATEMENT_GET_AVG_RATING).setParameter("myGame", game).getSingleResult();
        return ((Number)rating).intValue();
        }catch (NoResultException e){
            return 0;
        }



    }

    @Override
    public int getRating(String game, String username) {
        Rating rating=null;
        final String STATEMENT_GET_RATING = "select rt from Rating rt where rt.game=:myGame and rt.username=:myUsername";

        try{
            rating=(Rating) entityManager.createQuery(STATEMENT_GET_RATING)
                    .setParameter("myGame",game)
                    .setParameter("myUsername",username)
                    .getSingleResult();
          return rating.getRating();
        }catch (NoResultException e){
            return 0;
        }
    }

    @Override
    public void reset() {

    }
}
