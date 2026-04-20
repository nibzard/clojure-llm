(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (letfn [(to-polar [z]
            (let [re (cond
                       (map? z) (or (:real z) (:re z) 0.0)
                       (vector? z) (double (or (nth z 0 nil) 0.0))
                       (sequential? z) (double (or (first z) 0.0))
                       :else 0.0)
                  im (cond
                       (map? z) (or (:imag z) (:im z) 0.0)
                       (vector? z) (double (or (nth z 1 nil) 0.0))
                       (sequential? z) (double (or (second z) 0.0))
                       :else 0.0)
                  r (Math/sqrt (+ (* re re) (* im im)))
                  theta (Math/atan2 im re)]
              [r theta]))]
    (cond
      (nil? numbers) nil
      (and (sequential? numbers)
           (or (empty? numbers)
               (map? (first numbers))
               (sequential? (first numbers))))
      (mapv to-polar numbers)
      :else
      (to-polar numbers))))