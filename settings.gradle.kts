rootProject.name = "Advent of Code"
include(List(4) { "day" + "${it.inc()}".padStart(2, '0') })
