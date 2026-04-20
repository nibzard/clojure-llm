(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (letfn [(to-polar [z]
            (let [[re im] (cond
                            (nil? z) [0.0 0.0]
                            (and (sequential? z) (>= (count z) 2)) [(double (nth z 0)) (double (nth z 1))]
                            (map? z) [(double (or (:real z) (:re z) 0.0))
                                      (double (or (:imag z) (:im z) 0.0))]
                            :else [0.0 0.0])
                  r (Math/sqrt (+ (* re re) (* im im)))
                  theta (Math/atan2 im re)]
              [r theta]))]
    (cond
      (nil? numbers) nil
      (sequential? numbers) (mapv to-polar numbers)
      :else (to-polar numbers))))