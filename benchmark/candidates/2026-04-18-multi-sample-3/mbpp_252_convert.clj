(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (letfn [(to-polar [z]
            (let [[x y] (cond
                          (nil? z) [0.0 0.0]
                          (and (sequential? z) (>= (count z) 2)) [(double (nth z 0)) (double (nth z 1))]
                          (map? z) [(double (or (:real z) (:re z) 0))
                                    (double (or (:imag z) (:im z) 0))]
                          :else [0.0 0.0])
                  r (Math/sqrt (+ (* x x) (* y y)))
                  theta (Math/atan2 y x)]
              [r theta]))]
    (cond
      (nil? numbers) []
      (sequential? numbers) (mapv to-polar numbers)
      :else [(to-polar numbers)])))