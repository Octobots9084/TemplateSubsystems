package frc.robot.Subsystems.Base.Templates;

import frc.robot.Subsystems.Base.Templates.SubsystemHandle;

public abstract class PivotWithFollowerSubsystemBase<T extends Enum<T>> extends SubsystemHandle<T> {
    public PivotWithFollowerSubsystemBase(T state) {
        super(state);
    }
}
