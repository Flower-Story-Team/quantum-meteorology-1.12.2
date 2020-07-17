package com.konpi.flower.common.capabilities.energy;

import java.math.BigInteger;

public interface IEnergy {
    void setPower(BigInteger power);

    BigInteger getPower();
}
