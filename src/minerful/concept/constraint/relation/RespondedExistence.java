/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minerful.concept.constraint.relation;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import minerful.concept.TaskChar;
import minerful.concept.TaskCharSet;
import minerful.concept.constraint.Constraint;
import minerful.concept.constraint.ConstraintFamily.ConstraintImplicationVerse;
import minerful.concept.constraint.ConstraintFamily.RelationConstraintSubFamily;

@XmlRootElement
public class RespondedExistence extends RelationConstraint {
    public static final String DISTANCE_PRINT_TEMPLATE = " <%+d \u00F7 %+d> ";

    @XmlTransient
    public Double expectedDistance;
    @XmlTransient
    public Double confidenceIntervalMargin;
    
    @Override
	public String getRegularExpressionTemplate() {
		return "[^%1$s]*(([%1$s].*[%2$s].*)|([%2$s].*[%1$s].*))*[^%1$s]*";
    }
    
    @Override
    public String getLTLpfExpressionTemplate() {
    	return "G(%1$s -> (X(F(%2$s)) | Y(O(%2$s)))"; // G(a -> (X(F(b)) | Y(O(b)))
    }

	@Override
	public String getNegativeRegularExpressionTemplate() {
		return "[^%1$s%2$s]*(([%1$s][^%2$s]*)|([%2$s][^%1$s]*))?";
	}

	@Override
	public String getNegativeLTLpfExpressionTemplate() {
		return "G(%1$s -> !(X(F(%2$s)) | Y(O(%2$s))))"; // G(a -> !(X(F(b)) | Y(O(b))))
	}

	protected RespondedExistence() {
    	super();
    }

    public RespondedExistence(TaskChar param1, TaskChar param2) {
        super(param1, param2);
    }
    public RespondedExistence(TaskChar param1, TaskChar param2, double support) {
        super(param1, param2, support);
    }
    public RespondedExistence(TaskCharSet param1, TaskCharSet param2,
			double support) {
		super(param1, param2, support);
	}
	public RespondedExistence(TaskCharSet param1, TaskCharSet param2) {
		super(param1, param2);
	}
    
    @Override
    public int getHierarchyLevel() {
        return super.getHierarchyLevel()+1;
    }
    
    public boolean isExpectedDistanceConfidenceIntervalProvided() {
    	return expectedDistance != null && confidenceIntervalMargin != null;
    }
    
    @Override
    public ConstraintImplicationVerse getImplicationVerse() {
    	return ConstraintImplicationVerse.FORWARD;
    }
    
    @Override
	public RelationConstraintSubFamily getSubFamily() {
		return RelationConstraintSubFamily.SINGLE_ACTIVATION;
	}

	@Override
    public String toString() {
    	if (isExpectedDistanceConfidenceIntervalProvided()) {
    		return super.toString().replace(", ", printDistances());
    	}
    	return super.toString();
    }
    
    protected String printDistances() {
    	return String.format(RespondedExistence.DISTANCE_PRINT_TEMPLATE,
    			getMinimumExpectedDistance(),
    			getMaximumExpectedDistance());
    }
    
    public Integer getMinimumExpectedDistance() {
    	if (this.isExpectedDistanceConfidenceIntervalProvided())
    		return (int)StrictMath.round(expectedDistance - confidenceIntervalMargin);
    	return null;
    }
    
    public Integer getMaximumExpectedDistance() {
    	if (this.isExpectedDistanceConfidenceIntervalProvided())
    		return (int)StrictMath.round(expectedDistance + confidenceIntervalMargin);
    	return null;
    }

	@Override
	public Constraint suggestConstraintWhichThisShouldBeBasedUpon() {
		return null;
	}

	@Override
	public Constraint copy(TaskChar... taskChars) {
		super.checkParams(taskChars);
		return new RespondedExistence(taskChars[0], taskChars[1]);
	}

	@Override
	public Constraint copy(TaskCharSet... taskCharSets) {
		super.checkParams(taskCharSets);
		return new RespondedExistence(taskCharSets[0], taskCharSets[1]);
	}
}