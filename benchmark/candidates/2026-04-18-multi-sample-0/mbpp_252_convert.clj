(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (letfn [(to-polar [z]
            (let [[x y] (cond
                          (and (sequential? z) (= 2 (count z))) z
                          (map? z) [(or (:real z) (:re z) 0)
                                    (or (:imag z) (:im z) 0)]
                          :else [0 0])
                  x (double x)
                  y (double y)]
              {:magnitude (Math/sqrt (+ (* x x) (* y y)))
               :angle (Math/atan2 y x)}))]
    (cond
      (nil? numbers) nil
      (and (sequential? numbers)
           (= 2 (count numbers))
           (every? number? numbers))
      (to-polar numbers)
      (sequential? numbers)
      (mapv to-polar numbers)
      :else
      (to-polar numbers))))