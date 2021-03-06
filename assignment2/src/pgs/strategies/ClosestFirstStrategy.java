package pgs.strategies;

import pgs.models.ParityGame;
import pgs.models.Vertex;

import java.util.*;

public class ClosestFirstStrategy extends InputLiftingStrategy {
    private final Map<Vertex, Integer> priority = new HashMap<>();
    private Map<Vertex, Set<Vertex>> precessors;


    public ClosestFirstStrategy(ParityGame parityGame) {
        super(parityGame);
        for(Vertex v : getVertexOrder()){
            priority.put(v, 0);
        }
        precessors = parityGame.getPrecessors();
    }

    public void didLift(Vertex v){
        for(Vertex w : precessors.get(v))
        {
            priority.put(w, priority.get(w)+ 2);
        }
        priority.put(v, 0);

        Collections.sort(super.getVertexOrder(), new VertexPrioComparator());
        super.initializeIterator();
    }

    public void didNotLift(Vertex v) {
        priority.put(v, -1);

        Collections.sort(super.getVertexOrder(), new VertexPrioComparator());
        super.initializeIterator();
    }


    @Override
    public boolean hasNext() {
        return priority.get(super.getVertexOrder().get(0)) != -1;
    }

    class VertexPrioComparator implements Comparator<Vertex>
    {
        //public VertexSSCComparator
        public int compare(Vertex v1, Vertex v2)
        {
            return priority.get(v2).compareTo(priority.get(v1));
        }
    }
}
