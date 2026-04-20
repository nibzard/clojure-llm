(defn rgb_to_hsv
  "	Write a function to convert rgb color to hsv color. https://www.geeksforgeeks.org/program-change-rgb-color-model-hsv-color-model/"
  [r g b]
  (let [r (double (or r 0))
        g (double (or g 0))
        b (double (or b 0))
        mx (max r g b)
        mn (min r g b)
        diff (- mx mn)
        h (cond
            (zero? diff) 0.0
            (= mx r) (mod (* 60.0 (/ (- g b) diff)) 360.0)
            (= mx g) (mod (+ 120.0 (* 60.0 (/ (- b r) diff))) 360.0)
            :else    (mod (+ 240.0 (* 60.0 (/ (- r g) diff))) 360.0))
        s (if (zero? mx) 0.0 (* 100.0 (/ diff mx)))
        v (* 100.0 (/ mx 255.0))]
    [h s v]))