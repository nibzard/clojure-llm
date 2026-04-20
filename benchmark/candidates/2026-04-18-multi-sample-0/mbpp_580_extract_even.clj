(defn extract_even
  "	Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (letfn [(process [x]
            (cond
              (vector? x)
              (->> x
                   (map process)
                   (remove nil?)
                   vec)

              (number? x)
              (when (even? x) x)

              :else nil))]
    (process test_tuple)))