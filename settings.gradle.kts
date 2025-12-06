rootProject.name = "Advent of Code"
include(List(6) { "day" + "${it.inc()}".padStart(2, '0') })
