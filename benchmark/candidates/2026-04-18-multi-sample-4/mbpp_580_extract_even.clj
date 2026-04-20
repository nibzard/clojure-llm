(defn extract_even
  "	Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (letfn [(even-structure [x]
            (cond
              (vector? x)
              (let [res (->> x
                             (map even-structure)
                             (remove nil?)
                             vec)]
                (when (seq res) res))

              (sequential? x)
              (let [res (->> x
                             (map even-structure)
                             (remove nil?)
                             vec)]
                (when (seq res) res))

              (and (number? x) (even? x)) x
              :else nil))]
    (or (even-structure test_tuple) [])))