package testproject.testsuite.section_1;

import org.jbehave.core.annotations.*;

public class Section_1{

@Given("Preconditions common")
public void preconditions_common() {
}

@When("Step 1 param $param and par <par>")
public void step_1_param_and_par(String param, String par) {
}

@Then("Expected 1 expected $expected and exp <exp>")
public void expected_1_expected_and_exp(String expected, String exp) {
}

@Then("Expected Common")
public void expected_common() {
}

}