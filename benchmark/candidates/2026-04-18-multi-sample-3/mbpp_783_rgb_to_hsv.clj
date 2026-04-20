(defn rgb_to_hsv
  "	Write a function to convert rgb color to hsv color. https://www.geeksforgeeks.org/program-change-rgb-color-model-hsv-color-model/"
  [r g b]
  (let [rf (/ (double (or r 0)) 255.0)
        gf (/ (double (or g 0)) 255.0)
        bf (/ (double (or b 0)) 255.0)
        cmax (max rf gf bf)
        cmin (min rf gf bf)
        delta (- cmax cmin)
        h (cond
            (zero? delta) 0.0
            (= cmax rf) (mod (* 60.0 (/ (- gf bf) delta)) 360.0)
            (= cmax gf) (+ 120.0 (* 60.0 (/ (- bf rf) delta)))
            :else       (+ 240.0 (* 60.0 (/ (- rf gf) delta))))
        s (if (zero? cmax) 0.0 (* 100.0 (/ delta cmax)))
        v (* 100.0 cmax)]
    [h s v]))