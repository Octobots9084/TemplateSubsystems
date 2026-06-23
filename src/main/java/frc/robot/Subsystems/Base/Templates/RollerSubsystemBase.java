package frc.robot.Subsystems.Base.Templates;

import frc.robot.Subsystems.Base.Templates.SubsystemHandle;

public abstract class RollerSubsystemBase<T extends Enum<T>> extends SubsystemHandle<T> {
    public RollerSubsystemBase(T state) {
        super(state);
    }
}
