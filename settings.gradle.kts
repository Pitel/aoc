rootProject.name = "Advent of Code"
include(List(2) { "day" + "${it.inc()}".padStart(2, '0') })
