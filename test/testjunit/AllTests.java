package testjunit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AgileTestJunit.class, AlertJunit.class, AnnaUnitJunit.class,
		AssignmentAgileJunit.class, AssignmentDragandDropJunit.class,
		AssignmentGoogleToolTipJunit.class, AtaCalcTestJunit.class,
		GoogleMapsTestJunit.class, GoogleTestJunit.class,
		JqueryTestJunit.class, ManipalJnuit.class, VodafoneTestJunit.class,
		WikipediaKeyboardJunit.class, WikipidiaTestJnuit.class })
public class AllTests {

}
