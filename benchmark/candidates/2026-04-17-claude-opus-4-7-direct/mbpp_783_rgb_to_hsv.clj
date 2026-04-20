(defn rgb_to_hsv
  "Write a function to convert rgb color to hsv color. https://www.geeksforgeeks.org/program-change-rgb-color-model-hsv-color-model/"
  [r g b]
  (let [r-prime (/ r 255.0)
        g-prime (/ g 255.0)
        b-prime (/ b 255.0)
        c-max (max r-prime g-prime b-prime)
        c-min (min r-prime g-prime b-prime)
        delta (- c-max c-min)]
    (if (zero? c-max)
      [0.0 0.0 0.0]
      (let [;; Calculate Hue
            h (cond
                (zero? delta) 0.0
                (= c-max r-prime) (mod (/ (* 60.0 (- (- g-prime b-prime) delta)) delta) 360.0)
                (= c-max g-prime) (/ (* 60.0 (+ (- b-prime r-prime) delta)) delta)
                :else (/ (* 60.0 (+ (- r-prime g-prime) delta)) delta))
            ;; Calculate Saturation
            s (/ delta c-max)
            ;; Calculate Value
            v c-max]
        [(if (neg? h) (+ h 360.0) h) s v]))))