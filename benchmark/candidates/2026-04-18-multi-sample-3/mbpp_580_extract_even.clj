(defn extract_even
  "	Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (letfn [(evenify [x]
            (cond
              (vector? x)
              (let [res (->> x
                             (map evenify)
                             (remove nil?)
                             vec)]
                (when (seq res) res))

              (number? x)
              (when (even? x) x)

              :else nil))]
    (or (evenify test_tuple) [])))