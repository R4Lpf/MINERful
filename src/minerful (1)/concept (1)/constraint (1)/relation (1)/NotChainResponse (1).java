/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minerful.concept.constraint.relation;

import javax.xml.bind.annotation.XmlRootElement;

import minerful.concept.TaskChar;
import minerful.concept.TaskCharSet;
import minerful.concept.constraint.Constraint;

@XmlRootElement
public class NotChainResponse extends NegativeRelationConstraint {
    @Override
	public String getRegularExpressionTemplate() {
//		return "[^%1$s]*([%1$s][%1$s]*[^%1$s%2$s][^%1$s]*)*([^%1$s]*|[%1$s])";
		return "[^%1$s]*([%1$s][%1$s]*[^%1$s%2$s][^%1$s]*)*([^%1$s]*|[%1$s]*)";
    }
    
    @Override
    public String getLTLpfExpressionTemplate() {
    	return "G(%1$s -> !X(%2$s))"; // G(a -> !X(b))
    }

	@Override
	public String getNegativeRegularExpressionTemplate() {
		return "[^%1$s]*([%1$s][%2$s][^%1$s]*)*[^%1$s]*";
	}

	@Override
	public String getNegativeLTLpfExpressionTemplate() {
		return "G(%1$s -> X(%2$s))"; // G(a -> X(b))
	}

	protected NotChainResponse() {
    	super();
    }

    public NotChainResponse(TaskChar param1, TaskChar param2, double support) {
        super(param1, param2, support);
    }
    public NotChainResponse(TaskChar param1, TaskChar param2) {
        super(param1, param2);
    }
    public NotChainResponse(TaskCharSet param1, TaskCharSet param2,
			double support) {
		super(param1, param2, support);
	}

	public NotChainResponse(TaskCharSet param1, TaskCharSet param2) {
		super(param1, param2);
	}

	@Override
    public int getHierarchyLevel() {
        return super.getHierarchyLevel()+1;
    }
    
    public void setOpposedTo(RelationConstraint opposedTo) {
        super.setOpponent(opposedTo, ChainResponse.class);
    }

	@Override
	public Constraint suggestConstraintWhichThisShouldBeBasedUpon() {
		return null;
	}

	@Override
	public Constraint getSupposedOpponentConstraint() {
		return new ChainResponse(base, implied);
	}
	
	@Override
	public Constraint copy(TaskChar... taskChars) {
		super.checkParams(taskChars);
		return new NotChainResponse(taskChars[0], taskChars[1]);
	}

	@Override
	public Constraint copy(TaskCharSet... taskCharSets) {
		super.checkParams(taskCharSets);
		return new NotChainResponse(taskCharSets[0], taskCharSets[1]);
	}
}