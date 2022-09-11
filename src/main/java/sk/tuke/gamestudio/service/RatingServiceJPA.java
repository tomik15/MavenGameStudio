package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RatingServiceJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) {
        final String STATEMENT_GET_RATING = "select rt from Rating rt where rt.game=:myGame and rt.username=:myUsername";
        final String STATEMENT_UPDATE_RATING = "UPDATE Rating set rating=:rating,rated_at=:rated_at where game=:game and username=:username";

        var rating2=entityManager.createQuery(STATEMENT_GET_RATING)
                .setParameter("myGame",rating.getGame())
                .setParameter("myUsername",rating.getUsername())
                .getResultList();
        if(rating2.isEmpty()){
            entityManager.persist(rating);
            return;
        }
        else{
            entityManager.createQuery(STATEMENT_UPDATE_RATING)
                    .setParameter("rating",rating.getRating())
                    .setParameter("rated_at",rating.getRated_at())
                    .setParameter("game",rating.getGame())
                    .setParameter("username",rating.getUsername())
                    .executeUpdate();
            return;

        }





    }

    @Override
    public int getAverageRating(String game) {
        final String STATEMENT_GET_AVG_RATING = "select AVG(rt.rating) from Rating rt where rt.game=:myGame";

        var rating=entityManager.createQuery(STATEMENT_GET_AVG_RATING).setParameter("myGame",game).getResultList();
        if(rating.isEmpty()){

            return 0;
        }
        else {
            return ((Number)rating.get(0)).intValue();
        }
    }

    @Override
    public int getRating(String game, String username) {
        final String STATEMENT_GET_RATING = "select rating from Rating rt where rt.game=:myGame and rt.username=:myUsername";

        var rating=entityManager.createQuery(STATEMENT_GET_RATING).setParameter("myGame",game).setParameter("myUsername",username).getResultList();

        System.out.println("--------------------------------------- rating list");
        for (Object o : rating) {
            System.out.println(o);
        }
        if(rating.isEmpty()){

            return 0;
        }
        else {
            return ((Number)rating.get(0)).intValue();
        }

    }

    @Override
    public void reset() {
        final String STATEMENT_RESET = "DELETE FROM rating";
        entityManager.createNativeQuery(STATEMENT_RESET).executeUpdate();

    }
}
