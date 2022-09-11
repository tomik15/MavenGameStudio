package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);

    }

    @Override
    public List<Comment> getComments(String game) {
        final String STATEMENT_COMMENTS = "select co from Comment co where co.game=:myGame  order by co.commented_at desc";
        return entityManager
                .createQuery(STATEMENT_COMMENTS)
                .setParameter("myGame",game)
                .getResultList();

    }

    @Override
    public void reset() {
        final String STATEMENT_RESET = "DELETE FROM comment";
        entityManager.createNativeQuery(STATEMENT_RESET).executeUpdate();

    }
}
