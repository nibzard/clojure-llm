(defn square_area-or-perimeter
  "Return a map describing a square from its side length.

  If the side length is a positive number, return both:
  {:area <side^2> :perimeter <4*side>}

  If the input is nil or not a number, return nil.

  Examples:
  (square-area-or-perimeter 3) => {:area 9, :perimeter 12}
  (square-area-or-perimeter 2.5) => {:area 6.25, :perimeter 10.0}
  (square-area-or-perimeter nil) => nil"
  [side])