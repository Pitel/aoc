package aoc

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

object Config : AbstractProjectConfig() {
    override val parallelism = Runtime.getRuntime().availableProcessors()
    override val isolationMode = IsolationMode.InstancePerTest
//    override val timeout = 1.seconds
}
