package pgs.strategies;

import pgs.models.ParityGame;
import pgs.models.Vertex;

import java.util.*;

public class ShortestSelfCycleStrategy extends InputLiftingStrategy  {

    private final Map<Vertex, Integer> shortestSelfCycle = new HashMap<>();
    private Map<Vertex, Set<Vertex>> game_successors;


    public ShortestSelfCycleStrategy(ParityGame parityGame) {
        super(parityGame);
        game_successors = parityGame.getSuccessors();
        for(Vertex v : getVertexOrder()){
            this.shortestSelfCycle.put(v, this.findShortestSelfCycleBFS(v));
        }
        Collections.sort(super.getVertexOrder(), new VertexSSCComparator());
        super.initializeIterator();
    }

    private Integer findShortestSelfCycleBFS(Vertex v)
    {
        Map<Vertex, Integer> distance = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(v);
        distance.put(v, 0);
        while(!queue.isEmpty())
        {
            Vertex w = queue.remove();
            int d = distance.get(w);
            for ( Vertex s : this.game_successors.get(w))
            {
                if(s == v)
                    return d + 1;
                if(distance.get(s) == null)
                {
                    distance.put(s, d + 1);
                    queue.add(s);
                }
            }
        }
        return -1;
    }

    class VertexSSCComparator implements Comparator<Vertex>
    {
        //public VertexSSCComparator
        public int compare(Vertex v1, Vertex v2)
        {
            if(shortestSelfCycle.get(v1) == shortestSelfCycle.get(v2))
                return 0;
            else if(shortestSelfCycle.get(v1) == -1)
                return 1;
            else if(shortestSelfCycle.get(v2) == -1)
                return -1;
            else
                return shortestSelfCycle.get(v1).compareTo(shortestSelfCycle.get(v2));
        }
    }
}
