package frc.robot.Subsystems.Base.Templates;

import frc.robot.Subsystems.Base.Templates.SubsystemHandle;

public abstract class PivotSubsystemBase<T extends Enum<T>> extends SubsystemHandle<T> {
    public PivotSubsystemBase(T state) {
        super(state);
    }
}
