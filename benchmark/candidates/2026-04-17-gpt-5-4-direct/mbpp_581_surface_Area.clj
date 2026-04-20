(defn surface_Area
  "Write a cljthon function to find the surface area of a square cljramid with a given base edge and height."
  [b s]
  (let [b (double (or b 0))
        s (double (or s 0))]
    (+ (* b b) (* 2 b s))))