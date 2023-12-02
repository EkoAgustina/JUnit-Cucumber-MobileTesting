package hooks;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;


import java.util.ArrayList;
import java.util.List;



public class ITestStepStarted implements ConcurrentEventListener {
    
    public EventHandler<TestStepStarted> handler = this::logTestStepName;
    public static List<String> getAllStep =new ArrayList<String>();
    public static String currentStep;  

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepStarted.class, handler);
    }

    private void logTestStepName(TestStepStarted testStepStarted) {
        if(testStepStarted.getTestStep() != null && testStepStarted.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) testStepStarted.getTestStep();
            String getTestStep = testStep.getStep().getKeyword() + testStep.getStep().getText();
            getAllStep.add("✓ "+getTestStep);
            currentStep = getTestStep;
            
        }
        
    }
}