(defn rgb_to_hsv
  "Write a function to convert rgb color to hsv color."
  [r g b]
  (let [r' (/ r 255.0)
        g' (/ g 255.0)
        b' (/ b 255.0)
        cmax (max r' g' b')
        cmin (min r' g' b')
        diff (- cmax cmin)
        h (cond
            (zero? diff) 0.0
            (= cmax r') (mod (* 60.0 (/ (- g' b') diff)) 360.0)
            (= cmax g') (mod (+ (* 60.0 (/ (- b' r') diff)) 120.0) 360.0)
            (= cmax b') (mod (+ (* 60.0 (/ (- r' g') diff)) 240.0) 360.0))
        s (if (zero? cmax)
            0.0
            (* (/ diff cmax) 100.0))
        v (* cmax 100.0)]
    [h s v]))