package memory.game.kids.fun.play.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import memory.game.kids.fun.play.ui.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TileList extends ArrayList<Tile>
{

    /**
     * Constructor
     */
    TileList()
    {
    }

    /**
     * Constructeur
     * @param serialized A serialized list
     */
    TileList(String serialized)
    {
        try
        {
            JSONArray array = new JSONArray(serialized);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject object = array.getJSONObject(i);
                Tile t = new Tile(object);
                add(t);
            }
        } catch (JSONException ex)
        {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Serialize the List
     * @return The list as a String
     */
    String serialize()
    {
        JSONArray array = new JSONArray();
        for (Tile t : this)
        {
            array.put(t.json());
        }
        return array.toString();
    }

    ArrayList<Tile> getSelected()
    {
        ArrayList<Tile> list = new ArrayList<Tile>();
        for (Tile t : this)
        {
            if ( t.mSelected && !t.mFound )
            {
                list.add( t );
            }
        }
        return list;
    }
}
