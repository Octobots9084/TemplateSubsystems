package frc.robot.Subsystems.Base.Templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Base.Templates.SubsystemHandle;

public abstract class OtherSubsystemBase<T extends Enum<T>> extends SubsystemHandle<T> {
    public Map<String, MotorRollerBase> rollers = new HashMap<>();
    private String internalName;

    /**After initalizing this, add rollers and their followers with addRollers, then run OtherSubsystemBase#completeInit().
     * If you add a roller afterwards (not recommended) run either its reapplyConfigurator() or the method described above.
     * 
    */
    public OtherSubsystemBase(T def, String internalName) {
        super(def);
    }
    
    /**Add a roller with the same name as another only if you want to throw the old one in the trash.*/
    public void addRollers(MotorRollerBase... add) {
        for (MotorRollerBase individualMotor : add) {
            rollers.put(individualMotor.name, individualMotor);
        }
    }
    public MotorRollerBase getRoller(String name) {
        AtomicReference<MotorRollerBase> foundRoller = new AtomicReference<>(null);
        rollers.forEach((searchedName, roller) -> {
            if (searchedName.equals(name)) foundRoller.set(roller);
        });
        if (foundRoller.get() == null) throw new RuntimeException("No roller by the name of " + name + "!");
        return foundRoller.get();
    }

    public void completeInit() {
        rollers.forEach((name, roller) -> {
            roller.reapplyConfigurator();
        });
    }
   
    @Overridable
    @Override
    public void logging() {
        log("CurrentState", currentState);
        log("WantedState", wantedState);

        rollers.forEach((name, roller) -> {
            String id = internalName + "/" + name + "/";

            log(id + "RPS", roller.getVelocity().getValueAsDouble());
            log(id + "Temp", roller.getDeviceTemp().getValueAsDouble());
            log(id + "Current", roller.getStatorCurrent().getValueAsDouble());
            log(id + "Voltage", roller.getMotorVoltage().getValueAsDouble());

        });
    }
    public final void log(String name, Object data) {
        Logger.recordOutput(internalName + "/" + name, data.toString());
    }

}
