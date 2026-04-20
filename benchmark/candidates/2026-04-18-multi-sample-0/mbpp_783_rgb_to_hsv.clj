(defn rgb_to_hsv
  "	Write a function to convert rgb color to hsv color. https://www.geeksforgeeks.org/program-change-rgb-color-model-hsv-color-model/"
  [r g b]
  (let [r' (/ (double (or r 0)) 255.0)
        g' (/ (double (or g 0)) 255.0)
        b' (/ (double (or b 0)) 255.0)
        cmax (max r' g' b')
        cmin (min r' g' b')
        delta (- cmax cmin)
        h (cond
            (zero? delta) 0.0
            (= cmax r') (mod (* 60.0 (/ (- g' b') delta)) 360.0)
            (= cmax g') (mod (+ 120.0 (* 60.0 (/ (- b' r') delta))) 360.0)
            :else (mod (+ 240.0 (* 60.0 (/ (- r' g') delta))) 360.0))
        s (if (zero? cmax) 0.0 (* 100.0 (/ delta cmax)))
        v (* 100.0 cmax)]
    [h s v]))