import java.util.ArrayList;
/**
 * A steel factory (an implementation of an enterprise)
 * represented by its name and list of employees.
 * @author AlgoDat team
 *
 */
public class SteelFactory implements Enterprise{
  
    /** Name of the factory */
    private String name;
    /** List of workers */
    private ArrayList<Worker> workers;

    /**
     * Creates a new steel factory with a given name
     * @param name Name of the factory
     */
    public SteelFactory(String name) {
    	this.name = name;
    	this.workers = new ArrayList<Worker>(); 
    	
    	if (name.isEmpty()) {
    		throw new NotImplementedException("SteelFactory");
    	}
    }
    /**
     * Add workers to enterprise
     * @param firstName
     * @param lastName
     */
    @Override
    public void addWorker(String firstName, String lastName) {
      if (firstName.isEmpty() || lastName.isEmpty()) {
    	  throw new NotImplementedException("addWorker");
      }
      Worker CurrentWorker = new Worker(firstName, lastName); 
      (this.workers).add(CurrentWorker);
    }
    /**
     * @return name of the enterprise
     */
    @Override
    public String getName() {
    	if ((this.name).isEmpty()) {
    		throw new NotImplementedException("getName");
    	}
    	return this.name;
    	}
    /**
     * @param o is another enterprise to be compared with our object
     * @return 1 if out enterprise is bigger
     * @return -1 if our enterprise is smaller
     * @return 0 if these enterprises are compareble in sizes
     * size of the enterprice is defined by count of its employes
     */
    @Override
    public int compareTo(Enterprise o) {
    	if (o == null) {
      throw new NotImplementedException("compareTo");//TODO
    	}
    	else {
    		if(o.getWorkerCount()<this.getWorkerCount()) {
    			return 1;
    		}
    		else if(o.getWorkerCount()>this.getWorkerCount()) {
    			return -1;
    		}
    		else {
    			return 0;
    		}
    	}
    }
    /**
     * @return count of employes
     */
    @Override
    public int getWorkerCount() {
    	if (this.workers != null) {
    		return this.workers.size();
    	}
    	else {
      throw new NotImplementedException("getWorkerCount");//TODO
    	}
    }
}

