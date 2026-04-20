(defn problem-function-composition
  [& fs]
  (fn [& args]
    (let [rfs (reverse fs)]
      (reduce (fn [acc f] (f acc))
              (apply (first rfs) args)
              (rest rfs)))))