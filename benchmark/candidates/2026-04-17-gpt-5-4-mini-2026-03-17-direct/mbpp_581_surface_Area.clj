(defn surface_Area
  "	Write a cljthon function to find the surface area of a square cljramid with a given base edge and height."
  [b s]
  (let [b (double b)
        s (double s)
        slant-height (Math/sqrt (+ (* s s) (* (/ b 2.0) (/ b 2.0))))]
    (+ (* b b) (* 2.0 b slant-height))))