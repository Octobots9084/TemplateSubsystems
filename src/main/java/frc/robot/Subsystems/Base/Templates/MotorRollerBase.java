package frc.robot.Subsystems.Base.Templates;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

public class MotorRollerBase extends TalonFX {

    public VelocityVoltage rpmController;
    public TalonFXConfiguration config;
    public final String name;
    public TalonFX leader;
    public Follower followInstance = null;
        

    public MotorRollerBase(int id, String name, double gearRatio, TalonFX leader, MotorAlignmentValue alignment) {
        super(id);
        this.rpmController = new VelocityVoltage(0);
        this.name = name;
        this.leader = leader;

        if (leader != null) {
            this.followInstance = new Follower(id, alignment);
            this.setControl(followInstance);
        }

        config = new TalonFXConfiguration().withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(gearRatio)); 
        this.getConfigurator().apply(config);       
    }

    public MotorRollerBase(int id, String name, double gearRatio) {
        this(id, name, gearRatio, null, null);
    }

    public void reapplyConfigurator() {
        this.getConfigurator().apply(config);
    }

    public void setRPM(double d) {
        if (followInstance != null) throw new RuntimeException("Don't set RPMs on a follower!");
        this.setControl(rpmController.withVelocity(d));
    }
}
