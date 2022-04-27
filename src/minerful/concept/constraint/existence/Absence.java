package minerful.concept.constraint.existence;

import javax.xml.bind.annotation.XmlRootElement;

import minerful.concept.TaskChar;
import minerful.concept.TaskCharSet;
import minerful.concept.constraint.Constraint;

@XmlRootElement
public class Absence extends AtMost1 {
	@Override
	public String getRegularExpressionTemplate() {
		return "[^%1$s]*";
	}
    
    @Override
    public String getLTLpfExpressionTemplate() {
    	return "G(!%1$s)"; // G(!a)
    }

	@Override
	public String getNegativeRegularExpressionTemplate() {
		return "[^%1$s]*([%1$s][^%1$s]*){1,}[^%1$s]*";
	}

	@Override
	public String getNegativeLTLpfExpressionTemplate() {
		return "F(%1$s)"; // F(a)
	}
    
	protected Absence() {
    	super();
    }

	public Absence(TaskChar param1, double support) {
		super(param1, support);
	}
	public Absence(TaskChar param1) {
		super(param1);
	}
	public Absence(TaskCharSet param1, double support) {
		super(param1, support);
	}
	public Absence(TaskCharSet param1) {
		super(param1);
	}

	@Override
	public Constraint suggestConstraintWhichThisShouldBeBasedUpon() {
		return null;
	}

	@Override
	public Constraint copy(TaskChar... taskChars) {
		super.checkParams(taskChars);
		return new Absence(taskChars[0]);
	}

	@Override
	public Constraint copy(TaskCharSet... taskCharSets) {
		super.checkParams(taskCharSets);
		return new Absence(taskCharSets[0]);
	}
}