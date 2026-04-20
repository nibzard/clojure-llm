(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (letfn [(to-polar [z]
            (let [[x y] (cond
                          (vector? z) z
                          (sequential? z) [(first z) (second z)]
                          :else [0 0])
                  x (double (or x 0))
                  y (double (or y 0))]
              {:r (Math/sqrt (+ (* x x) (* y y)))
               :theta (Math/atan2 y x)}))]
    (cond
      (nil? numbers) nil
      (and (sequential? numbers)
           (or (empty? numbers)
               (sequential? (first numbers))
               (vector? (first numbers))))
      (mapv to-polar numbers)
      :else
      (to-polar numbers))))