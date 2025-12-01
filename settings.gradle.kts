rootProject.name = "Advent of Code"
include(List(1) { "day" + "${it.inc()}".padStart(2, '0') })
