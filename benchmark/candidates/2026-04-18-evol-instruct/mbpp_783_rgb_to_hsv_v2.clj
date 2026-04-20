(defn rgb->hsl
  "Convert an RGB color to HSL.

  Accepts a single map {:r r :g g :b b} where each channel is an integer in [0,255].
  Returns a map {:h h :s s :l l} where:
  - h is hue in degrees [0,360)
  - s and l are percentages in [0,100]

  Examples:
  (rgb->hsl {:r 255 :g 0 :b 0})   ;=> {:h 0, :s 100, :l 50}
  (rgb->hsl {:r 0 :g 0 :b 0})     ;=> {:h 0, :s 0, :l 0}
  (rgb->hsl {:r 255 :g 255 :b 255}) ;=> {:h 0, :s 0, :l 100}"
  [color]
  (let [r (/ (double (:r color)) 255.0)
        g (/ (double (:g color)) 255.0)
        b (/ (double (:b color)) 255.0)
        maxc (max r g b)
        minc (min r g b)
        l (/ (+ maxc minc) 2.0)
        d (- maxc minc)
        s (if (zero? d)
            0.0
            (/ d (- 1.0 (Math/abs (- (* 2.0 l) 1.0)))))
        h (cond
            (zero? d) 0.0
            (= maxc r) (mod (* 60.0 (/ (- g b) d)) 360.0)
            (= maxc g) (+ 120.0 (* 60.0 (/ (- b r) d)))
            :else (+ 240.0 (* 60.0 (/ (- r g) d))))]
    {:h (int (Math/round h))
     :s (int (Math/round (* 100.0 s)))
     :l (int (Math/round (* 100.0 l)))}))