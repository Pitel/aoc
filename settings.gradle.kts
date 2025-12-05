rootProject.name = "Advent of Code"
include(List(3) { "day" + "${it.inc()}".padStart(2, '0') })
