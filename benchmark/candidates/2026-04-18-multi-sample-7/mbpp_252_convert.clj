(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (letfn [(to-polar [z]
            (let [re (double (or (:real z) (:re z) (first z) 0.0))
                  im (double (or (:imag z) (:im z) (:imaginary z) (second z) 0.0))]
              {:r     (Math/sqrt (+ (* re re) (* im im)))
               :theta (Math/atan2 im re)}))]
    (cond
      (nil? numbers) nil
      (and (sequential? numbers)
           (not (map? numbers))
           (or (empty? numbers)
               (map? (first numbers))
               (sequential? (first numbers))))
      (mapv to-polar numbers)
      :else
      (to-polar numbers))))