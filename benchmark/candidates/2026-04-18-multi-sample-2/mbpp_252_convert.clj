(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (let [to-polar (fn [n]
                   (let [re (double (or (real-part n) 0.0))
                         im (double (or (imag-part n) 0.0))]
                     [(Math/sqrt (+ (* re re) (* im im)))
                      (Math/atan2 im re)]))]
    (cond
      (nil? numbers) nil
      (sequential? numbers) (mapv to-polar numbers)
      :else (to-polar numbers))))