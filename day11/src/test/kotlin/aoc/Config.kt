package aoc

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

object Config : AbstractProjectConfig() {
    override val parallelism = 4
    override val isolationMode = IsolationMode.InstancePerTest
}
