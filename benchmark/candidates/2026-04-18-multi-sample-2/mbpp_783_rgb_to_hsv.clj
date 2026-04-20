(defn rgb_to_hsv
  "	Write a function to convert rgb color to hsv color. https://www.geeksforgeeks.org/program-change-rgb-color-model-hsv-color-model/"
  [r g b]
  (let [r (double (or r 0))
        g (double (or g 0))
        b (double (or b 0))
        mx (max r g b)
        mn (min r g b)
        delta (- mx mn)
        h (cond
            (zero? delta) 0.0
            (= mx r) (mod (* 60.0 (/ (- g b) delta)) 360.0)
            (= mx g) (+ 120.0 (* 60.0 (/ (- b r) delta)))
            :else    (+ 240.0 (* 60.0 (/ (- r g) delta))))
        s (if (zero? mx) 0.0 (* 100.0 (/ delta mx)))
        v (* 100.0 (/ mx 255.0))]
    [h s v]))