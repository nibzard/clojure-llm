(defn surface-Area
  "Calculate the surface area of a square pyramid with a given base edge and height."
  [b s]
  (let [base-area (* b b)
        slant-height (Math/sqrt (+ (* s s) (* (/ b 2) (/ b 2))))
        face-area (* 0.5 b slant-height)]
    (+ base-area (* 4 face-area))))