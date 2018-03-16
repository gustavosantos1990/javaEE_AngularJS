package produtos;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author gustavo
 */
@Stateless
@Path("produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoService {

    @PersistenceContext(unitName = "produtosPU")
    private EntityManager em;
    
    static Integer contador;
    List<Produto> produtos;

    public ProdutoService() {
        contador = 1;
        produtos = new ArrayList<>();
    }

    @GET
    public List<Produto> getProdutos() {
        Query query = em.createQuery("SELECT p FROM Produto p");
        return query.getResultList();
    }

    @POST
    public Produto adicionar(Produto produto) {
        em.persist(produto);
        return produto;
    }

    @PUT
    @Path("{id}")
    public Produto atualizar(@PathParam("id") Integer id, Produto produto) {
        em.merge(produto);
        return produto;
    }

    @DELETE
    @Path("{id}")
    public Produto excluir(@PathParam("id") Integer id) {
        Produto produto = getProduto(id);
        em.remove(produto);
        return produto;
    }

    @GET
    @Path("{id}")
    public Produto getProduto(@PathParam("id") Integer id) {
        return em.find(Produto.class, id);
    }
    
    
}
