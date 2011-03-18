/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vrw.ejb.session;

import javax.ejb.Local;
import java.util.Collection;
import vrw.ejb.entity.Item;

/**
 *
 * @author Robbie
 */
public interface ItemSessionRemote {

    public void add(Item item);
    public Item find(int id);
    public Collection<Item> findAll();
    public Collection<Item> findAllInShopWindow();
    public Collection<Item> search(String searchString);           
    
}
