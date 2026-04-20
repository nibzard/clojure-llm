(defn rgb_to_hsv
  "Write a function to convert rgb color to hsv color."
  [r g b]
  (let [r (/ (double r) 255.0)
        g (/ (double g) 255.0)
        b (/ (double b) 255.0)
        max-c (max r g b)
        min-c (min r g b)
        delta (- max-c min-c)
        h (cond
            (zero? delta) 0.0
            (= max-c r) (mod (* 60.0 (/ (- g b) delta)) 360.0)
            (= max-c g) (+ 120.0 (* 60.0 (/ (- b r) delta)))
            :else (+ 240.0 (* 60.0 (/ (- r g) delta))))
        s (if (zero? max-c) 0.0 (/ delta max-c))
        v max-c]
    [h (* 100.0 s) (* 100.0 v)]))