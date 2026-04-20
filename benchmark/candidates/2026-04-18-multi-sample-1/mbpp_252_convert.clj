(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (let [to-polar (fn [z]
                   (let [[x y] (cond
                                 (vector? z) z
                                 (sequential? z) [(first z) (second z)]
                                 :else [nil nil])
                         x (double (or x 0))
                         y (double (or y 0))]
                     [(Math/sqrt (+ (* x x) (* y y)))
                      (Math/atan2 y x)]))]
    (cond
      (nil? numbers) nil
      (and (sequential? numbers)
           (or (empty? numbers)
               (and (sequential? (first numbers))
                    (not (number? (first numbers))))))
      (mapv to-polar numbers)
      :else
      (to-polar numbers))))